package com.TNTStudios.viceburger.util;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.math.Box;

public class VoxelShapeUtil {

    public static VoxelShape rotateY(VoxelShape shape, int degrees) {
        int times = (degrees / 90) % 4;
        VoxelShape rotatedShape = shape;

        for (int i = 0; i < times; i++) {
            VoxelShape[] buffer = new VoxelShape[]{VoxelShapes.empty()};
            rotatedShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
                buffer[0] = VoxelShapes.union(buffer[0], VoxelShapes.cuboid(
                        minZ, minY, 1 - maxX,
                        maxZ, maxY, 1 - minX
                ));
            });
            rotatedShape = buffer[0];
        }

        return rotatedShape;
    }
}
