package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.entity.DemonEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    public static final EntityType<DemonEntity> DEMON = Registry.register(Registry.ENTITY_TYPE,
            LesserOccultArtsMain.id("demon"),
            EntityType.Builder.create(DemonEntity::new, SpawnGroup.MISC).setDimensions(0.6F, 1.8F).maxTrackingRange(80).build("demon"));

    public static void register() {
        FabricDefaultAttributeRegistry.register(DEMON, DemonEntity.getDefaultAttributes());
    }

}
