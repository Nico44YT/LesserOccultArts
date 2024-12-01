package nazario.lesseroccultarts.common.block;

import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;

import java.util.Map;
import java.util.function.Predicate;

public class MaliceInkCauldronBlock extends LeveledCauldronBlock {
    public static final Map<Item, CauldronBehavior> MALICE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

    public MaliceInkCauldronBlock(Settings settings, Predicate<Biome.Precipitation> precipitationPredicate, Map<Item, CauldronBehavior> behaviorMap) {
        super(settings, precipitationPredicate, behaviorMap);
    }
}
