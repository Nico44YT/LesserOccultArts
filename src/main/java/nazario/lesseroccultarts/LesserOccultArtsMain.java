package nazario.lesseroccultarts;

import nazario.lesseroccultarts.common.world.feature.LoaConfiguredFeatures;
import nazario.lesseroccultarts.common.world.gen.LoaOreGeneration;
import nazario.lesseroccultarts.registry.*;
import nazario.liby.registry.auto.LibyEntrypoints;
import nazario.liby.registry.auto.LibyRegistryLoader;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

public class LesserOccultArtsMain implements ModInitializer {

    public static final String MOD_ID = "lesseroccultarts";

    @Override
    public void onInitialize() {
        LibyRegistryLoader.load("nazario.lesseroccultarts.registry", LibyEntrypoints.MAIN);

        GeckoLib.initialize();
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
