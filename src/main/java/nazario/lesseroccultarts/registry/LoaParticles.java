package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.liby.registry.auto.LibyAutoRegister;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.Registry;

@LibyAutoRegister
public class LoaParticles {
    public static final DefaultParticleType MALICE_INK = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registry.PARTICLE_TYPE, LesserOccultArtsMain.id("malice_ink"), MALICE_INK);
    }
}
