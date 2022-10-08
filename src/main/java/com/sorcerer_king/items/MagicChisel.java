package com.sorcerer_king.items;

import com.sorcerer_king.common.Globals;
import com.sorcerer_king.blocks.ManaBlock;
import com.sorcerer_king.blocks.ModBlocks;
import com.sorcerer_king.item_groups.ItemGroups;
import com.sorcerer_king.materials.ModMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MagicChisel extends ToolItem {
    public static final String ID = "magic_chisel";
    public MagicChisel() {
        super(ModMaterials.MANA_MATERIAL, new FabricItemSettings()
                .group(ItemGroups.MAIN)
                .rarity(Rarity.COMMON)
                .maxCount(1)
                .maxDamage(64)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, 1.0F, 1.0F);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockState clickedBlockState = context.getWorld().getBlockState(context.getBlockPos());
            if (context.getPlayer() != null && clickedBlockState != null && clickedBlockState.getBlock() instanceof ManaBlock) {
                Globals.LOGGER.info("You right clicked a magic cube with a magic chisel.");
                context.getWorld().removeBlock(context.getBlockPos(), false);
                ItemStack manaCube = new ItemStack(ModBlocks.get(ManaCube.ID));
                context.getPlayer().giveItemStack(manaCube);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
