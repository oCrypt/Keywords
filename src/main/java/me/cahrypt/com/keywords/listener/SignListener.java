package me.cahrypt.com.keywords.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Arrays;
import java.util.List;

public class SignListener extends KeywordListener {

    public SignListener() {
        super();
    }

    @Override
    protected boolean shouldListen() {
        return config.canConvertSign();
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        List<String> originalLines = Arrays.stream(event.getLines()).toList();

        for (int i = 0; i < originalLines.size(); i++) {
            String finalLine = formatMessage(event.getLine(i));
            event.setLine(i, finalLine);
        }
    }
}
