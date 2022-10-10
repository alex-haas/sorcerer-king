package com.sorcerer_king.common.components;

import com.sorcerer_king.server.PlayerSaveData;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

public interface ModPlayerComponent extends ComponentV3 {
    int getTier();
    double getMaxMana();
    double getPassiveManaReg();
    double getCurrentMana();
    PlayerEntity getPlayer();

    PlayerSaveData getSaveData();

    void applySaveData(@Nullable PlayerSaveData saveData);

    void addMana(double i);

    void setTier(int newTier);
    String getTierTitle();
}
