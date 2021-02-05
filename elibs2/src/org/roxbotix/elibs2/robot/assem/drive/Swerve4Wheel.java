package org.roxbotix.elibs2.robot.assem.drive;

import org.roxbotix.elibs2.robot.assem.SwerveModule;

/**
 * A simple swerve drive chassis.
 *
 * <p>
 * This functions almost identically to the Independent4Wheel class, except,
 * well, it's entirely different. Swerve drive obviously functions differently
 * than meccanum drive or tank drive or whatever other drive you're thinking of.
 * </p>
 *
 * <p>
 * Please note - a lot of the math that happens in regards to how the swerve
 * drive works doesn't actually happen here. If there's a mathematical error
 * somewhere, you're going to need to hunt that down in another file related
 * to swerve drive. The prime suspects, in this case, would be...
 * <ul>
 *     <li>SwerveModule.java</li>
 *     <li>PID360.java</li>
 *     <li>Swerve.java</li>
 * </ul>
 * </p>
 *
 * @author Colin Robertson
 */
public class Swerve4Wheel {
    /**
     * Different types of wheels on a swerve chassis.
     *
     * <p>
     * This isn't really needed for meccanum and tank drives, however, the
     * coding process behind a swerve chassis makes using an enum a fantastic
     * idea. Hopefully.
     * </p>
     */
    public enum wheels {
        /**
         * Front right.
         */
        FR,
        /**
         * Front left.
         */
        FL,
        /**
         * Back right.
         */
        BR,
        /**
         * Back left.
         */
        BL
    }

    /**
     * The front-right swerve module.
     */
    private SwerveModule frontRight;

    /**
     * The front-left swerve module.
     */
    private SwerveModule frontLeft;

    /**
     * The back-right swerve module.
     */
    private SwerveModule backRight;

    /**
     * The back-left swerve module.
     */
    private SwerveModule backLeft;

    /**
     * An array of IDs for a given wheel.
     *
     * <p>
     * This array should contain three values, in order.
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Encoder hardware ID.</li>
     * </ul>
     * </p>
     */
    private int[] fris;

    /**
     * An array of IDs for a given wheel.
     *
     * <p>
     * This array should contain three values, in order.
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Encoder hardware ID.</li>
     * </ul>
     * </p>
     */
    private int[] flis;

    /**
     * An array of IDs for a given wheel.
     *
     * <p>
     * This array should contain three values, in order.
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Encoder hardware ID.</li>
     * </ul>
     * </p>
     */
    private int[] bris;

    /**
     * An array of IDs for a given wheel.
     *
     * <p>
     * This array should contain three values, in order.
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Encoder hardware ID.</li>
     * </ul>
     * </p>
     */
    private int[] blis;

    /**
     * Create a new swerve chassis.
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
    public Swerve4Wheel(int[] fris,
                        int[] flis,
                        int[] bris,
                        int[] blis) {
        this.fris = fris;
        this.flis = flis;
        this.bris = bris;
        this.blis = blis;
    }

    /**
     * Initialize the swerve drivetrain.
     */
    public void init() {
        frontRight = new SwerveModule(
                fris[0],
                fris[1],
                fris[2],
                fris[3]
        );
        frontLeft = new SwerveModule(
                flis[0],
                flis[1],
                flis[2],
                flis[3]
        );
        backRight = new SwerveModule(
                bris[0],
                bris[1],
                bris[2],
                bris[3]
        );
        backLeft = new SwerveModule(
                blis[0],
                blis[1],
                blis[2],
                blis[3]
        );

        frontRight.init();
        frontLeft.init();
        backRight.init();
        backLeft.init();
    }

    /**
     * Set power to all of the drivetrain's wheels.
     *
     * <p>
     * Each of the inputted arrays should be of 2 lengths. That means they
     * only contain two doubles. I know it sounds a little bit crazy -
     * really? Arrays of length 2 only have 2 arguments? but you'll have to
     * trust me on this one here. In case you're curious about what those
     * two arguments are...
     * <ul>
     *     <li>Drive motor power.</li>
     *     <li>Turn motor ANGLE.</li>
     * </ul>
     * </p>
     *
     * <p>
     * Yes - that's right. It's turn motor ANGLE, not turn motor power.
     * The power we set to the motors is determined in the drive method
     * of each of the swerve modules.
     * </p>
     *
     * @param power the power to set to the drivetrain.
     */
    public void setPower(SwervePower power) {
        frontRight.drive(power.fr[0], power.fr[1]);
        frontLeft.drive(power.fl[0], power.fl[1]);
        backRight.drive(power.br[0], power.br[1]);
        backLeft.drive(power.bl[0], power.bl[1]);
    }
}
