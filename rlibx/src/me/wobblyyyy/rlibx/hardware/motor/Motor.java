package me.wobblyyyy.rlibx.hardware.motor;

import me.wobblyyyy.rlibx.hardware.Component;
import me.wobblyyyy.rlibx.interfaces.MotorCore;
import me.wobblyyyy.rlibx.util.Direction;

/**
 * Expansive class used in controlling motors to their maximum potential.
 *
 * <p>
 * I would STRONGLY suggest that you read the documentation for the
 * MotorConfig class before attempting to create a motor - it's a lot easier
 * to make a motor if you actually understand what's going on behind the
 * creation of that motor.
 * </p>
 *
 * <p>
 * The original motor class had significantly more parameters - however, in
 * an effort to simplify the codebase of any of my robotics code, the new (and
 * current) version of this class is significantly slimmer, but just nearly
 * just as powerful.
 * </p>
 *
 * @author Colin Robertson
 */
public class Motor implements Component {
    /**
     * Minimum power value.
     */
    private double min;

    /**
     * Maximum power value.
     */
    private double max;

    /**
     * Power multiplier.
     */
    private double multiplier;

    /**
     * The motor's power deadzone.
     */
    private double deadzone;

    /**
     * Should the motor use lazy power setting?
     */
    private boolean isLazy;

    /**
     * The motor's direction.
     */
    private Direction direction;

    /**
     * Is the motor user-controlled?
     */
    private boolean isUserControlled;

    /**
     * The motor's power.
     */
    private double power = 0;

    /**
     * The motor's motor core - y'know, the thing that's actually used in
     * making motors work and all that.
     */
    private final MotorCore motor;

    /**
     * Create a new Motor class.
     *
     * @param motor the core motor responsible for doing motor stuff.
     */
    public Motor(MotorCore motor) {
        this.motor = motor;
    }

    /**
     * Create a new Motor class, with a set of configuration rules that
     * are applied.
     *
     * @param motor  the motor's core element.
     * @param config the motor's configuration.
     */
    public Motor(MotorCore motor,
                 MotorConfig config) {
        this.motor = motor;

        this.min = config.getMin();
        this.max = config.getMax();
        this.multiplier = config.getMultiplier();
        this.deadzone = config.getDeadzone();
        this.direction = config.getDirection();
    }

    /**
     * Get the motor's minimum power.
     *
     * @return the motor's minimum power.
     */
    public double getMin() {
        return min;
    }

    /**
     * Set the motor's minimum power.
     *
     * @param min the motor's minimum power.
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * Get the motor's maximum power.
     *
     * @return the motor's maximum power.
     */
    public double getMax() {
        return max;
    }

    /**
     * Set the motor's maximum power.
     *
     * @param max the motor's maximum power.
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * Get the motor's multiplier.
     *
     * @return the motor's multiplier.
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Set the motor's multiplier.
     *
     * @param multiplier the motor's multiplier.
     */
    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Get the motor's deadzone.
     *
     * @return the motor's deadzone.
     */
    public double getDeadzone() {
        return deadzone;
    }

    /**
     * Set the motor's deadzone.
     *
     * @param deadzone the motor's deadzone.
     */
    public void setDeadzone(double deadzone) {
        this.deadzone = deadzone;
    }

    /**
     * Is the motor currently in lazy mode?
     *
     * @return whether or not the motor is in lazy motor.
     */
    public boolean isLazy() {
        return isLazy;
    }

    /**
     * Set the motor's lazy mode state.
     *
     * @param lazy should the motor use lazy mode? true = yes, false = no.
     */
    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    /**
     * Get the motor's direction.
     *
     * @return the motor's direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set the motor's direction.
     *
     * @param direction the motor's direction.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Get the motor core that's used for doing motor things.
     *
     * <p>
     * You should almost never use this method, ever.
     * </p>
     *
     * @return the motor's core.
     */
    public MotorCore getMotor() {
        return motor;
    }

    /**
     * Internal method to set the motor's power.
     *
     * @param power the motor's power.
     */
    private void _set(double power) {
        power *= multiplier;

        switch (direction) {
            case FORWARDS -> power *= 1.0;
            case BACKWARDS -> power *= -1.0;
        }

        power = Math.max(power, min);
        power = Math.min(power, max);

        if (Math.abs(power) <= deadzone) power = 0.0;

        this.power = power;

        motor.setPower(power);
    }

    /**
     * Internal method to get the motor's power.
     *
     * @return the motor's power.
     */
    private double _get() {
        switch (direction) {
            case FORWARDS -> {
                return power;
            }
            case BACKWARDS -> {
                return power * -1.0;
            }
        }

        return 0.0;
    }

    /**
     * Set power to the motor.
     *
     * @param power the power value to set to the motor.
     * @param user  is the controlling source a user?
     */
    public void setPower(double power,
                         boolean user) {
        if (isUserControlled && user) {
            _set(power);
        } else if (!isUserControlled && !user) {
            _set(power);
        }
    }

    /**
     * Get the motor's power.
     *
     * @return the motor's power.
     */
    public double getPower() {
        return _get();
    }

    /**
     * Set power to the motor.
     *
     * @param power the power value to set to the motor.
     */
    public void setPower(double power) {
        setPower(power, true);
    }

    /**
     * Enable user control - meaning user sources can now control the motor.
     *
     * <p>
     * This is used mostly in pathfinding-related situations, where you'll
     * have a separate part of the robot controlling the drivetrain for
     * different purposes at the same time.
     * </p>
     */
    public void enableUserControl() {
        isUserControlled = true;
    }

    /**
     * Disable user control - meaning non-user sources can no longer
     * control the motor.
     *
     * <p>
     * This is used mostly in pathfinding-related situations, where you'll
     * have a separate part of the robot controlling the drivetrain for
     * different purposes at the same time.
     * </p>
     */
    public void disableUserControl() {
        isUserControlled = false;
    }

    /**
     * Initialize the component.
     */
    @Override
    public void init() {
        motor.init();
    }
}
