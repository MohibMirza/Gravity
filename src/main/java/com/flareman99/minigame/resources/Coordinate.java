package com.flareman99.minigame.resources;

public class Coordinate {
    public double locX, locY, locZ;
    public Coordinate(double locX, double locY, double locZ) {
        this.locX = locX;
        this.locY = locY;
        this.locZ = locZ;
    }

    public String toString() {
        return locX + " " + locY + " " + locZ;
    }

}
