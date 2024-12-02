package nazario.lesseroccultarts.mixin.client;

import nazario.lesseroccultarts.library.CustomHandSwingItem;
import nazario.lesseroccultarts.registry.LoaItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BipedEntityModel.class})
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> {

    @Shadow
    @Final
    public ModelPart body;
    @Shadow
    @Final
    public ModelPart head;

    public BipedEntityModelMixin() {
    }

    @Shadow
    protected abstract Arm getPreferredArm(T var1);

    @Shadow
    protected abstract ModelPart getArm(Arm var1);

    @Inject(method = "animateArms", at = @At("TAIL"))
    protected void loa$twoHanding(T entity, float animationProgress, CallbackInfo ci) {
        entity.getHandItems().forEach(stack -> {
            if(stack.getItem() instanceof CustomHandSwingItem customHandSwingItem) customHandSwingItem.swingHand(entity, (BipedEntityModel<? extends LivingEntity>)(Object)this, animationProgress, ci);
        });
    }
}
