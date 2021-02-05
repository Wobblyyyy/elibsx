package org.roxbotix.elibs2.robot.components;

/**
 * Interface used for standardizing the way motors work together.
 *
 * @author Colin Robertson
 */
public interface MotorComponent {
    /**
     * Set power to the motor.
     *
     * @param power a double, with a range of +1 to -1 (positive one to negative
     *              one), that represents the amount of power the motor should
     *              receive. A power of 1 is 100% of the output in the positive
     *              direction. A power of -0.75 is 75% of the output in the
     *              negative direction.
     */
    void set(double power);

    /**
     * Get the motor's power.
     *
     * <p>
     * The returned percent power has a range of -1 to +1. Negative 1 represents
     * the motor spinning as fast as it can, backwards. Positive 0.5 means the
     * motor is spinning half as fast as it can in the forwards direction.
     * </p>
     *
     * @return the motor's percent power.
     */
    double get();
}
