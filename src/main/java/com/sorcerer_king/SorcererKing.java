package com.sorcerer_king;

import com.sorcerer_king.biome_mods.ModBiomeModifications;
import com.sorcerer_king.blocks.ModBlocks;
import com.sorcerer_king.common.Globals;
import com.sorcerer_king.common.spells.ModSpells;
import com.sorcerer_king.items.ModItems;
import net.fabricmc.api.ModInitializer;


public class SorcererKing implements ModInitializer {
    @Override
    public void onInitialize() {
        Globals.LOGGER.info("Hello Fabric world from Server!");
        ModSpells.registerModSpells();
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModBiomeModifications.registerBiomeModifications();
    }
}
