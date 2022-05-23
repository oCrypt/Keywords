package me.cahrypt.com.keywords.listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {
    private List<KeywordListener> listenerList;

    public ListenerManager() {
        createListeners();
    }

    public void reloadListeners() {
        listenerList.forEach(KeywordListener::unloadListener);
        createListeners();
    }

    private void createListeners() {
        this.listenerList = new ArrayList<>();
        listenerList.add(new AnvilListener());
        listenerList.add(new BookListener());
        listenerList.add(new SignListener());
        listenerList.add(new TalkListener());
    }
}
