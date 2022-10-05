package com.sorcerer_king.client;

import com.sorcerer_king.Constants;
import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class SorcererKingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Constants.LOGGER.info("Hello Fabric world from Client!");
    }
}
