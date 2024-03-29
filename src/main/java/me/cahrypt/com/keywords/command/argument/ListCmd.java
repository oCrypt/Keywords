package me.cahrypt.com.keywords.command.argument;

import me.cahrypt.com.keywords.config.ConfigManager;
import me.cahrypt.com.keywords.permissions.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;

import java.util.Map;

public class ListCmd extends Argument {
    private final ConfigManager config;

    public ListCmd() {
        super(Permissions.USER.getPerm() + ".list", "list", "View all keywords and emotes you're able to use", "list");
        config = main.getConfigManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        String listTopLn = config.getListTopLine();
        String listBottomLn = config.getListBottomLine();
        Map<String, String> keywords = config.getKeywords();

        if (!listTopLn.isEmpty()) {
            player.sendMessage(listTopLn);
        }

        TextComponent msg = new TextComponent();
        keywords.forEach((keyword, emote) -> {
            TextComponent replaceComp = new TextComponent(TextComponent.fromLegacyText(emote));
            replaceComp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GREEN + keyword)));
            replaceComp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,  keyword));

            msg.addExtra(replaceComp);
            msg.addExtra(" ");
        });
        player.spigot().sendMessage(msg);

        if (!listBottomLn.isEmpty()) {
            player.sendMessage(listBottomLn);
        }
    }
}
