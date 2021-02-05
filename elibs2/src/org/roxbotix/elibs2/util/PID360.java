package org.roxbotix.elibs2.util;

/**
 * A class used for controlling a proportional integral derivative device.
 *
 * <p>
 * Yet again, we're encountering the issue of me simply not studying enough
 * math to understand exactly what's going on here. I'd strongly suggest that
 * someone else on the team who understands all of the math behind this to take
 * a look at it, double check it, and make sure everything is as good as it can
 * be.
 * </p>
 *
 * <p>
 * With that being said, my intention with this is to have a class that is
 * attached to a swerve module's turn motor, and use the turn motor's encoder
 * count to determine the angle at which the motor is currently at and the
 * angle which the turn motor should actually turn to.
 * </p>
 *
 * @author Colin Robertson
 */
public class PID360 {
    /**
     * The PID controller's range.
     *
     * <p>
     * As for now, this feature is entirely disabled, but I'm lazy and don't
     * feel like removing it. You never know if it'll be needed... or something.
     * </p>
     */
    private double range;

    /**
     * The center and start point of the encoder's position.
     *
     * <p>
     * A center of 0 means that it's assumed the encoder will always start at
     * the 0 degree quadrangle. A center of 360 means that it's assumed the
     * encoder will start at the 360 degree quadrangle, which is, conveniently
     * enough, exactly the same as the 0 degree one.
     * </p>
     */
    private double center;

    /**
     * The PID target point.
     */
    private double setpoint;

    /**
     * The current position, represented in degrees.
     */
    private double current;

    /**
     * The original current position - updated when setpoints are set.
     */
    private double original;

    /**
     * How aggressive the curving of the PID is.
     *
     * <p>
     * A curve of 1 will act normally - the motor will have a 1:1 ratio between
     * distance from the target and speed it travels at.
     * </p>
     *
     * <p>
     * A curve of 2 will make the PID more aggressive. It'll stop less slowly
     * and it'll continue going at full speed longer than a curve of 1.
     * </p>
     *
     * <p>
     * A curve of 0.5 will make the PID more passive. It'll stop very slowly
     * and will stop going at full speed before a curve of 1.
     * </p>
     */
    private double curve;

    /**
     * "Counts per rotation."
     *
     * <p>
     * CPR is a representation of the amount of encoder counts that are needed
     * to do a complete 360 degree circle. A CPR of 1 means that the encoder
     * will tick upwards only once when doing a complete circle. A CPR of 360
     * means that the encoder will tick upwards once per degree.
     * </p>
     */
    private double cpr;

    /**
     * Degrees per count.
     */
    private double dpc;

    /**
     * Create a new PID360 instance.
     *
     * @param center A center of 0 means that it's assumed the encoder will
     *               always start at the 0 degree quadrangle. A center of 360
     *               means that it's assumed the encoder will start at the 360
     *               degree quadrangle, which is, conveniently enough,
     *               exactly the same as the 0 degree one.
     * @param cpr    CPR is a representation of the amount of encoder counts
     *               that are needed to do a complete 360 degree circle. A CPR
     *               of 1 means that the encoder will tick upwards only once
     *               when doing a complete circle. A CPR of 360 means that the
     *               encoder will tick upwards once per degree.
     */
    public PID360(double center,
                  double cpr) {
        this.center = center;
        this.cpr = cpr;

        dpc = 360 / cpr;
    }

    /**
     * Set the setpoint.
     *
     * <p>
     * Setpoints are notated in degrees. Setpoints should never...
     * <ul>
     *     <li>Be above 360.</li>
     *     <li>Be below 0.</li>
     *     <li>Be notated in radians.</li>
     *     <li>Be the same as the current position.</li>
     * </ul>
     * </p>
     *
     * @param setpoint the setpoint.
     */
    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
        original = current;
    }

    /**
     * Get the setpoint.
     *
     * @return the setpoint.
     */
    public double getSetpoint() {
        return setpoint;
    }

    /**
     * Get the position of the PID360.
     *
     * @return the position, notated in degrees.
     */
    public double getPosition() {
        return current;
    }

    /**
     * Update the motor with an encoder count.
     *
     * @param count the encoder's count.
     */
    public void update(int count) {
        boolean isNegative = count < 0;
        count = Math.abs(count);

        while (count > 360) {
            count -= 360;
        }

        count = isNegative ? -count : count;
        current = count * dpc;
    }

    /**
     * Calculate the power that should be set to a motor based on the
     * encoder's distance from the target setpoint.
     *
     * <p>
     * A value of 1 represents the maximum power that should possibly go to
     * the motor.
     * </p>
     *
     * <p>
     * A value of -1 represents the maximum power that should possibly go to
     * the motor in the opposite direction (reverse motor!)
     * </p>
     *
     * <p>
     * Motors should thus be adjusted to scale - if a motor's minimum input is
     * 5, this value should be scaled up, or the motor won't properly adjust.
     * </p>
     *
     * @return the power that should be given to the motor.
     */
    public double calculate() {
        if (current <= 180 && setpoint == 360) setpoint = 0;
        double n_dist = current - setpoint;
        double o_dist = original - setpoint;
        return n_dist / o_dist;
    }
}
