package com.flareman99.minigame.events;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Gravity;
import com.flareman99.minigame.resources.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Respawn implements Listener {

    @EventHandler
    public void onDeath(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.setBedSpawnLocation(new Location(Bukkit.getWorld("world"), 0, 0, 0));
        player.sendMessage("test!");
        event.setRespawnLocation(Gravity.players.get(player.getName()).getSpawnPoint());

    }
}
