package com.sorcerer_king.items;

import com.sorcerer_king.Constants;
import com.sorcerer_king.item_groups.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MagicChisel extends Item {
    public static final String ID = "magic_chisel";

    public MagicChisel() {
        super(new FabricItemSettings()
                .group(ItemGroups.MAIN)
                .rarity(Rarity.COMMON)
                .maxCount(1)
                .maxDamage(3)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, 1.0F, 1.0F);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getStack().getItem().equals(ModItems.get("magic_cube"))) {
            Constants.LOGGER.info("You right clicked a magic cube with a magic chisel.");
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
