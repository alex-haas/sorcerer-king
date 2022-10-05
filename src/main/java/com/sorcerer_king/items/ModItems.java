package com.sorcerer_king.items;

import com.sorcerer_king.Constants;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static void registerModItems() {
        registerManaCube();
    }

    private static void registerManaCube() {
        registerItem("mana_cube", new ManaCube());
    }

    private static Item registerItem(String itemName, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, itemName), item);
    }
}
