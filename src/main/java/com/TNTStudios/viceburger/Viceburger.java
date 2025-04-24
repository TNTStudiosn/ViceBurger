package com.TNTStudios.viceburger;

import com.TNTStudios.viceburger.commands.ReloadConfigCommand;
import com.TNTStudios.viceburger.config.ToyConfig;
import com.TNTStudios.viceburger.registry.ViceburgerTabs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Viceburger implements ModInitializer {

    @Override
    public void onInitialize() {
        ViceburgerTabs.register();

        // Cargar configuración al iniciar el mod
        ToyConfig.load();

        // Registrar el comando /ViceBurger reload
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ReloadConfigCommand.register(dispatcher);
        });
    }
}
