package com.zambettigiorgio.magicalfrontiers.block.custom;

import com.mojang.serialization.MapCodec;
import com.zambettigiorgio.magicalfrontiers.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class MushroomBlock extends Block {
    public MushroomBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = Block.createCuboidShape(
            4.0, 0.0, 4.0, 12.0, 8.0, 12.0  // (x1, y1, z1, x2, y2, z2) — in pixels
    );

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        return below.isOf(Blocks.MYCELIUM) || below.isOf(Blocks.PODZOL) || below.isOf(ModBlocks.BROWN_MYCELIUM);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (!canPlaceAt(state, world, pos)) {
            world.breakBlock(pos, true);
        }
        super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
    }
}