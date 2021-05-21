package com.flareman99.minigame.timers;

import com.flareman99.minigame.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TestTimer extends BukkitRunnable {

    int i = 10;

    public TestTimer(Main plugin) {
        this.runTaskTimer(plugin, 0, 5L);
    }
    @Override
    public void run() {

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText("test" + i));
        });

        i--;

        if(i < 0) this.cancel();

    }
}
