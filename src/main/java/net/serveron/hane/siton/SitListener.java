package net.serveron.hane.siton;


import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.Objects;


public class SitListener implements Listener {

    private final SitOn plugin;

    public SitListener(SitOn plugin){
        this.plugin = plugin;
    }
/*
    @EventHandler
    public void onPlayer(PlayerJumpEvent e){
        if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN))
    }

 */

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
        Block block = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
        if(block.getType().toString().contains("STAIRS")){
            if(Objects.requireNonNull(e.getPlayer().getTargetBlock(2)).equals(block)){
                new TimerTask(e.getPlayer(),block).runTaskLater(plugin, 20);
            }
        }
    }

    @EventHandler
    public void onLeaveChair(EntityDismountEvent e){
        if(e.getDismounted().getType().equals(EntityType.ARMOR_STAND)){
            e.getDismounted().remove();
        }
    }
}
