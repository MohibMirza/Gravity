package com.flareman99.minigame.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gravity {

    Random random;
    List<Map> maps;
    int numberOfMaps;
    public static List<Portal> activePortals;

    public Gravity(List<Map> maps, int numberOfMaps) {
        this.maps = maps;
        this.numberOfMaps = numberOfMaps;
        if(numberOfMaps > maps.size()) numberOfMaps = 5;
        random = new Random();

        activePortals = new ArrayList<Portal>();
    }

    public void start() {
        List<Integer> chosenMaps = new ArrayList<Integer>();
        for(int i = 0; i < numberOfMaps; i++) {
            int choose = random.nextInt(maps.size());
            while(chosenMaps.contains(choose) ) {
                choose = random.nextInt(maps.size());
            }
            chosenMaps.add(choose);
            System.out.println(choose);
        }



        for(int i = 0; i < chosenMaps.size()-1; i++){
            int mapNo = chosenMaps.get(i);
            int mapNo2 = chosenMaps.get(i+1);

            for(Portal portal : maps.get(mapNo).portals) {
                portal.setDest(maps.get(mapNo2).spawn);
                activePortals.add(portal);
                System.out.println(portal.getDest().toString());
            }
        }



    }



}
