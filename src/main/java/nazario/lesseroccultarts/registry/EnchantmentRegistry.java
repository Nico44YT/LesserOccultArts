package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.common.enchantments.BanishmentEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class EnchantmentRegistry {
    public static final Enchantment BANISHMENT = Registry.register(Registry.ENCHANTMENT, LesserOccultArtsMain.id("curse_banishment"), new BanishmentEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static void register() {

    }
}
