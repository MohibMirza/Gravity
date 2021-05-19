package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LobbyTimer extends BukkitRunnable {

    // TODO: MAKE WINNER LIST WORK IN GRAVITY.JAVA + IF SOMEONE LEAVES THEY ARE REMOVED FROM THE WINNER LIST BEFORE GAME LOOP SCANS FOR IT

    enum GameState {
        IDLE,
        WAITING,
        READY,
        STARTED,
        END,
        CANCELLED
    }

    Main plugin;
    public int timer = 0;
    Gravity gravity;
    private GameState state;

    public LobbyTimer(Main plugin, Gravity gravity) {
        this.plugin = plugin;
        this.gravity = gravity;
        state = GameState.IDLE;

    }

    @Override
    public void run() {
        int playerCount = Bukkit.getOnlinePlayers().size();
        if(state == GameState.IDLE) {
            if(playerCount > 0) {
                state = GameState.WAITING;
                timer = Main.MAX_LOBBY_WAIT_TIME;
            }
        }

        if(state == GameState.WAITING) {
            timer--;

            // UPDATE PLAYERS HUDS HERE

           if(timer == 0 || playerCount == Main.MAX_PLAYERS) {
                state = GameState.READY;
                timer = Main.MAX_GAME_TIME;
            }

            if(playerCount == 0) {
                state = GameState.CANCELLED;
            }
        }

        if(state == GameState.READY) {
            timer--;

            // UPDATE PLAYER HUDS HERE

            if(timer == 0 || gravity.winners.size() == playerCount) {
                state = GameState.END;
            }

            if(playerCount == 0){
                state = GameState.CANCELLED;
            }


        }

        if(state == GameState.CANCELLED) {
            state = GameState.IDLE;

            // do cancellation stuff here
        }








    }

/*    @Override
    public void run() {
        System.out.println(timer);
        timer--;

        if(timer == 0 || Main.gravity.state == Gravity.GameState.STARTED) {
            this.cancel();
            Main.gravity.reset();
            Coordinate firstMap = Main.gravity.activeMaps.get(0).spawn;
            Location loc = new Location(Bukkit.getWorld("world"), firstMap.locX, firstMap.locY, firstMap.locZ);
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.teleport(loc);

                GravityPlayer gPlayer = Gravity.players.get(player.getName());
                gPlayer.setCurrentMap(Main.gravity.activeMaps.get(0));
                gPlayer.setInLobby(false);
            });
        }
    }*/
}
