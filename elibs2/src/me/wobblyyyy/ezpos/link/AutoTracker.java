package me.wobblyyyy.ezpos.link;

import me.wobblyyyy.ezpos.common.PosDeg;

/**
 * An interface used for automatically updating tracker values.
 *
 * <p>
 * AutoTracker(s) still need to be registered in the AutoTrackerRegistry class
 * before they can be utilized. In the case of the elibs2 library, a separate
 * thread should automatically handle this for you.
 * </p>
 *
 * @author Colin Robertson
 */
public interface AutoTracker {
    /**
     * Update the tracker, and thus position.
     */
    void track();

    /**
     * Get the position of the robot as estimated by the chassis' trackers.
     *
     * @return the robot's position.
     */
    PosDeg getPosition();
}
