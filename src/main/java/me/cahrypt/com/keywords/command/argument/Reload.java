package me.cahrypt.com.keywords.command.argument;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reload extends Argument {
    private final ConfigManager config;

    public Reload() {
        super("keywords.admin", "reload", "Reloads the configuration file", "reload");
        config = Keywords.getConfigManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        config.reload();
        player.sendMessage(ChatColor.GREEN + "Keywords configuration reloaded successfully!");
    }
}
