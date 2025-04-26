package com.TNTStudios.viceburger.blocks;

import com.TNTStudios.viceburger.util.VoxelShapeUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.EnumMap;


public class TaniBlock extends FacingXBlock {

    private static final VoxelShape BASE_SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(5.62 / 16.0, 4.85 / 16.0, 5.62 / 16.0, 10.38 / 16.0, 9.62 / 16.0, 10.38 / 16.0),
            VoxelShapes.cuboid(6.55 / 16.0, 2.22 / 16.0, 6.92 / 16.0, 9.45 / 16.0, 4.97 / 16.0, 9.08 / 16.0),
            VoxelShapes.cuboid(5.65 / 16.0, 2.98 / 16.0, 7.41 / 16.0, 10.37 / 16.0, 4.97 / 16.0, 8.59 / 16.0),
            VoxelShapes.cuboid(6.80 / 16.0, 0.01 / 16.0, 7.19 / 16.0, 9.18 / 16.0, 2.22 / 16.0, 8.81 / 16.0)
    );

    // Guardamos todas las rotaciones listas
    private static final EnumMap<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, BASE_SHAPE);
        SHAPES.put(Direction.SOUTH, VoxelShapeUtil.rotateY(BASE_SHAPE, 180));
        SHAPES.put(Direction.WEST, VoxelShapeUtil.rotateY(BASE_SHAPE, 90));
        SHAPES.put(Direction.EAST, VoxelShapeUtil.rotateY(BASE_SHAPE, 270));
    }

    public TaniBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.getOrDefault(state.get(Properties.HORIZONTAL_FACING), BASE_SHAPE);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.getOrDefault(state.get(Properties.HORIZONTAL_FACING), BASE_SHAPE);
    }



}