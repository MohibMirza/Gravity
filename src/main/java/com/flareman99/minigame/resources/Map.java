package com.flareman99.minigame.resources;

import java.util.List;

public class Map {
    public Coordinate spawn;
    public String facing;
    public List<Portal> portals;
    public String name;

    public Map(String name, Coordinate spawn, String facing, List<Portal> portals) {
        this.spawn = spawn;
        this.facing = facing;
        this.portals = portals;
        this.name = name;
    }

    public String toString() {
        String str = "\nMap: \n";
        str += "    Spawn: " + spawn.toString() + "\n";
        str += "    Facing: " + facing + "\n";
        str += "    Portal: \n";
        for(Portal portal : portals) {
            str += "        " + portal.toString();
        }
        return str;

    }

}

