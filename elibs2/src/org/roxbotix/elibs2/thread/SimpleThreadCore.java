package org.roxbotix.elibs2.thread;

/**
 * Simple core for threading.
 *
 * @author Colin Robertson
 */
public interface SimpleThreadCore {
    /**
     * Get the name of the thread.
     *
     * <p>
     * This name is only important if you plan on messing with the thread
     * after it's been constructed. If, for example, you'd like to stop a
     * thread half way through a 1 minute timer, you would need to know
     * the name of the thread you'd like to stop.
     * </p>
     *
     * @return the thread's name.
     */
    String getName();

    /**
     * Get the runnable.
     *
     * <p>
     * This will usually be "this".
     * </p>
     *
     * @return the thread's runnable.
     */
    Runnable getRunnable();
}
