package com.flareman99.minigame.timers;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Gravity;
import com.flareman99.minigame.resources.HUD;
import dev.lone.itemsadder.api.ItemsAdder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class GameLoop extends BukkitRunnable {

    // TODO: MAKE WINNER LIST WORK IN GRAVITY.JAVA + IF SOMEONE LEAVES THEY ARE REMOVED FROM THE WINNER LIST BEFORE GAME LOOP SCANS FOR IT
    // TODO: DO ALL COMMENTED ITEMS IN THIS FILE
    // TODO: SKIP FUNCTIONALITY
    // TODO: END FIREWORKS
    // TODO:

    public static final int MAX_PLAYERS = 8;
    public static final int MAX_LOBBY_WAIT_TIME = 20;
    public static final int READY_TIME = 15;
    public static final int MAX_GAME_TIME = 360;

    private float pitch = 0.6F;
    private float pitchDropoff = 0.005F;

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

    boolean readyFlag = false;

    public GameLoop(Main plugin, Gravity gravity) {
        this.plugin = plugin;
        this.gravity = gravity;
        state = GameState.IDLE;

    }

    @Override
    public void run() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        int playerCount = Bukkit.getOnlinePlayers().size();
        if(state == GameState.IDLE) {
            System.out.println("IDLE STATE");
            if(playerCount > 0) {
                state = GameState.WAITING;
                timer = MAX_LOBBY_WAIT_TIME;

                // DO SQL GAME SELECTOR FUNCS TO ALLOW PLAYERS TO JOIN IN
            }
        }else

        if(state == GameState.WAITING) {
            System.out.println("WAITING STATE" + timer);
            timer--;

            // UPDATE PLAYERS HUDS HERE
            waitingHUD(players);

           if(timer == 0 || playerCount == MAX_PLAYERS) {
                state = GameState.READY;
                timer = READY_TIME;

                // REMOVE ROOM FROM SQL GAME SELECTOR LIST
            }

            if(playerCount == 0) {
                state = GameState.CANCELLED;
            }
        }else

        if(state == GameState.READY) {
            // DO THE COUNTDOWN HERE
            countdown(players);
        }else

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

        }else

        if(state == GameState.END) {
            System.out.println("END STATE");

            // SQL SAVE PRIZES
            // PLAY SOME PARTICLE ANIMATION FOR PLAYER

            // RELOOP AND PLAY THE NEXT GAME ONCE DONE!

            Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(""));
            state = GameState.IDLE;

        }else

        if(state == GameState.CANCELLED) {
            System.out.println("CANCELLED STATE");

            state = GameState.IDLE;

            // do cancellation stuff here


        }





    }

    public void waitingHUD(Collection<? extends Player> players) {
        int playersNeeded = this.MAX_PLAYERS - players.size();

        if(playersNeeded < 1 || playersNeeded > 15) return;

        players.forEach(player -> {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(HUD.WAITING_ON[playersNeeded-1]));

        });
    }

    public void countdown(Collection<? extends Player> players) {


        players.forEach(player -> {

            if(timer <= 5) {
                ItemsAdder.playTotemAnimation(
                        player, "animecraft:countdown_" + timer);

                if (timer != 0) {
                    player.playNote(player.getLocation(), Instrument.CHIME,
                            Note.natural(1, Note.Tone.B));

                }
            }
            if(timer != 0) player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(HUD.COUNTDOWN[timer-1]));
        });


        timer--;
        pitch += pitchDropoff;

        if(timer < 0){
            players.forEach(player-> {
                state = GameState.STARTED;
                Main.gravity.reset();
                Main.gravity.start();
                timer = MAX_GAME_TIME;
                player.playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_DISAPPEARED,
                        0.7F, pitch);
            });
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
