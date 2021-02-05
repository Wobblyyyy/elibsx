package org.roxbotix.elibs2.motor;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

/**
 * The hardware core for Talon motor controllers.
 *
 * @author Colin Robertson
 */
public class TalonCore implements SharedMotorCore {
    private final int id;

    private TalonSRX talon;

    public TalonCore(int id) {
        this.id = id;
    }

    @Override
    public void setPower(double power) {
        talon.set(PercentOutput, power);
    }

    @Override
    public double getPower() {
        return 0;
    }

    @Override
    public void init() {
        talon = new TalonSRX(id);
    }
}
