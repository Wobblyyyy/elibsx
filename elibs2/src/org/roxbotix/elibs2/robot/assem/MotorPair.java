package org.roxbotix.elibs2.robot.assem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import org.roxbotix.elibs2.robot.components.RbComponent;
import org.roxbotix.elibs2.robot.components.SpMotor;

/**
 * A pair of motors that change change speed at the same time.
 *
 * <p>
 * Most useful in a situation like a tank drive - the two left motors and
 * the two right motors both move together at the same time.
 * </p>
 *
 * <p>
 * The multipliers should only be modified if there's an issue with polarity.
 * For example, if the first motor and the second motor are moving in opposite
 * directions, you can change the multiplier of one of the motors to -1.
 * </p>
 *
 * @author Colin Robertson
 */
public class MotorPair extends RbComponent {
    /**
     * The first of the two motors.
     */
    public SpMotor motor1;

    /**
     * The second of the two motors.
     */
    public SpMotor motor2;

    /**
     * The number by which the input power is multiplied by before setting
     * power to the first of the two motors.
     */
    private double motor1Multiplier = 1.0;

    /**
     * The number by which the input power is multiplied by before setting
     * power to the second of the two motors.
     */
    private double motor2Multiplier = 1.0;

    /**
     * Create a new motor pair.
     *
     * @param hwId1     the first motor's hardware id.
     * @param hwId2     the second motor's hardware id.
     * @param motorType the type of the two motors.
     * @param idleMode  the idle mode of the two motors.
     * @param inputMode the input mode of the two motors.
     */
    public MotorPair(int hwId1,
                     int hwId2,
                     CANSparkMaxLowLevel.MotorType motorType,
                     CANSparkMax.IdleMode idleMode,
                     CANSparkMax.InputMode inputMode) {
        motor1 = new SpMotor(
                hwId1,
                motorType,
                idleMode,
                inputMode
        );

        motor2 = new SpMotor(
                hwId2,
                motorType,
                idleMode,
                inputMode
        );
    }

    /**
     * Initialize the two motors.
     */
    @Override
    public void init() {
        motor1.init();
        motor2.init();
    }

    /**
     * Set the multiplier of the first motor.
     *
     * @param motor1Multiplier the first motor's multiplier.
     */
    public void setMotor1Multiplier(double motor1Multiplier) {
        this.motor1Multiplier = motor1Multiplier;
    }

    /**
     * Set the multiplier of the second motor.
     *
     * @param motor2Multiplier the second motor's multiplier.
     */
    public void setMotor2Multiplier(double motor2Multiplier) {
        this.motor2Multiplier = motor2Multiplier;
    }

    /**
     * Set power to both of the motors.
     *
     * @param power power to set to the motors.
     */
    public void setPower(double power) {
        motor1.setPower(power * motor1Multiplier);
        motor2.setPower(power * motor2Multiplier);
    }
}
