package com.sorcerer_king.blocks;

import com.sorcerer_king.Globals;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class ModBlocks {
    private static final HashMap<String, Block> blocks = new HashMap<>();

    public static void registerModBlocks() {
        register(ManaOre.ID, new ManaOre());
        register(ManaBlock.ID, new ManaBlock());
    }

    private static void register(String blockName, Block block) {
        blocks.put(blockName, block);
        Registry.register(Registry.BLOCK, new Identifier(Globals.MOD_ID, blockName), block);
    }

    public static Block get(String itemName) {
        return blocks.get(itemName);
    }

}
