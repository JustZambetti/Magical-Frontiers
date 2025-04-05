package com.zambettigiorgio.magicalfrontiers.effect;

import com.zambettigiorgio.magicalfrontiers.MagicalFrontiers;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect INCREASED_MASS = new IncreasedMassEffect();

    public static void registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(MagicalFrontiers.MOD_ID, "increased_mass"), INCREASED_MASS);
    }
}