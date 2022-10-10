package com.sorcerer_king.items;

import com.sorcerer_king.common.components.ModComponents;
import com.sorcerer_king.common.components.ModPlayerComponent;
import com.sorcerer_king.item_groups.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ManaGem extends Item {
    public static final String ID = "mana_gem";

    public ManaGem() {
        super(new FabricItemSettings()
                .group(ItemGroups.MAIN)
                .rarity(Rarity.COMMON)
                .maxCount(64)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ModPlayerComponent modPlayer = ModComponents.PLAYER.get(playerEntity);
        if (modPlayer.getTier() == 0) {
            if (world.isClient()) {
                playerEntity.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            }
            modPlayer.setTier(1);
        }
        else {
            if (world.isClient()) {
                playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
            }
            modPlayer.addMana(10);
        }
        playerEntity.getStackInHand(hand).decrement(1);
        return TypedActionResult.consume(playerEntity.getStackInHand(hand));
    }
}
