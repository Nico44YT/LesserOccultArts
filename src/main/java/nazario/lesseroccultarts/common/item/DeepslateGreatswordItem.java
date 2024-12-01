package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.registry.BlockRegistry;
import nazario.lesseroccultarts.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeepslateGreatswordItem extends SwordItem {
    public DeepslateGreatswordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (!world.isClient) {
            BlockState clickedState = world.getBlockState(context.getBlockPos());
            if (clickedState.isOf(BlockRegistry.MALICE_INK_CAULDRON)) {
                BlockState inkCauldron = Blocks.CAULDRON.getDefaultState();
                world.setBlockState(context.getBlockPos(), inkCauldron);

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_MUD_STEP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                assert player != null;

                player.setStackInHand(Hand.MAIN_HAND, new ItemStack(ItemRegistry.DAMNED_GREATSWORD));
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
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
