package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;

public class GravityPlayer {

    String username;

    Map currentMap;

    public GravityPlayer(String username) {
        currentMap = Main.gravity.activeMaps.get(0);
    }

}