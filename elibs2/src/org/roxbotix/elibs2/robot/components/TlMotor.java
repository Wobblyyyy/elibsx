package org.roxbotix.elibs2.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import me.wobblyyyy.intra.ftc2.utils.math.Math;

/**
 * An extension of the default Talon motor controller functionality.
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
public class TlMotor extends RbComponent implements MotorComponent {
    private static final ControlMode CONTROL_MODE = ControlMode.PercentOutput;

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
     * Internally-used hardware ID.
     */
    private final int hwId;

    /**
     * The motor's power.
     *
     * <p>
     * I can't find a TalonSRX method for getting the motor's power, so
     * we're just gonna end up using this.
     * </p>
     */
    private double power;

    /**
     * Reference to talon.
     */
    private TalonSRX talon;

    /**
     * Create a new Talon motor.
     *
     * @param hwId the hardware ID of the Talon motor.
     */
    public TlMotor(int hwId) {
        this.hwId = hwId;
    }

    /**
     * Initialize the motor.
     */
    @Override
    public void init() {
        talon = new TalonSRX(hwId);
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
        this.power = power;

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

        talon.set(CONTROL_MODE, power);
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
        return power;
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

    public TalonSRX getTalon() {
        return talon;
    }

    public TalonSRX getTalonSRX() {
        return getTalon();
    }
}
