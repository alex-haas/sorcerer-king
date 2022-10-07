package com.sorcerer_king.items;

import com.sorcerer_king.item_groups.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class ManaGem extends Item {
    public static final String ID = "mana_gem";

    public ManaGem() {
        super(new FabricItemSettings()
                .group(ItemGroups.MAIN)
                .rarity(Rarity.COMMON)
                .maxCount(64)
        );
    }
}
