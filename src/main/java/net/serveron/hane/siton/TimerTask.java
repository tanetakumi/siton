package net.serveron.hane.siton;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class TimerTask extends BukkitRunnable {

    private Player player;
    private Block block;

    public TimerTask(Player player, Block block){
        this.player = player;
        this.block = block;
    }

    @Override
    public void run(){
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).equals(block)){
            ArmorStand armorStand = player.getWorld().spawn(block.getLocation().add(0.5,-0.7,0.5), ArmorStand.class);
            armorStand.setBodyPose(new EulerAngle());
            armorStand.setMarker(true);
            armorStand.setInvisible(true);
            armorStand.setGravity(false);
            armorStand.addPassenger(player);
        }
    }
}
