package me.wobblyyyy.ezpos.link;

import java.util.ArrayList;

/**
 * A simple manager class used to manage all of the self-tracking components.
 *
 * <p>
 * Note that in order for any of this to work you need to have something
 * actually run the run() function here. In the case of FRC, elibs2 provides
 * a multithreading system with repeating code segments that would allow you
 * to track all of this accurately.
 * </p>
 *
 * @author Colin Robertson
 */
public class AutoTrackerRegistry {
    protected static ArrayList<AutoTracker> autoTrackers = new ArrayList<>();

    /**
     * Add a tracker to the auto tracking registry.
     *
     * @param autoTracker the auto tracker to add.
     */
    public static void addTracker(AutoTracker autoTracker) {
        autoTrackers.add(autoTracker);
    }

    /**
     * Get all of the auto trackers.
     *
     * @return all of the registered auto trackers.
     */
    public static ArrayList<AutoTracker> getAutoTrackers() {
        return autoTrackers;
    }

    /**
     * Run all of the AutoTracker(s) tracking update methods.
     */
    public static void run() {
        ArrayList<AutoTracker> toUpdate = new ArrayList<>(autoTrackers);
        for (AutoTracker tracker : toUpdate) {
            if (tracker != null) {
                tracker.track();
            }
        }
    }
}
