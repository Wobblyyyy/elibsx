package org.roxbotix.elibs2.op;

import org.roxbotix.elibs2.op.template.ThreadedController;
import org.roxbotix.elibs2.subsystem.Subsystems;
import org.roxbotix.elibs2.subsystem.subsystems.SwerveSystem;

/**
 * The first, hopefully final, full implementation of a swerve drive.
 *
 * <p>
 * Before I say anything else - the documentation here is LONG. Like, really
 * long. Bits of the documentation are copy-pasted from other files. So unless
 * you're genuinely interested in how this swerve drive works, or unless you're
 * trying to debug the swerve drive, you can skip most, if not all, of the
 * documentation. Best of luck, brave adventurer!
 * </p>
 *
 * <p>
 * Confused about what's going on? Well, I can (hopefully) help. Maybe. We'll
 * just have to see. But keep reading if you are.
 * </p>
 *
 * <p>
 * As you can probably tell by control-clicking any of the fields in this file,
 * this file is the highest-level abstraction of quite a few different classes.
 * And as a result of how many different classes are used here, it can
 * undoubtedly be very confusing to figure out what's going on where. In order
 * to remedy this presumed issue, I'm going to list all of the important steps
 * and in what file they happen.
 * </p>
 *
 * <p>
 * The controllers and input methods are set up in the ThreadedController class.
 * </p>
 *
 * <p>
 * The math behind the swerve drive is located in two separate places.
 * <ul>
 *     <li>
 *         Firstly, some of the math is in the SwerveModule class. I say some
 *         for a reason - that's only the math that each individual swerve
 *         module needs to run in order to figure out how much power to set to
 *         each motor.
 *     </li>
 *     <li>
 *         Secondly, some of the math is done in the Swerve class. This is all
 *         of the math that has to do with calculating the motor power and turn
 *         angle for all four of the wheels at once.
 *     </li>
 * </ul>
 * </p>
 *
 * <p>
 * Motors are controlled and abstracted via the SpMotor class.
 * </p>
 *
 * <p>
 * The drivetrain is built on top of the Swerve class, which in turn uses four
 * SwerveModule instances.
 * </p>
 *
 * <p>
 * Swerve drive... isn't something that I understand very well. As a result,
 * it's very likely that some of my math here is wrong. If it is, please
 * feel free to correct it. I'd love to have to re-write a couple fewer lines
 * of code.
 * </p>
 *
 * <p>
 * Although unrelated, I have to say - god, I'm not excited to write kinematics
 * and pathfinding code for this stuff. Meccanum drive is one beast - I already
 * understand it well enough to create a pathfinder using it. Swerve drive,
 * however - well, that's where it starts to get a bit less fun.
 * </p>
 *
 * <p>
 * Swerve modules are comprised of two motors - a motor that spins, causing
 * the robot to move in the direction of the attached wheel, and a motor that
 * turns, causing the wheel that propels the robot to turn. This allows the
 * robot to move and turn at the same time - the turn motor, the angle motor,
 * whatever you wanna call it - doesn't restrict the drive motor from working.
 * </p>
 *
 * <p>
 * In the future, it may be advisable to make a method of allowing for
 * swerve modules to be counted as motors - this way, incredibly long init
 * steps are much shorter. Currently, it's a bit painful - having to pass
 * 12 parameters in order to init a full drivetrain of swerve modules.
 * This is a to-do, but it's not important enough to warrant a to-do.
 * </p>
 *
 * <p>
 * Swerve modules also require an encoder. Having an encoder allows for the
 * turn wheel to accurately determine the angle at which it's currently pointed.
 * Without this precision, turning precisely may become a very big challenge
 * and may prove itself to be quite the hassle to work around.
 * </p>
 *
 * <p>
 * For those (that) reason, swerve modules need 4 inputs. Those inputs, in
 * order, are as follows:
 * <ul>
 *     <li>The drive motor's hardware ID.</li>
 *     <li>The turn motor's hardware ID.</li>
 *     <li>The turn motor's encoder hardware ID.</li>
 *     <li>The turn motor's encoder's CPM (counts per rotation).</li>
 * </ul>
 * If none of that made sense to you, you should go ahead and Google what
 * you're confused about. CPM is an important concept for swerve module
 * encoding, so you should definitely have a solid understanding of what
 * it means and what's going on behind the scenes.
 * </p>
 *
 * <p>
 * Swerve power is handled internally via the SwervePower class. It contains
 * four double arrays that represent the power values of each of the motors.
 * It's very important to note one thing when dealing with these.
 * The first value is a percent value of power. It has a range of -1 to +1,
 * just like your typical motor power values. However, the second value is
 * where things start to get... spicy. This value is a measure of angle, stored
 * in DEGREES (NOT RADIANS). This value must be positive and must be less than
 * 360. Most of that should be handled for you. However, if it's not, you know
 * why now. Knowledge - isn't it great?
 * </p>
 *
 * @author Colin Robertson
 */
public class SwerveDrive extends ThreadedController {
    /**
     * Front-right IDs.
     *
     * <p>
     * Each of these mini-arrays contains four values, which are...
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Turn motor encoder hardware ID.</li>
     *     <li>Turn motor encoder CPR (counts per rotation).</li>
     * </ul>
     * </p>
     */
    private static final int[] fris = { 0, 0, 0, 0 };

    /**
     * Front-left IDs.
     *
     * <p>
     * Each of these mini-arrays contains four values, which are...
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Turn motor encoder hardware ID.</li>
     *     <li>Turn motor encoder CPR (counts per rotation).</li>
     * </ul>
     * </p>
     */
    private static final int[] flis = { 0, 0, 0, 0 };

    /**
     * Back-right IDs.
     *
     * <p>
     * Each of these mini-arrays contains four values, which are...
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Turn motor encoder hardware ID.</li>
     *     <li>Turn motor encoder CPR (counts per rotation).</li>
     * </ul>
     * </p>
     */
    private static final int[] bris = { 0, 0, 0, 0 };

    /**
     * Back-left IDs.
     *
     * <p>
     * Each of these mini-arrays contains four values, which are...
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Turn motor encoder hardware ID.</li>
     *     <li>Turn motor encoder CPR (counts per rotation).</li>
     * </ul>
     * </p>
     */
    private static final int[] blis = { 0, 0, 0, 0 };

    /**
     * The actual swerve drivetrain itself.
     *
     * <p>
     * This is accessed via the subsystem manager.
     * </p>
     *
     * <p>
     * It should be noted that SwerveSystem is yet another abstraction of the
     * swerve drivetrain. In order to make the original Swerve code I had
     * written play nice with everything else, I had to make another wrapper
     * class. If you'd like to access the swerve drivetrain directly, or at
     * least a little bit more directly than normally, you can use the method
     * titled "getSwerve()" to get it.
     * </p>
     */
    private SwerveSystem swerveSystem;

    /**
     * Create a new ThreadedController instance.
     *
     * <p>
     * This is an extension of the ThreadedController set-up - here we actually
     * add swerve code. This is not, however, the furthest code can go. I would
     * very strongly suggest extending this class to add some more purpose and
     * utility to the robot's code.
     * </p>
     *
     * @param joystick1_id  the port of the LEFT joystick.
     * @param joystick2_id  the port of the RIGHT joystick.
     * @param controller_id the port of the Xbox controller.
     */
    public SwerveDrive(int joystick1_id,
                       int joystick2_id,
                       int controller_id) {
        /*
         * Run all of the super code so Java doesn't get mad at us.
         *
         * This just sets up the controllers, which is certainly an important
         * step in getting controllers to work.
         */
        super(
                joystick1_id,
                joystick2_id,
                controller_id
        );

        /*
         * Initialize the swerve system by getting a reference to it
         * via the subsystem manager.
         *
         * The subsystem manager is used to access different subsystems simply
         * via a string and on-demand. This means that the swerve subsystem
         * (the drivetrain itself) has to be initialized by grabbing the static
         * and already instantiated version from the manager.
         */
        fRobotInit.add(
                new Runnable() {
                    @Override
                    public void run() {
                        swerveSystem = (SwerveSystem) Subsystems
                                .getSubsystem("drivetrain_swerve");
                    }
                }
        );

        /*
         * Add some code to make the drivetrain actually drive.
         *
         * We currently just have updated joystick values and... not much
         * else, actually. This code right here just makes the drivetrain
         * respond to power based on joystick inputs.
         *
         * This IS NOT where any potential mathematical errors will be hiding.
         * All this does is get the joystick values of two joysticks and pass
         * them along to the swerve system to handle from there.
         *
         * If there's some wonky mathematical issues going on with the swerve
         * drive, it's advisable to first check the Swerve and SwerveModule
         * classes. In addition to those two classes, PID360 might be a good
         * place to look.
         */
        fTeleopRegular.add(
                new Runnable() {
                    @Override
                    public void run() {
                        swerveSystem.drive(
                                joysticks.getState().lsx,
                                joysticks.getState().lsy,
                                joysticks.getState().rsx,
                                joysticks.getState().rsy
                        );
                    }
                }
        );
    }
}
