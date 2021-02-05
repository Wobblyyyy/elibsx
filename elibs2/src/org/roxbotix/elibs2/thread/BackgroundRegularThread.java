package org.roxbotix.elibs2.thread;

import me.wobblyyyy.intra.ftc2.utils.jrep.ListWrapper;

import java.util.ArrayList;

/**
 * Thread class used for running tasks in the background.
 *
 * <p>
 * This is utilized in the threaded template class to run tasks in the
 * background as frequently as possible without having to wait for the
 * periodic stuff to get called.
 * </p>
 *
 * @author Colin Robertson
 */
public class BackgroundRegularThread
        extends RepeatingRunnable
        implements SimpleThreadCore {
    /**
     * Different operation modes.
     *
     * <p>
     * These modes are switched by the ThreadEnabledTemplate class. All of
     * these modes are run in symphony with the robot mode - in other words,
     * the robot regular functionality will always be running.
     * </p>
     */
    public enum modes {
        /**
         * When the robot starts up.
         */
        ROBOT,
        /**
         * When the autonomous period starts up.
         */
        AUTONOMOUS,
        /**
         * When the teleop period starts up.
         */
        TELEOP,
        /**
         * When the disabled mode is enabled.
         */
        DISABLED,
        /**
         * When the testing mode is enabled.
         */
        TEST,
        /**
         * When the simulation mode is enabled.
         */
        SIMULATION
    }

    /**
     * Internal mode.
     */
    private modes mode = modes.ROBOT;

    /**
     * A reference to a list wrapper defined higher up.
     */
    private final ListWrapper<Runnable> fRobotRegular;

    /**
     * A reference to a list wrapper defined higher up.
     */
    private final ListWrapper<Runnable> fAutonomousRegular;

    /**
     * A reference to a list wrapper defined higher up.
     */
    private final ListWrapper<Runnable> preTeleop;

    /**
     * A reference to a list wrapper defined higher up.
     */
    private final ListWrapper<Runnable> fTeleopRegular;

    /**
     * A reference to a list wrapper defined higher up.
     */
    private final ListWrapper<Runnable> fDisabledRegular;

    /**
     * A reference to a list wrapper defined higher up.
     */
    private final ListWrapper<Runnable> fTestRegular;

    /**
     * A reference to a list wrapper defined higher up.
     */
    private final ListWrapper<Runnable> fSimulationRegular;

    /**
     * Execute a list.
     *
     * @param list the list to run.
     */
    private void runList(ListWrapper<Runnable> list) {
        ArrayList<Runnable> al = new ArrayList<>(list.list);

        for (Runnable r : al) {
            r.run();
        }
    }

    /**
     * Create a new background regular thread instance. Who could've guessed?
     *
     * <p>
     * It's very unlikely you'll actually need to construct an instance of this
     * class. You should probably double check to make sure you actually
     * need to instantiate one of these bad boys - if you're using the
     * ThreadedTemplate class, this should be handled for you.
     * </p>
     *
     * @param fRobotRegular      a list, comes from a higher-up implementation.
     * @param fAutonomousRegular a list, comes from a higher-up implementation.
     * @param preTeleop          a list, comes from a higher-up implementation.
     * @param fTeleopRegular     a list, comes from a higher-up implementation.
     * @param fDisabledRegular   a list, comes from a higher-up implementation.
     * @param fTestRegular       a list, comes from a higher-up implementation.
     */
    public BackgroundRegularThread(ListWrapper<Runnable> fRobotRegular,
                                   ListWrapper<Runnable> fAutonomousRegular,
                                   ListWrapper<Runnable> preTeleop,
                                   ListWrapper<Runnable> fTeleopRegular,
                                   ListWrapper<Runnable> fDisabledRegular,
                                   ListWrapper<Runnable> fTestRegular,
                                   ListWrapper<Runnable> fSimulationRegular) {
        this.fRobotRegular = fRobotRegular;
        this.fAutonomousRegular = fAutonomousRegular;
        this.preTeleop = preTeleop;
        this.fTeleopRegular = fTeleopRegular;
        this.fDisabledRegular = fDisabledRegular;
        this.fTestRegular = fTestRegular;
        this.fSimulationRegular = fSimulationRegular;
    }

    /**
     * Get the name of the thread.
     *
     * @return the thread's name.
     */
    @Override
    public String getName() {
        return "BackgroundRegularThread";
    }

    /**
     * Get this runnable.
     *
     * @return this.
     */
    @Override
    public Runnable getRunnable() {
        return this;
    }

    /**
     * Run the Runnable(s) associated with the current mode.
     */
    @Override
    public void run() {
        while (run) {
            Thread.onSpinWait();
            switch (mode) {
                case ROBOT:
                    runList(fRobotRegular);
                    break;
                case AUTONOMOUS:
                    runList(fRobotRegular);
                    runList(fAutonomousRegular);
                    break;
                case TELEOP:
                    runList(fRobotRegular);
                    runList(preTeleop);
                    runList(fTeleopRegular);
                    break;
                case DISABLED:
                    runList(fRobotRegular);
                    runList(fDisabledRegular);
                    break;
                case TEST:
                    runList(fRobotRegular);
                    runList(fTestRegular);
                    break;
                case SIMULATION:
                    runList(fRobotRegular);
                    runList(fSimulationRegular);
            }
        }
    }

    /**
     * Set the mode of the thread.
     *
     * @param mode a different mode, corresponds to robot's mode.
     */
    public void setMode(modes mode) {
        this.mode = mode;
    }
}
