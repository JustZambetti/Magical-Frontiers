package com.zambettigiorgio.magicalfrontiers.item;

import com.zambettigiorgio.magicalfrontiers.MagicalFrontiers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final Item EMPTY_MASS_GEM = register("empty_mass_gem", EmptyMassGemItem::new, new Item.Settings()
            .maxCount(1)
            .fireproof()
    );

    public static final Item FULL_MASS_GEM = register("full_mass_gem", FullMassGemItem::new, new Item.Settings()
            .maxCount(1)
            .fireproof()
    );

    public static final Item DIGESTED_SEEDS = register("digested_seeds", DigestedSeedsItem::new, new Item.Settings()
            .maxCount(1)
    );

    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MagicalFrontiers.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static void registerModItems(){
        MagicalFrontiers.LOGGER.info("Registering Mod Items for " + MagicalFrontiers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(EMPTY_MASS_GEM);
            fabricItemGroupEntries.add(FULL_MASS_GEM);
            fabricItemGroupEntries.add(DIGESTED_SEEDS);
        });
    }
}
