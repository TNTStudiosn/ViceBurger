package com.TNTStudios.viceburger.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;


public class AbejaBlock extends FacingXBlock {

    private static final VoxelShape SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(6.96647 / 16.0, 0.8282 / 16.0, 6.59735 / 16.0, 9.03353 / 16.0, 2.89527 / 16.0, 9.5503 / 16.0),
            VoxelShapes.cuboid(8.44294 / 16.0, 2.30468 / 16.0, 5.71146 / 16.0, 8.73824 / 16.0, 2.89527 / 16.0, 6.59735 / 16.0),
            VoxelShapes.cuboid(7.26176 / 16.0, 2.30468 / 16.0, 5.71146 / 16.0, 7.55706 / 16.0, 2.89527 / 16.0, 6.59735 / 16.0),
            VoxelShapes.cuboid(8.0 / 16.0, 1.71409 / 16.0, 9.5503 / 16.0, 8.0 / 16.0, 2.00938 / 16.0, 10.14089 / 16.0),
            VoxelShapes.cuboid(8.44294 / 16.0, 2.89527 / 16.0, 7.18794 / 16.0, 11.1006 / 16.0, 2.89527 / 16.0, 8.95971 / 16.0),
            VoxelShapes.cuboid(4.8994 / 16.0, 2.89527 / 16.0, 7.18794 / 16.0, 7.55706 / 16.0, 2.89527 / 16.0, 8.95971 / 16.0),
            VoxelShapes.cuboid(6.96647 / 16.0, 0.23761 / 16.0, 7.48323 / 16.0, 9.03353 / 16.0, 0.8282 / 16.0, 7.48323 / 16.0),
            VoxelShapes.cuboid(6.96647 / 16.0, 0.23761 / 16.0, 8.07382 / 16.0, 9.03353 / 16.0, 0.8282 / 16.0, 8.07382 / 16.0),
            VoxelShapes.cuboid(6.96647 / 16.0, 0.23761 / 16.0, 8.66441 / 16.0, 9.03353 / 16.0, 0.8282 / 16.0, 8.66441 / 16.0),
            VoxelShapes.cuboid(6.84835 / 16.0, -0.05768 / 16.0, 6.89264 / 16.0, 9.26977 / 16.0, 0.26714 / 16.0, 9.28453 / 16.0)
    );

    public AbejaBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);
        return rotateShape(facing, SHAPE);
    }

    /** Rota un VoxelShape según la dirección del eje Y */
    private static VoxelShape rotateShape(Direction direction, VoxelShape shape) {
        switch (direction) {
            case SOUTH -> {
                return rotateY(shape, 180);
            }
            case WEST -> {
                return rotateY(shape, 270);
            }
            case EAST -> {
                return rotateY(shape, 90);
            }
            default -> {
                return shape;
            }
        }
    }


    private static VoxelShape rotateY(VoxelShape shape, int degrees) {
        return shape.getBoundingBoxes().stream()
                .map(box -> switch (degrees) {
                    case 0 -> VoxelShapes.cuboid( // NORTH (ajustado)
                            1 - box.maxX, box.minY, 1 - box.maxZ,
                            1 - box.minX, box.maxY, 1 - box.minZ
                    );
                    case 90 -> VoxelShapes.cuboid( // EAST
                            box.minZ, box.minY, 1 - box.maxX,
                            box.maxZ, box.maxY, 1 - box.minX
                    );
                    case 180 -> VoxelShapes.cuboid( // SOUTH
                            box.minX, box.minY, box.minZ,
                            box.maxX, box.maxY, box.maxZ
                    );
                    case 270 -> VoxelShapes.cuboid( // WEST
                            1 - box.maxZ, box.minY, box.minX,
                            1 - box.minZ, box.maxY, box.maxX
                    );
                    default -> VoxelShapes.cuboid( // fallback (igual a NORTH)
                            1 - box.maxX, box.minY, 1 - box.maxZ,
                            1 - box.minX, box.maxY, 1 - box.minZ
                    );
                })
                .reduce(VoxelShapes::union)
                .orElse(VoxelShapes.empty());
    }



}
