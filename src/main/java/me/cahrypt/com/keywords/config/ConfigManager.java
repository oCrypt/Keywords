package me.cahrypt.com.keywords.config;

import me.cahrypt.com.keywords.Keywords;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigManager {
    private final Keywords main;
    private final Pattern hexPattern = Pattern.compile("#[a-fA-f0-9]{6}");
    private FileConfiguration config;

    private Map<String, String> keywords;
    private String cmdPermError;
    private String listTopLn;
    private String listBottomLn;
    private String helpTopLn;
    private String helpBottomLn;
    private String helpFormat;


    public ConfigManager() {
        this.main = Keywords.getInstance();
        reload();
    }

    public void reload() {
        main.reloadConfig();
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();

        config = main.getConfig();
        extractConfigVals();
    }

    private void extractConfigVals() {
        keywords = new LinkedHashMap<>();
        config.getConfigurationSection("keywords")
                .getValues(false)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(String::length).reversed()))
                .forEach(stringObjectEntry -> keywords.put(stringObjectEntry.getKey(), translateCodes(stringObjectEntry.getValue().toString())));

        cmdPermError = translateCodes(config.getString("cmd-permission-error"));
        listTopLn = translateCodes(config.getString("list-cmd-topln"));
        listBottomLn = translateCodes(config.getString("list-cmd-bottomln"));
        helpTopLn = translateCodes(config.getString("help-cmd-topln"));
        helpBottomLn = translateCodes(config.getString("help-cmd-bottomln"));
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

    public Map<String, String> getKeywords() {
        return keywords;
    }

    public String getCmdPermError() {
        return cmdPermError;
    }

    public String getListTopLine() {
        return listTopLn;
    }

    public String getListBottomLine() {
        return listBottomLn;
    }

    public String getHelpTopLine() {
        return helpTopLn;
    }

    public String getHelpBottomLine() {
        return helpBottomLn;
    }

    public String getHelpFormat() {
        return helpFormat;
    }
}
