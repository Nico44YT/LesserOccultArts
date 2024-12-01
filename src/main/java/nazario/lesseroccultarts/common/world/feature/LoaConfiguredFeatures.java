package nazario.lesseroccultarts.common.world.feature;

import nazario.lesseroccultarts.registry.LoaBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class LoaConfiguredFeatures {
    public static final List<OreFeatureConfig.Target> OVERWORLD_SILVER_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, LoaBlocks.SILVER_ORE.getDefaultState())
    );

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> SILVER_ORE = ConfiguredFeatures.register("silver_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_SILVER_ORES, 5));

    public static void registerConfiguredFeatures() {

    }
}
