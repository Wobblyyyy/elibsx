package me.wobblyyyy.ezpos.swerve;

import me.wobblyyyy.ezpos.ChassisTracker;
import me.wobblyyyy.ezpos.common.PosDeg;

/**
 * A complete implementation of the ChassisTracker interface that is adapted
 * to track the position of swerve modules.
 *
 * @author Colin Robertson
 */
public class SwerveChassisTracker implements ChassisTracker {
    /**
     * Front-right swerve module tracker.
     */
    private SwerveModuleTracker fr;

    /**
     * Front-left swerve module tracker.
     */
    private SwerveModuleTracker fl;

    /**
     * Back-right swerve module tracker.
     */
    private SwerveModuleTracker br;

    /**
     * Back-left swerve module tracker.
     */
    private SwerveModuleTracker bl;

    /**
     * The front-right tracker's estimated position.
     */
    private PosDeg frPos = new PosDeg(0, 0, 0);

    /**
     * The front-left tracker's estimated position.
     */
    private PosDeg flPos = new PosDeg(0, 0, 0);

    /**
     * The back-right tracker's estimated position.
     */
    private PosDeg brPos = new PosDeg(0, 0, 0);

    /**
     * The back-left tracker's estimated position.
     */
    private PosDeg blPos = new PosDeg(0, 0, 0);

    /**
     * The center position - an average of the positions all of the other
     * trackers.
     */
    private PosDeg pos = new PosDeg(0, 0, 0);

    private TrackableSwerveModule _fr, _fl, _br, _bl;

    /**
     * Create a new swerve chassis tracker instance.
     *
     * @param fr the front-right trackable swerve module.
     * @param fl the front-left trackable swerve module.
     * @param br the back-right trackable swerve module.
     * @param bl the back-left trackable swerve module.
     */
    public SwerveChassisTracker(TrackableSwerveModule fr,
                                TrackableSwerveModule fl,
                                TrackableSwerveModule br,
                                TrackableSwerveModule bl) {
        this._fr = fr;
        this._fl = fl;
        this._br = br;
        this._bl = bl;
    }

    public void init() {
        fr = new SwerveModuleTracker(_fr);
        fl = new SwerveModuleTracker(_fl);
        br = new SwerveModuleTracker(_br);
        bl = new SwerveModuleTracker(_bl);
    }

    /**
     * Get the front-right tracker.
     *
     * @return the front-right tracker.
     */
    public SwerveModuleTracker getFr() {
        return fr;
    }

    /**
     * Get the front-left tracker.
     *
     * @return the front-left tracker.
     */
    public SwerveModuleTracker getFl() {
        return fl;
    }

    /**
     * Get the back-right tracker.
     *
     * @return the back-right tracker.
     */
    public SwerveModuleTracker getBr() {
        return br;
    }

    /**
     * Get the back-left tracker.
     *
     * @return the back-left tracker.
     */
    public SwerveModuleTracker getBl() {
        return bl;
    }

    /**
     * Get the position of a single of the chassis's trackers.
     *
     * @return the tracker's estimated position.
     */
    public PosDeg getFrPos() {
        return frPos;
    }

    /**
     * Get the position of a single of the chassis's trackers.
     *
     * @return the tracker's estimated position.
     */
    public PosDeg getFlPos() {
        return flPos;
    }

    /**
     * Get the position of a single of the chassis's trackers.
     *
     * @return the tracker's estimated position.
     */
    public PosDeg getBrPos() {
        return brPos;
    }

    /**
     * Get the position of a single of the chassis's trackers.
     *
     * @return the tracker's estimated position.
     */
    public PosDeg getBlPos() {
        return blPos;
    }

    /**
     * Get the estimated position of the robot.
     *
     * @return the estimated position of the robot.
     */
    public PosDeg getPos() {
        return pos;
    }

    /**
     * Update the tracker's cache of positions.
     */
    private void updateCache() {
        frPos = fr.getPosition();
        flPos = fl.getPosition();
        brPos = br.getPosition();
        blPos = bl.getPosition();

        pos = new PosDeg(
                (frPos.getAngle() +
                        flPos.getAngle() +
                        brPos.getAngle() +
                        blPos.getAngle()) / 4,
                (frPos.getX() +
                        flPos.getX() +
                        brPos.getX() +
                        blPos.getX()) / 4,
                (frPos.getX() +
                        flPos.getX() +
                        brPos.getX() +
                        blPos.getX()) / 4
        );
    }

    /**
     * Get the X position.
     *
     * @return the X position.
     */
    @Override
    public double getX() {
        updateCache();

        return pos.getX();
    }

    /**
     * Get the Y position.
     *
     * @return the Y position.
     */
    @Override
    public double getY() {
        updateCache();

        return pos.getY();
    }

    /**
     * Get the angle.
     *
     * @return the robot's angle.
     */
    @Override
    public double getAngle() {
        updateCache();

        return pos.getAngle();
    }

    /**
     * Get the robot's position.
     *
     * @return the robot's position.
     */
    @Override
    public PosDeg getPosition() {
        updateCache();

        return pos;
    }

    @Override
    public double getVelocity() {
        return 0.0;
    }
}
