package com.zambettigiorgio.magicalfrontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class BouncyMushroom extends Block {

    public static final MapCodec<BouncyMushroom> CODEC = createCodec(BouncyMushroom::new);

    public BouncyMushroom(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    private static final VoxelShape SHAPE = VoxelShapes.union(
        Block.createCuboidShape(0.0, 4.0, 0.0, 16.0, 11.0, 16.0),
        Block.createCuboidShape(7, 0, 7, 9, 4, 9)
    );
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        return SHAPE;
    }


}
