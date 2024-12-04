package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.registry.LoaBlocks;
import nazario.lesseroccultarts.registry.LoaItems;
import nazario.liby.interfaces.LibyItemRenderOverrider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class DeepslateGreatswordItem extends SwordItem implements LibyItemRenderOverrider {
    public DeepslateGreatswordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (!world.isClient) {
            BlockState clickedState = world.getBlockState(context.getBlockPos());
            if (clickedState.isOf(LoaBlocks.MALICE_INK_CAULDRON)) {
                BlockState inkCauldron = Blocks.CAULDRON.getDefaultState();
                world.setBlockState(context.getBlockPos(), inkCauldron);

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_MUD_STEP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                assert player != null;

                player.setStackInHand(Hand.MAIN_HAND, new ItemStack(LoaItems.DAMNED_GREATSWORD));
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public void animateArmSwing(Entity entity, BipedEntityModel<? extends LivingEntity> model, float animationProgress, CallbackInfo ci) {
        if(animationProgress < 0f) return;
        Arm arm = ((LivingEntity) entity).getMainArm().getOpposite();
        ModelPart modelPart = model.getArm(arm);
        double f = 1.0 - Math.pow((double)(1.0F - model.handSwingProgress), 3.0);
        float h = MathHelper.sin(model.handSwingProgress * 3.1415927F) * -(model.head.pitch - 0.7F) * 0.75F;
        modelPart.pitch -= MathHelper.sin((float)(f * Math.PI)) * 1.2F + h;
        modelPart.yaw += model.body.yaw * 2.0F;
        modelPart.roll += MathHelper.sin(model.handSwingProgress * 3.1415927F) * -0.4F;
    }


    public static class DeepslateGreatswordMaterial implements ToolMaterial {
        public static final DeepslateGreatswordMaterial INSTANCE = new DeepslateGreatswordMaterial();

        @Override
        public int getDurability() {
            return 1840;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 8;
        }

        @Override
        public int getMiningLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    }
}
