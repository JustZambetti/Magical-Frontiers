package com.zambettigiorgio.magicalfrontiers.block.custom;

import com.mojang.serialization.MapCodec;
import com.zambettigiorgio.magicalfrontiers.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class BrownMycelium extends SpreadableBlock {
    public BrownMycelium(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
        } else {
            for (int i = 0; i < 4; ++i) {
                BlockPos target = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                BlockState targetState = world.getBlockState(target);
                if (targetState.isOf(Blocks.DIRT) && canSpread(state, world, target)) {
                    world.setBlockState(target, this.getDefaultState());
                }
            }
        }

        if (world.getBlockState(pos.up()).isOf(Blocks.RED_MUSHROOM) && world.getBlockState(pos.up().up()).isAir()) {
            if (random.nextInt(5) == 0) {
                world.setBlockState(pos.up(), ModBlocks.BIG_RED_MUSHROOM.getDefaultState());
            }
        }

    }

    private boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockState above = world.getBlockState(pos.up());
        return !above.isOpaqueFullCube();
    }

    @Override
    protected MapCodec<? extends SpreadableBlock> getCodec() {
        return null;
    }

    private boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        return canSurvive(state, world, pos);
    }
}