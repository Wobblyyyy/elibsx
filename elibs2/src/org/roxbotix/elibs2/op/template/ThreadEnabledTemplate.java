package org.roxbotix.elibs2.op.template;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import me.wobblyyyy.intra.ftc2.utils.jrep.ListWrapper;
import org.roxbotix.elibs2.thread.BackgroundRegularThread;
import org.roxbotix.elibs2.thread.PassiveTickingManager;
import org.roxbotix.elibs2.thread.RunnableThread;
import org.roxbotix.elibs2.thread.ThreadManager;

import java.util.ArrayList;

/**
 * The lowest-level multithreaded template available.
 *
 * <p>
 * Given how powerful modern computing systems are, it would be a waste to
 * not use any of that power. So here we go - multithreading.
 * </p>
 *
 * <p>
 * Multithreading, in case you were somehow unaware, is the act of utilizing
 * multiple separate threads of execution in order to utilize more of the CPU's
 * available power. We also have a couple of added bonuses when we use several
 * threads as compared to only a single thread.
 * <ul>
 *     <li>
 *         Firstly, and most importantly, crashes that occur on an off thread
 *         won't affect the main thread. If something goes terribly wrong with
 *         a sensor value or a motor controller, the robot itself will
 *         continue functioning exactly as it did previously.
 *     </li>
 *     <li>
 *         Secondly, it allows code to be organized more effectively. Code that
 *         should be run hundreds of time per second during the autonomous
 *         period should be somewhere that only code that's run hundreds of
 *         times per second during the autonomous period is.
 *     </li>
 *     <li>
 *         Thirdly, we can do more things at once. Although the StringEvents
 *         system in the FTC2 library does in fact work for multithreading,
 *         it's simply more effective to use multiple threads. In our case,
 *         we have a whole thread dedicated to repeatedly firing the string
 *         events ticking system.
 *     </li>
 * </ul>
 * </p>
 *
 * @author Colin Robertson
 */
public class ThreadEnabledTemplate extends TimedRobot {
    private static final String tag = "[OP] ";
    private static final String threadTag = "[THREAD] ";

    /**
     * Code that's run before anything else (during an init, that is)
     *
     * <p>
     * This should only really ever be used internally.
     * </p>
     */
    protected static final ListWrapper<Runnable> preInit =
            new ListWrapper<>();

    /**
     * Code that's run before any other teleop code (regular).
     *
     * <p>
     * This should only really ever be used internally.
     * </p>
     */
    protected static final ListWrapper<Runnable> preTeleop =
            new ListWrapper<>();

    /**
     * Run on robot's init.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fRobotInit =
            new ListWrapper<>();

    /**
     * Run periodically.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fRobotPeriodic =
            new ListWrapper<>();

    /**
     * Run during auton init.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fAutonomousInit =
            new ListWrapper<>();

    /**
     * Run periodically during auton.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fAutonomousPeriodic =
            new ListWrapper<>();

    /**
     * Run during teleop init.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fTeleopInit =
            new ListWrapper<>();

    /**
     * Run whenever the driver station has a packet to send over to the robot.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fTeleopPeriodic =
            new ListWrapper<>();

    /**
     * Run during disabled mode init.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fDisabledInit =
            new ListWrapper<>();

    /**
     * Run periodically during the disabled mode.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fDisabledPeriodic =
            new ListWrapper<>();

    /**
     * Run during test mode init.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fTestInit =
            new ListWrapper<>();

    /**
     * Run periodically during test mode.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fTestPeriodic =
            new ListWrapper<>();

    /**
     * Run as frequently as possible all the time.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fRobotRegular =
            new ListWrapper<>();

    /**
     * Run as frequently as possible during autonomous.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fAutonomousRegular =
            new ListWrapper<>();

    /**
     * Run as frequently as possible during teleop.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fTeleopRegular =
            new ListWrapper<>();

    /**
     * Run as frequently as possible during the disabled mode.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fDisabledRegular =
            new ListWrapper<>();

    /**
     * Run as frequently as possible during the test mode.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fTestRegular =
            new ListWrapper<>();

    /**
     * Run when the simulation mode is initialized.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fSimulationInit =
            new ListWrapper<>();

    /**
     * Run periodically during the simulation mode.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fSimulationPeriodic =
            new ListWrapper<>();

    /**
     * Run as frequently as possible during the simulation mode.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ListWrapper<Runnable> fSimulationRegular =
            new ListWrapper<>();

    /**
     * Thread manager, used for storing information about our threads.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final ThreadManager threadManager;

    /**
     * A thread dedicated just to ticking the StringEvents system.
     *
     * <p>
     * These ListWrappers serve to replace regular functions! In an effort
     * to maximize the usability and versatility of all of this code, you
     * should stick to adding a Runnable to a list and waiting for it to
     * execute at that given time.
     * </p>
     */
    public static final PassiveTickingManager ticker;

    /**
     * A background thread. Read more!
     *
     * <p>
     * This thread handles all of the background stuff that's going on. In
     * other words, this handles all of the "regular" lists. These lists are
     * run whenever the CPU has enough cycles to do so, which is probably more
     * frequently than the periodic lists.
     * </p>
     */
    public static final BackgroundRegularThread bgRegular;

    /**
     * A thread that runs a set of Runnable instances! Read more.
     *
     * <p>
     * This is the thread that you should (probably) use if you'd like to
     * offload a commonly-occurring event to a separate thread. By default,
     * several things are offloaded to a separate thread - motor control as
     * one example. You should go visit RunnableThread's JavaDocs to learn
     * more about how to use it.
     * </p>
     */
    public static final RunnableThread runnableThread;

    /*
     * Static code - woo hoo!
     */
    static {
        String tt = tag + threadTag;

        System.out.println(
                tt + "Starting thread manager."
        );

        threadManager = new ThreadManager();

        /*
         * Set up all of the essential threads.
         */
        System.out.println(tt + "- Creating automatic ticking thread.");
        ticker = new PassiveTickingManager();
        System.out.println(tt + "- Creating background regular thread.");
        bgRegular = new BackgroundRegularThread(
                fRobotRegular,
                fAutonomousRegular,
                preTeleop,
                fTeleopRegular,
                fDisabledRegular,
                fTestRegular,
                fSimulationRegular
        );
        System.out.println(tt + "- Creating runnable thread.");
        runnableThread = new RunnableThread();

        /*
         * Enable the threads in the thread manager.
         */
        System.out.println(tt + "- Starting automatic ticking thread.");
        threadManager.registerThread(ticker);
        System.out.println(tt + "- Starting background regular thread.");
        threadManager.registerThread(bgRegular);
        System.out.println(tt + "- Starting runnable thread.");
        threadManager.registerThread(runnableThread);

        /*
         * Make sure the threads are created and started before
         * anything important happens.
         */
        fRobotInit.add(
                () -> {
                    System.out.println(tt + "Creating and starting threads.");
                    threadManager.createThreads();
                    threadManager.startThreads();
                }
        );

        /*
         * Add a Runnable to each of the init modes.
         * This Runnable has the sole purpose of making sure the background
         * regular thread knows what mode the robot is currently in. If,
         * for example, the robot is in the ROBOT mode, we want the background
         * thread manager to only execute Runnable(s) for the ROBOT mode.
         */
        fRobotInit.add(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(
                                tt + "Mode set to ROBOT."
                        );
                        bgRegular.setMode(
                                BackgroundRegularThread.modes.ROBOT
                        );
                    }
                }
        );
        fAutonomousInit.add(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(
                                tt + "Mode set to AUTONOMOUS."
                        );
                        bgRegular.setMode(
                                BackgroundRegularThread.modes.AUTONOMOUS
                        );
                    }
                }
        );
        fTeleopInit.add(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(
                                tt + "Mode set to TELEOP."
                        );
                        bgRegular.setMode(
                                BackgroundRegularThread.modes.TELEOP
                        );
                    }
                }
        );
        fDisabledInit.add(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(
                                tt + "Mode set to DISABLED."
                        );
                        bgRegular.setMode(
                                BackgroundRegularThread.modes.DISABLED
                        );
                    }
                }
        );
        fTestInit.add(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(
                                tt + "Mode set to TEST."
                        );
                        bgRegular.setMode(
                                BackgroundRegularThread.modes.TEST
                        );
                    }
                }
        );
        fSimulationInit.add(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(
                                tt + "Mode set to SIMULATION."
                        );
                        bgRegular.setMode(
                                BackgroundRegularThread.modes.SIMULATION
                        );
                    }
                }
        );

        /*
         * Finally, we initialize the thread manager.
         */
        fRobotInit.add(
//                threadManager::init
        );

        fRobotRegular.add(
                () -> {
                    SmartDashboard.putNumber("BG Exec Time", PassiveTickingManager.getMsTime());
                    SmartDashboard.putNumber("BG R Per Sec", PassiveTickingManager.getPerSec());
                    SmartDashboard.putNumber("BG Total Exe", PassiveTickingManager.getTotal());
                }
        );
    }

    /**
     * Run any given list.
     *
     * @param list the list to run.
     */
    private void runList(ListWrapper<Runnable> list) {
        ArrayList<Runnable> al = (ArrayList<Runnable>) list.list;

        if (!(al.size() > 0)) return;

        for (Runnable r : al) {
            if (r == null) return;
            r.run();
        }
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void robotInit() {
        runList(preInit);
        runList(fRobotInit);
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void robotPeriodic() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fRobotPeriodic);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void autonomousInit() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fAutonomousInit);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void autonomousPeriodic() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fAutonomousPeriodic);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void teleopInit() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fTeleopInit);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void teleopPeriodic() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fTeleopPeriodic);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void disabledInit() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fDisabledInit);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void disabledPeriodic() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fDisabledPeriodic);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void testInit() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fTestInit);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void testPeriodic() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fTestPeriodic);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void simulationInit() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fSimulationInit);
                }
        );
    }

    /**
     * Yes, this is final for a reason!
     *
     * <p>
     * If you'd like to add functionality to a predefined point in the robot's
     * timeline (such as this one) you should go ahead and add a Runnable to
     * one of the ListWrappers at the top of this file.
     * </p>
     *
     * <p>
     * These Runnable lists will be executed where their namesake normally would
     * be. This allows us to write more functionality in different places for
     * one thing. If you're confused, don't be afraid to ask! :)
     * </p>
     */
    @Override
    public final void simulationPeriodic() {
        PassiveTickingManager.addToRun(
                () -> {
                    runList(fSimulationPeriodic);
                }
        );
    }
}
