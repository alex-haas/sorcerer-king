package com.sorcerer_king.common.components;

import com.sorcerer_king.server.PlayerSaveData;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.apache.commons.lang3.NotImplementedException;

public class ModPlayer implements ModPlayerComponent, CommonTickingComponent, AutoSyncedComponent {
    public static final String KEY_TIER = "tier";
    public static final String KEY_CURRENT_MANA = "current_mana";
    public static final String KEY_MAX_MANA = "max_mana";
    public static final String KEY_PASSIVE_MANA_REG = "passive_mana_reg";

    private int tier;
    private double currentMana;
    private double maxMana;
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

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public PlayerSaveData getSaveData() {
        return new PlayerSaveData(tier, currentMana);
    }

    @Override
    public void applySaveData(PlayerSaveData saveData) {
        if (saveData == null) {
            tier = 0;
        } else {
            tier = saveData.getTier();
            currentMana = saveData.getCurrentMana();
        }
        reloadDataForTier();
    }

    @Override
    public void addMana(double mana) {
        currentMana += mana;
        if (currentMana > maxMana) {
            currentMana = maxMana;
        }
    }

    @Override
    public void setTier(int newTier) {
        tier = newTier;
        reloadDataForTier();
    }

    @Override
    public String getTierTitle() {
        switch (tier) {
            case 0: return "Commoner";
            case 1: return "Apprentice";
            case 2: return "Intermediate";
            case 3: return "Scholar";
            case 4: return "High Mage";
            case 5: return "Arch-Wizard";
            case 6: return "Sage";
            case 7: return "Grand Master";
            case 8: return "Demi-God";
            case 9: return "Sorcerer King";
            default: throw new NotImplementedException();
        }
    }

    private void reloadDataForTier() {
        switch (tier) {
            case 0 -> {
                maxMana = 0;
                passiveManaReg = 0;
            }
            case 1 -> {
                maxMana = 10;
                passiveManaReg = 0.01;
            }
            case 2 -> {
                maxMana = 40;
                passiveManaReg = 0.03;
            }
            case 3 -> {
                maxMana = 160;
                passiveManaReg = 0.08;
            }
            case 4 -> {
                maxMana = 640;
                passiveManaReg = 0.15;
            }
            case 5 -> {
                maxMana = 2_400;
                passiveManaReg = 0.30;
            }
            case 6 -> {
                maxMana = 8_000;
                passiveManaReg = 0.60;
            }
            case 7 -> {
                maxMana = 32_000;
                passiveManaReg = 1.25;
            }
            case 8 -> {
                maxMana = 128_000;
                passiveManaReg = 2.50;
            }
            case 9 -> {
                maxMana = 512_000;
                passiveManaReg = 5.00;
            }
        }
        if (currentMana > maxMana) {
            currentMana = maxMana;
        }
        ModComponents.PLAYER.sync(this.player);
    }
}