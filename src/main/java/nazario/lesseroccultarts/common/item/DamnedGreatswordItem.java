package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.common.entity.demonentity.DemonEntity;
import nazario.lesseroccultarts.registry.LoaDamageSources;
import nazario.lesseroccultarts.registry.LoadEnchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class DamnedGreatswordItem extends SwordItem implements IOverrideMeleeDamageType {
    public DamnedGreatswordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();
        if (target.isDead()) {
            if (stack.getEnchantments().contains(LoadEnchantments.REPURPOSING)) {
                if (attacker instanceof PlayerEntity player) {
                    DemonEntity.spawnAsAlly(world, target.getPos(), player);
                }
            }
            if (stack.getEnchantments().contains(LoadEnchantments.ANNIHILATING)) {
                target.sendMessage(Text.literal("You'd be banned when this works :)"));
                // ban logic needs to be added
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public DamageSource getDamageSource(World world, LivingEntity attacker) {
        if (attacker.getMainHandStack().getEnchantments().contains(LoadEnchantments.ANNIHILATING)) {
            return LoaDamageSources.DAMNED;
        } else if (attacker.getMainHandStack().getEnchantments().contains(LoadEnchantments.REPURPOSING)) {
            return LoaDamageSources.REPURPOSED;
        }
        return DamageSource.GENERIC;
    }

    public static class DamnedGreatswordMaterial implements ToolMaterial {
        public static final DamnedGreatswordMaterial INSTANCE = new DamnedGreatswordMaterial();

        @Override
        public int getDurability() {
            return 1840;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 8;
        }

        @Override
        public int getMiningLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    }
}
