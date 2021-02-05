package org.roxbotix.elibs2.op.test;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import org.roxbotix.elibs2.controller.Controller;
import org.roxbotix.elibs2.drive.XboxFourTank;

public class FourTankTest extends TimedRobot {
    private XboxController xboxController;
    private Controller controller;
    private XboxFourTank drive;

    @Override
    public void robotInit() {
        xboxController = new XboxController(0);
        controller = new Controller(xboxController);
        drive = new XboxFourTank(controller);

        drive.init(0, 0, 0, 0);
    }

    @Override
    public void robotPeriodic() {
        drive.update();
    }
}
