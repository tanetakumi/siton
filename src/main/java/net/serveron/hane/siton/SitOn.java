package net.serveron.hane.siton;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SitOn extends JavaPlugin {

    SitListener selectListener;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Init");
        getServer().getPluginManager().registerEvents(new SitListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Deinit");
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if ((entity instanceof ArmorStand)) {
                    if (Objects.requireNonNull(entity.getCustomName()).equals("sitarmor")) {
                        entity.remove();
                    }
                }
            }
        }
        HandlerList.unregisterAll();
    }
}
