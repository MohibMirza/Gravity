package com.flareman99.minigame.events;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Gravity;
import com.flareman99.minigame.resources.GravityPlayer;
import com.flareman99.minigame.resources.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

public class JoinLeave implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String name = event.getPlayer().getName();
        GravityPlayer gplayer = new GravityPlayer(name);
        Gravity.players.put(name, gplayer);
        player.teleport(Main.gravity.location);

        int playerCount = Bukkit.getOnlinePlayers().size()-1;

        if(playerCount == 0) {
            // START QUEUE TIMER
            BukkitTask timer = new Timer(Main.getPlugin()).runTaskTimer(Main.getPlugin(), 0, 20L);
        }
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        Gravity.players.remove(event.getPlayer().getName());
        System.out.println(Bukkit.getOnlinePlayers().size());

        int playerCount = Bukkit.getOnlinePlayers().size()-1;
        // STOP QUEUE TIMER OR STOP GAME TIMER

    }
}
