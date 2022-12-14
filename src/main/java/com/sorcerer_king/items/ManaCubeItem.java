package com.sorcerer_king.items;

import com.sorcerer_king.blocks.ManaCubeBlock;
import com.sorcerer_king.blocks.ModBlocks;
import com.sorcerer_king.common.components.ModComponents;
import com.sorcerer_king.common.components.ModPlayerComponent;
import com.sorcerer_king.guis.ModScreen;
import com.sorcerer_king.guis.SpellConfigGui;
import com.sorcerer_king.item_groups.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ManaCubeItem extends BlockItem {
    public static final String ID = "mana_cube";

    public ManaCubeItem() {
        super(ModBlocks.get(ManaCubeBlock.ID), new FabricItemSettings()
                .group(ItemGroups.MAIN)
                .rarity(Rarity.RARE)
                .maxCount(1)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.isClient()) {
            if (playerEntity.isSneaking()) {
                playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
                ModPlayerComponent modPlayer = ModComponents.PLAYER.get(playerEntity);
                MinecraftClient.getInstance().setScreen(new ModScreen(new SpellConfigGui(modPlayer)));
            }
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}