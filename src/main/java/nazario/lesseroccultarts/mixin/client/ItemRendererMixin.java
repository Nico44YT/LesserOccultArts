package nazario.lesseroccultarts.mixin.client;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.registry.LoaItems;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    private static final ModelIdentifier DAMNED_GREATSWORD_HANDHELD;
    private static final ModelIdentifier DEEPSLATE_GREATSWORD_HANDHELD;

    @Shadow @Final private ItemModels models;

    @Shadow public abstract BakedModel getModel(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed);

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"), argsOnly = true)
    public BakedModel onRenderItem(BakedModel value, ItemStack stack, ModelTransformation.Mode mode)
    {
        if(stack.isOf(LoaItems.DAMNED_GREATSWORD) && !mode.equals(ModelTransformation.Mode.GUI))
            return models.getModelManager().getModel(DAMNED_GREATSWORD_HANDHELD);
        if(stack.isOf(LoaItems.DEEEPSLATE_GREATSWORD) && !mode.equals(ModelTransformation.Mode.GUI))
            return models.getModelManager().getModel(DEEPSLATE_GREATSWORD_HANDHELD);
        return value;
    }

    static {
        DAMNED_GREATSWORD_HANDHELD = new ModelIdentifier(LesserOccultArtsMain.id("damned_greatsword_handheld"), "inventory");
        DEEPSLATE_GREATSWORD_HANDHELD = new ModelIdentifier(LesserOccultArtsMain.id("deepslate_greatsword_handheld"), "inventory");
    }
}
