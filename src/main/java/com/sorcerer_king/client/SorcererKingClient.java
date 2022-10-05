package com.sorcerer_king.client;

import com.sorcerer_king.Constants;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class SorcererKingClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(Constants.MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello Fabric world from Client!");
    }
}
