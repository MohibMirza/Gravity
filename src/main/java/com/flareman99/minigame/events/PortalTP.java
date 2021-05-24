package com.flareman99.minigame.events;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Coordinate;
import com.flareman99.minigame.resources.Gravity;
import com.flareman99.minigame.resources.GravityPlayer;
import com.flareman99.minigame.resources.Portal;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PortalTP implements Listener {

    // TO DO: Make a Custom Event Portal TP
    // TO DO: Add fireworks if finish portal tp
    // TO DO: Money system

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




            // IF PORTAL IS DETECTED //

            Coordinate dest = portal.getDest();

            player.teleport(new Location(player.getWorld(), dest.locX, dest.locY, dest.locZ));
            player.setHealth(20);
            GravityPlayer gPlayer = Gravity.players.get(player.getName());

            stageRewards(gPlayer, player);

            gPlayer.setCurrentMap(portal.getDestMap());



            if(portal.getFinish()) {
                Main.gravity.winners.add(player.getName());
                player.sendMessage("Congratulations! You have finished the course!");

            }
        }

    }


    public static void stageRewards(GravityPlayer gPlayer, Player player) {
        long l = System.currentTimeMillis();

        double time = gPlayer.clockLap(l);
        String mapName = gPlayer.getCurrentMap().name;

        player.sendMessage(ChatColor.GOLD + "You have finished " + ChatColor.GREEN + mapName +
                ChatColor.GOLD + " in " +
                ChatColor.GREEN + time +
                ChatColor.GOLD + " seconds!");
        gPlayer.setStartTime(l);

    }





}
