package com.TNTStudios.viceburger.client;

import com.TNTStudios.viceburger.registry.ViceburgerBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ViceburgerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Usa blending real para transparencias suaves
        BlockRenderLayerMap.INSTANCE.putBlock(ViceburgerBlocks.HAPPYMEAL, RenderLayer.getCutout());

        // Si agregaste bloques para los juguetes, regístralos también
        BlockRenderLayerMap.INSTANCE.putBlock(ViceburgerBlocks.ABEJA_BLOCK,   RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ViceburgerBlocks.CHANEKE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ViceburgerBlocks.HUEVO_BLOCK,   RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ViceburgerBlocks.RANA_BLOCK,    RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ViceburgerBlocks.TANI_BLOCK,    RenderLayer.getCutout());
    }
}
