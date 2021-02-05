package org.roxbotix.elibs2.drive;

import org.roxbotix.elibs2.robot.assem.drive.Independent4Wheel;
import org.roxbotix.elibs2.robot.assem.drive.SourcedMotorPower;

/**
 * A meccanum-enabled drivetrain. Yaya!
 *
 * <p>
 * It's probably beneficial to my sanity and the clean-ness of this codebase
 * if there's a wrapper class specifically for meccanum drive that's fully
 * integrated with a controller, but for now, this will probably do.
 * </p>
 *
 * @author Colin Robertson
 */
public class Meccanum implements Drive {
    /**
     * The meccanum system's drivetrain.
     */
    private Independent4Wheel drivetrain;

    public void init(int fr,
                     int fl,
                     int br,
                     int bl) {
        drivetrain = new Independent4Wheel(
                fr,
                fl,
                br,
                bl
        );
        drivetrain.init();
    }

    /**
     * Move the meccanum drivetrain based on a couple of values.
     *
     * <p>
     * To drive, we really only need a full joystick (x and y) and half of a
     * joystick (x or y, preferably x). Everything else can be ignored.
     * Shifter stuff is obviously an added bonus, but who knows.
     * </p>
     *
     * @param lsx the value of the drive controller's left stick X. This is
     *            more understandable as the "strafe" value of the robot.
     * @param lsy the value of the drive controller's left stick Y. This is
     *            more understandable as the "drive" value of the robot.
     * @param rsx the value of the drive controller's right stick X. This is
     *            more understandable as the "turn" value of the robot.
     */
    @Override
    public void drive(double lsx,
                      double lsy,
                      double rsx,
                      double rsy) {
        SourcedMotorPower power = new SourcedMotorPower(
                lsy + rsx + lsx,
                -lsy + rsx + lsx,
                lsy + rsx - lsx,
                -lsy + rsx - lsx,
                0
        );

        drivetrain.setPower(power);
    }

    /**
     * Set the drivetrain to run at LOW speed.
     */
    public void setLow() {
        drivetrain.setMultiplier(0.25);
    }

    /**
     * Set the drivetrain to run at MEDIUM speed.
     */
    public void setMedium() {
        drivetrain.setMultiplier(0.5);
    }

    /**
     * Set the drivetrain to run at HIGH speed.
     */
    public void setHigh() {
        drivetrain.setMultiplier(1.0);
    }
}
