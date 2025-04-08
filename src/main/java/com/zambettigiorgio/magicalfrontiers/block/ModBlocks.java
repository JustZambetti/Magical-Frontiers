package com.zambettigiorgio.magicalfrontiers.block;

import com.zambettigiorgio.magicalfrontiers.MagicalFrontiers;
import com.zambettigiorgio.magicalfrontiers.block.custom.BrownMycelium;
import com.zambettigiorgio.magicalfrontiers.block.custom.TeapotBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import com.zambettigiorgio.magicalfrontiers.block.custom.WallMushroom;
import com.zambettigiorgio.magicalfrontiers.block.custom.MushroomBlock;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BROWN_MYCELIUM = register("brown_mycelium", BrownMycelium::new, Block.Settings.create().strength(0.6f).sounds(BlockSoundGroup.GRASS).instrument(NoteBlockInstrument.HARP).ticksRandomly());
    public static final Block TEAPOT = register("teapot", TeapotBlock::new, Block.Settings.create().nonOpaque().strength(4.0f));
    public static final Block WALL_MUSHROOM = register("wall_mushroom", WallMushroom::new, Block.Settings.copy(Blocks.RED_MUSHROOM).solidBlock((state, world, pos) -> false).strength(0.6f).sounds(BlockSoundGroup.CROP).nonOpaque());

    //Mushrooms crops:
    public static final Block BIG_RED_MUSHROOM = register("aaa", MushroomBlock::new, Block.Settings.copy(Blocks.RED_MUSHROOM).solidBlock((state, world, pos) -> false).strength(0.6f).sounds(BlockSoundGroup.CROP).nonOpaque());
    public static final Block SMALL_PARASOL = register("small_parasol", MushroomBlock::new, Block.Settings.copy(Blocks.RED_MUSHROOM).solidBlock((state, world, pos) -> false).strength(0.6f).sounds(BlockSoundGroup.CROP).nonOpaque());
    public static final Block CLOSED_PARASOL = register("closed_parasol", MushroomBlock::new, Block.Settings.copy(Blocks.RED_MUSHROOM).solidBlock((state, world, pos) -> false).strength(0.6f).sounds(BlockSoundGroup.CROP).nonOpaque());
    public static final Block OPEN_PARASOL = register("open_parasol", MushroomBlock::new, Block.Settings.copy(Blocks.RED_MUSHROOM).solidBlock((state, world, pos) -> false).strength(0.6f).sounds(BlockSoundGroup.CROP).nonOpaque());

    public static final Block POTTED_BIG_RED_MUSHROOM = register("potted_big_red_mushroom",  (settings) -> new FlowerPotBlock(BIG_RED_MUSHROOM, settings), AbstractBlock.Settings.copy(Blocks.POTTED_RED_MUSHROOM));


    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(MagicalFrontiers.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    private static Block registerBlockWithoutBlockItem(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(MagicalFrontiers.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        return Blocks.register(registryKey, factory, settings);
    }

    public static void registerModBlocks(){
        MagicalFrontiers.LOGGER.info("Registering Mod Blocks for " + MagicalFrontiers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(TEAPOT.asItem());
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BROWN_MYCELIUM.asItem());
            fabricItemGroupEntries.add(BIG_RED_MUSHROOM.asItem());
            fabricItemGroupEntries.add(WALL_MUSHROOM.asItem());
            fabricItemGroupEntries.add(SMALL_PARASOL.asItem());
            fabricItemGroupEntries.add(CLOSED_PARASOL.asItem());
            fabricItemGroupEntries.add(OPEN_PARASOL.asItem());
        });

    }
}
