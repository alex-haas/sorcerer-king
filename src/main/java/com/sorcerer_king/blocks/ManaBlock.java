package com.sorcerer_king.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class ManaBlock extends Block {
    public static final String ID = "mana_block";

    public ManaBlock() {
        super(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    }
}
