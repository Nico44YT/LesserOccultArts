package nazario.lesseroccultarts.common.block;

import nazario.lesseroccultarts.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class TestBlockEntity extends BlockEntity {
    public TestBlockEntity(BlockPos pos, BlockState state) {
        super(BlockRegistry.TEST_BLOCK_ENTITY, pos, state);
    }
}
