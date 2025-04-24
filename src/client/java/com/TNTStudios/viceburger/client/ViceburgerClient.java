package com.TNTStudios.viceburger.client;

import com.TNTStudios.viceburger.registry.ViceburgerBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ViceburgerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Registra tu bloque "happymeal" para que use el canal translucent
        BlockRenderLayerMap.INSTANCE.putBlock(
                ViceburgerBlocks.HAPPYMEAL,
                RenderLayer.getCutout()
        );
    }
}
