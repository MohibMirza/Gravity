package com.flareman99.minigame.cmds;

import com.flareman99.minigame.Main;
import com.flareman99.minigame.resources.Coordinate;
import com.flareman99.minigame.resources.Map;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MapSearch implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("gravity.maps")) return false;

        ComponentBuilder message = new ComponentBuilder();
        message.append(ChatColor.GOLD + "Maps: ");
        for(Map map : Main.maps) {
            Coordinate spawn = map.spawn;
            message.append(map.name);
            message.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/minecraft:teleport "
                    + spawn.locX + " " + spawn.locY + " " + spawn.locZ));
            message.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(ChatColor.GOLD
                    + "Click to teleport!")));
            message.append("  ");
        }

        sender.spigot().sendMessage(message.create());


        return true;
    }
}
