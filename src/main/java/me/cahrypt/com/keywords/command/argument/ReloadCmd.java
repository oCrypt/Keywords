package me.cahrypt.com.keywords.command.argument;

import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.listener.ListenerManager;
import me.cahrypt.com.keywords.permissions.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReloadCmd extends Argument {
    private final ConfigManager config;
    private final ListenerManager listenerManager;

    public ReloadCmd() {
        super(Permissions.ADMIN.getPerm() + ".reload", "reload", "Reloads the plugin to apply changes made to config (not recommended during server uptime)", "reload");
        this.config = main.getConfigManager();
        this.listenerManager = main.getListenerManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(ChatColor.RED + "Plugin Reloaded. Reloading the plugin during server uptime is NEVER recommended! Please check console for errors to confirm everything has reloaded properly");
        config.reloadConfig();
        listenerManager.reloadListeners();
    }
}
