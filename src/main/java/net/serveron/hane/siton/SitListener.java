package net.serveron.hane.siton;


import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.Collection;
import java.util.List;
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
                //new TimerTask(e.getPlayer(),block).runTaskLater(plugin, 20);
            }
        }
    }

    @EventHandler
    public void onJump(PlayerJumpEvent e){
        //Bukkit.broadcastMessage(e.getPlayer().getInventory().getItemInMainHand().toString());
        if(e.getPlayer().getInventory().getItemInMainHand().equals(new ItemStack(Material.STICK))){
            Block block = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
            if(block.getType().toString().contains("STAIRS")){
                if(Objects.requireNonNull(e.getPlayer().getTargetBlock(2)).equals(block)){
                    ArmorStand armorStand = e.getPlayer().getWorld().spawn(block.getLocation().add(0.5,0.3,0.5), ArmorStand.class);
                    armorStand.setCustomName("sitarmor");
                    armorStand.setMarker(true);
                    armorStand.setInvisible(true);
                    armorStand.setGravity(false);
                    armorStand.setRotation(e.getPlayer().getLocation().getYaw(),e.getPlayer().getLocation().getPitch());
                    armorStand.addPassenger(e.getPlayer());
                }
            } else {
                if(e.getPlayer().getPassengers().size()==0){
                    Collection<Entity> entities = e.getPlayer().getLocation().getNearbyEntities(2,2,2);
                    for(Entity entity:entities){
                        if(entity instanceof Player && entity.getPassengers().size()==0){
                            if(((Player) entity).isSneaking()){
                                entity.addPassenger(e.getPlayer());
                                break;
                            }
                        }
                    }
                }
            }
        }

    }

    @EventHandler
    public void onLeaveChair(EntityDismountEvent e){
        if(e.getDismounted().getType().equals(EntityType.ARMOR_STAND)){
            e.getDismounted().remove();
        }
        //Bukkit.broadcastMessage("居りました");
    }
}
