package org.roxbotix.elibs2.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class used for managing several threads at once.
 *
 * @author Colin Robertson
 */
public class ThreadManager {
    /**
     * A HashMap containing all of the threads - Strings and Runnable(s).
     */
    private final HashMap<String, Runnable> threads = new HashMap<>();

    /**
     * A list of all of the threads that have been created.
     */
    private final ArrayList<Thread> j_threads = new ArrayList<>();

    /**
     * A list of all of the names of the threads that have been created.
     */
    private final ArrayList<String> j_names = new ArrayList<>();

    /**
     * Register a thread.
     *
     * <p>
     * Anything that implements the SimpleThreadCore interface can be used as
     * a parameter here. With that being said, you should certainly check to
     * ensure that whatever thread you're scheduling is running as effectively
     * as it possibly can. Here's a very short checklist.
     * <ul>
     *     <li>
     *         Make sure there's no instances of busy-waiting. This means
     *         running a loop constantly and checking if a condition is true.
     *         Rather than busy-waiting, you can write code in while true loop
     *         and use the Thread.onSpinWait() method.
     *     </li>
     *     <li>
     *         Make sure that there's a way to stop the thread's execution
     *         temporarily. This isn't 100% required, but it's strongly
     *         suggested. The Thread.stop() method is officially deprecated and
     *         should be avoided at all costs.
     *     </li>
     * </ul>
     * </p>
     *
     * @param thread the thread to be registered.
     */
    public void registerThread(SimpleThreadCore thread) {
        threads.put(
                thread.getName(),
                thread.getRunnable()
        );
    }

    /**
     * Create all of the threads.
     *
     * <p>
     * This method also checks to ensure that the thread has not already been
     * created. However, you should avoid calling this method whenever possible.
     * Threads are finicky. Or something. Or maybe I'm just stupid... oh well.
     * </p>
     */
    public void createThreads() {
        for (Map.Entry<String, Runnable> e : threads.entrySet()) {
            if (!j_names.contains(e.getKey())) {
                j_threads.add(
                        new Thread(
                                e.getValue(),
                                e.getKey()
                        )
                );
                j_names.add(e.getKey());
            }
        }
    }

    /**
     * Start all of the threads.
     *
     * <p>
     * Once the threads are started, they should probably not be stopped.
     * Thread.stop() is a deprecated method for whatever reason, so unless
     * you really have a reason to call it, you should do something else.
     * </p>
     *
     * <p>
     * May I suggest to you the RunnableThread class?
     * </p>
     */
    public void startThreads() {
        for (Thread t : j_threads) {
            if (!t.isAlive()) t.start();
        }
    }

    /**
     * Stop all of the threads.
     *
     * <p>
     * Thread#stop() is a deprecated method for whatever reason. Meaning that
     * Java officially suggests that it shouldn't be used. Unless you have a
     * very important reason to be using this method, you shouldn't.
     * </p>
     */
    public void stopThreads() {
        for (Thread t : j_threads) {
            t.stop();
        }
    }

    /**
     * Initialize the entire thread system.
     *
     * <p>
     * This is a combo of the create and start methods.
     * </p>
     */
    public void init() {
        createThreads();
        startThreads();
    }
}
