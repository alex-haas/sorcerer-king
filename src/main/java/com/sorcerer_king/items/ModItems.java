package com.sorcerer_king.items;

import com.sorcerer_king.Constants;
import com.sorcerer_king.blocks.ManaBlock;
import com.sorcerer_king.blocks.ManaOre;
import com.sorcerer_king.blocks.ModBlocks;
import com.sorcerer_king.item_groups.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class ModItems {
    private static final HashMap<String, Item> items = new HashMap<>();

    public static void registerModItems() {
        register(ManaCube.ID, new ManaCube());
        register(MagicChisel.ID, new MagicChisel());
        register(MagicGem.ID, new MagicGem());
        registerBlockItem(ManaOre.ID);
        registerBlockItem(ManaBlock.ID);
    }

    private static void register(String itemName, Item item) {
        items.put(itemName, item);
        Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, itemName), item);
    }

    private static void registerBlockItem(String blockName) {
        Block block = ModBlocks.get(blockName);
        register(blockName, new BlockItem(block, new FabricItemSettings().group(ItemGroups.MAIN)));
    }

    public static Item get(String itemName) {
        return items.get(itemName);
    }
}
