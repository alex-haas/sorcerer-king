package com.sorcerer_king.common.components;

import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class ModPlayer implements ModPlayerComponent, CommonTickingComponent {
    public static final String KEY_TIER = "tier";
    public static final String KEY_CURRENT_MANA = "current_mana";
    public static final String KEY_MAX_MANA = "max_mana";
    public static final String KEY_PASSIVE_MANA_REG = "passive_mana_reg";

    private int tier;
    private double currentMana;
    private double maxMana = 1000;
    private double passiveManaReg = 0.01;

    private final PlayerEntity player;

    public ModPlayer(PlayerEntity provider) {
        this.player = provider;
    }

    @Override
    public void readFromNbt(NbtCompound nbt) {
        tier = nbt.getInt(KEY_TIER);
        currentMana = nbt.getDouble(KEY_CURRENT_MANA);
        maxMana = nbt.getDouble(KEY_MAX_MANA);
        passiveManaReg = nbt.getDouble(KEY_PASSIVE_MANA_REG);
    }

    @Override
    public void writeToNbt(NbtCompound nbt) {
        nbt.putInt(KEY_TIER, tier);
        nbt.putDouble(KEY_CURRENT_MANA, currentMana);
        nbt.putDouble(KEY_MAX_MANA, maxMana);
        nbt.putDouble(KEY_PASSIVE_MANA_REG, passiveManaReg);
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public double getMaxMana() {
        return maxMana;
    }

    @Override
    public double getPassiveManaReg() {
        return passiveManaReg;
    }

    @Override
    public double getCurrentMana() {
        return currentMana;
    }

    @Override
    public void tick() {
        currentMana += passiveManaReg;
        if (currentMana > maxMana) {
            currentMana = maxMana;
        }
    }
}
