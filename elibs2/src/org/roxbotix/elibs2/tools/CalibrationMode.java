package org.roxbotix.elibs2.tools;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import me.wobblyyyy.ezpos.ChassisTracker;
import me.wobblyyyy.intra.ftc2.utils.async.event.StringEvents;
import org.roxbotix.elibs2.motor.Motor;
import org.roxbotix.elibs2.motor.MotorConfig;
import org.roxbotix.elibs2.op.template.ThreadEnabledTemplate;

/**
 * Calibration-oriented operation mode designed to improve the accuracy
 * of potential trajectory planning.
 *
 * <p>
 * In order to calibrate your robot, you need to extend this class, mark your
 * extension as a RobotMode with the annotation, and create a constructor
 * matching the super-constructor (in this case, MotorConfig, MotorConfig,
 * MotorConfig, MotorConfig, ChassisTracker).
 * </p>
 *
 * <p>
 * You can do whatever you'd like with the data that this class outputs, but
 * its primary objective is to determine how long (in seconds) it takes for
 * the robot to accelerate to its maximum speed and how long it takes to
 * decelerate from its maximum speed.
 * </p>
 *
 * @author Colin Robertson
 */
public class CalibrationMode extends ThreadEnabledTemplate {
    private final Motor fr;
    private final Motor fl;
    private final Motor br;
    private final Motor bl;
    private final ChassisTracker tracker;

    public CalibrationMode(MotorConfig fr,
                           MotorConfig fl,
                           MotorConfig br,
                           MotorConfig bl,
                           ChassisTracker tracker) {
        this.fr = new Motor(fr);
        this.fl = new Motor(fl);
        this.br = new Motor(br);
        this.bl = new Motor(bl);
        this.tracker = tracker;

        Runnable accelerate = () -> {
            this.fr.set(1.0);
            this.fl.set(1.0);
            this.br.set(1.0);
            this.bl.set(1.0);
        };
        Runnable decelerate = () -> {
            this.fr.set(0.0);
            this.fl.set(0.0);
            this.br.set(0.0);
            this.bl.set(0.0);
        };

        fRobotInit.add(
                () -> {
                    this.fr.init();
                    this.fl.init();
                    this.br.init();
                    this.bl.init();
                }
        );

        fTeleopInit.add(
                () -> {
                    StringEvents.delay(
                            0,
                            accelerate
                    );
                    StringEvents.delay(
                            5000,
                            decelerate
                    );
                }
        );

        fTeleopRegular.add(
                () -> {
                    SmartDashboard.putNumber("Velocity", tracker.getVelocity());
                }
        );
    }
}
