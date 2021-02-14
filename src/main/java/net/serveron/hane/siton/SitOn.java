package net.serveron.hane.siton;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

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
        HandlerList.unregisterAll();
    }
}
