package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.block.MaliceInkCauldronBlock;
import nazario.lesseroccultarts.common.block.SilverPressurePlate;
import nazario.lesseroccultarts.common.block.TestBlock;
import nazario.lesseroccultarts.common.block.TestBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static final Block SILVER_PRESSURE_PLATE = registerBlock("silver_pressure_plate", new SilverPressurePlate(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).noCollision()), new Item.Settings().group(ItemRegistry.LOA_ITEM_GROUP));
    public static final Block SILVER_ORE = registerBlock("silver_ore", new OreBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE)), new Item.Settings().group(ItemRegistry.LOA_ITEM_GROUP));
    public static final Block SILVER_BLOCK = registerBlock("silver_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), new Item.Settings().group(ItemRegistry.LOA_ITEM_GROUP));

    public static final Block MALICE_INK_CAULDRON = registerBlock("malice_ink_cauldron", new MaliceInkCauldronBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), new Item.Settings());

    public static final Block TEST_BLOCK = registerBlock("test_block", new TestBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)), new Item.Settings().group(ItemRegistry.LOA_ITEM_GROUP));
    public static final BlockEntityType<TestBlockEntity> TEST_BLOCK_ENTITY = registerType("test_block", FabricBlockEntityTypeBuilder.create(TestBlockEntity::new, TEST_BLOCK).build());

    public static void register() {

    }

    public static Block registerBlock(String name, Block block, Item.Settings itemSettings) {
        ItemRegistry.registerItem(name, new BlockItem(block, itemSettings));
        return Registry.register(Registry.BLOCK, LesserOccultArtsMain.id(name), block);
    }

    public static <T extends BlockEntityType<?>> T registerType(String name, T blockEntityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, LesserOccultArtsMain.id(name), blockEntityType);
    }}
