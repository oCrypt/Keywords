package me.cahrypt.com.keywords;

import me.cahrypt.com.keywords.command.List;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.listener.TalkListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Keywords extends JavaPlugin {
    private static Keywords instance;
    private static ConfigManager config;

    @Override
    public void onEnable() {
        instance = this;
        config = new ConfigManager();
        new TalkListener();
        getCommand("keywords").setExecutor(new List());
        update(true);
    }

    @Override
    public void onDisable() {
        update(false);
    }

    public static Keywords getInstance() { return instance; }

    public static ConfigManager getConfigManager() { return config; }

    private void update(boolean enabled) {
        Bukkit.getLogger().info("----------------------------------");
        Bukkit.getLogger().info(getDescription().getName() + " version " + getDescription().getVersion() + (enabled ? " enabled " : " disabled ") + "successfully!");
        Bukkit.getLogger().info("Author: " + getDescription().getAuthors());
        Bukkit.getLogger().info("----------------------------------");
    }
}
