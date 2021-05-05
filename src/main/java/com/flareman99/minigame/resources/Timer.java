package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Timer extends BukkitRunnable {

    Main plugin;

    public static int timer = 5;

    public Timer(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        System.out.println(timer);
        timer--;

        if(timer == 0 || Main.gravity.state == Gravity.GameState.STARTED) {
            this.cancel();
            Coordinate firstMap = Main.gravity.activeMaps.get(0).spawn;
            Location loc = new Location(Bukkit.getWorld("world"), firstMap.locX, firstMap.locY, firstMap.locZ);
            Bukkit.getOnlinePlayers().forEach(player -> { player.teleport(loc); });
        }
    }
}
