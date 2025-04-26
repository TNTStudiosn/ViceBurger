package com.TNTStudios.viceburger.blocks;

import com.TNTStudios.viceburger.config.ToyConfig;
import com.TNTStudios.viceburger.util.ToyUtils;
import com.TNTStudios.viceburger.util.VoxelShapeUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.EnumMap;

public class HappyMealBlock extends FacingXBlock {

    private static final VoxelShape BASE_SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(4.681 / 16.0, -0.058 / 16.0, 5.145 / 16.0, 11.253 / 16.0, 5.003 / 16.0, 11.332 / 16.0)

    );

    // Guardamos todas las rotaciones listas
    private static final EnumMap<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, BASE_SHAPE);
        SHAPES.put(Direction.SOUTH, VoxelShapeUtil.rotateY(BASE_SHAPE, 180));
        SHAPES.put(Direction.WEST, VoxelShapeUtil.rotateY(BASE_SHAPE, 90));
        SHAPES.put(Direction.EAST, VoxelShapeUtil.rotateY(BASE_SHAPE, 270));
    }

    public HappyMealBlock(Settings settings) {
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

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              net.minecraft.entity.player.PlayerEntity player, Hand hand,
                              BlockHitResult hit) {
        if (!world.isClient) {
            // 1) Elige juguete usando el Random de Minecraft
            Identifier toyId = ToyConfig.getRandomToy(world.getRandom());
            ItemStack toyStack = new ItemStack(Registries.ITEM.get(toyId));

            // 2) Suelta el ítem sobre el bloque
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    toyStack
            );
            world.spawnEntity(itemEntity);

            // 3) Sonidos: fanfarria + pickup
            world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1f, 1f);
            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1.2f);

            // 4) Partículas de confetti (HAPPY_VILLAGER)
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    20,    // count
                    0.3, 0.5, 0.3,  // spread x/y/z
                    0.2   // speed
            );

            // 5) Efecto de rotura de bloque
            world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));

            // 6) Rompe la HappyMeal sin soltar más bloques
            world.breakBlock(pos, false);

            // 7) Muestra mensaje con nombre bonito
            player.sendMessage(ToyUtils.getDisplayName(toyId), false);
        }
        return ActionResult.SUCCESS;
    }
}
