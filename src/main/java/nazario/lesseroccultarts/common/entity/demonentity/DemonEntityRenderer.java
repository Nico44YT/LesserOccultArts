package nazario.lesseroccultarts.common.entity.demonentity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DemonEntityRenderer extends GeoEntityRenderer<DemonEntity> {
    public DemonEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DemonEntityModel());
    }


}
