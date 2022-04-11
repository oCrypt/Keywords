package me.cahrypt.com.keywords.listener;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TalkListener implements Listener {
    private final ConfigManager config;

    public TalkListener() {
        Bukkit.getPluginManager().registerEvents(this, Keywords.getInstance());
        config = Keywords.getConfigManager();
    }

    @EventHandler
    public void onPlayerTalk(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("keywords.keywords")) {
            return;
        }

        String message = event.getMessage();
        event.setMessage(refactorMessage(message));
    }

    public String refactorMessage(String message) {
        String refactored = message;
        for (String key : config.getKeywords()) {
            refactored = refactored.replace(":" + key + ":", ColorUtil.translateCodes(config.getReplacement(key)));
        }
        return refactored;
    }

}
