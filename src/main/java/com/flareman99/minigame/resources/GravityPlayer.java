package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GravityPlayer {

    private Map currentMap;
    private boolean inLobby = true;
    Player player;

    public GravityPlayer(Player player) {
        this.player = player;

    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public boolean isInLobby() {
        return inLobby;
    }

    public void setCurrentMap(Map map) {
        currentMap = map;
    }

    public void setInLobby(boolean inLobby) {
        this.inLobby = inLobby;
    }

    public void setSpawnPoint() {
        if(inLobby) {
            Location loc = Main.gravity.lobby;
            player.setBedSpawnLocation( Main.gravity.lobby );
        }else{
            player.setBedSpawnLocation(Util.coordToLocation(currentMap.spawn));
        }
    }



}