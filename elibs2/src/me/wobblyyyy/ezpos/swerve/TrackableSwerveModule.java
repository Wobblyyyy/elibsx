package me.wobblyyyy.ezpos.swerve;

import me.wobblyyyy.ezpos.EncoderInterface;

/**
 * A swerve module that's capable of being tracked.
 *
 * @author Colin Robertson
 */
public interface TrackableSwerveModule {
    /**
     * The drive encoder's interface.
     *
     * @return the drive encoder's interface.
     */
    EncoderInterface getDriveEncoder();

    /**
     * The turn encoder's interface.
     *
     * @return the turn encoder's interface.
     */
    EncoderInterface getTurnEncoder();

    /**
     * Get the drive encoder's CPR.
     *
     * @return the drive encoder's CPR.
     */
    double getDriveCpr();

    /**
     * Get the turn encoder's CPR.
     *
     * @return the turn encoder's CPR.
     */
    double getTurnCpr();

    /**
     * Get the wheel's diameter.
     *
     * @return the diameter of the wheel.
     */
    double getWheelDiameter();
}
