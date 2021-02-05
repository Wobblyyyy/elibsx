package org.roxbotix.elibs2.subsystem.subsystems;

import org.roxbotix.elibs2.drive.Drive;
import org.roxbotix.elibs2.drive.Swerve;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;

/**
 * A beautiful swerve subsystem! Yay! How exciting, right?
 *
 * <p>
 * This uses the Subsystems and SubsystemManager class to simplify the
 * registration and usage of different systems. If you're confused about how
 * that works, you should either read the JavaDocs or ask someone for help
 * on what to do.
 * </p>
 *
 * @author Colin Robertson
 */
public class SwerveSystem implements Subsystem, Drive {
    static {
        Subsystems.registerSubsystem(
                "drivetrain_swerve",
                new SwerveSystem(
                        new int[] { 0, 0, 0 }, // Front-right
                        new int[] { 0, 0, 0 }, // Front-left
                        new int[] { 0, 0, 0 }, // Back-right
                        new int[] { 0, 0, 0 }  // Back-left
                )
        );
    }

    /**
     * The internally-used swerve drivetrain.
     */
    private final Swerve swerve;

    /**
     * Front-right IDs.
     */
    private final int[] fris;

    /**
     * Front-left IDs.
     */
    private final int[] flis;

    /**
     * Back-right IDs.
     */
    private final int[] bris;

    /**
     * Back-left IDs.
     */
    private final int[] blis;

    /**
     * Initialize the drivetrain. I'd strongly suggest you read the rest of this
     * JavaDoc if you'd like to have an idea as to what's going on here.
     *
     * <p>
     * Each of the inputted arrays should be of length FOUR - meaning there
     * is FOUR different integers in the array. Those integers are as follows.
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Turn motor encoder hardware ID.</li>
     *     <li>Turn motor encoder's CPR (counts per rotation).</li>
     * </ul>
     * </p>
     *
     * @param fris the hardware IDs for the front-right motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param flis the hardware IDs for the front-left motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param bris the hardware IDs for the back-right motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param blis the hardware IDs for the back-left motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     */
    public SwerveSystem(int[] fris,
                        int[] flis,
                        int[] bris,
                        int[] blis) {
        this.fris = fris;
        this.flis = flis;
        this.bris = bris;
        this.blis = blis;

        swerve = new Swerve();
    }

    @Override
    public void init() {
        swerve.init(
                fris,
                flis,
                bris,
                blis
        );
    }

    @Override
    public String getName() {
        return "Swerve Subsystem";
    }

    /**
     * Drive the drivetrain.
     *
     * @param lsx LEFT STICK X
     * @param lsy LEFT STICK Y
     * @param rsx RIGHT STICK X
     * @param rsy RIGHT STICK Y
     */
    @Override
    public void drive(double lsx,
                      double lsy,
                      double rsx,
                      double rsy) {
        swerve.drive(
                lsx,
                lsy,
                rsx,
                rsy
        );
    }

    /**
     * Get the internal swerve system.
     *
     * @return the swerve system that's being used internally.
     */
    public Swerve getSwerve() {
        return swerve;
    }
}
