package org.roxbotix.elibs2.motor;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

/**
 * The hardware core for Spark motor controllers.
 *
 * @author Colin Robertson
 */
public class SparkCore implements SharedMotorCore {
    public static final CANSparkMax.IdleMode IDLE_MODE =
            CANSparkMax.IdleMode.kCoast;

    private final int id;
    private final CANSparkMaxLowLevel.MotorType type;
    private CANSparkMax spark;

    public SparkCore(int id,
                     CANSparkMaxLowLevel.MotorType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public void setPower(double power) {
        spark.set(power);
    }

    @Override
    public double getPower() {
        return spark.get();
    }

    @Override
    public void init() {
        spark = new CANSparkMax(
                id,
                type
        );

        spark.setIdleMode(IDLE_MODE);
    }
}
