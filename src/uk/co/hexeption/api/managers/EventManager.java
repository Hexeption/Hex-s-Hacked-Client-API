package uk.co.hexeption.api.managers;

import uk.co.hexeption.api.event.Event;
import uk.co.hexeption.api.event.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hexeption on 16/01/2017.
 */
public abstract class EventManager<T extends Event, L extends Listener> {

    private List<L> listeners = new ArrayList<L>();

    public void registerListerner(L listener) {

        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    public void unregisterListener(L listerner) {

        if (listeners.contains(listerner))
            listeners.remove(listerner);
    }

    public void clearListeners() {

        listeners.clear();

    }

    public void fire(T event) {

        for (int i = 0; i < listeners.size(); i++) {
            invoke(listeners.get(i), event);
        }
    }

    protected abstract void invoke(L listner, T event);

}
