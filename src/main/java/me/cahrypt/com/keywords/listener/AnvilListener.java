package me.cahrypt.com.keywords.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilListener extends KeywordListener {

    public AnvilListener() {
        super();
    }

    @Override
    protected boolean shouldListen() {
        return config.canConvertItem();
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack item = event.getResult();

        if (item == null) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) {
            return;
        }

        itemMeta.setDisplayName(formatMessage(itemMeta.getDisplayName()));
        item.setItemMeta(itemMeta);
    }
}
