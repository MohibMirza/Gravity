package com.flareman99.minigame.events;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Coordinate;
import com.flareman99.minigame.resources.Gravity;
import com.flareman99.minigame.resources.Portal;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PortalTP implements Listener {

    @EventHandler
    public void enterPortal(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        Location loc = player.getLocation();

        for(Portal portal : Main.gravity.activePortals) {

            Coordinate pos1 = portal.getPos1();
            Coordinate pos2 = portal.getPos2();

            double lesserX, greaterX, lesserY, greaterY, lesserZ, greaterZ;

            if(pos1.locX < pos2.locX) {
                lesserX = pos1.locX;
                greaterX = pos2.locX;
            }else{
                lesserX = pos2.locX;
                greaterX = pos1.locX;
            }

            if( !(loc.getBlockX() >= lesserX && loc.getBlockX() <= greaterX) ) continue;

            if(pos1.locY < pos2.locY) {
                lesserY = pos1.locY;
                greaterY = pos2.locY;
            }else {
                lesserY = pos2.locY;
                greaterY = pos1.locY;
            }

            if( !(loc.getBlockY() >= lesserY && loc.getBlockY() <= greaterY) ) continue;

            if(pos1.locZ < pos2.locZ) {
                lesserZ = pos1.locZ;
                greaterZ = pos2.locZ;
            }else{
                lesserZ = pos2.locZ;
                greaterZ = pos1.locZ;
            }

            if( !(loc.getBlockZ() >= lesserZ && loc.getBlockZ() <= greaterZ) ) continue;

            Coordinate dest = portal.getDest();

            player.teleport(new Location(player.getWorld(), dest.locX, dest.locY, dest.locZ));
            Gravity.players.get(player.getName()).setCurrentMap(portal.getDestMap());

            if(portal.getFinish()) {

                player.sendMessage("Congratulations! You have finished the course!");

            }
        }

    }



}
