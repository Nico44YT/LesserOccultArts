package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.block.MaliceInkCauldronBlock;
import nazario.lesseroccultarts.common.block.SilverPressurePlate;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class LoaBlocks {
    public static final Block SILVER_PRESSURE_PLATE = registerBlock("silver_pressure_plate", new SilverPressurePlate(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).noCollision()), new Item.Settings().group(LoaItems.LOA_ITEM_GROUP));
    public static final Block SILVER_ORE = registerBlock("silver_ore", new OreBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE)), new Item.Settings().group(LoaItems.LOA_ITEM_GROUP));
    public static final Block SILVER_BLOCK = registerBlock("silver_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), new Item.Settings().group(LoaItems.LOA_ITEM_GROUP));

    public static final Block MALICE_INK_CAULDRON = registerBlock("malice_ink_cauldron",
            new MaliceInkCauldronBlock(
                    AbstractBlock.Settings.copy(Blocks.CAULDRON),
                    null,
                    MaliceInkCauldronBlock.MALICE_CAULDRON_BEHAVIOR), new Item.Settings());


    public static void register() {

    }

    public static Block registerBlock(String name, Block block, Item.Settings itemSettings) {
        LoaItems.registerItem(name, new BlockItem(block, itemSettings));
        return Registry.register(Registry.BLOCK, LesserOccultArtsMain.id(name), block);
    }

    public static <T extends BlockEntityType<?>> T registerType(String name, T blockEntityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, LesserOccultArtsMain.id(name), blockEntityType);
    }}
