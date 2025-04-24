package com.TNTStudios.viceburger.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

public class ViceburgerTabs {

    public static final RegistryKey<ItemGroup> VICEBURGER_TAB = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            new Identifier("viceburger", "main")
    );

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, VICEBURGER_TAB,
                FabricItemGroup.builder()
                        .displayName(Text.literal("ViceBurger"))
                        .icon(() -> new ItemStack(ViceburgerBlocks.HAPPYMEAL))
                        .entries((context, entries) -> {
                            // Bloque principal
                            entries.add(ViceburgerBlocks.HAPPYMEAL);
                            // Ahora tus juguetes como bloques:
                            entries.add(ViceburgerBlocks.ABEJA_BLOCK);
                            entries.add(ViceburgerBlocks.CHANEKE_BLOCK);
                            entries.add(ViceburgerBlocks.HUEVO_BLOCK);
                            entries.add(ViceburgerBlocks.RANA_BLOCK);
                            entries.add(ViceburgerBlocks.TANI_BLOCK);
                        })
                        .build()
        );
    }
}
