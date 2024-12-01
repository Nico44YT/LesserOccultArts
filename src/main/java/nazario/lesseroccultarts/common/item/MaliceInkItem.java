package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class MaliceInkItem extends Item {
    public MaliceInkItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (!world.isClient) {
            BlockState clickedState = world.getBlockState(context.getBlockPos());
            if (clickedState.isOf(Blocks.WATER_CAULDRON)) {
                BlockState inkCauldron = BlockRegistry.MALICE_INK_CAULDRON.getDefaultState();
                world.setBlockState(context.getBlockPos(), inkCauldron);

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_MUD_STEP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!player.isCreative()) {
                    player.getMainHandStack().decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }
}
