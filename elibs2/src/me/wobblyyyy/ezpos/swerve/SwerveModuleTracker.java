package me.wobblyyyy.ezpos.swerve;

import me.wobblyyyy.ezpos.EncoderInterface;
import me.wobblyyyy.ezpos.common.PosDeg;
import me.wobblyyyy.ezpos.link.AutoTracker;
import me.wobblyyyy.ezpos.link.AutoTrackerRegistry;

/**
 * A tracker for a single swerve module.
 *
 * @author Colin Robertson
 */
public class SwerveModuleTracker implements AutoTracker {
    /**
     * Drive encoder.
     */
    private final EncoderInterface driveEncoder;

    /**
     * Turn encoder.
     */
    private final EncoderInterface turnEncoder;

    /**
     * Drive motor CPR.
     */
    private final double driveCpr;

    /**
     * Turn motor CPR.
     */
    private final double turnCpr;

    /**
     * Wheel diameter.
     */
    private final double diameter;

    /**
     * Degrees per tick.
     */
    private final double dpt;

    /**
     * The internal positional tracker.
     */
    private final SwervePositionalTracker tracker;

    /**
     * Create a new swerve module tracker.
     *
     * @param tsm a trackable swerve module.
     */
    public SwerveModuleTracker(TrackableSwerveModule tsm) {
        this(
                tsm.getDriveEncoder(),
                tsm.getTurnEncoder(),
                tsm.getDriveCpr(),
                tsm.getTurnCpr(),
                tsm.getWheelDiameter()
        );
    }

    /**
     * Create a new swerve module tracker.
     *
     * @param driveEncoder drive encoder interface.
     * @param turnEncoder  turn encoder interface.
     * @param driveCpr     drive encoder CPR.
     * @param turnCpr      turn encoder CPR.
     * @param diameter     wheel diameter.
     */
    public SwerveModuleTracker(EncoderInterface driveEncoder,
                               EncoderInterface turnEncoder,
                               double driveCpr,
                               double turnCpr,
                               double diameter) {
        this.driveEncoder = driveEncoder;
        this.turnEncoder = turnEncoder;
        this.driveCpr = driveCpr;
        this.turnCpr = turnCpr;
        this.diameter = diameter;
        this.dpt = 360 / turnCpr;
        this.tracker = new SwervePositionalTracker(
                driveCpr,
                diameter
        );

        AutoTrackerRegistry.addTracker(this);
    }

    private double ensureSub360(double angle) {
        while (angle > 360) {
            angle -= 360;
        }

        return angle;
    }

    private double ensurePositive(double angle) {
        while (angle < 0) {
            angle += 360;
        }

        return angle;
    }

    private double getTurnAngle(int count) {
        double angle = dpt * count;

        angle = ensurePositive(angle);
        angle = ensureSub360(angle);

        return angle;
    }

    /**
     * Update/track the robot's position.
     */
    @Override
    public void track() {
        tracker.update(
                driveEncoder.getCount(),
                getTurnAngle(turnEncoder.getCount())
        );
    }

    /**
     * Get the tracker's position.
     *
     * @return the tracker's position.
     */
    @Override
    public PosDeg getPosition() {
        return tracker.getPosition();
    }

    /**
     * Get X coord.
     *
     * @return x coord.
     */
    public double getX() {
        return getPosition().getX();
    }

    /**
     * Get Y coord.
     *
     * @return Y coord.
     */
    public double getY() {
        return getPosition().getY();
    }

    /**
     * Get turn motor angle.
     *
     * @return turn motor angle.
     */
    public double getTurnAngle() {
        return getTurnAngle(turnEncoder.getCount());
    }

    /**
     * Get turn encoder count.
     *
     * @return turn encoder count.
     */
    public int getTurnCount() {
        return turnEncoder.getCount();
    }

    /**
     * Get drive encoder count.
     *
     * @return drive encoder count.
     */
    public int getDriveCount() {
        return driveEncoder.getCount();
    }
}
