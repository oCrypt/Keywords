package me.cahrypt.com.keywords.command;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.utils.ColorUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class List implements CommandExecutor {
    private final ConfigManager config;
    private final Set<String> keywords;
    private final String cmdError;
    private final String listTopLn;
    private final String listBottomLn;

    public List() {
        config = Keywords.getConfigManager();
        keywords = config.getKeywords();
        cmdError = config.getCmdPermError();
        listTopLn = config.getListTopLine();
        listBottomLn = config.getListBottomLine();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        if (!player.hasPermission("keywords.cmd")) {
            if (!cmdError.equals("")) player.sendMessage(ColorUtil.translateCodes(cmdError));
            return true;
        }

        if (!listTopLn.equals("")) player.sendMessage(ColorUtil.translateCodes(listTopLn));
        TextComponent msg = new TextComponent();
        keywords.forEach(rawKeyword -> {
            String keyword = ":" + rawKeyword + ":";
            TextComponent replaceComp = new TextComponent(TextComponent.fromLegacyText(ColorUtil.translateCodes(config.getReplacement(rawKeyword))));
            replaceComp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GREEN + keyword)));
            replaceComp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,  keyword));

            msg.addExtra(replaceComp);
            msg.addExtra(" ");
        });
        player.spigot().sendMessage(msg);
        if (!listBottomLn.equals("")) player.sendMessage(ColorUtil.translateCodes(listBottomLn));
        return true;
    }
}
