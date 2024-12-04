package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.enchantments.AnnihilatingEnchantment;
import nazario.lesseroccultarts.common.enchantments.BanishmentEnchantment;
import nazario.lesseroccultarts.common.enchantments.RepurposingEnchantment;
import nazario.liby.registry.auto.LibyAutoRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

@LibyAutoRegister
public class LoaEnchantments {
    public static final Enchantment BANISHMENT = Registry.register(Registry.ENCHANTMENT, LesserOccultArtsMain.id("curse_banishment"), new BanishmentEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final Enchantment ANNIHILATING = Registry.register(Registry.ENCHANTMENT, LesserOccultArtsMain.id("annihilating"), new AnnihilatingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final Enchantment REPURPOSING = Registry.register(Registry.ENCHANTMENT, LesserOccultArtsMain.id("repurposing"), new RepurposingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static void register() {

    }
}
