package me.cahrypt.com.keywords.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

public class BookListener extends KeywordListener {

    public BookListener() {
        super();
    }

    @Override
    protected boolean shouldListen() {
        return config.canConvertBook();
    }

    @EventHandler
    public void onBookEdit(PlayerEditBookEvent event) {
        BookMeta meta = event.getNewBookMeta();
        for (int i = 1; i <= meta.getPageCount(); i++) {
            meta.setPage(i, formatMessage(meta.getPage(i)));
        }
        event.setNewBookMeta(meta);
    }
}
