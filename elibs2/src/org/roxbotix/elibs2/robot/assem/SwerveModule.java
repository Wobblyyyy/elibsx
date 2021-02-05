package org.roxbotix.elibs2.robot.assem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import org.roxbotix.elibs2.robot.components.SpMotor;
import org.roxbotix.elibs2.util.PID360;

/**
 * A group of two motors, used in a swerve module for a swerve drive.
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
 * @author Colin Robertson
 */
public class SwerveModule {
    /**
     * The motor used for driving the robot.
     *
     * <p>
     * This motor does not need an encoder - who the hell cares if the motor
     * used for moving forward is moving forward or not? Oh well.
     * </p>
     */
    private SpMotor drive;

    /**
     * The motor used for turning the drive motor.
     *
     * <p>
     * This motor DOES need an encoder to function - not having an encoder
     * means we have no way of knowing what angle the wheel is already pointed
     * at, meaning we have no way of knowing what power needs to be set.
     * </p>
     */
    private SpMotor turn;

    /**
     * The hardware ID of the drive motor.
     */
    private final int driveId;

    /**
     * The hardware ID of the turn motor.
     */
    private final int turnId;

    /**
     * The hardware ID of the turn motor's encoder.
     */
    private final int encoder;

    /**
     * The maximum allowable voltage for the swerve module.
     *
     * <p>
     * I don't really know what this is used for, but it's apparently rather
     * important. I'll probably end up implementing my own PID controller at
     * some point in the future anyways, but we'll have to see.
     * </p>
     */
    public double MAX_VOLTS = 4.5;

    /**
     * Counts per rotation of the turn motor's encoder.
     */
    private double cpr;

    /**
     * PID controller for the turn motor.
     *
     * <p>
     * This uses the motor's encoder to determine what to do. Hopefully.
     * No promises or guarantees here, but we'll have to see.
     * </p>
     */
    public PID360 pid;

    /**
     * Create a new swerve module.
     *
     * @param driveId the hardware ID of the drive motor.
     * @param turnId  the hardware ID of the turn motor.
     * @param encoder the relevant ID for the PID controller's encoder.
     * @param cpr     the motor's Counts Per Rotation.
     */
    public SwerveModule(int driveId,
                        int turnId,
                        int encoder,
                        double cpr) {
        this.driveId = driveId;
        this.turnId = turnId;
        this.encoder = encoder;
        this.cpr = cpr;
    }

    /**
     * Initialize the swerve module.
     *
     * <p>
     * As with anything hardware-related, initialization is an important
     * step that needs to occur before you actually do anything. Don't
     * forget to initialize your swerve modules! If you're using a drivetrain,
     * such as the Swerve4Wheel class, you don't have to worry as much about
     * remembering to initialize each individual swerve module, as much of that
     * is handled for you. You just have to remember to run the main init()
     * code. Or else. Or. Else.
     * </p>
     */
    public void init() {
        drive = new SpMotor(
                driveId,
                CANSparkMaxLowLevel.MotorType.kBrushless,
                CANSparkMax.IdleMode.kCoast,
                CANSparkMax.InputMode.kPWM
        );
        turn = new SpMotor(
                turnId,
                CANSparkMaxLowLevel.MotorType.kBrushless,
                CANSparkMax.IdleMode.kCoast,
                CANSparkMax.InputMode.kPWM
        );
        pid = new PID360(
                0,
                cpr
        );

        drive.init();
        turn.init();
    }

    /**
     * Set power to the swerve module's two motors.
     *
     * <p>
     * As I've said quite a few times in the past, I have no clue how swerve
     * drive math works. As a result of that, this probably won't work. If it
     * does, that's cool. If it doesn't, that's expected. We'll just have to
     * see how it goes, I guess.
     * </p>
     *
     * <p>
     * Swerve drives aren't exactly known for being responsive. The turn motors
     * must engage while the robot is still moving, so turning will obviously
     * be a little bit slower than most people would presumably like. This can
     * obviously be countered by mastering driving a swerve drive, but that's
     * a fairly hard thing to do. I don't really know. If you're still reading
     * this documentation, you're making a mistake - go do something productive.
     * </p>
     *
     * @param speed the speed the drive motor should run at. This speed is
     *              how fast the drive motor will spin, and it has nothing to
     *              do with the turn motor.
     * @param angle the angle which the turn motor needs to turn to. Angles, at
     *              least in the context of swerve drives, are measured in
     *              DEGREES and not radians. If you have a radian measure
     *              you'd like to convert to degrees, you can use the loved
     *              Math.toDeg() method. Good luck!
     */
    public void drive(double speed,
                      double angle) {
        // Set power to the drive motor first - it's really hard to mess that
        // one up, or at least I'd hope so.
        drive.setPower(speed);

        // Now do the angle stuff. Oh boy.
        // First, check if the setpoint is the same as what it previously was.
        if (!(pid.getSetpoint() == angle)) {
            pid.setSetpoint(angle);
        }

        turn.setPower(pid.calculate());
    }
}
