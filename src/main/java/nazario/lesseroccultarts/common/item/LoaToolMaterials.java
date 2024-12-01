package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.registry.LoaItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class LoaToolMaterials {
    public static final ToolMaterial SILVER = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 164;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 0.5f;
        }

        @Override
        public int getMiningLevel() {
            return 1;
        }

        @Override
        public int getEnchantability() {
            return 4;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofStacks(new ItemStack(LoaItems.SILVER_INGOT));
        }
    };
}
