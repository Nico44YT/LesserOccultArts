package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.item.LoaToolMaterials;
import nazario.lesseroccultarts.common.item.SilverDagger;
import nazario.lesseroccultarts.common.item.SoulTagItem;
import nazario.lesseroccultarts.common.item.SpellbookItem;
import nazario.lesseroccultarts.common.item.contract.FinishedContract;
import nazario.lesseroccultarts.common.item.contract.SignedContract;
import nazario.lesseroccultarts.common.item.contract.UnfinishedContract;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static final ItemGroup LOA_ITEM_GROUP = FabricItemGroupBuilder.build(LesserOccultArtsMain.id("loa_tab"), () -> new ItemStack(ItemRegistry.SOUL_TAG));

    public static final Item SILVER_DAGGER = registerItem("silver_dagger", new SilverDagger(LoaToolMaterials.SILVER, 3, -4+2.2f, new Item.Settings().group(ItemRegistry.LOA_ITEM_GROUP)));

    public static final Item SPELL_BOOK = registerItem("spellbook", new SpellbookItem(new Item.Settings().group(LOA_ITEM_GROUP).maxCount(1)));
    public static final Item SILVER_INGOT = registerSimpleItem("silver_ingot");
    public static final Item SILVER_NUGGET = registerSimpleItem("silver_nugget");
    public static final Item RAW_SILVER = registerSimpleItem("raw_silver");
    public static final Item SOUL_TAG = registerItem("soul_tag", new SoulTagItem(new Item.Settings().group(LOA_ITEM_GROUP).maxCount(1)));
    public static final Item TOTEM_OF_RETRIEVAL = registerItem("totem_of_retrieval", new Item(new Item.Settings().group(LOA_ITEM_GROUP).maxCount(1).rarity(Rarity.RARE)));

    public static final Item CONTRACT = registerItem("contract", new UnfinishedContract(new Item.Settings().group(LOA_ITEM_GROUP).maxCount(1)));
    public static final Item FINISHED_CONTRACT = registerItem("finished_contract", new FinishedContract(new Item.Settings().maxCount(1)));
    public static final Item SIGNED_CONTRACT = registerItem("signed_contract", new SignedContract(new Item.Settings().maxCount(1)));

    public static void register() {
    }

    public static Item registerSimpleItem(String name) {
        return registerItem(name, new Item(new Item.Settings().group(LOA_ITEM_GROUP)));
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, LesserOccultArtsMain.id(name), item);
    }
}
