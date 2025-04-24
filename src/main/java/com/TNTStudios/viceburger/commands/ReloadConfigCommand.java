package com.TNTStudios.viceburger.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.TNTStudios.viceburger.config.ToyConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ReloadConfigCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("ViceBurger")
                .requires(source -> source.hasPermissionLevel(4))
                .then(CommandManager.literal("reload")
                        .executes(context -> {
                            ToyConfig.load();
                            context.getSource().sendFeedback(() -> Text.literal("§a[ViceBurger] Configuración recargada correctamente."), true);
                            return 1;
                        })
                )
        );
    }
}
