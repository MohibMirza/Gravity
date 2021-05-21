package com.flareman99.minigame.timers;

import com.flareman99.minigame.Main;
import dev.lone.itemsadder.api.ItemsAdder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public class GameStartAnimation extends BukkitRunnable {

    private Main plugin;
    private GameLoop gameLoop;

    private int j = 15;
    private Set<Player> players;
    private final long PERIOD = 20L;
    private float pitch = 0.6F;
    private float pitchDropoff = 0.005F;
    private final String[] COUNTDOWN = {"渭", "沺", "淄", "洳", "潍", "汝", "涔", "漼", "溦", "簿", "箔",
        "槃", "条", "夂", "反"};
    private final String[] WAITING_ON = {"湍", "汹", "漴", "鎏", "汕", "澧", "漕", "滋", "溯", "湴", "汫",
        "洴", "灌", "溠", "洋", "溚"};

    public GameStartAnimation(Main plugin, Set<Player> players) {
        this.plugin = plugin;
        this.players = players;

        this.runTaskTimer(Main.getPlugin(), 0, PERIOD);
    }
    @Override
    public void run() {



        players.forEach(player -> {

            if(j <= 5) {
                ItemsAdder.playTotemAnimation(
                        player, "animecraft:countdown_" + j);

                if (j != 0) {
                    player.playNote(player.getLocation(), Instrument.CHIME,
                            Note.natural(1, Note.Tone.B));

                }
            }
            if(j != 0) player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(COUNTDOWN[j-1]));
        });


        j--;
        pitch += pitchDropoff;

        if(j < 0){
            players.forEach(player-> {
                player.teleport(Main.gravity.lobby);
                player.playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_DISAPPEARED,
                        0.7F, pitch);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("湍"));

            });

            this.cancel();
        }
    }


}
