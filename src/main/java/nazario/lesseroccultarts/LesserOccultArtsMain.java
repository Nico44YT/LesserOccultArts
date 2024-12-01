package nazario.lesseroccultarts;

import nazario.lesseroccultarts.common.world.feature.LoaConfiguredFeatures;
import nazario.lesseroccultarts.common.world.gen.LoaOreGeneration;
import nazario.lesseroccultarts.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

public class LesserOccultArtsMain implements ModInitializer {

    public static final String MOD_ID = "lesseroccultarts";

    @Override
    public void onInitialize() {
        LoaConfiguredFeatures.registerConfiguredFeatures();
        LoaOreGeneration.register();

        PacketRegistry.registerC2SPackets();

        EntityRegistry.register();
        EnchantmentRegistry.register();
        BlockRegistry.register();
        ItemRegistry.register();
        GeckoLib.initialize();
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
