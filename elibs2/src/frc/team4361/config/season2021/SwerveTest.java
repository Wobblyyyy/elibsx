package frc.team4361.config.season2021;

import frc.team4361.config.Common;
import frc.team4361.config.Element;
import frc.team4361.config.RobotConfig;

public class SwerveTest extends RobotConfig {
    public SwerveTest() {
        super(
                new Element(
                        Common.DRIVE_FRD,
                        0
                ),
                new Element(
                        Common.DRIVE_FRT,
                        0
                ),
                new Element(
                        Common.DRIVE_FLD,
                        0
                ),
                new Element(
                        Common.DRIVE_FLT,
                        0
                ),

                new Element(
                        Common.DRIVE_BRD,
                        0
                ),
                new Element(
                        Common.DRIVE_BRT,
                        0
                ),
                new Element(
                        Common.DRIVE_BLD,
                        0
                ),
                new Element(
                        Common.DRIVE_BLT,
                        0
                )
        );
    }
}
