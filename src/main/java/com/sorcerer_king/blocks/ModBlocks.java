package com.sorcerer_king.blocks;

import com.sorcerer_king.Constants;
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

    private static Block register(String blockName, Block block) {
        blocks.put(blockName, block);
        return Registry.register(Registry.BLOCK, new Identifier(Constants.MOD_ID, blockName), block);
    }

    public static Block get(String itemName) {
        return blocks.get(itemName);
    }

}
