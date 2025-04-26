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

public class RanaBlock extends FacingXBlock {

    private static final VoxelShape BASE_SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(8.51 / 16.0, 0.03 / 16.0, 5.83 / 16.0, 9.34 / 16.0, 0.87 / 16.0, 6.66 / 16.0),
            VoxelShapes.cuboid(6.84 / 16.0, 0.03 / 16.0, 5.83 / 16.0, 7.67 / 16.0, 0.87 / 16.0, 6.66 / 16.0),
            VoxelShapes.cuboid(6.00 / 16.0, 3.37 / 16.0, 5.62 / 16.0, 7.67 / 16.0, 5.04 / 16.0, 6.87 / 16.0),
            VoxelShapes.cuboid(8.51 / 16.0, 3.37 / 16.0, 5.62 / 16.0, 10.18 / 16.0, 5.04 / 16.0, 6.87 / 16.0),
            VoxelShapes.cuboid(6.84 / 16.0, 1.29 / 16.0, 7.71 / 16.0, 9.34 / 16.0, 3.79 / 16.0, 9.38 / 16.0),
            VoxelShapes.cuboid(6.42 / 16.0, 0.87 / 16.0, 4.79 / 16.0, 9.76 / 16.0, 4.21 / 16.0, 7.71 / 16.0)
    );

    // Guardamos todas las rotaciones listas
    private static final EnumMap<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, VoxelShapeUtil.rotateY(BASE_SHAPE, 180));
        SHAPES.put(Direction.SOUTH, BASE_SHAPE);
        SHAPES.put(Direction.WEST, VoxelShapeUtil.rotateY(BASE_SHAPE, 270));
        SHAPES.put(Direction.EAST, VoxelShapeUtil.rotateY(BASE_SHAPE, 90));
    }

    public RanaBlock(Settings settings) {
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
