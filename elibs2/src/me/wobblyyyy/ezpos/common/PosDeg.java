package me.wobblyyyy.ezpos.common;

/**
 * A coordinate (in degrees).
 *
 * @author Colin Robertson
 */
public class PosDeg implements CommonCoordinate {
    /**
     * The coordinate's angle.
     */
    private final double angle;

    /**
     * The coordinate's X value.
     */
    private final double x;

    /**
     * The coordinate's Y value.
     */
    private final double y;

    /**
     * Create a new degrees position.
     *
     * @param x the X value.
     * @param y the Y value.
     */
    public PosDeg(final double x,
                  final double y) {
        this.angle = 0;
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new degrees position.
     *
     * @param angle the angle (measured in degrees).
     * @param x the X value.
     * @param y the Y value.
     */
    public PosDeg(final double angle,
                  final double x,
                  final double y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the angle of the coordinate.
     *
     * @return the angle (in degrees).
     */
    @Override
    public double getAngle() {
        return angle;
    }

    /**
     * Get the angle of the coordinate.
     *
     * @return the angle (in degrees).
     */
    @Override
    public double getAngleDeg() {
        return angle;
    }

    /**
     * Get the angle of the coordinate.
     *
     * @return the angle (in radians).
     */
    @Override
    public double getAngleRad() {
        return Math.toRadians(angle);
    }

    /**
     * Get the X value of the coordinate.
     *
     * @return the coordinate's X value.
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Get the Y value of the coordinate.
     *
     * @return the coordinate's Y value.
     */
    @Override
    public double getY() {
        return y;
    }
}
