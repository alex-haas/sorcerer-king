package com.sorcerer_king.server;

public class PlayerSaveData {
    private int tier;
    private double currentMana;

    public PlayerSaveData(int tier, double currentMana) {
        this.tier = tier;
        this.currentMana = currentMana;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(double currentMana) {
        this.currentMana = currentMana;
    }
}