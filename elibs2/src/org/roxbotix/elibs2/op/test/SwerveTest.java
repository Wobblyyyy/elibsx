package org.roxbotix.elibs2.op.test;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import org.roxbotix.elibs2.controller.Controller;
import org.roxbotix.elibs2.drive.XboxLongSwerve;

public class SwerveTest extends TimedRobot {
    private XboxController xboxController;
    private Controller controller;
    private XboxLongSwerve drive;

    @Override
    public void robotInit() {
        xboxController = new XboxController(0);
        controller = new Controller(xboxController);
        drive = new XboxLongSwerve(controller);

        drive.init(
                0, // front-right drive
                0, // front-right turn
                0, // front-left drive
                0, // front-left turn
                0, // back-right drive
                0, // back-right turn
                0, // back-left drive
                0, // back-left turn

                0, // front-right encoder
                0, // front-left encoder
                0, // back-right encoder
                0  // back-left encoder
        );
    }

    @Override
    public void robotPeriodic() {
        drive.update();
    }
}
