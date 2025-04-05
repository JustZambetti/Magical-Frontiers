package com.zambettigiorgio.magicalfrontiers.mixin;

import com.zambettigiorgio.magicalfrontiers.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FallDamageMixin {

    @Inject(method = "handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z", at = @At("HEAD"), cancellable = true)
    private void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!entity.getWorld().isClient() && entity.getWorld() instanceof ServerWorld serverWorld) {
            var registry = Registries.STATUS_EFFECT.getEntry(ModEffects.INCREASED_MASS);
            if (entity.hasStatusEffect(registry)) {
                int baseDamage = MathHelper.ceil((fallDistance - 0.5f) * damageMultiplier);

                int amplifier = entity.getStatusEffect(registry).getAmplifier();
                int increasedDamage = baseDamage * (2 + amplifier);

                if (increasedDamage > 0) {
                    entity.damage(serverWorld, damageSource, increasedDamage);
                    cir.setReturnValue(true);
                }
            }
        }
    }
/*
    @Inject(method = "jump()V", at = @At("TAIL"))
    private void onJump(CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;
        var increasedMassEffect = Registries.STATUS_EFFECT.getEntry(ModEffects.INCREASED_MASS);

        if (entity.hasStatusEffect(increasedMassEffect)){
            int amplifier = entity.getStatusEffect(increasedMassEffect).getAmplifier();
            entity.setVelocity(entity.getVelocity().multiply(1f / (amplifier+2)));
        }
    }
 */
}