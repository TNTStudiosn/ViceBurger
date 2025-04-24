package com.TNTStudios.viceburger.blocks;

import com.TNTStudios.viceburger.config.ToyConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BlockEntityType.Builder;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.util.Locals;

public class HappyMealBlock extends Block {

    public HappyMealBlock(Locals.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              net.minecraft.entity.player.PlayerEntity player, Hand hand,
                              net.minecraft.util.hit.BlockHitResult hit) {

        if (!world.isClient) {
            // Obtener juguete aleatorio
            Identifier toyId = ToyConfig.getRandomToy(world.getRandom());

            // Crear ItemStack y soltarlo
            ItemStack toyStack = new ItemStack(
                    net.minecraft.registry.Registries.ITEM.get(toyId)
            );
            ItemEntity itemEntity = new ItemEntity(world,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    toyStack);
            world.spawnEntity(itemEntity);

            // Sonido
            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1.2f);

            // Partículas (tipo confeti con partículas de poción)
            ((ServerWorld) world).spawnParticles(
                    net.minecraft.particle.ParticleTypes.HAPPY_VILLAGER,
                    pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5,
                    20, 0.2, 0.3, 0.2, 0.01
            );

            // Eliminar el bloque
            world.breakBlock(pos, false);
        }

        return ActionResult.SUCCESS;
    }
}

