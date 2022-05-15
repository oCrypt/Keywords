package me.cahrypt.com.keywords;

import me.cahrypt.com.keywords.command.KCommand;
import me.cahrypt.com.keywords.command.argument.ListCmd;
import me.cahrypt.com.keywords.command.argument.Reload;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.listener.TalkListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Keywords extends JavaPlugin {
    private ConfigManager config;

    @Override
    public void onEnable() {
        config = new ConfigManager();

        new TalkListener();

        getCommand("keywords").setExecutor(new KCommand(
                new ListCmd(),
                new Reload()
        ));

        logStatus(true);
    }

    @Override
    public void onDisable() {
        logStatus(false);
    }

    private void logStatus(boolean enabled) {
        Bukkit.getLogger().info("----------------------------------");
        Bukkit.getLogger().info(getDescription().getName() + " version " + getDescription().getVersion() + (enabled ? " enabled " : " disabled ") + "successfully!");
        Bukkit.getLogger().info("Author: " + getDescription().getAuthors());
        Bukkit.getLogger().info("----------------------------------");
    }

    public ConfigManager getConfigManager() {
        return config;
    }
}
