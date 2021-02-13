package me.wobblyyyy.rlibx.interfaces;

/**
 * Interface used for connecting to a gyroscope.
 *
 * @author Colin Robertson
 */
public interface GyroscopeCore extends ComponentCore {
    /**
     * Get the gyroscope's X value.
     *
     * @return the gyroscope's X value.
     */
    double getX();

    /**
     * Get the gyroscope's Y value.
     *
     * @return the gyroscope's Y value.
     */
    double getY();

    /**
     * Get the velocity of the robot.
     *
     * @return the robot's velocity.
     */
    double getVelocity();

    /**
     * The heading of the gyroscope (in degrees).
     *
     * @return the gyroscope's heading in degrees.
     */
    double getHeading();
}
