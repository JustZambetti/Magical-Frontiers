package com.zambettigiorgio.magicalfrontiers.block;

import com.zambettigiorgio.magicalfrontiers.MagicalFrontiers;
import com.zambettigiorgio.magicalfrontiers.block.custom.BrownMycelium;
import com.zambettigiorgio.magicalfrontiers.block.custom.TeapotBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.Instrument;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BROWN_MYCELIUM = register("brown_mycelium", BrownMycelium::new, Block.Settings.create().strength(0.6f).sounds(BlockSoundGroup.GRASS).instrument(NoteBlockInstrument.HARP).ticksRandomly());
    public static final Block TEAPOT = register("teapot", TeapotBlock::new, Block.Settings.create().nonOpaque().strength(4.0f));

    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(MagicalFrontiers.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static void registerModBlocks(){
        MagicalFrontiers.LOGGER.info("Registering Mod Blocks for " + MagicalFrontiers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(TEAPOT.asItem());
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BROWN_MYCELIUM.asItem());
        });

    }
}
