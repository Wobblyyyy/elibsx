package me.wobblyyyy.rlibx.manager;

/**
 * An operation mode that operates entirely linearly.
 *
 * <p>
 * If you have an operation mode that should execute in a pre-defined order,
 * this is the class you'd like to use. If you'd like it to repeat as
 * frequently as possible, the RepeatingMode might be more appealing to you.
 * </p>
 *
 * @author Colin Robertson
 */
public class LinearMode extends Mode {
    /**
     * Create a new mode, using a runnable.
     *
     * <p>
     * A LinearMode will execute and then stop after its execution has
     * ended. Unlike the {@link RepeatingMode} class, LinearMode instances
     * do not repeat. They're also a lot harder to interrupt - while
     * RepeatingMode instances can be interrupted by stopping repetition,
     * linear modes need to be interrupted by interrupting the thread itself,
     * which has been deprecated since Java 1.8. Uh oh.
     * </p>
     *
     * @param runnable the mode's runnable.
     */
    public LinearMode(Runnable runnable) {
        /*
         * Pass the runnable to the super-constructor.
         *
         * LinearMode is really a wrapper class designed to clean up the
         * nomenclature of code - it has very little functional purpose other
         * than doing exactly that.
         */
        super(runnable);
    }
}
