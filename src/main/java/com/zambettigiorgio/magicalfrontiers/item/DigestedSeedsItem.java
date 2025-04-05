package com.zambettigiorgio.magicalfrontiers.item;

import com.zambettigiorgio.magicalfrontiers.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static com.zambettigiorgio.magicalfrontiers.item.ModItems.EMPTY_MASS_GEM;

public class DigestedSeedsItem extends Item {
    public DigestedSeedsItem(Settings settings) {
        super(settings);
    }

}
