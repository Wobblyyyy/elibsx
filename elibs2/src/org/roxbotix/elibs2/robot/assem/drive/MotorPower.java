package org.roxbotix.elibs2.robot.assem.drive;

/**
 * Generic utility class used for storing the power values of four motors.
 *
 * @author Colin Robertson
 */
public class MotorPower {
    public double fr;
    public double fl;
    public double br;
    public double bl;

    public MotorPower(double fr,
                      double fl,
                      double br,
                      double bl) {
        this.fr = fr;
        this.fl = fl;
        this.br = br;
        this.bl = bl;
    }
}
