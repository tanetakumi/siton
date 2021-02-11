package net.serveron.hane.siton;


import org.bukkit.Bukkit;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;


public class SelectListener implements Listener {

    private final SitOn plugin;
    private final String player_name;

    public SelectListener(SitOn plugin, String player_name){
        this.plugin = plugin;
        this.player_name = player_name;
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
        Block block = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
        if(block.getType().toString().contains("STAIRS")){
            if(Objects.requireNonNull(e.getPlayer().getTargetBlock(2)).equals(block)){
                new TimerTask(e.getPlayer(),block).runTaskLater(plugin, 20);
            }
        }
    }
}
