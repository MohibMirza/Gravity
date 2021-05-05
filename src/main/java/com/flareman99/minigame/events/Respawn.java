package com.flareman99.minigame.events;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Respawn implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();

        Main.gravity.players.get(player.getName()).setSpawnPoint();


    }
}
