package me.cahrypt.com.keywords.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {
    private static final Pattern hexPattern = Pattern.compile("#[a-fA-f0-9]{6}");

    public static String translateCodes(String msg) {
        Matcher matcher = hexPattern.matcher(msg);
        while(matcher.find()) {
            String color = msg.substring(matcher.start(), matcher.end());
            msg = msg.replace(color, ChatColor.of(color) + "");
            matcher = hexPattern.matcher(msg);
        }
        return ChatColor.translateAlternateColorCodes('&', msg) + ChatColor.RESET;
    }
}
