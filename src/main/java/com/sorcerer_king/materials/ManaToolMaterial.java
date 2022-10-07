package com.sorcerer_king.materials;

import com.sorcerer_king.items.ManaGem;
import com.sorcerer_king.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class ManaToolMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 256;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1;
    }

    @Override
    public float getAttackDamage() {
        return 1;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofStacks(new ItemStack(ModItems.get(ManaGem.ID)));
    }
}
