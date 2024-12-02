package nazario.lesseroccultarts.mixin;

import nazario.lesseroccultarts.library.AdvancedItemMethods;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDeathMixin {
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void loa$onDeath(DamageSource damageSource, CallbackInfo ci) {

        if(damageSource.getAttacker() != null && damageSource.getAttacker() instanceof PlayerEntity playerEntity) {
            playerEntity.getHandItems().forEach(stack -> {
                if(stack.getItem() instanceof AdvancedItemMethods advancedItemMethods) advancedItemMethods.killedWithItem(damageSource, stack, (LivingEntity)(Object)this, ci);
            });
        }
    }
}
