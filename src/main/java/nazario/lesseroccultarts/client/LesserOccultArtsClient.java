package nazario.lesseroccultarts.client;

import nazario.lesseroccultarts.common.entity.demonentity.DemonEntityRenderer;
import nazario.lesseroccultarts.client.render.LargeItemRenderer;
import nazario.lesseroccultarts.common.particle.MaliceInkParticle;
import nazario.lesseroccultarts.registry.LoaEntities;
import nazario.lesseroccultarts.registry.LoaItems;
import nazario.lesseroccultarts.registry.LoaPackets;
import nazario.lesseroccultarts.registry.LoaParticles;
import nazario.liby.registry.auto.LibyAutoRegister;
import nazario.liby.registry.auto.LibyEntrypoints;
import nazario.liby.registry.auto.LibyRegistryLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LesserOccultArtsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerModelPredicateProviders();

        LibyRegistryLoader.load("nazario.lesseroccultarts.registry", LibyEntrypoints.CLIENT);

        EntityRendererRegistry.register(LoaEntities.DEMON, DemonEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(LoaParticles.MALICE_INK, MaliceInkParticle.Factory::new);

        Identifier damnedId = Registry.ITEM.getId(LoaItems.DAMNED_GREATSWORD);
        LargeItemRenderer damnedItemRenderer = new LargeItemRenderer(damnedId);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(damnedItemRenderer);
        BuiltinItemRendererRegistry.INSTANCE.register(LoaItems.DAMNED_GREATSWORD, damnedItemRenderer);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelIdentifier(damnedId, "inventory"));
            out.accept(new ModelIdentifier(damnedId + "_handheld", "inventory"));
        });

        Identifier bladeId = Registry.ITEM.getId(LoaItems.DEEEPSLATE_GREATSWORD);
        LargeItemRenderer bladeItemRenderer = new LargeItemRenderer(bladeId);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(bladeItemRenderer);
        BuiltinItemRendererRegistry.INSTANCE.register(LoaItems.DEEEPSLATE_GREATSWORD, bladeItemRenderer);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelIdentifier(bladeId, "inventory"));
            out.accept(new ModelIdentifier(bladeId + "_handheld", "inventory"));
        });
    }

    public static void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(LoaItems.SOUL_TAG, new Identifier("linked"), ((stack, world, entity, seed) -> {
            if(stack.hasNbt()) {
                assert stack.getNbt() != null;
                boolean linked = stack.getNbt().getBoolean("linked");
                return linked?1:0;
            }
            return 0;
        }));
        ModelPredicateProviderRegistry.register(LoaItems.SILVER_DAGGER, new Identifier("bloody"), (((stack, world, entity, seed) -> {
            if(stack.hasNbt()) {
                assert stack.getNbt() != null;
                return stack.getNbt().getBoolean("bloody")?1:0;
            }
            return 0;
        })));
    }
}
