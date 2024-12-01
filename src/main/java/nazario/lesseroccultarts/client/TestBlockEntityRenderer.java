package nazario.lesseroccultarts.client;

import nazario.lesseroccultarts.common.block.TestBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;

import static net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer.BEAM_TEXTURE;

public class TestBlockEntityRenderer implements BlockEntityRenderer<TestBlockEntity> {

    public TestBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(TestBlockEntity entity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float[] fs = DyeColor.MAGENTA.getColorComponents();
        BeaconBlockEntityRenderer.renderBeam(matrixStack, vertexConsumers, BEAM_TEXTURE, tickDelta, 1, entity.getWorld().getTime(), 0, 15*2, fs, (float)Math.sin(entity.getWorld().getTime()*0.01f) + 0.15f, (float)Math.sin(entity.getWorld().getTime()*0.01f) + 0.175f);
    }
}
