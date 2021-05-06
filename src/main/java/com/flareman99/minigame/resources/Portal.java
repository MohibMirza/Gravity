package com.flareman99.minigame.resources;

import com.flareman99.minigame.Main;
import org.bukkit.Location;

public class Portal {
    Coordinate pos1, pos2;
    Coordinate dest;
    boolean isFinish = false;
    Map destMap;

    public Portal(Coordinate pos1, Coordinate pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        dest = new Coordinate(0.0, 0.0, 0.0);
    }

    public Coordinate getDest() {
        return dest;
    }

    public boolean getFinish() {
        return isFinish;
    }

    public Coordinate getPos1() {
        return pos1;
    }

    public Coordinate getPos2() {
        return pos2;
    }

    public Map getMap() { return destMap; }

    public Map getDestMap() { return destMap; };

    public void setDest(Coordinate dest) {
        this.dest = dest;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public void setDestMap(Map map) { this.destMap = map; }

    public Location getDeathPoint() {

        if(destMap == null) return Main.gravity.lobby;

        return Util.coordToLocation(destMap.spawn);
    }


    public String toString() {
        return pos1.toString() + " " +  pos2.toString();
    }
}
