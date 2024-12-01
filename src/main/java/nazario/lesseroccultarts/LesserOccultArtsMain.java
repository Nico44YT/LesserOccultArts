package nazario.lesseroccultarts;

import nazario.lesseroccultarts.common.world.feature.LoaConfiguredFeatures;
import nazario.lesseroccultarts.common.world.gen.LoaOreGeneration;
import nazario.lesseroccultarts.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

public class LesserOccultArtsMain implements ModInitializer {

    public static final String MOD_ID = "lesseroccultarts";

    @Override
    public void onInitialize() {
        LoaConfiguredFeatures.registerConfiguredFeatures();
        LoaOreGeneration.register();

        LoaPackets.registerC2SPackets();
        LoaParticles.register();

        LoaEntities.register();
        LoadEnchantments.register();
        LoaBlocks.register();
        LoaItems.register();
        GeckoLib.initialize();
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
