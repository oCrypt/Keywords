package me.cahrypt.com.keywords.listener;

import me.cahrypt.com.keywords.permissions.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TalkListener extends KeywordListener {

    public TalkListener() {
        super();
    }

    @Override
    protected boolean shouldListen() {
        return config.canConvertChat();
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
}
