package com.flareman99.minigame;

import com.flareman99.minigame.cmds.ActiveMaps;
import com.flareman99.minigame.cmds.Debug;
import com.flareman99.minigame.cmds.MapSearch;
import com.flareman99.minigame.events.JoinLeave;
import com.flareman99.minigame.events.PortalTP;
import com.flareman99.minigame.events.Respawn;
import com.flareman99.minigame.resources.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static List<Map> maps;
    public static Gravity gravity;

    public static final int MAX_PLAYERS = 8;
    public static final int MAX_LOBBY_WAIT_TIME = 200;
    public static final int MAX_GAME_TIME = 360;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Gravity] Starting Up...");

        plugin = this;
        maps = new ArrayList<Map>();

        saveDefaultConfig();

        Parse.parseConfig();
        System.out.println();


        gravity = new Gravity(maps, 5);
        gravity.chooseMaps();

        getServer().getPluginManager().registerEvents(new PortalTP(), this);
        getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        getServer().getPluginManager().registerEvents(new Respawn(), this);

        getCommand("maps").setExecutor(new MapSearch());
        getCommand("activemaps").setExecutor(new ActiveMaps());
        getCommand("debug").setExecutor(new Debug());

        // BukkitTask timer = new Timer(this).runTaskTimer(this, 0, 20L);


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
