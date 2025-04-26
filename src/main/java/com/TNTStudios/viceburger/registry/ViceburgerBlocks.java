package com.TNTStudios.viceburger.registry;

import com.TNTStudios.viceburger.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ViceburgerBlocks {
    // El bloque “Happy Meal” ya lo tenías
    public static final Block HAPPYMEAL = register("happymeal",
            new HappyMealBlock(AbstractBlock.Settings.create().strength(0.5f).nonOpaque())
    );

    public static final Block ABEJA_BLOCK = register("abeja",
            new AbejaBlock(AbstractBlock.Settings.create().strength(0.2f).nonOpaque())
    );

    public static final Block CHANEKE_BLOCK = register("chaneke",
            new ChanekeBlock(AbstractBlock.Settings.create().strength(0.2f).nonOpaque())
    );
    public static final Block HUEVO_BLOCK = register("huevo",
            new HuevoBlock(AbstractBlock.Settings.create().strength(0.2f).nonOpaque())
    );
    public static final Block RANA_BLOCK = register("rana",
            new RanaBlock(AbstractBlock.Settings.create().strength(0.2f).nonOpaque())
    );
    public static final Block TANI_BLOCK = register("tani",
            new TaniBlock(AbstractBlock.Settings.create().strength(0.2f).nonOpaque())
    );


    public static void registerAll() {
        // Con esto basta para forzar la carga de la clase y que se ejecuten los statics
    }

    private static Block register(String name, Block block) {
        Identifier id = new Identifier("viceburger", name);
        // 1) Registra el bloque
        Registry.register(Registries.BLOCK, id, block);
        // 2) Registra su BlockItem (para poder ponerlo en el inventario)
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        return block;
    }
}
