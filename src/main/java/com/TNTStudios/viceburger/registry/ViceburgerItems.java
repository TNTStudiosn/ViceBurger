package com.TNTStudios.viceburger.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ViceburgerItems {

    public static final Item ABEJA = register("abeja", new Item(new Item.Settings()));
    public static final Item CHANEKE = register("chaneke", new Item(new Item.Settings()));
    public static final Item HUEVO = register("huevo", new Item(new Item.Settings()));
    public static final Item RANA = register("rana", new Item(new Item.Settings()));
    public static final Item TANI = register("tani", new Item(new Item.Settings()));

    public static void registerAll() {
        // Este método se llamará desde Viceburger.java más adelante
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("viceburger", name), item);
    }
}
