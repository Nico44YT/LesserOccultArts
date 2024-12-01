package nazario.lesseroccultarts.common.entity.demonentity;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DemonEntityModel extends AnimatedGeoModel<DemonEntity> {
    private static final Identifier MODEL = LesserOccultArtsMain.id("geo/entity/demon.geo.json");
    private static final Identifier ANIMATION = LesserOccultArtsMain.id("animations/entity/demon.animation.json");

    @Override
    public Identifier getModelResource(DemonEntity demonEntity) {
        return MODEL;
    }

    @Override
    public Identifier getTextureResource(DemonEntity demonEntity) {
        return LesserOccultArtsMain.id("textures/entity/demon.png");
    }

    @Override
    public Identifier getAnimationResource(DemonEntity demonEntity) {
        return ANIMATION;
    }
}
