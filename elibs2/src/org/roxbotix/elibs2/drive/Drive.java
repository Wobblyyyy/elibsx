package org.roxbotix.elibs2.drive;

/**
 * An interface for making sure all of the types of drive are relatively
 * consistent.
 *
 * @author Colin Robertson
 */
public interface Drive {
    /**
     * Void function, used to make the drivetrain actually operate.
     *
     * @param lsx LEFT STICK X.
     * @param lsy LEFT STICK Y.
     * @param rsx RIGHT STICK X.
     * @param rsy RIGHT STICK Y.
     */
    void drive(double lsx,
               double lsy,
               double rsx,
               double rsy);
}
