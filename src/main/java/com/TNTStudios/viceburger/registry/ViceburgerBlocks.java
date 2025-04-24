package com.TNTStudios.viceburger.registry;

import com.TNTStudios.viceburger.blocks.HappyMealBlock;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ViceburgerBlocks {

    public static final Block HAPPYMEAL = register("happymeal", new HappyMealBlock(
            AbstractBlock.Settings.create().strength(0.5f).nonOpaque()
    ));

    public static void registerAll() {
        // Llamado desde Viceburger.java
    }

    private static Block register(String name, Block block) {
        Identifier id = new Identifier("viceburger", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        return block;
    }
}
