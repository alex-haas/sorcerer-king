package com.sorcerer_king.item_groups;

import com.sorcerer_king.Constants;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemGroups {
    public static final ItemGroup MAIN = FabricItemGroupBuilder.build(
            new Identifier(Constants.MOD_ID, "main_group"),
            () -> new ItemStack(Blocks.COBBLESTONE)
    );
}
