package me.wobblyyyy.ezpos.link;

/**
 * The most simple form of a positional tracker.
 *
 * <p>
 * This class doesn't actually manage to track the position of the robot
 * on its own - you're gonna need some other classes to do that for you.
 * This class, however, does serve as a basic interface-like class to
 * connect different types of positional trackers.
 * </p>
 *
 * @author Colin Robertson
 */
public abstract class CommonTracker implements PositionalTracker {
    protected final double cpr;
    protected final double diameter;
    protected final double circumference;
    protected final double countsPerInch;
    public int count;

    public CommonTracker(final double cpr,
                         final double diameter) {
        this.cpr = cpr;
        this.diameter = diameter;
        this.circumference = Math.PI * diameter;
        this.countsPerInch = cpr / circumference;
    }

    public double getInches(int count) {
        return count / countsPerInch;
    }

    public double getDistanceTravelled() {
        return count / countsPerInch;
    }

    public int getCount() {
        return count;
    }
}
