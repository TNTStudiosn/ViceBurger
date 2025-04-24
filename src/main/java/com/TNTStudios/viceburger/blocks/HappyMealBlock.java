package com.TNTStudios.viceburger.blocks;

import com.TNTStudios.viceburger.config.ToyConfig;
import com.TNTStudios.viceburger.util.ToyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class HappyMealBlock extends Block {
    public HappyMealBlock(Settings settings) {
        super(settings);
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
