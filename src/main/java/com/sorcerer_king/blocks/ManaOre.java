package com.sorcerer_king.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class ManaOre extends Block {
    public static final String ID = "mana_ore";

    public ManaOre() {
        super(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    }
}
