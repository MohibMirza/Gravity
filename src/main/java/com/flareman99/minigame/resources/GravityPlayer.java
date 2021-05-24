package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GravityPlayer {

    private Map currentMap;
    private boolean inLobby = true;
    Player player;

    private int earnings = 0;

    private long startTime = 0L;

    public GravityPlayer(Player player) {
        this.player = player;
        Gravity.players.put(player.getName(), this);


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

    public Location getSpawnPoint() {
        if(currentMap == null) {
            Location loc = Main.gravity.lobby;
            return Main.gravity.lobby;
        }else{
            return Util.coordToLocation(currentMap.spawn);
        }

    }

    public void addEarnings(int gems) {
        earnings += gems;
    }

    public double clockLap(long endTime) {
        return ((double)((endTime - startTime) / 1000L));
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


}