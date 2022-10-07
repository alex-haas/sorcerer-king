package com.sorcerer_king.items;

import com.sorcerer_king.guis.SpellConfigGui;
import com.sorcerer_king.guis.SpellConfigScreen;
import com.sorcerer_king.item_groups.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ManaCube extends Item {
    public static final String ID = "mana_cube";

    public ManaCube() {
        super(new FabricItemSettings()
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
                MinecraftClient.getInstance().setScreen(new SpellConfigScreen(new SpellConfigGui()));
            }
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
