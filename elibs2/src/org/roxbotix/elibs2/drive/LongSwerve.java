package org.roxbotix.elibs2.drive;

import org.roxbotix.elibs2.robot.assem.SwerveModule;

/**
 * A swerve drive system, designed to... swerve, and drive.
 *
 * <p>
 * I'm gonna be 100% honest - entirely straight up, fully dead-ass, you get
 * what I'm trying to say... I got no idea how a swerve drive works. Not even
 * a clue. So it's pretty likely that... well, all of this math is wrong, but
 * I guess we'll just have to see, hey?
 * </p>
 *
 * @author Colin Robertson
 */
public class LongSwerve implements Drive {
    /**
     * A measurement, in inches, representing each of the swerve module's
     * distance from the center of the robot along the vertical axis.
     */
    public final double L = 0.0;

    /**
     * A measurement, in inches, representing each of the swerve module's
     * distance from the center of the robot along the horizontal axis.
     */
    public final double W = 0.0;

    /**
     * Use some classic Pythagorean Theorem type shit to figure out the
     * total distance from each of the swerve modules.
     */
    public final double R = Math.sqrt((L * L) + (W * W));

    /**
     * Front-right swerve module.
     */
    private SwerveModule frontRight;

    /**
     * Front-left swerve module.
     */
    private SwerveModule frontLeft;

    /**
     * Back-right swerve module.
     */
    private SwerveModule backRight;

    /**
     * Back-left swerve module.
     */
    private SwerveModule backLeft;

    /**
     * Initialize the swerve drive.
     *
     * @param frd front-right drive motor HWID.
     * @param frt front-right turn motor HWID.
     * @param fld front-left drive motor HWID.
     * @param flt front-left turn motor HWID.
     * @param brd back-right drive motor HWID.
     * @param brt back-right turn motor HWID.
     * @param bld back-left drive motor HWID.
     * @param blt back-left turn motor HWID.
     * @param fre front-right turn motor encoder HWID.
     * @param fle front-left turn motor encoder HWID.
     * @param bre back-right turn motor encoder HWID.
     * @param ble back-left turn motor encoder HWID.
     */
    public void init(int frd,
                     int frt,
                     int fld,
                     int flt,
                     int brd,
                     int brt,
                     int bld,
                     int blt,
                     int fre,
                     int fle,
                     int bre,
                     int ble) {
       frontRight = new SwerveModule(frd, frt, fre, 360);
       frontLeft = new SwerveModule(fld, flt, fle, 360);
       backRight = new SwerveModule(brd, brt, bre, 360);
       backLeft = new SwerveModule(bld, blt, ble, 360);

       frontRight.init();
       frontLeft.init();
       backRight.init();
       backLeft.init();
    }

    /**
     * Actually move the chassis according to a bunch of inputs.
     *
     * <p>
     * I will openly be the first person to admit it - I don't understand math
     * very well, and I especially don't understand the math behind a swerve
     * drive chassis - at all. So good luck.
     * </p>
     *
     * @param lsx LEFT STICK X.
     * @param lsy LEFT STICK Y.
     * @param rsx RIGHT STICK X.
     * @param rsy RIGHT STICK Y.
     */
    @Override
    public void drive(double lsx,
                      double lsy,
                      double rsx,
                      double rsy) {
        // Left stick = stick 1;
        // Right stick = stick 2;

        double a = lsx - rsx * (L / R);
        double b = lsx + rsx * (L / R);
        double c = lsy - rsx * (W / R);
        double d = lsy + rsx * (W / R);

        double speedFr = Math.sqrt((b * b) + (d * d));
        double speedFl = Math.sqrt((b * b) + (c * c));
        double speedBr = Math.sqrt((a * a) + (d * d));
        double speedBl = Math.sqrt((a * a) + (c * c));

        double turnFr = Math.atan2(b, d) / Math.PI;
        double turnFl = Math.atan2(b, c) / Math.PI;
        double turnBr = Math.atan2(a, d) / Math.PI;
        double turnBl = Math.atan2(a, c) / Math.PI;

        frontRight.drive(speedFr, turnFr);
        frontLeft.drive(speedFl, turnFl);
        backRight.drive(speedBr, turnBr);
        backLeft.drive(speedBl, turnBl);
    }
}
