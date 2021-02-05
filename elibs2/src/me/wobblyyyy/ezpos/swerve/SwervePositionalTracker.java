package me.wobblyyyy.ezpos.swerve;

import me.wobblyyyy.ezpos.common.PosDeg;
import me.wobblyyyy.ezpos.link.CommonTracker;

/**
 * Math class used to track the position of a single swerve module.
 *
 * @author Colin Robertson
 */
public class SwervePositionalTracker extends CommonTracker {
    /**
     * Last-recorded encoder count.
     */
    private int count = 0;

    /**
     * Last-recorded X value.
     */
    private double x = 0;

    /**
     * Last-recorded Y value.
     */
    private double y = 0;

    /**
     * Last-recorded angle.
     */
    private double angle = 0;

    /**
     * Last-recorded position.
     */
    private PosDeg pos = new PosDeg(0, 0, 0);

    /**
     * Create a new swerve positional tracker.
     *
     * @param cpr      the drive motor's counts per rotation.
     * @param diameter the drive motor's wheel's diameter.
     */
    public SwervePositionalTracker(double cpr,
                                   double diameter) {
        super(cpr, diameter);
    }

    /**
     * Update the position of the tracker.
     *
     * <p>
     * If there's a math issue going on, this should be one of the first
     * locations you check, as there's a pretty decent chance it's from here.
     * </p>
     *
     * @param count the encoder's current count. This value shouldn't be
     *              modified by the encoder or offset. In other words, this
     *              should be exactly the value the encoder is reporting.
     * @param angle the most recent angle of the encoder's movement.
     */
    @Override
    public void update(int count,
                       double angle) {
        if (count == 0) {
            this.count = count;
        }

        /*
         * Positive count difference: the motor has moved FORWARDS
         * Negative count difference: the motor has moved BACKWARDS
         */
        int countDifference = count - this.count;
        double inches = getInches(countDifference);

        /*
         * Variables, just to make abstraction easier to conceptualize on my
         * end.
         */
        double newX = this.x;
        double newY = this.y;

        /*
         * All of the distance math is derived from the following equations.
         *
         * Java's Math class uses RADIANS. We use DEGREES. For that reason,
         * you need to double-check that ALL of the measurements IN are in
         * radians, and all of the measurements OUT are in degrees.
         *
         * x1 = x + (n * cos(θ))
         * y1 = y + (n * sin(θ))
         */
        newX += (inches * Math.cos(Math.toDegrees(angle)));
        newY += (inches * Math.sin(Math.toDegrees(angle)));

        set(
                newX,
                newY,
                angle
        );
    }

    /**
     * Reset the tracker's position.
     */
    @Override
    public void reset() {
        x = 0;
        y = 0;
        angle = 0;

        pos = new PosDeg(
                angle,
                x,
                y
        );
    }

    /**
     * Set the tracker's position.
     *
     * @param x     the new position's X value.
     * @param y     the new position's Y value.
     * @param angle the new position's angle.
     */
    @Override
    public void set(double x,
                    double y,
                    double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;

        this.pos = new PosDeg(
                angle,
                x,
                y
        );
    }

    /**
     * Get the tracker's position.
     *
     * @return the tracker's position.
     */
    @Override
    public PosDeg getPosition() {
        return pos;
    }
}
