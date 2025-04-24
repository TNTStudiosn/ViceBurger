package com.TNTStudios.viceburger.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
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
        Registry.register(Registries.ITEM_GROUP, VICEBURGER_TAB, ItemGroup.builder()
                .displayName(Text.literal("ViceBurger"))
                .icon(() -> new ItemStack(ViceburgerBlocks.HAPPYMEAL))
                .entries((context, entries) -> {
                    entries.add(ViceburgerBlocks.HAPPYMEAL);
                    entries.add(ViceburgerItems.ABEJA);
                    entries.add(ViceburgerItems.CHANEKE);
                    entries.add(ViceburgerItems.HUEVO);
                    entries.add(ViceburgerItems.RANA);
                    entries.add(ViceburgerItems.TANI);
                })
                .build()
        );
    }
}
