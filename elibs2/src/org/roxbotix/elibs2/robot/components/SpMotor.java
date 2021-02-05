package org.roxbotix.elibs2.robot.components;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import me.wobblyyyy.intra.ftc2.utils.math.Math;

/**
 * An extension of the default REV Spark motor controller functionality.
 *
 * <p>
 * Motors are pretty simple when you really think about it. Power in, ground,
 * whatever, all that electronic stuff I don't get. The point is - I write some
 * code that makes it spin, and then it spins! So if you're also a code-writer
 * who likes making motors spin, you may be a bit confused about why exactly
 * there's a class called "SpMotor". Me, being the incredibly kind soul I am,
 * I'll explain that to you. Giving motors a wrapper class allows you to do a
 * lot of really cool manipulation regarding how the motor functions. In this
 * case, we have minimum power, maximum power, rounding, smoothing, all that
 * good stuff. Point is - the motor works exactly as it normally would if you
 * just write some normal code.
 * </p>
 *
 * @author Colin Robertson
 */
public class SpMotor extends RbComponent implements MotorComponent {
    /**
     * The default motor type.
     *
     * <p>
     * If an overloaded method is called and is missing a parameter, this
     * value is subbed in for where the user didn't add a parameter.
     * </p>
     */
    private static final CANSparkMaxLowLevel.MotorType MOTOR_TYPE =
            CANSparkMaxLowLevel.MotorType.kBrushless;

    /**
     * The default motor idle mode.
     *
     * <p>
     * If an overloaded method is called and is missing a parameter, this
     * value is subbed in for where the user didn't add a parameter.
     * </p>
     */
    private static final CANSparkMax.IdleMode IDLE_MODE =
            CANSparkMax.IdleMode.kCoast;

    /**
     * The default motor input mode.
     *
     * <p>
     * If an overloaded method is called and is missing a parameter, this
     * value is subbed in for where the user didn't add a parameter.
     * </p>
     */
    public static final CANSparkMax.InputMode INPUT_MODE =
            CANSparkMax.InputMode.kPWM;

    /**
     * The motor controller itself.
     */
    private CANSparkMax spark;

    /**
     * The motor controller's Hardware ID.
     *
     * <p>
     * Hardware ID's are roughly analogous to the String names that hardware
     * components have in the First Tech Challenge.
     * </p>
     */
    private int hwId;

    /**
     * The motor's motor type.
     *
     * <p>
     * There's two types of motors:
     * <ul>
     *     <li>kBrushed - for <b>brushed</b> motors.</li>
     *     <li>kBrushless - for <b>brushless</b> motors.</li>
     * </ul>
     * You should obviously pick whichever type of motor you have.
     * </p>
     */
    private CANSparkMaxLowLevel.MotorType motorType;

    /**
     * The idle functionality of the motor.
     *
     * <p>
     * The two default idle modes are...
     * <ul>
     *     <li>kCoast - the motor will coast when it's not powered.</li>
     *     <li>kBrake - the motor will brake when it's not powered.</li>
     * </ul>
     * Unless you have a reason to, I'd suggest that you use coast - constantly
     * powering the motor to brake can cause a bit of added wear and tear over
     * a long period of time.
     * </p>
     */
    private CANSparkMax.IdleMode idleMode;

    /**
     * The motor's input mode.
     *
     * <p>
     * There's currently only one single input mode - that being "kPWM".
     * </p>
     */
    private CANSparkMax.InputMode inputMode;

    /**
     * Non-scaled maximum power.
     *
     * <p>
     * Anything above this value is automatically set to this value.
     * </p>
     */
    private double max = 1.0;

    /**
     * Non-scaled minimum power.
     *
     * <p>
     * Anything below this value is automatically set to this value.
     * </p>
     */
    private double min = -1.0;

    /**
     * The distance from 0 that is required for the motor's power to be set.
     *
     * <p>
     * This uses the absolute value of the input power. Let's say we have a
     * deadzone of 5. Anything that's above 5 and below -5 will work normally -
     * just as intended. However, anything that's below 5 and above -5 will
     * get changed to a 0.
     * </p>
     *
     * <p>
     * This is useful if there's a minimum value that the motor needs to get
     * before it actually starts spinning. If the drivetrain motors don't spin
     * at 0.25 power, you might consider putting a deadzone of 0.25 to make
     * sure the motors don't try to spin and potentially burn out or something.
     * </p>
     */
    private double deadzone = 0.0;

    /**
     * The scale of the motor's power.
     *
     * <p>
     * Whenever motor power is set, it's multiplied by this value.
     * </p>
     *
     * <p>
     * To scale up the power of the motor, this value should be higher than 1.0.
     * A value such as 2.0 would double the input power of the motors.
     * </p>
     *
     * <p>
     * To scale down the power of the motor, this value should be lower than 1.0.
     * A value such as 0.5 would half the input power of the motors.
     * </p>
     */
    private double scale = 1.0;

    /**
     * Whether or not the power of the motor should be averaged with previous motor powers.
     *
     * <p>
     * Motor smoothing was a technique I implemented when I drove for an FTC time to improve
     * how driving the robot "felt."
     * </p>
     */
    private boolean isSmooth;

    /**
     * Create a new instance of a motor.
     *
     * @param hwId the motor's hardware ID.
     */
    public SpMotor(int hwId) {
        this(
                hwId,
                MOTOR_TYPE,
                IDLE_MODE,
                INPUT_MODE
        );
    }

    /**
     * Create a new instance of a motor.
     *
     * @param hwId      the motor's hardware ID.
     * @param motorType the motor's type - there's two types, kBrushed and
     *                  kBrushless.
     */
    public SpMotor(int hwId,
                   CANSparkMaxLowLevel.MotorType motorType) {
        this(
                hwId,
                motorType,
                IDLE_MODE,
                INPUT_MODE
        );
    }

    /**
     * Create a new instance of a motor.
     *
     * @param hwId     the motor's hardware ID.
     * @param idleMode the motor's idle mode. The two default idle modes are
     *                 kBrake and kCoast. Coasting disengages the motor, while
     *                 braking counter-engages the motor to brake.
     */
    public SpMotor(int hwId,
                   CANSparkMax.IdleMode idleMode) {
        this(
                hwId,
                MOTOR_TYPE,
                idleMode,
                INPUT_MODE
        );
    }

    /**
     * Create a new instance of a motor.
     *
     * @param hwId      the motor's hardware ID.
     * @param motorType the motor's type. There's two types - kBrushed and
     *                  kBrushless.
     * @param idleMode  the motor's idle mode. The two default idle modes are
     *                  kBrake and kCoast. Coasting disengages the motor,
     *                  while braking counter-engages the motor to brake.
     */
    public SpMotor(int hwId,
                   CANSparkMaxLowLevel.MotorType motorType,
                   CANSparkMax.IdleMode idleMode) {
        this(
                hwId,
                motorType,
                idleMode,
                INPUT_MODE
        );
    }

    /**
     * Create a new instance of a motor.
     *
     * @param hwId      the hardware ID.
     * @param motorType the type of motor (brushless or brushed).
     * @param idleMode  the motor's idle mode.
     * @param inputMode the motor's input mode.
     */
    public SpMotor(int hwId,
                   CANSparkMaxLowLevel.MotorType motorType,
                   CANSparkMax.IdleMode idleMode,
                   CANSparkMax.InputMode inputMode) {
        this.hwId = hwId;
        this.motorType = motorType;
        this.idleMode = idleMode;
        this.inputMode = inputMode;
    }

    /**
     * Initialize the motor.
     *
     * <p>
     * All motors must be initialized before anything happens.
     * </p>
     *
     * <p>
     * The motor is actually instantiated here. In addition to being
     * instantiated, the motor type, idle mode, and input modes are all
     * set here. For that reason, don't forget to init your darn motors.
     * </p>
     */
    @Override
    public void init() {
        if (!(hwId > -1)) {
            throw new NullPointerException(
                    "set your hardware id before trying to do" +
                            "this stuff you absolute moron your" +
                            "brain is impressively small like are you" +
                            "just stupid or something?"
            );
        }

        spark = new CANSparkMax(hwId, motorType);
        setMotorType(motorType);
        setIdleMode(idleMode);
        setInputMode(inputMode);
    }

    /**
     * Set the hardware ID of the motor.
     *
     * @param hwId the motor's hardware ID.
     */
    public void setHwId(int hwId) {
        this.hwId = hwId;
    }

    /**
     * Get the motor's hardware ID.
     *
     * @return the motor's hardware ID.
     */
    public int getHwId() {
        return hwId;
    }

    /**
     * Set the motor's type.
     *
     * <p>
     * The two types of motor are "kBrushed" and "kBrushless." In the event
     * you've experienced severe head trauma and don't understand what those
     * words mean, "kBrushed" corresponds with a brushed motor and
     * "kBrushless" corresponds with a brushless motor.
     * </p>
     *
     * @param motorType the type of motor that's being used.
     */
    public void setMotorType(CANSparkMaxLowLevel.MotorType motorType) {
        this.motorType = motorType;
    }

    /**
     * Get the motor's type.
     *
     * @return the motor's type.
     */
    public CANSparkMaxLowLevel.MotorType getMotorType() {
        return motorType;
    }

    /**
     * Set the idle mode of the motor.
     *
     * <p>
     * The two idle modes that are available are coasting and braking.
     * Coasting will allow the motor to slowly slow down before stopping.
     * Braking will actively fight against any additional movement.
     * I'd suggest you use coasting unless you have an application where
     * braking is needed, as constantly powering the motor can cause a bit
     * more wear and tear.
     * </p>
     *
     * @param idleMode the motor's idle mode.
     */
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        this.idleMode = idleMode;
    }

    /**
     * Get the motor's idle mode.
     *
     * @return the motor's idle mode.
     */
    public CANSparkMax.IdleMode getIdleMode() {
        return idleMode;
    }

    /**
     * Set the input mode of the motor.
     *
     * <p>
     * As of 1/20/2021, the only available input mode is "kPWM".
     * </p>
     *
     * @param inputMode the motor's input mode.
     */
    public void setInputMode(CANSparkMax.InputMode inputMode) {
        this.inputMode = inputMode;
    }

    /**
     * Get the motor's input mode.
     *
     * @return the motor's input mode.
     */
    public CANSparkMax.InputMode getInputMode() {
        return inputMode;
    }

    /**
     * Set power to the motor.
     *
     * @param power a double, with a range of +1 to -1 (positive one to negative
     *              one), that represents the amount of power the motor should
     *              receive. A power of 1 is 100% of the output in the positive
     *              direction. A power of -0.75 is 75% of the output in the
     */
    @Override
    public void set(double power) {
        setPower(power);
    }

    /**
     * Set power to the motor.
     *
     * @param power the power to set to the motor. Note that this input power
     *              is checked to see if it exceeds the MAX or is below the MIN
     *              values for power. This input power is also multiplied by the
     *              "scale" variable.
     */
    public void setPower(double power) {
        power *= scale;

        if (isSmooth) {
            power = smoothMotor(power);
        }

        power = applyDeadzone(power);

        if (power > max) {
            power = max;
        } else if (power < min) {
            power = min;
        }

        spark.set(power);
    }

    /**
     * Get the power of the motor.
     *
     * @return the motor's power.
     */
    @Override
    public double get() {
        return getPower();
    }

    /**
     * Get the power of the motor.
     *
     * @return the motor's power.
     */
    public double getPower() {
        return spark.get();
    }

    /**
     * Set a maximum for the amount of power which the motor can't exceed.
     *
     * @param max the max power the motor can get.
     */
    public void setMaxPower(double max) {
        this.max = max;
    }

    /**
     * Get the max power of the motor.
     *
     * @return the motor's max power.
     */
    public double getMaxPower() {
        return max;
    }

    /**
     * Set a minimum for the amount of power which the motor can't exceed.
     *
     * @param min the min power the motor can get.
     */
    public void setMinPower(double min) {
        this.min = min;
    }

    /**
     * Get the min power of the motor.
     *
     * @return the motor's min power.
     */
    public double getMinPower() {
        return min;
    }

    /**
     * Set a scale factor for the motor's power.
     *
     * <p>
     * Scale is a coefficient that the motor's input power is multiplied by
     * every time power is set to the motor. The only situations I've ever
     * used scale in are...
     * <ul>
     *     <li>"Shifter" functionality - high gear, low gear.</li>
     *     <li>Limiting the maximum speed of the robot.</li>
     *     <li>Reversing the polarity of a motor.</li>
     * </ul>
     * </p>
     *
     * @param scale the scale value that the motor's input power should always
     *              be multiplied by.
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Get the motor's scale value.
     *
     * @return the motor's scale value.
     */
    public double getScale() {
        return scale;
    }

    /**
     * Set the motor to run in smooth or non-smooth mode.
     *
     * @param smooth whether or not the motor should be smoothed. A value of
     *               true will set the motor to be smoothed, meaning it's power
     *               values are geometrically adjusted over time. A value of
     *               false will set the motor to not be smoothed, meaning it's
     *               power values will change linearly in response to your
     *               drivetrain's math's outputs.
     */
    public void setSmooth(boolean smooth) {
        isSmooth = smooth;
    }

    /**
     * Get whether or not the motor is smoothed.
     *
     * @return whether or not the motor is smoothed.
     */
    public boolean isSmooth() {
        return isSmooth;
    }

    /**
     * Smooth the motor's value.
     *
     * @param next the next power value.
     * @return a smoothed motor value.
     */
    private double smoothMotor(double next) {
        return Math.average(getPower(), next);
    }

    /**
     * Apply a deadzone to the motor.
     *
     * @param next the next value.
     * @return the returned value, with a deadzone applied.
     */
    private double applyDeadzone(double next) {
        if (Math.abs(next) > deadzone) {
            return next;
        }

        return 0.0;
    }
}
