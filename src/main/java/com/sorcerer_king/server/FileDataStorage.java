package com.sorcerer_king.server;

public class FileDataStorage {
    private static final FileDataStorage instance = new FileDataStorage();

    private FileDataStorage () {

    }

    public static FileDataStorage getInstance() {
        return instance;
    }
}
