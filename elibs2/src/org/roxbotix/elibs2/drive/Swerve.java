package org.roxbotix.elibs2.drive;

import org.roxbotix.elibs2.robot.assem.drive.Swerve4Wheel;
import org.roxbotix.elibs2.robot.assem.drive.SwervePower;

/**
 * An updated and shorter Swerve drive mode.
 *
 * <p>
 * Swerve drive... isn't something that I understand very well. As a result,
 * it's very likely that some of my math here is wrong. If it is, please
 * feel free to correct it. I'd love to have to re-write a couple fewer lines
 * of code.
 * </p>
 *
 * <p>
 * Although unrelated, I have to say - god, I'm not excited to write kinematics
 * and pathfinding code for this stuff. Meccanum drive is one beast - I already
 * understand it well enough to create a pathfinder using it. Swerve drive,
 * however - well, that's where it starts to get a bit less fun.
 * </p>
 *
 * @author Colin Robertson
 */
public class Swerve implements Drive {
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
     * The internally-used drivetrain.
     */
    private Swerve4Wheel drivetrain;

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
    public void init(int[] fris,
                     int[] flis,
                     int[] bris,
                     int[] blis) {
        drivetrain = new Swerve4Wheel(
                fris,
                flis,
                bris,
                blis
        );

        drivetrain.init();
    }

    /**
     * Use updated values to correct the motor power for the robot.
     *
     * <p>
     * In other words, drive the robot.
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
        // Calculate a couple of important values that'll be used later.
        double a = lsx - rsx * (L / R);
        double b = lsx + rsx * (L / R);
        double c = lsy - rsx * (W / R);
        double d = lsy + rsx * (W / R);

        // Calculate the speed of each of the four wheels.
        double speedFr = Math.sqrt((b * b) + (d * d));
        double speedFl = Math.sqrt((b * b) + (c * c));
        double speedBr = Math.sqrt((a * a) + (d * d));
        double speedBl = Math.sqrt((a * a) + (c * c));

        // Calculate the angle of each of the four wheels.
        double turnFr = Math.atan2(b, d) / Math.PI;
        double turnFl = Math.atan2(b, c) / Math.PI;
        double turnBr = Math.atan2(a, d) / Math.PI;
        double turnBl = Math.atan2(a, c) / Math.PI;

        // Create a new SwervePower.
        SwervePower swervePower = new SwervePower(
                new double[] { speedFr, turnFr },
                new double[] { speedFl, turnFl },
                new double[] { speedBr, turnBr },
                new double[] { speedBl, turnBl }
        );

        // Set power to the drivetrain. Hopefully, that is.
        drivetrain.setPower(swervePower);
    }
}
