package org.roxbotix.elibs2.robot.assem.drive;

import org.roxbotix.elibs2.motor.Direction;
import org.roxbotix.elibs2.motor.Motor;
import org.roxbotix.elibs2.motor.MotorConfig;
import org.roxbotix.elibs2.motor.Type;

/**
 * A four-wheel drivetrain with four independent wheels.
 *
 * <p>
 * This is applicable to pretty much any robot with four or more wheels,
 * regardless of the type of drive it uses. The purpose of this class is to
 * simplify the creation of alternative methods of drive and make sure that
 * everything remains consistent between said methods.
 * </p>
 *
 * <p>
 * Although this type of drivetrain only has four wheels, a combination of
 * two of them can be used to power an 8-wheel-drive system. Although I
 * couldn't imagine why any person would ever create such a monstrosity, the
 * option is still there.
 * </p>
 *
 * <p>
 * Wheels can be controlled individually and independently - hence the name,
 * "Independent Four Wheel". With that being said, however, it's probably a
 * wise idea to only control all four of the wheels at the same time. By
 * controlling a single individual motor, you're running the risk of improperly
 * powering a motor, or two motors, or even three, and potentially allowing for
 * the temporary loss of control over the robot. Although that may or may not
 * be a little dramatised, my point still stands.
 * </p>
 *
 * @author Colin Robertson
 */
public class Independent4Wheel {
    private static final double MIN = -1.0;
    private static final double MAX = 1.0;
    private static final double DEADZONE = 0.0;
    private static final Direction LEFT = Direction.BACKWARDS;
    private static final Direction RIGHT = Direction.FORWARDS;
    private static final Type TYPE = Type.SPARK_BRUSHLESS;

    /**
     * Front-right motor.
     */
    public Motor frontRight;

    /**
     * Front-left motor.
     */
    public Motor frontLeft;

    /**
     * Back-right motor.
     */
    public Motor backRight;

    /**
     * Back-left motor.
     */
    public Motor backLeft;

    /**
     * The ID of the front-right motor.
     */
    public int frontRightId;

    /**
     * The ID of the front-left motor.
     */
    public int frontLeftId;

    /**
     * The ID of the back-right motor.
     */
    public int backRightId;

    /**
     * The ID of the back-left motor.
     */
    public int backLeftId;

    /**
     * Should motor power rounding be used?
     */
    public boolean isRound;

    public double frontRightMultiplier = 1.0;
    public double frontLeftMultiplier = 1.0;
    public double backRightMultiplier = 1.0;
    public double backLeftMultiplier = 1.0;

    /**
     * Is the drivetrain able to be controlled by a user source?
     *
     * <p>
     * Very useful in pathfinding and controlling the robot automatically.
     * </p>
     */
    private boolean isUserControlled;

    /**
     * Is the drivetrain able to be controlled by a non-user source?
     *
     * <p>
     * Very useful in pathfinding and controlling the robot automatically.
     * </p>
     */
    private boolean isNonUserControlled;

    /**
     * Create a new four-wheel drivetrain.
     *
     * <p>
     * Although the drivetrain will have been created, it will not have been
     * initialized. In order for the drivetrain to actually do anything, it
     * needs to be initialized. Luckily, there's a wonderful method called
     * "init" that we can use to do just that.
     * </p>
     *
     * @param frontRightId the front-right ID.
     * @param frontLeftId  the front-left ID.
     * @param backRightId  the back-right ID.
     * @param backLeftId   the back-left ID.
     */
    public Independent4Wheel(int frontRightId,
                             int frontLeftId,
                             int backRightId,
                             int backLeftId) {
        this.frontRightId = frontRightId;
        this.frontLeftId = frontLeftId;
        this.backRightId = backRightId;
        this.backLeftId = backLeftId;
    }

    public double getFrontRightMultiplier() {
        return frontRightMultiplier;
    }

    public void setFrontRightMultiplier(double frontRightMultiplier) {
        this.frontRightMultiplier = frontRightMultiplier;
    }

    public double getFrontLeftMultiplier() {
        return frontLeftMultiplier;
    }

    public void setFrontLeftMultiplier(double frontLeftMultiplier) {
        this.frontLeftMultiplier = frontLeftMultiplier;
    }

    public double getBackRightMultiplier() {
        return backRightMultiplier;
    }

    public void setBackRightMultiplier(double backRightMultiplier) {
        this.backRightMultiplier = backRightMultiplier;
    }

    public double getBackLeftMultiplier() {
        return backLeftMultiplier;
    }

    public void setBackLeftMultiplier(double backLeftMultiplier) {
        this.backLeftMultiplier = backLeftMultiplier;
    }

    /**
     * Initialize the drivetrain.
     *
     * <p>
     * This <b>MUST</b> be called before the drivetrain is operational.
     * </p>
     *
     * <p>
     * Init is where all of the instantiation and related activities occur.
     * Although it (presumably) won't be an issue, if there's not enough
     * overhead in terms of the robot's performance speed, this may cause some
     * lag at the start of the robot's life cycle.
     * </p>
     */
    public void init() {
        MotorConfig frontRightConfig = new MotorConfig(
                frontRightId,
                frontRightMultiplier,
                MIN,
                MAX,
                DEADZONE,
                RIGHT,
                TYPE
        );
        MotorConfig frontLeftConfig = new MotorConfig(
                frontLeftId,
                frontLeftMultiplier,
                MIN,
                MAX,
                DEADZONE,
                RIGHT,
                TYPE
        );
        MotorConfig backRightConfig = new MotorConfig(
                backRightId,
                backRightMultiplier,
                MIN,
                MAX,
                DEADZONE,
                RIGHT,
                TYPE
        );
        MotorConfig backLeftConfig = new MotorConfig(
                backLeftId,
                backLeftMultiplier,
                MIN,
                MAX,
                DEADZONE,
                LEFT,
                TYPE
        );

        frontRight = new Motor(frontRightConfig);
        frontLeft = new Motor(frontLeftConfig);
        backRight = new Motor(backRightConfig);
        backLeft = new Motor(backLeftConfig);

        frontRight.init();
        frontLeft.init();
        backRight.init();
        backLeft.init();
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * Setting power to only one of the motors is generally advised against.
     * You should (typically) set power to all four of the motors at once
     * using whatever type of drive you prefer - think Meccanum, Swerve, etc.
     * </p>
     *
     * @param power the power to set to the motor.
     */
    public void setFrontRightPower(double power) {
        frontRight.setPower(power);
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * Setting power to only one of the motors is generally advised against.
     * You should (typically) set power to all four of the motors at once
     * using whatever type of drive you prefer - think Meccanum, Swerve, etc.
     * </p>
     *
     * @param power the power to set to the motor.
     */
    public void setFrontLeftPower(double power) {
        frontLeft.setPower(power);
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * Setting power to only one of the motors is generally advised against.
     * You should (typically) set power to all four of the motors at once
     * using whatever type of drive you prefer - think Meccanum, Swerve, etc.
     * </p>
     *
     * @param power the power to set to the motor.
     */
    public void setBackRightPower(double power) {
        backRight.setPower(power);
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * Setting power to only one of the motors is generally advised against.
     * You should (typically) set power to all four of the motors at once
     * using whatever type of drive you prefer - think Meccanum, Swerve, etc.
     * </p>
     *
     * @param power the power to set to the motor.
     */
    public void setBackLeftPower(double power) {
        backLeft.setPower(power);
    }

    private void updateMultipliers() {
        frontRight.setMultiplierPositive(frontRightMultiplier);
        frontRight.setMultiplierNegative(frontRightMultiplier);

        frontLeft.setMultiplierPositive(frontLeftMultiplier);
        frontLeft.setMultiplierNegative(frontLeftMultiplier);

        backRight.setMultiplierPositive(backRightMultiplier);
        backRight.setMultiplierNegative(backRightMultiplier);

        backLeft.setMultiplierPositive(backLeftMultiplier);
        backLeft.setMultiplierNegative(backLeftMultiplier);
    }

    /**
     * Set a multiplier for each of the motors.
     *
     * <p>
     * Multipliers can be very useful for creating a speed shifter or something
     * of the sorts. Generally, power shouldn't exceed the bounds of the motor's
     * maximum and minimum input. Multipliers should make sure that doesn't
     * end up happening.
     * </p>
     *
     * @param multiplier the multiplier to set.
     */
    public void setMultiplier(double multiplier) {
        frontRightMultiplier = multiplier;
        frontLeftMultiplier = multiplier;
        backRightMultiplier = multiplier;
        backLeftMultiplier = multiplier;
        updateMultipliers();
    }

    /**
     * Get the power values of all of the motors.
     *
     * @return the power of each of the motors.
     */
    public MotorPower getPower() {
        return new MotorPower(
                frontRight.getPower(),
                frontLeft.getPower(),
                backRight.getPower(),
                backLeft.getPower()
        );
    }

    /**
     * Set power to all of the motors.
     *
     * <p>
     * Setting power to only one of the motors is generally advised against.
     * You should (typically) set power to all four of the motors at once
     * using whatever type of drive you prefer - think Meccanum, Swerve, etc.
     * </p>
     *
     * @param power the MotorPower object that's used in setting power.
     */
    public void setPower(MotorPower power) {
        frontRight.setPower(power.fr);
        frontLeft.setPower(power.fl);
        backRight.setPower(power.br);
        backLeft.setPower(power.bl);
    }
}
