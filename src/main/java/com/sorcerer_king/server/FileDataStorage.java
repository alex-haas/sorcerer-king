package com.sorcerer_king.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sorcerer_king.common.components.ModPlayerComponent;
import org.jetbrains.annotations.Nullable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataStorage {
    private static final FileDataStorage instance = new FileDataStorage();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private Path playerDataPath;

    private FileDataStorage () {

    }

    public static FileDataStorage getInstance() {
        return instance;
    }

    private <T> void write(Path path, Class<T> classOfT, T data) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(data, classOfT, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> @Nullable T read(Path path, Class<T> classOfT) {
        try (FileReader reader = new FileReader(path.toFile())) {
            return gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public @Nullable PlayerSaveData readPlayerData(String playerUuid) {
        Path fullPath = playerDataPath.resolve(playerUuid + ".json");
        if (Files.exists(fullPath)) {
            return read(fullPath, PlayerSaveData.class);
        }
        return null;
    }

    public void savePlayer(ModPlayerComponent modPlayer) {
        Path fullPath = playerDataPath.resolve(modPlayer.getPlayer().getUuidAsString() + ".json");
        PlayerSaveData saveData = modPlayer.getSaveData();
        write(fullPath, PlayerSaveData.class, saveData);
    }

    public void setPlayerDataPath(Path playerDataPath) {
        this.playerDataPath = playerDataPath;
        try {
            Files.createDirectories(this.playerDataPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
