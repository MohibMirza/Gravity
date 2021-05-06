package com.flareman99.minigame.cmds;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Gravity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Debug implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("reset")) {
                Main.gravity.reset();
                System.out.println(ChatColor.RED + "Resetting the game");
            }
        }


        return false;
    }

}
