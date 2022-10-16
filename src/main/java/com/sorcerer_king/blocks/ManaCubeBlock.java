package com.sorcerer_king.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class ManaCubeBlock extends Block {
    public static final String ID = "mana_cube";
    
    public ManaCubeBlock() {
        super(FabricBlockSettings.of(Material.METAL).strength(1.0f));
    }
}