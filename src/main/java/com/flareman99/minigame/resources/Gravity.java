package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Gravity {



    public List<Portal> activePortals;
    public static java.util.Map<String, GravityPlayer> players;


    Random random;
    List<Map> maps;
    public List<Map> activeMaps;
    public List<String> winners;
    int numberOfMaps;
    public Location lobby;

    public Gravity(List<Map> maps, int numberOfMaps) {
        this.maps = maps;
        this.numberOfMaps = numberOfMaps;
        if(numberOfMaps > maps.size()) numberOfMaps = 5;
        random = new Random();
        activePortals = new ArrayList<Portal>();
        activeMaps = new ArrayList<Map>();
        players = new HashMap<String, GravityPlayer>();
        Coordinate c = Parse.convertToCoord(Main.getPlugin().getConfig().getString("lobby.location"));
        lobby = new Location(Bukkit.getServer().getWorld("world"), c.locX, c.locY, c.locZ);
        winners = new ArrayList<String>();
    }

    public void chooseMaps() {
        List<Integer> chosenMaps = new ArrayList<Integer>();

        // Chooses 5 distinct random integers less than the amount of maps
        for(int i = 0; i < numberOfMaps; i++) {
            int choose = random.nextInt(maps.size());
            while(chosenMaps.contains(choose) ) {
                choose = random.nextInt(maps.size());
            }
            chosenMaps.add(choose);
            System.out.println("CHOOSE:" + choose);
        }

        // Fills the activeMaps and activePortals lists with respective items
        for(int i = 0; i < chosenMaps.size()-1; i++){
            int mapNo = chosenMaps.get(i);
            int mapNo2 = chosenMaps.get(i+1);

            activeMaps.add(maps.get(mapNo));

            for(Portal portal : maps.get(mapNo).portals) {
                portal.setDest(maps.get(mapNo2).spawn);
                portal.setDestMap(maps.get(mapNo2));
                activePortals.add(portal);
            }
        }


        // Handles the final maps placement

        Map finalMap = maps.get(chosenMaps.get(chosenMaps.size()-1));
        System.out.println(finalMap.toString());
        activeMaps.add(finalMap);

        for(Portal portal : finalMap.portals){
            portal.setDest(new Coordinate(lobby.getBlockX(), lobby.getBlockY(), lobby.getBlockZ()));
            portal.setDestMap(null);
            activePortals.add(portal);
            portal.setFinish(true);
        }

    }

    public void reset() {
        activePortals.clear();
        activeMaps.clear();
        players.clear();
        winners.clear();
        Bukkit.getOnlinePlayers().forEach( player -> player.kickPlayer(""));
        chooseMaps();
    }



}
