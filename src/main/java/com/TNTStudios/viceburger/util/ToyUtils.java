package com.TNTStudios.viceburger.util;

import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

public class ToyUtils {

    /**
     * Devuelve un nombre formateado bonito para mostrar en mensajes, UI, etc.
     */
    public static Text getDisplayName(Identifier toyId) {
        String path = toyId.getPath();
        String capitalized = Character.toUpperCase(path.charAt(0)) + path.substring(1);
        return Text.literal("🎁 Juguete: " + capitalized);
    }

    /**
     * Devuelve el Item del juguete desde su ID
     */
    public static Item getToyItem(Identifier id) {
        return Registries.ITEM.get(id);
    }

    /**
     * Verifica si un ID pertenece a un juguete de ViceBurger
     */
    public static boolean isToy(Identifier id) {
        return id.getNamespace().equals("viceburger") &&
                switch (id.getPath()) {
                    case "abeja", "chaneke", "huevo", "rana", "tani" -> true;
                    default -> false;
                };
    }
}
