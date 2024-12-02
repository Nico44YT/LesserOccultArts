package nazario.lesseroccultarts.library;

import nazario.lesseroccultarts.mixin.client.BipedEntityModelMixin;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface CustomHandSwingItem {
    <T extends LivingEntity> void swingHand(T entity, BipedEntityModel<? extends LivingEntity> model, float animationProgress, CallbackInfo ci);
}