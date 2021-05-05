package com.flareman99.minigame.resources;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Util {

    public static Location coordToLocation(Coordinate c) {
        return new Location(Bukkit.getWorld("world"), c.locX, c.locY, c.locZ);
    }
}
