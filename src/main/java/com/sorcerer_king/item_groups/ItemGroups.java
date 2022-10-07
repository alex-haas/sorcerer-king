package com.sorcerer_king.item_groups;

import com.sorcerer_king.Globals;
import com.sorcerer_king.blocks.ModBlocks;
import com.sorcerer_king.items.ManaCube;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemGroups {
    public static final ItemGroup MAIN = FabricItemGroupBuilder.build(
            new Identifier(Globals.MOD_ID, "main_group"),
            () -> new ItemStack(ModBlocks.get(ManaCube.ID))
    );
}
