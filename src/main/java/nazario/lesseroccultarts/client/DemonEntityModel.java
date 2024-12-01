package nazario.lesseroccultarts.client;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.entity.DemonEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DemonEntityModel extends AnimatedGeoModel<DemonEntity> {
    private static final Identifier MODEL = LesserOccultArtsMain.id("geo/entity/2demon.geo.json");
    private static final Identifier ANIMATION = LesserOccultArtsMain.id("animations/entity/2demon.animation.json");

    @Override
    public Identifier getModelResource(DemonEntity demonEntity) {
        return MODEL;
    }

    @Override
    public Identifier getTextureResource(DemonEntity demonEntity) {
        return LesserOccultArtsMain.id("textures/entity/2demon.png");
    }

    @Override
    public Identifier getAnimationResource(DemonEntity demonEntity) {
        return ANIMATION;
    }
}
