package org.roxbotix.elibs2.robot.assem;

import me.wobblyyyy.ezpos.EncoderInterface;
import me.wobblyyyy.ezpos.swerve.TrackableSwerveModule;
import org.roxbotix.elibs2.motor.Direction;
import org.roxbotix.elibs2.motor.Motor;
import org.roxbotix.elibs2.motor.MotorConfig;
import org.roxbotix.elibs2.motor.Type;
import org.roxbotix.elibs2.robot.components.Encoder;
import org.roxbotix.elibs2.robot.components.LfComponent;

/**
 * A re-hash of the original Swerve module, this time built upon a lot
 * cleaner and clearer code.
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
 * @author Colin Robertson
 */
public class SwerveModuleV2 implements TrackableSwerveModule, LfComponent {
    /**
     * The drive motor's ID.
     */
    private final int driveMotorId;

    /**
     * The turn motor's ID.
     */
    private final int turnMotorId;

    /**
     * One of the drive encoder's channels.
     */
    private final int driveEncoderChannelA;

    /**
     * One of the drive encoder's channels.
     */
    private final int driveEncoderChannelB;

    /**
     * One of the turn encoder's channels.
     */
    private final int turnEncoderChannelA;
    /**
     * One of the turn encoder's channels.
     */
    private final int turnEncoderChannelB;
    /**
     * The drive encoder's CPR.
     */
    private final double driveCpr;
    /**
     * The turn encoder's CPR.
     */
    private final double turnCpr;
    /**
     * The wheel's diameter.
     */
    private final double wheelDiameter;
    /**
     * Motor configuration for setting up the drive motor.
     */
    private final MotorConfig driveConfig;
    /**
     * Motor configuration for setting up the turn motor.
     */
    private final MotorConfig turnConfig;
    /**
     * The drive motor itself.
     */
    private Motor drive;
    /**
     * The turn motor itself.
     */
    private Motor turn;
    /**
     * The drive motor's encoder.
     */
    private Encoder driveEncoder;
    /**
     * The turn motor's encoder.
     */
    private Encoder turnEncoder;

    /**
     * Create a new Swerve Module.
     *
     * @param driveMotorId         the drive motor's HWID.
     * @param turnMotorId          the turn motor's HWID.
     * @param driveEncoderChannelA the drive encoder's A channel.
     * @param driveEncoderChannelB the drive encoder's B channel.
     * @param turnEncoderChannelA  the turn encoder's A channel.
     * @param turnEncoderChannelB  the turn encoder's B channel.
     * @param driveCpr             the drive encoder's counts per rotation.
     * @param turnCpr              the turn encoder's counts per rotation.
     * @param wheelDiameter        the diameter of the drive wheel.
     */
    public SwerveModuleV2(int driveMotorId,
                          int turnMotorId,
                          int driveEncoderChannelA,
                          int driveEncoderChannelB,
                          int turnEncoderChannelA,
                          int turnEncoderChannelB,
                          double driveCpr,
                          double turnCpr,
                          double wheelDiameter) {
        driveConfig = getConfig(
                driveMotorId,
                driveEncoderChannelA,
                driveEncoderChannelB,
                driveCpr
        );
        turnConfig = getConfig(
                turnMotorId,
                turnEncoderChannelA,
                turnEncoderChannelB,
                turnCpr
        );

        this.driveMotorId = driveMotorId;
        this.turnMotorId = turnMotorId;
        this.driveEncoderChannelA = driveEncoderChannelA;
        this.driveEncoderChannelB = driveEncoderChannelB;
        this.turnEncoderChannelA = turnEncoderChannelA;
        this.turnEncoderChannelB = turnEncoderChannelB;
        this.driveCpr = driveCpr;
        this.turnCpr = turnCpr;
        this.wheelDiameter = wheelDiameter;
    }

    public int getDriveMotorId() {
        return driveMotorId;
    }

    public int getTurnMotorId() {
        return turnMotorId;
    }

    public int getDriveEncoderChannelA() {
        return driveEncoderChannelA;
    }

    public int getDriveEncoderChannelB() {
        return driveEncoderChannelB;
    }

    public int getTurnEncoderChannelA() {
        return turnEncoderChannelA;
    }

    public int getTurnEncoderChannelB() {
        return turnEncoderChannelB;
    }

    public Motor getDrive() {
        return drive;
    }

    public Motor getTurn() {
        return turn;
    }

    /**
     * Create a new motor config.
     *
     * @param motorId         motor's id.
     * @param encoderChannelA channel a
     * @param encoderChannelB channel b
     * @param cpr             cpr
     * @return new motor config
     */
    private MotorConfig getConfig(int motorId,
                                  int encoderChannelA,
                                  int encoderChannelB,
                                  double cpr) {
        return new MotorConfig(
                motorId,
                1.0,
                1.0,
                -1.0,
                1.0,
                -1.0,
                1.0,
                0.0,
                0.0,
                Direction.FORWARDS,
                Type.SPARK_BRUSHLESS,
                encoderChannelA,
                encoderChannelB,
                0,
                cpr
        );
    }

    /**
     * Initialize the swerve module.
     *
     * <p>
     * As with any module/component/whatever, swerve modules need to be
     * initialized before they're usable.
     * </p>
     */
    @Override
    public void init() {
        drive = new Motor(driveConfig);
        turn = new Motor(turnConfig);

        drive.init();
        turn.init();

        driveEncoder = drive.getEncoder();
        turnEncoder = turn.getEncoder();

        driveEncoder.init();
        turnEncoder.init();

        drive.enableAsyncEncoderUpdates(20);
        turn.enableAsyncEncoderUpdates(20);
    }

    /**
     * Get the drive encoder's interface.
     *
     * @return the drive encoder's interface.
     */
    @Override
    public EncoderInterface getDriveEncoder() {
        return driveEncoder;
    }

    /**
     * Get the turn encoder's interface.
     *
     * @return the turn encoder's interface.
     */
    @Override
    public EncoderInterface getTurnEncoder() {
        return turnEncoder;
    }

    /**
     * Get the drive encoder's CPR.
     *
     * @return the drive encoder's CPR.
     */
    @Override
    public double getDriveCpr() {
        return driveCpr;
    }

    /**
     * Get the turn encoder's CPR.
     *
     * @return the turn encoder's CPR.
     */
    @Override
    public double getTurnCpr() {
        return turnCpr;
    }

    /**
     * Get the drive wheel's diameter.
     *
     * @return the diameter of the drive wheel.
     */
    @Override
    public double getWheelDiameter() {
        return wheelDiameter;
    }

    /**
     * Set power to the swerve module.
     *
     * @param drivePower the drive motor's power.
     * @param turnPower  the turn motor's power.
     */
    public void setPower(double drivePower,
                         double turnPower) {
        this.drive.setPower(drivePower);
        this.turn.setPower(turnPower);
    }
}
