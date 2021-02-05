package org.roxbotix.elibs2.thread;

import java.util.HashMap;

/**
 * A thread with a list of Runnable(s) that should be run on every tick.
 *
 * <p>
 * If you have a function you want to be executed constantly and would like
 * to simply dump it somewhere, this is your place to do it. So long as the
 * thread is alive, all of the runnable items in the map here will be executed
 * as frequently as possible.
 * </p>
 *
 * @author Colin Robertson
 */
public class RunnableThread
        extends RepeatingRunnable
        implements SimpleThreadCore {
    /**
     * All of the Runnable items in the list.
     */
    public HashMap<String, RepeatingRunnable> list = new HashMap<>();

    /**
     * Add a Runnable to the list.
     *
     * @param key               the String key of the Runnable, used to
     *                          reference it.
     * @param repeatingRunnable the Runnable itself - code that's executed as
     *                          frequently as possible.
     */
    public void addRunnable(String key,
                            RepeatingRunnable repeatingRunnable) {
        list.put(
                key,
                repeatingRunnable
        );
    }

    /**
     * Remove a Runnable from the list.
     *
     * @param key the Runnable to remove.
     */
    public void removeRunnable(String key) {
        list.remove(key);
    }

    /**
     * Enable a Runnable to be executed.
     *
     * <p>
     * Note that these repeating runnable events will NOT run if they're
     * stopped.
     * </p>
     *
     * @param key the key of the Runnable that should be enabled.
     */
    public void enableRunnable(String key) {
        list.get(key).start();
    }

    /**
     * Disable a Runnable.
     *
     * @param key the Runnable to disable.
     */
    public void disableRunnable(String key) {
        list.get(key).stop();
    }

    /**
     * Clear all of the Runnable items from the list.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Run all of the Runnable instances.
     */
    @Override
    public void run() {
        while (run) {
            Thread.onSpinWait();
            for (HashMap.Entry<String, RepeatingRunnable> e : list.entrySet()) {
                e.getValue().run();
            }
        }
    }

    /**
     * Get the name of the thread.
     *
     * @return the thread's name.
     */
    @Override
    public String getName() {
        return "RunnableThread";
    }

    /**
     * Get the Runnable that executes all of this code.
     *
     * @return this.
     */
    @Override
    public Runnable getRunnable() {
        return this;
    }
}
