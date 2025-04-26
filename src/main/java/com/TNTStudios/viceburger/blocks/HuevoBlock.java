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

public class HuevoBlock extends FacingXBlock {

    private static final VoxelShape BASE_SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(5.83 / 16.0, 0.18 / 16.0, 6.19 / 16.0, 10.16 / 16.0, 0.78 / 16.0, 9.80 / 16.0),
            VoxelShapes.cuboid(5.83 / 16.0, 2.99 / 16.0, 6.04 / 16.0, 10.16 / 16.0, 4.00 / 16.0, 9.95 / 16.0),
            VoxelShapes.cuboid(6.24 / 16.0, 4.00 / 16.0, 6.24 / 16.0, 9.75 / 16.0, 4.60 / 16.0, 9.75 / 16.0),
            VoxelShapes.cuboid(5.43 / 16.0, 0.78 / 16.0, 6.19 / 16.0, 10.56 / 16.0, 2.99 / 16.0, 10.21 / 16.0)
    );

    // Guardamos todas las rotaciones listas
    private static final EnumMap<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, BASE_SHAPE);
        SHAPES.put(Direction.SOUTH, VoxelShapeUtil.rotateY(BASE_SHAPE, 180));
        SHAPES.put(Direction.WEST, VoxelShapeUtil.rotateY(BASE_SHAPE, 90));
        SHAPES.put(Direction.EAST, VoxelShapeUtil.rotateY(BASE_SHAPE, 270));
    }

    public HuevoBlock(Settings settings) {
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
