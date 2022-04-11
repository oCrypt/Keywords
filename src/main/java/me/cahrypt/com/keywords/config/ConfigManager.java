package me.cahrypt.com.keywords.config;

import me.cahrypt.com.keywords.Keywords;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Set;

public class ConfigManager {
    private final Keywords main;
    private FileConfiguration config;

    public ConfigManager() {
        this.main = Keywords.getInstance();
        this.config = main.getConfig();

        config.options().copyDefaults();
        main.saveDefaultConfig();
    }

    public void reload() {
        main.reloadConfig();
        main.saveDefaultConfig();
        config = main.getConfig();
    }

    public String getReplacement(String keyword) {
        return config.getString("keywords." + keyword);
    }

    public Set<String> getKeywords() {
        return config.getConfigurationSection("keywords").getKeys(true);
    }

    public String getCmdPermError() { return config.getString("cmd-permission-error"); }

    public String getListTopLine() { return config.getString("list-cmd-topln"); }

    public String getListBottomLine() { return config.getString("list-cmd-bottomln"); }
}
