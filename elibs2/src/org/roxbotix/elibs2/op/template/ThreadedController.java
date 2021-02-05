package org.roxbotix.elibs2.op.template;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import org.roxbotix.elibs2.controller.Controller;
import org.roxbotix.elibs2.controller.Stick;

/**
 * A threaded and controllable template op mode.
 *
 * <p>
 * This is an extension of the ThreadEnabledTemplate. If you haven't already,
 * you should probably go check that out before continuing much further.
 * </p>
 *
 * <p>
 * This class doesn't really do much, save create a joystick controller and an
 * Xbox controller. As well as creating the controllers during the init phase
 * of the robot, the controllers are also regularly updated during the teleop
 * phase of the robot's brief and meaningless existence.
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
public class ThreadedController extends ThreadEnabledTemplate {
    private static final String tag = "[TC] ";

    /**
     * The first (left) of the two joysticks.
     */
    public Stick joystick1;

    /**
     * The second (right) of the two joysticks.
     */
    public Stick joystick2;

    /**
     * The Xbox controller used.
     */
    public XboxController xboxController;

    /**
     * A virtualized controller, made up of two joysticks.
     */
    public Controller joysticks;

    /**
     * The Xbox controller's abstraction.
     */
    public Controller controller;

    /**
     * Create a new ThreadedController instance.
     *
     * @param joystick1_id  the port of the LEFT joystick.
     * @param joystick2_id  the port of the RIGHT joystick.
     * @param controller_id the port of the Xbox controller.
     */
    public ThreadedController(int joystick1_id,
                              int joystick2_id,
                              int controller_id) {
        /*
         * Set up the controllers during the init phase.
         */
        preInit.add(
                () -> {
                    System.out.println(tag + "Initializing joysticks with " +
                            "IDs " + joystick1_id + " and " +
                            joystick2_id
                    );
                    joystick1 = new Stick(new Joystick(joystick1_id));
                    joystick2 = new Stick(new Joystick(joystick2_id));
//                    xboxController = new XboxController(controller_id);

//                    joysticks = new DualStickConverter(
//                            joystick1,
//                            joystick2
//                    );
//                    controller = new Controller(xboxController);
                }
        );

        /*
         * Whenever teleop is active, update the controllers' states and
         * run their maps.
         */
        preTeleop.add(
                new Runnable() {
                    @Override
                    public void run() {
//                        joysticks.updateState();
//                        controller.updateState();

//                        joysticks.map.runMap();
//                        controller.map.runMap();
                    }
                }
        );
    }
}
