package me.cahrypt.com.keywords.command.argument;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.permissions.Perm;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reload extends Argument {
    private final ConfigManager config;

    public Reload() {
        super(Perm.ADMIN.getPerm() + ".reload", "reload", "Reloads the configuration file (not recommended during server uptime)", "reload");
        config = Keywords.getInstance().getConfigManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(ChatColor.RED + "Reloaded. Reloading the configuration file during server uptime is NEVER recommended! Please check console for errors to confirm everything has reloaded properly");
        config.reload();
    }
}
