package uk.co.hexeption.api.event;

/**
 * Created by Hexeption on 16/01/2017.
 */
public abstract class Event {

    private boolean cancelled = false;

    public boolean isCancelled() {

        return cancelled;
    }

    public void setCancelled(boolean cancelled) {

        this.cancelled = cancelled;
    }
}
