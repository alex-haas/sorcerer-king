package com.sorcerer_king.items;

import com.sorcerer_king.Constants;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class ModItems {
    private static final HashMap<String, Item> items = new HashMap<>();

    public static void registerModItems() {
        register(ManaCube.ID, new ManaCube());
        register(MagicChisel.ID, new MagicChisel());
    }

    private static Item register(String itemName, Item item) {
        items.put(itemName, item);
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, itemName), item);
    }

    public static Item get(String itemName) {
        return items.get(itemName);
    }
}
