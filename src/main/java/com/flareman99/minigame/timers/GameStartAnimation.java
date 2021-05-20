package com.flareman99.minigame.timers;

import com.flareman99.minigame.Main;
import dev.lone.itemsadder.api.ItemsAdder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public class GameStartAnimation extends BukkitRunnable {

    Main plugin;

    private int j = 5;
    private Set<Player> players;
    private final long DELAY = 20L;

    public GameStartAnimation(Main plugin, Set<Player> players) {
        this.plugin = plugin;
        this.players = players;

        for(j = 0; j <= 5; j++) {
            this.runTaskLater(plugin, (long) (j * DELAY));
        }
    }
    @Override
    public void run() {
        players.forEach(player -> ItemsAdder.playTotemAnimation(
                player, "animecraft:tenshi"));

        j--;
    }
}
