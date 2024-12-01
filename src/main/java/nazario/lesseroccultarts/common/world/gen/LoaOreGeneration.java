package nazario.lesseroccultarts.common.world.gen;

import nazario.lesseroccultarts.common.world.feature.LoaPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class LoaOreGeneration {
    public static void register() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, LoaPlacedFeatures.SILVER_ORE_PLACED.getKey().get());
    }
}
