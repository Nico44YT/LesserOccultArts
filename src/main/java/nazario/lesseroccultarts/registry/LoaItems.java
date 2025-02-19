package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.item.*;
import nazario.lesseroccultarts.common.item.contract.FinishedContract;
import nazario.lesseroccultarts.common.item.contract.SignedContract;
import nazario.lesseroccultarts.common.item.contract.UnfinishedContract;
import nazario.liby.registry.auto.LibyAutoRegister;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

@LibyAutoRegister
public class LoaItems {
    public static final ItemGroup LOA_ITEM_GROUP = FabricItemGroupBuilder.build(LesserOccultArtsMain.id("loa_tab"), () -> new ItemStack(LoaItems.SOUL_TAG));

    public static final Item SILVER_DAGGER = registerItem("silver_dagger", new SilverDagger(LoaToolMaterials.SILVER, 3, -4+2.2f, new Item.Settings().group(LoaItems.LOA_ITEM_GROUP)));

    public static final Item SILVER_INGOT = registerSimpleItem("silver_ingot");
    public static final Item SILVER_NUGGET = registerSimpleItem("silver_nugget");
    public static final Item RAW_SILVER = registerSimpleItem("raw_silver");
    public static final Item SOUL_TAG = registerItem("soul_tag", new SoulTagItem(new Item.Settings().group(LOA_ITEM_GROUP).maxCount(1)));
    public static final Item TOTEM_OF_RETRIEVAL = registerItem("totem_of_retrieval", new Item(new Item.Settings().group(LOA_ITEM_GROUP).maxCount(1).rarity(Rarity.RARE)));

    public static final Item CONTRACT = registerItem("contract", new UnfinishedContract(new Item.Settings().group(LOA_ITEM_GROUP).maxCount(1)));
    public static final Item FINISHED_CONTRACT = registerItem("finished_contract", new FinishedContract(new Item.Settings().maxCount(1)));
    public static final Item SIGNED_CONTRACT = registerItem("signed_contract", new SignedContract(new Item.Settings().maxCount(1)));
    public static final Item MALICE_INK = registerItem("clump_of_malice", new MaliceInkItem(new FabricItemSettings().group(LoaItems.LOA_ITEM_GROUP)));

    public static final Item DEEEPSLATE_GREATSWORD = registerItem("deepslate_greatsword", new DeepslateGreatswordItem(DeepslateGreatswordItem.DeepslateGreatswordMaterial.INSTANCE,2,-3f, new FabricItemSettings().group(LoaItems.LOA_ITEM_GROUP)));
    public static final Item DAMNED_GREATSWORD = registerItem("damned_greatsword", new DamnedGreatswordItem(2,-3f, new FabricItemSettings().group(LoaItems.LOA_ITEM_GROUP)));

    public static final Item DEMON_SPAWN_EGG = registerItem("demon_spawn_egg", new SpawnEggItem(LoaEntities.DEMON, 0x280a18, 0xfa1a55, new Item.Settings().group(LoaItems.LOA_ITEM_GROUP)));

    public static void register() {
    }

    public static Item registerSimpleItem(String name) {
        return registerItem(name, new Item(new Item.Settings().group(LOA_ITEM_GROUP)));
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, LesserOccultArtsMain.id(name), item);
    }
}
