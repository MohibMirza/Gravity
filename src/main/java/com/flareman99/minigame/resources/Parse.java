package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Parse {

    public static void parseConfig(){
        Set<String> keys = Main.getPlugin().getConfig().getConfigurationSection("maps").getKeys(false);

        for(String key : keys){
            String spawnPath = "maps." + key + ".spawn";
            String facingPath = "maps."+ key + ".facing";

            String spawn = Main.getPlugin().getConfig().getString(spawnPath);

            Coordinate spawnCoord = convertToCoord(spawn);
            String facing = Main.getPlugin().getConfig().getString(facingPath);

            List<Portal> portals = new ArrayList<Portal>();

            String portalsPath = "maps." + key + ".portals";
            Set<String> portalKeys = Main.getPlugin().getConfig().getConfigurationSection(portalsPath).getKeys(false);

            for(String portalKey : portalKeys) {
                List<String> portalEdges = Main.getPlugin().getConfig().getStringList(portalsPath + "." + portalKey);
                Coordinate pos1 = convertToCoord(portalEdges.get(0));
                Coordinate pos2 = convertToCoord(portalEdges.get(1));

                Portal portal = new Portal(pos1, pos2);

                portals.add(portal);

            }

            Map map = new Map(spawnCoord, facing, portals);

            Main.maps.add(map);
        }
    }

    public static Coordinate convertToCoord(String nums){
        String temp = "";
        double coordX = 0.0;
        double coordY = 0.0;
        double coordZ = 0.0;
        int counter = 1;
        for(int i = 0; i < nums.length(); i++){
            char c = nums.charAt(i);
            if (c != ' ') {
                temp += c;
            } else {
                double value = Double.valueOf(temp);

                if(counter == 1){
                    coordX = value;
                }
                else if(counter == 2){
                    coordY = value;
                }
                temp = "";
                counter++;
            }

            if(i == (nums.length() -1) )
                coordZ = Double.valueOf(temp);

        }
        return new Coordinate(coordX, coordY, coordZ);
    }

}