package me.cahrypt.com.keywords.command.argument;

import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.permissions.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ConversionsCmd extends Argument {
    private final ConfigManager config;

    public ConversionsCmd() {
        super(Permissions.ADMIN.getPerm() + ".conversions", "conversions", "View all enabled keyword conversions", "conversions");
        this.config = main.getConfigManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(
                ChatColor.GRAY + "Chat Conversions: " + getColor(config.canConvertChat()) + "\n"
                + ChatColor.GRAY + "Item Conversions: " + getColor(config.canConvertItem()) + "\n"
                + ChatColor.GRAY + "Book Conversions: " + getColor(config.canConvertBook()) + "\n"
                + ChatColor.GRAY + "Sign Conversions: " + getColor(config.canConvertSign())
        );
    }

    private String getColor(boolean conversion) {
        return (conversion ? ChatColor.GREEN + "true" : ChatColor.RED + "false");
    }
}
