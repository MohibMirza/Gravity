package com.flareman99.minigame.events;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Gravity;
import com.flareman99.minigame.resources.GravityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        String name = event.getPlayer().getName();
        GravityPlayer player = new GravityPlayer(name);
        Gravity.players.put(name, player);


    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {



    }
}
