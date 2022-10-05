package com.sorcerer_king;

import com.sorcerer_king.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SorcererKing implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(Constants.MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world from Server!");
        ModItems.registerModItems();
    }
}
