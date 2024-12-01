package nazario.lesseroccultarts.mixin;

import nazario.lesseroccultarts.registry.ItemRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(PlayerEntity.class)
public abstract class PlayerDeathMixin {
    private HashMap<Integer, ItemStack> toKeep;

    @Inject(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;vanishCursedItems()V"))
    private void preDropInventory(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) return;

        ItemStack stack = null;
        if (player.getMainHandStack().getItem().equals(ItemRegistry.TOTEM_OF_RETRIEVAL))
            stack = player.getMainHandStack();
        else if (player.getOffHandStack().getItem().equals(ItemRegistry.TOTEM_OF_RETRIEVAL))
            stack = player.getOffHandStack();

        if (stack == null) return;
        if (!stack.getItem().equals(ItemRegistry.TOTEM_OF_RETRIEVAL)) return;

        toKeep = new HashMap<>();
        for (int i = 0; i < player.getInventory().size(); i++) {
            toKeep.put(i, player.getInventory().getStack(i));
            player.getInventory().setStack(i, new ItemStack(Items.AIR));
        }

        if (player instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.USED_TOTEM.trigger(serverPlayerEntity, stack);
            stack.decrement(1);
        }
    }

    @Inject(method = "dropInventory", at = @At(value = "TAIL"))
    private void postDropInventory(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;

        if(player.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) return;

        if(toKeep == null) return;

        for(int i = 0;i<toKeep.keySet().size();i++) {
            int keepIndex = (int)toKeep.keySet().stream().toArray()[i];
            player.getInventory().setStack(keepIndex, toKeep.get(keepIndex));
        }
        toKeep = new HashMap<>();
    }
}

@Mixin(ServerPlayerEntity.class)
abstract class ServerPlayerEntityMixin {
    @Inject(method = "copyFrom", at = @At(value = "TAIL"))
    private void test(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        if(player.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) return;
        player.getInventory().clone(oldPlayer.getInventory());
    }
}

