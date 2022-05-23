package me.cahrypt.com.keywords.listener;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.atomic.AtomicReference;

public abstract class KeywordListener implements Listener {
    protected final Keywords main;
    protected final ConfigManager config;

    public KeywordListener() {
        this.main = JavaPlugin.getPlugin(Keywords.class);
        this.config = main.getConfigManager();
        
        if (shouldListen()) {
            Bukkit.getPluginManager().registerEvents(this, main);
        }
    }

    protected String formatMessage(String message) {
        AtomicReference<String> refactored = new AtomicReference<>(message);
        config.getKeywords().forEach((key, value) -> refactored.set(refactored.get().replace(key, value)));
        return refactored.get();
    }

    protected void unloadListener() {
        HandlerList.unregisterAll(this);
    }

    protected abstract boolean shouldListen();
}
