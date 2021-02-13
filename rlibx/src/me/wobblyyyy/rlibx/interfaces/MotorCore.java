package me.wobblyyyy.rlibx.interfaces;

/**
 * Interface used in connecting different types of motors.
 *
 * @author Colin Robertson
 */
public interface MotorCore extends ComponentCore {
    /**
     * Get the motor's power.
     *
     * <p>
     * Power values should always be within the range of (-1) to (+1). -1
     * represents the motor's maximum speed BACKWARDS. +1 represents the
     * motor's maximum speed FORWARDS.
     * </p>
     *
     * @return the motor's power.
     */
    double getPower();

    /**
     * Set the motor's power.
     *
     * <p>
     * Power values should always be within the range of (-1) to (+1). -1
     * represents the motor's maximum speed BACKWARDS. +1 represents the
     * motor's maximum speed FORWARDS.
     * </p>
     *
     * @param power the motor's power.
     */
    void setPower(double power);
}
