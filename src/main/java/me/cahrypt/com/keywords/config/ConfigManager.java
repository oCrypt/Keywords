package me.cahrypt.com.keywords.config;

import me.cahrypt.com.keywords.Keywords;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigManager {
    private final Keywords main;
    private final Pattern hexPattern;
    private FileConfiguration config;

    private boolean convertChat;
    private boolean convertItem;
    private boolean convertSign;
    private boolean convertBook;

    private Map<String, String> keywords;

    private String commandPermissionError;
    private String listTopLine;
    private String listBottomLine;
    private String helpTopLine;
    private String helpBottomLine;
    private String helpFormat;


    public ConfigManager() {
        this.main = JavaPlugin.getPlugin(Keywords.class);
        this.hexPattern = Pattern.compile("#[a-fA-f0-9]{6}");
        reloadConfig();
    }

    public void reloadConfig() {
        main.reloadConfig();
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();

        config = main.getConfig();
        extractConfigVals();
    }

    private void extractConfigVals() {
        convertChat = config.getBoolean("public-chat");
        convertItem = config.getBoolean("item-renaming");
        convertSign = config.getBoolean("sign-editing");
        convertBook = config.getBoolean("book-editing");

        keywords = new LinkedHashMap<>();
        config.getConfigurationSection("keywords")
                .getValues(false)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(String::length).reversed()))
                .forEach(stringObjectEntry -> keywords.put(stringObjectEntry.getKey(), translateCodes(stringObjectEntry.getValue().toString())));

        commandPermissionError = translateCodes(config.getString("cmd-permission-error"));
        listTopLine = translateCodes(config.getString("list-cmd-topln"));
        listBottomLine = translateCodes(config.getString("list-cmd-bottomln"));
        helpTopLine = translateCodes(config.getString("help-cmd-topln"));
        helpBottomLine = translateCodes(config.getString("help-cmd-bottomln"));
        helpFormat = translateCodes(config.getString("help-cmd-cmds"));
    }

    private String translateCodes(String msg) {
        Matcher matcher = hexPattern.matcher(msg);
        while(matcher.find()) {
            String color = msg.substring(matcher.start(), matcher.end());
            msg = msg.replace(color, ChatColor.of(color) + "");
            matcher = hexPattern.matcher(msg);
        }
        return ChatColor.translateAlternateColorCodes('&', msg) + ChatColor.RESET;
    }

    public boolean canConvertChat() {
        return convertChat;
    }

    public boolean canConvertItem() {
        return convertItem;
    }

    public boolean canConvertSign() {
        return convertSign;
    }

    public boolean canConvertBook() {
        return convertBook;
    }

    public Map<String, String> getKeywords() {
        return keywords;
    }

    public String getCommandPermissionError() {
        return commandPermissionError;
    }

    public String getListTopLine() {
        return listTopLine;
    }

    public String getListBottomLine() {
        return listBottomLine;
    }

    public String getHelpTopLine() {
        return helpTopLine;
    }

    public String getHelpBottomLine() {
        return helpBottomLine;
    }

    public String getHelpFormat() {
        return helpFormat;
    }
}
