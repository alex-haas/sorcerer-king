package com.sorcerer_king.server;

import com.sorcerer_king.common.Globals;
import com.sorcerer_king.common.components.ModComponents;
import com.sorcerer_king.common.components.ModPlayerComponent;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerManager {
    private static final PlayerManager instance = new PlayerManager();

    private PlayerManager() {}

    public static PlayerManager getInstance() {
        return instance;
    }

    public void playerJoined(ServerPlayerEntity player) {
        Globals.LOGGER.info("PlayerManager -> player joined: " + player.getUuidAsString());
        PlayerSaveData saveData = FileDataStorage.getInstance().readPlayerData(player.getUuidAsString());
        ModPlayerComponent modPlayer = ModComponents.PLAYER.get(player);
        modPlayer.applySaveData(saveData);
    }

    public void playerLeft(ServerPlayerEntity player) {
        Globals.LOGGER.info("PlayerManager -> player left: " + player.getUuidAsString());
        ModPlayerComponent modPlayer = ModComponents.PLAYER.get(player);
        FileDataStorage.getInstance().savePlayer(modPlayer);
    }
}
