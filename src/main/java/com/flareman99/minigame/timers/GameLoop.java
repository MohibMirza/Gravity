package com.flareman99.minigame.timers;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Gravity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameLoop extends BukkitRunnable {

    // TODO: MAKE WINNER LIST WORK IN GRAVITY.JAVA + IF SOMEONE LEAVES THEY ARE REMOVED FROM THE WINNER LIST BEFORE GAME LOOP SCANS FOR IT
    // TODO: DO ALL COMMENTED ITEMS IN THIS FILE

    enum GameState {
        IDLE,
        WAITING,
        STARTED,
        END,
        CANCELLED
    }

    Main plugin;
    public int timer = 0;
    Gravity gravity;
    private GameState state;

    public GameLoop(Main plugin, Gravity gravity) {
        this.plugin = plugin;
        this.gravity = gravity;
        state = GameState.IDLE;

    }

    @Override
    public void run() {
        int playerCount = Bukkit.getOnlinePlayers().size();
        if(state == GameState.IDLE) {
            System.out.println("IDLE STATE");
            if(playerCount > 0) {
                state = GameState.WAITING;
                timer = Main.MAX_LOBBY_WAIT_TIME;

                // DO SQL GAME SELECTOR FUNCS TO ALLOW PLAYERS TO JOIN IN
            }
        }

        if(state == GameState.WAITING) {
            System.out.println("WAITING STATE" + timer);
            timer--;

            // UPDATE PLAYERS HUDS HERE

           if(timer == 0 || playerCount == Main.MAX_PLAYERS) {
                state = GameState.STARTED;
                timer = Main.MAX_GAME_TIME;

                // REMOVE ROOM FROM SQL GAME SELECTOR LIST
            }

            if(playerCount == 0) {
                state = GameState.CANCELLED;
            }
        }

        if(state == GameState.STARTED) {
            System.out.println("STARTED STATE " + timer);
            timer--;

            // UPDATE PLAYER HUDS HERE

            if(timer == 0 || gravity.winners.size() == playerCount) {
                state = GameState.END;
            }

            if(playerCount == 0){
                state = GameState.CANCELLED;
            }


        }

        if(state == GameState.END) {
            System.out.println("END STATE");

            // SQL SAVE PRIZES
            // PLAY SOME PARTICLE ANIMATION FOR PLAYER

            // RELOOP AND PLAY THE NEXT GAME ONCE DONE!

            Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(""));
            state = GameState.IDLE;
        }

        if(state == GameState.CANCELLED) {
            System.out.println("CANCELLED STATE");

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
