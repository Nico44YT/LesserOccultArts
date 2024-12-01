package nazario.lesseroccultarts.mixin.client;

import nazario.lesseroccultarts.registry.LoaItems;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = {"getArmPose"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private static void amarite$swordPoses(@NotNull AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        ItemStack main = player.getMainHandStack();
        if (main.isOf(LoaItems.DAMNED_GREATSWORD)) {
            if (hand != Hand.MAIN_HAND) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            } else {
                cir.setReturnValue(BipedEntityModel.ArmPose.BLOCK);
            }
        }
        if (main.isOf(LoaItems.DEEEPSLATE_GREATSWORD)) {
            if (hand != Hand.MAIN_HAND) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            } else {
                cir.setReturnValue(BipedEntityModel.ArmPose.BLOCK);
            }
        }
    }
}
