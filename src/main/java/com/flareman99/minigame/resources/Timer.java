package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Timer extends BukkitRunnable {

    Main plugin;

    public static int timer = 120;

    public Timer(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        System.out.println(timer);
        timer--;
    }
}
