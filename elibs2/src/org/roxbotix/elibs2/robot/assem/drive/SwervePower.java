package org.roxbotix.elibs2.robot.assem.drive;

/**
 * Utility class used to store power inputs for a swerve chassis.
 *
 * @author Colin Robertson
 */
public class SwervePower {
    public double[] fr;
    public double[] fl;
    public double[] br;
    public double[] bl;

    /**
     * Create a new SwervePower power holder.
     *
     * <p>
     * Each of the input parameters are ARRAYS of the length TWO. If the array
     * is over 2 in length, it's immediately thrown out. Each of these arrays
     * should only contain two values, in order, which are...
     * <ul>
     *     <li>Drive motor power.</li>
     *     <li>Turn motor angle. (IN DEGREES)</li>
     * </ul>
     * </p>
     *
     * @param fr an array containing two values, in order: drive motor power and
     *           turn motor angle (in degrees!)
     * @param fl an array containing two values, in order: drive motor power and
     *           turn motor angle (in degrees!)
     * @param br an array containing two values, in order: drive motor power and
     *           turn motor angle (in degrees!)
     * @param bl an array containing two values, in order: drive motor power and
     *           turn motor angle (in degrees!)
     */
    public SwervePower(double[] fr,
                       double[] fl,
                       double[] br,
                       double[] bl) {
        if (fr.length > 2 || fl.length > 2 || br.length > 2 || bl.length > 2) {
            throw new IllegalArgumentException("Too many arguments supplied" +
                    "to swerve drive!");
        }

        this.fr = fr;
        this.fl = fl;
        this.br = br;
        this.bl = bl;
    }
}
