package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Gravity {

    enum GameState {
        WAITING,
        STARTED
    }

    public static List<Portal> activePortals;
    public static java.util.Map<String, GravityPlayer> players;


    Random random;
    List<Map> maps;
    public List<Map> activeMaps;
    int numberOfMaps;



    Location lobby;

    public GameState state;

    public Location location;

    public Gravity(List<Map> maps, int numberOfMaps) {
        this.maps = maps;
        this.numberOfMaps = numberOfMaps;
        if(numberOfMaps > maps.size()) numberOfMaps = 5;
        random = new Random();

        activePortals = new ArrayList<Portal>();
        activeMaps = new ArrayList<Map>();
        state = GameState.WAITING;
        players = new HashMap<String, GravityPlayer>();
        Coordinate c = Parse.convertToCoord(Main.getPlugin().getConfig().getString("lobby.location"));
        location = new Location(Bukkit.getServer().getWorld("world"), c.locX, c.locY, c.locZ);
    }

    public void start() {
        List<Integer> chosenMaps = new ArrayList<Integer>();
        for(int i = 0; i < numberOfMaps; i++) {
            int choose = random.nextInt(maps.size());
            while(chosenMaps.contains(choose) ) {
                choose = random.nextInt(maps.size());
            }
            chosenMaps.add(choose);
            System.out.println("CHOOSE:" + choose);
        }

        for(int i = 0; i < chosenMaps.size()-1; i++){
            int mapNo = chosenMaps.get(i);
            int mapNo2 = chosenMaps.get(i+1);

            activeMaps.add(maps.get(mapNo));

            for(Portal portal : maps.get(mapNo).portals) {
                portal.setDest(maps.get(mapNo2).spawn);
                activePortals.add(portal);
            }
        }

        Map finalMap = maps.get(chosenMaps.get(chosenMaps.size()-1));
        activeMaps.add(finalMap);

        Location lobby = Main.gravity.lobby;
        for(Portal portal : finalMap.portals){
            portal.dest = new Coordinate(lobby.getBlockX(), lobby.getBlockY(), lobby.getBlockZ());
            activePortals.add(portal);

        }

    }

    public void reset() {
        activePortals.clear();
        activeMaps.clear();
        // players.clear(); KICK PLAYERS AND THIS WILL ALREADY BE CLEARED!
        start();
    }



}
