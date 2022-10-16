package com.sorcerer_king.server;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.WorldSavePath;

import java.nio.file.Path;

public class ServerEventListener {
    private static final ServerEventListener instance = new ServerEventListener();

    private ServerEventListener() {}

    public static ServerEventListener getInstance() {
        return instance;
    }

    public void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(this::onServerStart);
        ServerPlayConnectionEvents.JOIN.register(this::onPlayerJoin);
        ServerPlayConnectionEvents.DISCONNECT.register(this::onPlayerLeave);
        ServerLifecycleEvents.SERVER_STOPPING.register(this::onServerStop);
    }

    private void onServerStart(MinecraftServer server) {
        Path playerDataPath = server.getSavePath(WorldSavePath.ROOT).resolve("sorcerer-king/playerdata");
        FileDataStorage.getInstance().setPlayerDataPath(playerDataPath);
    }

    private void onServerStop(MinecraftServer server) {
        // todo: check if just closing the server will trigger the disconnect action - else implement a save here
    }

    private void onPlayerLeave(ServerPlayNetworkHandler handler, MinecraftServer server) {
        try {
            PlayerManager.getInstance().playerLeft(handler.getPlayer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        try {
            PlayerManager.getInstance().playerJoined(handler.getPlayer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}