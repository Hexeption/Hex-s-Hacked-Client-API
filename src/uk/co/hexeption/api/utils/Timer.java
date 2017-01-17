package uk.co.hexeption.api.utils;

/**
 * Created by Hexeption on 17/01/2017.
 */
public final class Timer {

    /**
     * Get the current time and stores it.
     */
    private long lastChecked = getSystemTime();

    /**
     * @return Current Time.
     */
    public static long getSystemTime() {

        return System.nanoTime() / (long) (1E6);
    }

    /**
     * @return How long hast passed from the last checked.
     */
    public long getTimePassed() {

        return getSystemTime() - lastChecked;
    }

    /**
     * @param targetTime
     * @return True if the target time is greater or equal .
     */
    public boolean hasReached(int targetTime) {

        return getTimePassed() >= targetTime;
    }

    /**
     * Resets the lastChecked.
     */
    public void reset() {

        lastChecked = getSystemTime();
    }

}
