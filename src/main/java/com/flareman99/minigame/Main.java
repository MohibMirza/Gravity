package com.flareman99.minigame;

import com.flareman99.minigame.cmds.ActiveMaps;
import com.flareman99.minigame.cmds.MapSearch;
import com.flareman99.minigame.events.PortalTP;
import com.flareman99.minigame.resources.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static List<Map> maps;

    public static Gravity gravity;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Gravity] Starting Up...");

        plugin = this;
        maps = new ArrayList<Map>();

        saveDefaultConfig();

        Parse.parseConfig();
        System.out.println(maps.toString());


        gravity = new Gravity(maps, 5);
        gravity.start();

        getServer().getPluginManager().registerEvents(new PortalTP(), this);

        getCommand("maps").setExecutor(new MapSearch());
        getCommand("activemaps").setExecutor(new ActiveMaps());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[Gravity] Shutting Down...");

    }

    public static Main getPlugin() {
        return plugin;
    }
}
