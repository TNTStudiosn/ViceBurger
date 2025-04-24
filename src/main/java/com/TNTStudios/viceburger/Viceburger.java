package com.TNTStudios.viceburger;

import com.TNTStudios.viceburger.commands.ReloadConfigCommand;
import com.TNTStudios.viceburger.config.ToyConfig;
import com.TNTStudios.viceburger.registry.ViceburgerBlocks;
import com.TNTStudios.viceburger.registry.ViceburgerItems;
import com.TNTStudios.viceburger.registry.ViceburgerTabs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Viceburger implements ModInitializer {

    @Override
    public void onInitialize() {
        // Registrar ítems y bloques
        ViceburgerItems.registerAll();
        ViceburgerBlocks.registerAll();

        ViceburgerTabs.register();
        ToyConfig.load();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ReloadConfigCommand.register(dispatcher);
        });
    }
}
