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


public class ChanekeBlock extends FacingXBlock {

    private static final VoxelShape BASE_SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(6.40 / 16.0, 5.45 / 16.0, 6.51 / 16.0, 9.52 / 16.0, 8.07 / 16.0, 9.63 / 16.0),
            VoxelShapes.cuboid(6.85 / 16.0, 2.06 / 16.0, 7.71 / 16.0, 9.08 / 16.0, 5.18 / 16.0, 9.05 / 16.0),
            VoxelShapes.cuboid(8.41 / 16.0, -0.04 / 16.0, 8.20 / 16.0, 8.85 / 16.0, 2.19 / 16.0, 8.64 / 16.0),
            VoxelShapes.cuboid(7.07 / 16.0, -0.04 / 16.0, 8.20 / 16.0, 7.52 / 16.0, 2.19 / 16.0, 8.64 / 16.0)
    );

    // Guardamos todas las rotaciones listas
    private static final EnumMap<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, BASE_SHAPE);
        SHAPES.put(Direction.SOUTH, VoxelShapeUtil.rotateY(BASE_SHAPE, 180));
        SHAPES.put(Direction.WEST, VoxelShapeUtil.rotateY(BASE_SHAPE, 90));
        SHAPES.put(Direction.EAST, VoxelShapeUtil.rotateY(BASE_SHAPE, 270));
    }

    public ChanekeBlock(Settings settings) {
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
