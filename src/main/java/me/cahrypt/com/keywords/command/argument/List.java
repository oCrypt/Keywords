package me.cahrypt.com.keywords.command.argument;

import me.cahrypt.com.keywords.Keywords;
import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.utils.ColorUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;

import java.util.Set;

public class List extends Argument {
    private final ConfigManager config;

    public List() {
        super("keywords.cmd", "list", "View all keywords and emotes", "list");
        config = Keywords.getConfigManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        String listTopLn = ColorUtil.translateCodes(config.getListTopLine());
        String listBottomLn = ColorUtil.translateCodes(config.getListBottomLine());
        Set<String> keywords = config.getKeywords();

        if (!listTopLn.equals("")) player.sendMessage(listTopLn);
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
        if (!listBottomLn.equals("")) player.sendMessage(listBottomLn);
    }
}
