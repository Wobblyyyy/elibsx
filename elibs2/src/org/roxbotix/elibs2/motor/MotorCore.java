package org.roxbotix.elibs2.motor;

import org.roxbotix.elibs2.robot.components.LfComponent;

/**
 * A simple extension of the LfComponent interface, designed for motors.
 *
 * <p>
 * This allows us to simplify motors controlled by both SPARK and Talon motor
 * controllers by simply combining them into one Motor superclass.
 * </p>
 *
 * @author Colin Robertson
 */
public interface MotorCore extends LfComponent {
    /**
     * Set power to a motor.
     *
     * @param power a double, between the values of -1 (negative one) and +1
     *              (positive one). This double, when multiplied by 100, would
     *              represent the percentage of power (positive or negative)
     *              out of a maximum 100% that the motor will be sent.
     */
    void setPower(double power);

    /**
     * Get a motor's power.
     *
     * <p>
     * Motor power is defined as a double between -1 and +1. -1 represents the
     * fastest the motor can possibly spin - in the backwards direction. On the
     * other hand, +1 represents the fastest the motor can possibly spin -
     * in the forwards direction.
     * </p>
     *
     * @return a double, representing the motor's power.
     */
    double getPower();
}
