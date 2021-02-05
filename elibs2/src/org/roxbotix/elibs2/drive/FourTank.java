package org.roxbotix.elibs2.drive;

import org.roxbotix.elibs2.robot.assem.drive.Independent4Wheel;
import org.roxbotix.elibs2.robot.assem.drive.MotorPower;

public class FourTank implements Drive {
    private Independent4Wheel drivetrain;

    /**
     * Initialize the drivetrain.
     *
     * @param fr front-right HWID.
     * @param fl front-left HWID.
     * @param br back-right HWID.
     * @param bl back-left HWID.
     */
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
     * Operate the drivetrain.
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
        // FR and BR are controlled by RSY
        // FL and BL are controlled by LSY
        drivetrain.setPower(
                new MotorPower(
                        rsy,
                        lsy,
                        rsy,
                        lsy
                )
        );
    }
}
