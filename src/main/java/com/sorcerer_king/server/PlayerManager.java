package com.sorcerer_king.server;

import com.sorcerer_king.common.Globals;
import net.minecraft.server.network.ServerPlayerEntity;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static final PlayerManager instance = new PlayerManager();
    private Map<String, ServerPlayerEntity> playerMap = new HashMap<>();
    private Path storagePath;

    private PlayerManager() {}

    public static PlayerManager getInstance() {
        return instance;
    }

    public void playerJoined(ServerPlayerEntity player) {
        Globals.LOGGER.info("PlayerManager -> player joined: " + player.getUuid().toString());
        playerMap.put(player.getUuid().toString(), player);
        // todo: check storage if data is present for player
    }

    public void playerLeft(ServerPlayerEntity player) {
        Globals.LOGGER.info("PlayerManager -> player left: " + player.getUuid().toString());
        playerMap.remove(player.getUuid().toString());
    }

    public void reload() {
        playerMap.clear();
    }

    public void init(Path storagePath) {
        this.storagePath = storagePath;
        Globals.LOGGER.info("PlayerManager -> init");
    }
}
