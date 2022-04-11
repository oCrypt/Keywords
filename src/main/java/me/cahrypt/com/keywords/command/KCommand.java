package me.cahrypt.com.keywords.command;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.command.argument.Argument;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;

public class KCommand implements CommandExecutor {
    private final ConfigManager config;
    private final Argument[] arguments;

    public KCommand(Argument... arguments) {
        this.config = Keywords.getConfigManager();
        this.arguments = arguments;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().info("That command cannot be executed through console!");
            return true;
        }

        String cmdPermError = ColorUtil.translateCodes(config.getCmdPermError());

        if (!player.hasPermission("keywords.cmd")) {
            player.sendMessage(cmdPermError);
            return true;
        }

        Optional<Argument> rawArgument = getArgument(args);

        if (rawArgument.isEmpty()) {
            sendInvalidUsage(player);
            return true;
        }

        Argument argument = rawArgument.get();

        if (!player.hasPermission(argument.getPermission())) {
            if (!cmdPermError.equals("")) player.sendMessage(cmdPermError);
            return true;
        }

        argument.execute(player, args);
        return true;

    }

    private Optional<Argument> getArgument(String[] args) {
        return Arrays.stream(arguments).filter(argument -> (Arrays.equals(argument.getArgs(), args))).findFirst();
    }

    private void sendInvalidUsage(Player player) {
        player.sendMessage(ChatColor.DARK_GREEN + "Keyword Commands:");
        Arrays.stream(arguments).forEach(argument -> {
            if (player.hasPermission(argument.getPermission()))
                player.sendMessage(ChatColor.GREEN + "/keywords " + argument.getUsage() + ChatColor.GRAY + " - " + argument.getDesc());
        });
    }
}
