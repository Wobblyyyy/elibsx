package me.wobblyyyy.rlibx.manager;

/**
 * A template mode, which teleop/autonomous/etc will extend.
 *
 * @author Colin Robertson
 */
public class Mode {
    /**
     * The mode's executable.
     */
    private final Runnable runnable;

    /**
     * The mode's execution thread.
     */
    private Thread executionThread;

    /**
     * Create a new mode, using a runnable.
     *
     * @param runnable the mode's runnable.
     */
    public Mode(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * Execute the Runnable.
     *
     * <p>
     * Execution can't be started more than once - well, it can, but the
     * issue you're going to then encounter is that the execution will
     * restart, because the thread is initialized here.
     * </p>
     */
    public void start() {
        /*
         * Initialize the execution thread.
         *
         * Because a new one is constructed, the previous one loses any
         * sort of progress that it had towards finishing its execution.
         */
        executionThread = new Thread(runnable);

        /*
         * Start the execution thread.
         */
        executionThread.start();
    }

    /**
     * Stop the runnable execution prematurely.
     *
     * <p>
     * Stopping thread execution is generally discouraged and will 100% be
     * deprecated soon.
     * </p>
     */
    public void stop() {
        /*
         * We need to figure out another way that doesn't involving calling
         * the Thread#stop method, as that method... kinda ain't very good.
         */
        executionThread.stop();
    }
}
