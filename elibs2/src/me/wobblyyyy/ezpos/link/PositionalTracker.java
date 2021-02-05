package me.wobblyyyy.ezpos.link;

import me.wobblyyyy.ezpos.common.PosDeg;

/**
 * Positional tracker interface - define shared methods.
 *
 * @author Colin Robertson
 */
public interface PositionalTracker {
    /**
     * Update a single positional tracker given encoder counts and last angle.
     *
     * @param count the encoder's current count. This value shouldn't be
     *              modified by the encoder or offset. In other words, this
     *              should be exactly the value the encoder is reporting.
     * @param angle the most recent angle of the encoder's movement.
     */
    void update(int count, double angle);

    /**
     * Reset the tracker's position.
     */
    void reset();

    /**
     * Set the position of a tracker.
     *
     * @param x     the new position's X value.
     * @param y     the new position's Y value.
     * @param angle the new position's angle.
     */
    void set(double x, double y, double angle);

    /**
     * Get the position of the tracker.
     *
     * @return the tracker's current position.
     */
    PosDeg getPosition();
}
