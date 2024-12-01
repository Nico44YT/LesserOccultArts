package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.common.block.MaliceInkCauldronBlock;
import nazario.lesseroccultarts.registry.LoaBlocks;
import nazario.lesseroccultarts.registry.LoaParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
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
            if(clickedState.isOf(Blocks.WATER_CAULDRON)) {
                BlockState inkCauldron = LoaBlocks.MALICE_INK_CAULDRON.getDefaultState().with(MaliceInkCauldronBlock.LEVEL, clickedState.get(LeveledCauldronBlock.LEVEL));
                world.setBlockState(context.getBlockPos(), inkCauldron);

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_MUD_STEP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                for(int i = 0;i<10;i++) {
                    double dx = world.getRandom().nextBetween(-3, 3)/20f;
                    double dy = world.getRandom().nextBetween(1, 5)/30f;
                    double dz = world.getRandom().nextBetween(-3, 3)/20f;
                    Vec3d pos = new Vec3d(context.getBlockPos().getX(), context.getBlockPos().getY(), context.getBlockPos().getZ());
                    ((ServerWorld)world).spawnParticles(LoaParticles.MALICE_INK, pos.x + 0.5, pos.y + 0.9f, pos.z + 0.5, 1, dx, dy, dz, 0.1f);
                }

                if (!player.isCreative()) {
                    player.getMainHandStack().decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }
}
