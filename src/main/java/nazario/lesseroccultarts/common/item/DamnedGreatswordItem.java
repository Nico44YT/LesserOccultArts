package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.common.entity.demonentity.DemonEntity;
import nazario.lesseroccultarts.library.AdvancedItemMethods;
import nazario.lesseroccultarts.registry.LoaDamageSources;
import nazario.lesseroccultarts.registry.LoaEnchantments;
import nazario.lesseroccultarts.registry.LoaItems;
import nazario.liby.interfaces.LibyItemRenderOverrider;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class DamnedGreatswordItem extends SwordItem implements IOverrideMeleeDamageType, LibyItemRenderOverrider, AdvancedItemMethods {
    public DamnedGreatswordItem(int attackDamage, float attackSpeed, Settings settings) {
        super(DamnedGreatswordMaterial.INSTANCE, attackDamage, attackSpeed, settings);
    }

    @Override
    public void killedWithItem(DamageSource damageSource, ItemStack stack, LivingEntity target, CallbackInfo ci) {
        PlayerEntity playerEntity = (PlayerEntity)damageSource.getAttacker();
        World world = target.getWorld();

        if (EnchantmentHelper.get(stack).containsKey(LoaEnchantments.REPURPOSING)) {
            DemonEntity.spawnAsTamed(world, target.getPos(), playerEntity);
        }
        if (EnchantmentHelper.get(stack).containsKey(LoaEnchantments.ANNIHILATING)) {
            target.sendMessage(Text.literal("You'd be banned when this works :)"));
            // ban logic needs to be added
        }
    }

    @Override
    public DamageSource getDamageSource(World world, LivingEntity attacker) {
        if (attacker.getMainHandStack().getEnchantments().contains(LoaEnchantments.ANNIHILATING)) {
            return LoaDamageSources.DAMNED;
        } else if (attacker.getMainHandStack().getEnchantments().contains(LoaEnchantments.REPURPOSING)) {
            return LoaDamageSources.REPURPOSED;
        }
        return DamageSource.GENERIC;
    }

    @Override
    public void animateArmSwing(Entity entity, BipedEntityModel<? extends LivingEntity> model, float animationProgress, CallbackInfo ci) {
        if(animationProgress < 0f) return;
        Arm arm = ((LivingEntity) entity).getMainArm().getOpposite();
        ModelPart modelPart = model.getArm(arm);
        double f = 1.0 - Math.pow((double)(1.0F - model.handSwingProgress), 3.0);
        float h = MathHelper.sin(model.handSwingProgress * 3.1415927F) * -(model.head.pitch - 0.7F) * 0.75F;
        modelPart.pitch -= MathHelper.sin((float)(f * Math.PI)) * 1.2F + h;
        modelPart.yaw += model.body.yaw * 2.0F;
        modelPart.roll += MathHelper.sin(model.handSwingProgress * 3.1415927F) * -0.4F;
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
            return 1;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(LoaItems.MALICE_INK);
        }
    }
}
