package net.serveron.hane.siton;

import org.bukkit.plugin.java.JavaPlugin;

public final class SitOn extends JavaPlugin {

    SelectListener selectListener;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Init");
        new SitStickCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Deinit");
    }
}
