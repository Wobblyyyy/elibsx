/*
 *
 * Copyright (c) 2020, Colin Robertson (wobblyyyy@gmail.com)
 *
 * This file is part of the elibsx project. The elibsx project is licensed
 * under the GNU General Public License V3.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * license for the specific language governing permissions and limitations
 * under the License.
 *
 * Along with this file, you should have received a license file, containing
 * a copy of the GNU General Public License V3. If you did not receive a copy
 * of that license, you may find one online.
 *
 * elibsx GitHub repository:
 * https://github.com/Wobblyyyy/elibsx
 *
 * GNU General Public License V3:
 * http://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *
 */

package me.wobblyyyy.rlibx.hardware.motor;

import me.wobblyyyy.intra.ftc2.utils.math.Comparator;
import me.wobblyyyy.rlibx.hardware.Component;
import me.wobblyyyy.rlibx.interfaces.MotorCore;

/**
 * Expansive class used in controlling motors to their maximum potential.
 *
 * <p>
 * Motors are the core of any movement-driven robot. As a result, the motor
 * class here needs to be pretty bulky, just to ensure that it has all the
 * features we could possibly need. Obviously, there's more that you can do
 * with a motor, but here are just some of the basics.
 * </p>
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
 * @version 1.0.0
 * @see MotorConfig
 * @since 0.1.0
 */
public class Motor implements Component {
    /**
     * The motor's motor core - you know, the thing that's actually used in
     * making motors work and all that.
     */
    private final MotorCore motor;
    /**
     * The motor's configuration.
     *
     * @see MotorConfig
     */
    private MotorConfig config;
    /**
     * Minimum power value.
     *
     * @see MotorConfig#getMin()
     */
    private double min;
    /**
     * Maximum power value.
     *
     * @see MotorConfig#getMax()
     */
    private double max;
    /**
     * Power multiplier.
     *
     * @see MotorConfig#getMultiplier()
     */
    private double multiplier;
    /**
     * The motor's power deadzone.
     *
     * @see MotorConfig#getDeadzone()
     */
    private double deadzone;
    /**
     * Should the motor use lazy power setting?
     *
     * @see MotorConfig#isLazy()
     */
    private boolean isLazy;
    /**
     * The motor's direction.
     *
     * @see MotorConfig#getDirection()
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
     * The last timestamp of when power was set to the motor.
     */
    private double lastTime = System.currentTimeMillis();
    /**
     * A comparator, used for comparing numbers while checking whether or
     * not power should be set to a motor operating in lazy mode.
     */
    private Comparator lazyPowerComparator;
    /**
     * A comparator, used for comparing numbers while checking the elapsed
     * time since power was last set to a motor.
     */
    private Comparator lazyTimeComparator;

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
     * @see MotorCore
     * @see MotorConfig
     */
    public Motor(MotorCore motor,
                 MotorConfig config) {
        /*
         * Set the motor to the motor core that was passed in the original
         * constructor.
         */
        this.motor = motor;

        /*
         * Apply constructor variables.
         *
         * This should be re-factored soon.
         */
        this.min = config.getMin();
        this.max = config.getMax();
        this.multiplier = config.getMultiplier();
        this.deadzone = config.getDeadzone();
        this.direction = config.getDirection();

        lazyPowerComparator = new Comparator(0.03);
        lazyTimeComparator = new Comparator(10);
    }

    /**
     * Get the motor's minimum value.
     *
     * @return the motor's minimum value.
     * @see Motor#min
     */
    public double getMin() {
        return config.getMin();
    }

    /**
     * Get the motor's maximum value.
     *
     * @return the motor's maximum value.
     * @see Motor#max
     */
    public double getMax() {
        return config.getMax();
    }

    /**
     * Get the motor's multiplier.
     *
     * @return the motor's multiplier.
     * @see Motor#multiplier
     */
    public double getMultiplier() {
        return config.getMultiplier();
    }

    /**
     * Get the deadzone of the motor.
     *
     * @return the motor's deadzone.
     * @see MotorConfig#getDirection()
     */
    public double getDeadzone() {
        return config.getDeadzone();
    }

    /**
     * Get the direction of the motor.
     *
     * @return the motor's direction.
     * @see Direction
     */
    public Direction getDirection() {
        return config.getDirection();
    }

    /**
     * Get whether or not the motor is configured to use lazy mode.
     *
     * @return whether or not the motor is configured to use lazy mode.
     * @see Motor#isLazy()
     */
    public boolean isLazy() {
        return config.isLazy();
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
     * Use a comparator to check whether or not the current power is within
     * a given range of the new power, meaning the new power should or should
     * not be set.
     *
     * @param power the new power to test.
     * @return whether or not the power values are far enough to re-set.
     */
    private boolean arePowerValuesFarEnough(double power) {
        /*
         * This condition needs to be inverted.
         *
         * If the comparator returns true on comparison, it means that the
         * numbers are within a given proximity to each other.
         *
         * If the comparator returns false, it means that the numbers are far
         * apart.
         *
         * By inverting the condition, we get the following ruleset:
         * - Numbers are too far apart:
         *   - Return true.
         * - Numbers are too close together:
         *   - Return false.
         */
        return !lazyPowerComparator.compare(this.power, power);
    }

    /**
     * Check to see if the duration of time elapsed since the last time power
     * was set to the motor is long enough to warrant re-setting the power.
     *
     * @param time the current system time.
     * @return if the timestamps are far enough apart.
     */
    private boolean hasTooMuchTimeElapsed(double time) {
        /*
         * This condition, like the power values condition, needs to be
         * inverted for it to work properly.
         */
        return !lazyTimeComparator.compare(lastTime, time);
    }

    /**
     * Should a motor operating in lazy mode re-set power to the motor?
     *
     * <p>
     * This condition is based on two factors.
     * <ul>
     *     <li>
     *         The power values of the motor. If the motor currently has the
     *         same power (or 0.03 plus/minus), there's no point in wasting
     *         CPU cycles on sending power to the motor controller.
     *     </li>
     *     <li>
     *         The amount of elapsed time since power was last set to the
     *         motor. If it's been a solid 15-20 minutes since power was last
     *         set to the motor, we should probably go set some power to the
     *         motor. Just maybe.
     *     </li>
     * </ul>
     * </p>
     *
     * <p>
     * <code>
     * if (too much time has elapsed since power was last set) AND
     * if (the current and target power values are far apart)
     *   return true
     * </code>
     * </p>
     *
     * @param power the new suggested power value.
     * @return whether or not the motor's power should be updated.
     * @see Motor#hasTooMuchTimeElapsed(double)
     * @see Motor#arePowerValuesFarEnough(double)
     */
    private boolean shouldReSet(double power, double time) {
        return (hasTooMuchTimeElapsed(time) && arePowerValuesFarEnough(power));
    }

    /**
     * Apply power to the core motor component.
     *
     * @param power the power value to apply.
     * @see MotorCore#setPower(double)
     */
    private void applyPower(double power) {
        /*
         * In addition to applying power to the motor, we need to update the
         * lazy clock, so our lazy power loading functions as intended.
         *
         * This should be the current system time.
         */
        lastTime = System.currentTimeMillis();

        /*
         * We need to set the locally-stored power value to the inputted
         * power for two reasons.
         *
         * Firstly, it needs to be updated for any getters of the value.
         *
         * Secondly, it needs to be updated so lazy power works as intended.
         */
        this.power = power;

        /*
         * Finally, we get to apply power to the motor.
         *
         * So exciting, I know!
         */
        motor.setPower(power);
    }

    /**
     * Apply a multiplier to a given power value.
     *
     * @return a multiplied power value.
     * @see Motor#multiplier
     */
    private double applyMultiplier(double power) {
        return power * multiplier;
    }

    /**
     * Apply the motor's direction to a given power value.
     *
     * @param power the input power value.
     * @return the directional power value.
     * @see Motor#direction
     */
    private double applyDirection(double power) {
        /*
         * Based on the motor's direction, we need to determine what we need
         * to multiply the motor's power by.
         *
         * By default, motors are set to go forwards. Towards positive 1, that
         * is. If we'd like to invert that, we need to multiply any inputted
         * power by -1, thus inverting it.
         */
        switch (direction) {
            case FORWARDS -> {
                return power;
            }
            case BACKWARDS -> {
                return power * -1.0;
            }
            default -> {
                return 0;
            }
        }
    }

    /**
     * Clip an input value based on minimum and maximum values.
     *
     * @param power the input power.
     * @return the resulting clipped power value.
     * @see Motor#min
     * @see Motor#max
     */
    private double applyClip(double power) {
        /*
         * Apply minimum and maximum.
         *
         * It's a bit confusing that Math.max() is used for minimum and
         * Math.min() is used for maximum, but hey - it works!
         */
        power = Math.max(power, min);
        power = Math.min(power, max);

        return power;
    }

    /**
     * Modify an input power value by checking to make sure it fits within
     * a defined deadzone.
     *
     * @param power the input power value.
     * @return the with-deadzone power value.
     */
    private double applyDeadzone(double power) {
        /*
         * Apply the deadzone!
         *
         * We check if the absolute value of the power is contained within the
         * deadzone - if it is, we set it to 0.
         */
        return Math.abs(power) <= deadzone ? 0.0 : power;
    }

    /**
     * Apply a whole host of modifications to an input power value.
     *
     * <p>
     * This method should...
     * <ul>
     *     <li>
     *         Apply minimum and maximum limits.
     *     </li>
     *     <li>
     *         Apply a deadzone.
     *     </li>
     *     <li>
     *         Apply a power multiplier.
     *     </li>
     *     <li>
     *         Apply a directional power multiplier.
     *     </li>
     * </ul>
     * </p>
     *
     * @param power the unmodified input power value.
     * @return a modified power value.
     * @see Motor#applyDirection(double)
     * @see Motor#applyMultiplier(double)
     * @see Motor#applyClip(double)
     * @see Motor#applyDeadzone(double)
     */
    private double applyModifications(double power) {
        /*
         * Direction comes first.
         */
        power = applyDirection(power);

        /*
         * Multiplier next - min, max, deadzones - should all be for
         * post-multiplier power values anyways.
         */
        power = applyMultiplier(power);

        /*
         * Apply minimum and maximum power values with the clip method.
         */
        power = applyClip(power);

        /*
         * And finally, apply a deadzone.
         */
        power = applyDeadzone(power);

        return power;
    }

    /**
     * Internal method to set the motor's power.
     *
     * <p>
     * We actually need to do several things when setting power to the motor.
     * <ul>
     *     <li>
     *         Modify the motor power. Modifying the motor power entails
     *         applying multipliers, directions, minimums, maximums, deadzones,
     *         and just about everything else.
     *     </li>
     *     <li>
     *         Check whether or not power should be set to the motor. That is,
     *         if the motor uses lazy power setting, which some motors might
     *         not (for a reason I couldn't possibly imagine).
     *     </li>
     * </ul>
     * </p>
     *
     * @param power the motor's power.
     * @see Motor#applyModifications(double)
     * @see Motor#shouldReSet(double, double)
     * @see Motor#applyPower(double)
     */
    private void _set(double power) {
        /*
         * Modify the power value:
         *
         * - Direction
         * - Multiplier
         * - Minimum
         * - Maximum
         * - Deadzone
         */
        power = applyModifications(power);

        /*
         * If the motor uses lazy mode, we need to check that power should
         * actually be set to the motor. If the motor does not use lazy mode,
         * we can just skip right to applying power to the motor.
         */
        if (isLazy()) {
            /*
             * Check whether or not the qualifications for re-setting power
             * are met, meaning power should be applied in lazy mode.
             */
            if (shouldReSet(
                    power,
                    System.currentTimeMillis()
            )) {
                applyPower(power);
            }
        } else {
            /*
             * Lazy mode isn't active - we can just go ahead and set the power.
             */
            applyPower(power);
        }
    }

    /**
     * Internal method to get the motor's power.
     *
     * <p>
     * If the motor is going forwards, we know the stored power value is
     * positive.
     * <p>
     * If the motor is going backwards, we need to invert the stored power
     * value so that it's correct.
     * <p>
     * Apply a direction to the power before returning that power value.
     * </p>
     *
     * @return the motor's power.
     * @see Motor#applyDirection(double)
     */
    private double _get() {
        /*
         * Apply a direction to the power before returning that power value.
         */
        return applyDirection(power);
    }

    /**
     * Can a given input source set power to the motor?
     *
     * @param source the source - true = user, false = non-user
     * @return whether the given source can power the motor
     * @see Motor#isUserControlled
     */
    private boolean canSourceControlPower(boolean source) {
        return (isUserControlled && source) || (!isUserControlled && !source);
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * This method accepts a parameter for whether or not the power came from
     * a user source or a non-user source. If the boolean is true, the power
     * came from the user. If the boolean is false, the power came from a
     * non-user source. If the motor is operating in user-only mode, only
     * user sources will be accepted. If the motor is operating in non-user-only
     * mode, only non-user sources will be accepted.
     * </p>
     *
     * @param power the power value to set to the motor.
     * @param user  is the controlling source a user?
     * @see Motor#canSourceControlPower(boolean)
     * @see Motor#_set(double)
     */
    public void setPower(double power,
                         boolean user) {
        /*
         * Check to see if the given input source (user or non-user) is
         * currently able to control the motor.
         *
         * Remember, users can only control the motor during user mode, and
         * non-users can only control the motor during non-user mode.
         */
        if (canSourceControlPower(user)) _set(power);
    }

    /**
     * Get the motor's power.
     *
     * @return the motor's power.
     * @see Motor#_get()
     */
    public double getPower() {
        return _get();
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * This is an overloaded method for the other power setting method - this
     * one doesn't have a parameter for whether or not the power to be set
     * came from a user source or not.
     * </p>
     *
     * <p>
     * If you'd like to control the robot while it's not user-controlled, you
     * can call the other setPower method and use a boolean representing
     * whether or not the power came from the user source.
     * </p>
     *
     * @param power the power value to set to the motor.
     * @see Motor#_set(double)
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
     *
     * <p>
     * In some cases, the motor will not turn at all unless it has already
     * been initialized.
     * </p>
     */
    @Override
    public void init() {
        motor.init();
    }

    /**
     * Get the motor's configuration.
     *
     * <p>
     * You can modify this configuration and it'll automatically sync with
     * the motor.
     * </p>
     *
     * @return the motor's configuration.
     */
    public MotorConfig getConfig() {
        return config;
    }

    /**
     * Set the motor's configuration.
     *
     * <p>
     * The motor's values will be adjusted based on this configuration.
     * </p>
     *
     * @param config the configuration to apply.
     */
    public void setConfig(MotorConfig config) {
        this.config = config;
    }
}
