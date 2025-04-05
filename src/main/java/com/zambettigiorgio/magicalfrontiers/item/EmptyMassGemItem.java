package com.zambettigiorgio.magicalfrontiers.item;

import com.zambettigiorgio.magicalfrontiers.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static com.zambettigiorgio.magicalfrontiers.item.ModItems.EMPTY_MASS_GEM;
import static com.zambettigiorgio.magicalfrontiers.item.ModItems.FULL_MASS_GEM;

public class EmptyMassGemItem extends Item {
    public EmptyMassGemItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!entity.getWorld().isClient()) {
            entity.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, 2f, 0.7f);
        }

        var increasedMassStatusEffectRegistry = Registries.STATUS_EFFECT.getEntry(ModEffects.INCREASED_MASS);

        if(!entity.hasStatusEffect(StatusEffects.LEVITATION)){

            if(entity.hasStatusEffect(increasedMassStatusEffectRegistry))
            {
                int amplifier = entity.getStatusEffect(increasedMassStatusEffectRegistry).getAmplifier();
                entity.removeStatusEffect(increasedMassStatusEffectRegistry);

                if(amplifier > 0)
                    entity.addStatusEffect(new StatusEffectInstance(increasedMassStatusEffectRegistry, Integer.MAX_VALUE, amplifier-1));
            }
            else
            {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, Integer.MAX_VALUE, 0));
            }
        }
        else{
            int amplifier = entity.getStatusEffect(StatusEffects.LEVITATION).getAmplifier();
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, Integer.MAX_VALUE, amplifier+1));
        }

        user.getInventory().removeStack(user.getInventory().selectedSlot);
        var masGemStack = new ItemStack(FULL_MASS_GEM);
        user.getInventory().insertStack(user.getInventory().selectedSlot, masGemStack);
        user.getItemCooldownManager().set(masGemStack, 100);

       return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking())
            useOnEntity(user.getStackInHand(hand), user, user, hand);
        return ActionResult.SUCCESS;
    }
}
