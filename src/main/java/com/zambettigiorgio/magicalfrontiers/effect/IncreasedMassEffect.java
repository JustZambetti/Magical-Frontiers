package com.zambettigiorgio.magicalfrontiers.effect;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;

public class IncreasedMassEffect extends StatusEffect {
    protected IncreasedMassEffect() {
        super(StatusEffectCategory.HARMFUL, 0x0000);
        addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, Identifier.of("slowdown_modifier"),
                -0.5,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        addAttributeModifier(EntityAttributes.GRAVITY, Identifier.of("increase_gravity_modifier"),
                2,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
/*
    @Override
    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return EntityEffectParticleEffect.create(ParticleTypes.ENTITY_EFFECT, ColorHelper.withAlpha(0, 0));
    }
 */
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
