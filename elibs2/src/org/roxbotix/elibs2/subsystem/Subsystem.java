package org.roxbotix.elibs2.subsystem;

/**
 * A basic interface for simplifying the process of coordinating subsystems.
 *
 * <p>
 * A subsystem is defined as anything that has one or more physical components
 * and must be initialized before use. The purpose of a subsystem interface
 * is quite simple - using a single interface allows for a programmer to
 * forgo writing tons of initialization code themselves. Additionally, this
 * allows programmers to use already-instantiated subsystems instead of having
 * to worry about that themselves.
 * </p>
 *
 * @author Colin Robertson
 */
public interface Subsystem {
    /**
     * Initialize the entire subsystem.
     *
     * <p>
     * This should ideally initialize all of the subsystem's components.
     * </p>
     */
    void init();

    /**
     * Get the name of the subsystem.
     *
     * <p>
     * This is the name that should be used when statically registering
     * a subsystem.
     * </p>
     *
     * @return the subsystem's name.
     */
    String getName();
}
