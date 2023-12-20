package me.cahrypt.com.keywords.command;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.command.argument.Argument;
import me.cahrypt.com.keywords.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class KCommand implements CommandExecutor {
    private final ConfigManager config;
    private final Argument[] arguments;

    public KCommand(Argument... arguments) {
        this.config = JavaPlugin.getPlugin(Keywords.class).getConfigManager();
        this.arguments = arguments;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().info("That command cannot be executed through console!");
            return true;
        }

        String cmdPermError = config.getCommandPermissionError();
        Optional<Argument> rawArgument = getArgument(args);

        if (rawArgument.isEmpty()) {
            sendInvalidUsage(player);
            return true;
        }

        Argument argument = rawArgument.get();

        if (!player.hasPermission(argument.getPermission())) {
            if (!cmdPermError.isEmpty()) player.sendMessage(cmdPermError);
            return true;
        }

        argument.execute(player, args);
        return true;

    }

    private Optional<Argument> getArgument(String[] args) {
        return Arrays.stream(arguments).filter(argument -> (Arrays.equals(argument.getArgs(), args))).findFirst();
    }

    private void sendInvalidUsage(Player player) {
        String helpTopLn = config.getHelpTopLine();
        String helpBottomLn = config.getHelpBottomLine();
        String helpFormat = config.getHelpFormat();

        if (!helpTopLn.isEmpty()) {
            player.sendMessage(helpTopLn);
        }
        AtomicReference<Integer> accessNum = new AtomicReference<>(0);
        Arrays.stream(arguments).forEach(argument -> {
            if (player.hasPermission(argument.getPermission())) {
                if (!helpFormat.isEmpty()) {
                    player.sendMessage(String.format(helpFormat, "/keywords", argument.getUsage(), argument.getDesc()));
                }
                accessNum.set(accessNum.get()+1);
            }
        });
        if (!helpBottomLn.isEmpty()) {
            player.sendMessage(String.format(helpBottomLn, accessNum.get()));
        }
    }
}
