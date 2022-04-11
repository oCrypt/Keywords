package me.cahrypt.com.keywords.command.argument;

import org.bukkit.entity.Player;

public abstract class Argument {
    private final String permission;
    private final String usage;
    private final String desc;
    private final String[] args;

    public Argument(String permission, String usage, String desc, String... args) {
        this.permission = permission;
        this.usage = usage;
        this.desc = desc;
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public String getPermission() {
        return permission;
    }

    public String getUsage() { return usage; }

    public String getDesc() { return desc; }

    public abstract void execute(Player player, String[] args);
}
