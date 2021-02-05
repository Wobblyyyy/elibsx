package org.roxbotix.elibs2.thread;

/**
 * A template class for a repeating Runnable.
 *
 * @author Colin Robertson
 */
public abstract class RepeatingRunnable implements Runnable {
    /**
     * Should the runnable still be run?
     */
    public boolean run = true;

    /**
     * Start running the runnable.
     *
     * <p>
     * Runnable items are, by default, enabled. If they're disabled, they will
     * not be allowed to execute until they are enabled once more.
     * </p>
     */
    public void start() {
        run = true;
    }

    /**
     * Stop running the runnable.
     *
     * <p>
     * A Runnable starts enabled by default. By calling this method, you disable
     * the Runnable's execution, and from the moment you call this method
     * onwards (until you call the start() method) the Runnable will not run.
     * </p>
     */
    public void stop() {
        run = false;
    }
}
