package me.cahrypt.com.keywords.listener;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.permissions.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.atomic.AtomicReference;

public class TalkListener implements Listener {
    private final ConfigManager config;
    private final Keywords main;

    public TalkListener() {
        main = JavaPlugin.getPlugin(Keywords.class);
        Bukkit.getPluginManager().registerEvents(this, main);
        config = main.getConfigManager();
    }

    @EventHandler
    public void onPlayerTalk(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission(Permissions.USER.getPerm() + ".keywords")) {
            return;
        }

        String message = event.getMessage();
        event.setMessage(formatMessage(message));
    }

    public String formatMessage(String message) {
        AtomicReference<String> refactored = new AtomicReference<>(message);
        config.getKeywords().forEach((key, value) -> refactored.set(refactored.get().replace(key, value)));
        return refactored.get();
    }

}
