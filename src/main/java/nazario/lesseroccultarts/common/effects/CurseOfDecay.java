package nazario.lesseroccultarts.common.effects;

import nazario.lesseroccultarts.registry.DamageRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CurseOfDecay extends StatusEffect {
    public CurseOfDecay(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.damage(DamageRegistry.DECAYED, 999999);
        // ban logic has to be added
        super.onRemoved(entity, attributes, amplifier);
    }
}
