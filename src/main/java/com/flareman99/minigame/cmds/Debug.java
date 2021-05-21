package com.flareman99.minigame.cmds;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Gravity;
import com.flareman99.minigame.timers.GameStartAnimation;
import com.flareman99.minigame.timers.TestTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class Debug implements CommandExecutor {

    public static BukkitRunnable anim;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("reset")) {
                Main.gravity.reset();
                System.out.println(ChatColor.RED + "Resetting the game");
            }
        }

        Set<Player> players = new HashSet<Player>();

        Bukkit.getOnlinePlayers().forEach(player-> players.add(player));

        anim = new GameStartAnimation(Main.getPlugin(), players);

        return false;
    }

}
