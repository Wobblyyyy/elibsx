package me.wobblyyyy.ezpos;

import me.wobblyyyy.ezpos.common.PosDeg;

/**
 * Tracker for an entire chassis.
 *
 * @author Colin Robertson
 */
public interface ChassisTracker {
    /**
     * Get the chassis X position.
     *
     * @return the chassis X position.
     */
    double getX();

    /**
     * Get the chassis Y position.
     *
     * @return the chassis Y position.
     */
    double getY();

    /**
     * Get the chassis angle.
     *
     * @return the chassis angle.
     */
    double getAngle();

    /**
     * Get the chassis position.
     *
     * @return the chassis position.
     */
    PosDeg getPosition();

    /**
     * Get the chassis's velocity.
     *
     * @return the velocity of the chassis.
     */
    double getVelocity();
}
