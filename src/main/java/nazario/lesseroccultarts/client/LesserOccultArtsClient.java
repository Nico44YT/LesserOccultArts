package nazario.lesseroccultarts.client;

import nazario.lesseroccultarts.registry.BlockRegistry;
import nazario.lesseroccultarts.registry.EntityRegistry;
import nazario.lesseroccultarts.registry.ItemRegistry;
import nazario.lesseroccultarts.registry.PacketRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LesserOccultArtsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerModelPredicateProviders();

        BlockEntityRendererFactories.register(BlockRegistry.TEST_BLOCK_ENTITY, TestBlockEntityRenderer::new);

        PacketRegistry.registerS2CPackets();

        EntityRendererRegistry.register(EntityRegistry.DEMON, DemonEntityRenderer::new);

        Identifier damnedId = Registry.ITEM.getId(ItemRegistry.DAMNED_GREATSWORD);
        LargeItemRenderer damnedItemRenderer = new LargeItemRenderer(damnedId);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(damnedItemRenderer);
        BuiltinItemRendererRegistry.INSTANCE.register(ItemRegistry.DAMNED_GREATSWORD, damnedItemRenderer);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelIdentifier(damnedId, "inventory"));
            out.accept(new ModelIdentifier(damnedId + "_handheld", "inventory"));
        });

        Identifier bladeId = Registry.ITEM.getId(ItemRegistry.DEEEPSLATE_GREATSWORD);
        LargeItemRenderer bladeItemRenderer = new LargeItemRenderer(bladeId);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(bladeItemRenderer);
        BuiltinItemRendererRegistry.INSTANCE.register(ItemRegistry.DEEEPSLATE_GREATSWORD, bladeItemRenderer);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelIdentifier(bladeId, "inventory"));
            out.accept(new ModelIdentifier(bladeId + "_handheld", "inventory"));
        });
    }

    public static void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(ItemRegistry.SOUL_TAG, new Identifier("linked"), ((stack, world, entity, seed) -> {
            if(stack.hasNbt()) {
                assert stack.getNbt() != null;
                boolean linked = stack.getNbt().getBoolean("linked");
                return linked?1:0;
            }
            return 0;
        }));
        ModelPredicateProviderRegistry.register(ItemRegistry.SILVER_DAGGER, new Identifier("bloody"), (((stack, world, entity, seed) -> {
            if(stack.hasNbt()) {
                assert stack.getNbt() != null;
                return stack.getNbt().getBoolean("bloody")?1:0;
            }
            return 0;
        })));
    }
}
