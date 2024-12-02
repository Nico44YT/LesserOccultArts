package nazario.lesseroccultarts.library;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface AdvancedItemMethods {
    default void killedWithItem(DamageSource damageSource, ItemStack stack, LivingEntity target, CallbackInfo ci) {};
}
