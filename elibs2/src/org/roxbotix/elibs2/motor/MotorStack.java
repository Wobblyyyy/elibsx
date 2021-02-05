package org.roxbotix.elibs2.motor;

import org.roxbotix.elibs2.robot.components.EncoderCore;
import org.roxbotix.elibs2.robot.components.LfComponent;
import org.roxbotix.elibs2.robot.components.MotorComponent;

import java.util.ArrayList;

public class MotorStack implements
        MotorComponent,
        MotorCore,
        LfComponent,
        EncoderCore {
    private final ArrayList<Motor> motors = new ArrayList<>();

    public MotorStack(MotorConfig... configs) {
        for (MotorConfig config : configs) {
            motors.add(
                    new Motor(
                            config
                    )
            );
        }
    }

    private void _set(double power) {
        for (Motor motor : motors) {
            motor.set(power);
        }
    }

    private double _get() {
        double total = 0;
        double count = motors.size();
        for (Motor motor : motors) {
            total += motor.get();
        }
        return total / count;
    }

    private void setOffsets(int offset) {
        for (Motor motor : motors) {
            motor.setCountOffset(offset);
        }
    }

    private int getOffsets() {
        double total = 0;
        double count = motors.size();
        for (Motor motor : motors) {
            total += motor.getCountOffset();
        }
        return (int) (total / count);
    }

    private int getAvgCount() {
        double total = 0;
        double count = motors.size();
        for (Motor motor : motors) {
            total += motor.getCount();
        }
        return (int) (total / count);
    }

    private void _zero() {
        for (Motor motor : motors) {
            motor.zeroCount();
        }
    }

    private void initMotors() {
        for (Motor motor : motors) {
            motor.init();
        }
    }

    @Override
    public void setPower(double power) {
        _set(power);
    }

    @Override
    public double getPower() {
        return _get();
    }

    @Override
    public int getCount() {
        return getAvgCount();
    }

    @Override
    public void setCountOffset(int offset) {
        setOffsets(offset);
    }

    @Override
    public int getCountOffset() {
        return getOffsets();
    }

    @Override
    public void zeroCount() {
        _zero();
    }

    @Override
    public void init() {
        initMotors();
    }

    @Override
    public void set(double power) {
        _set(power);
    }

    @Override
    public double get() {
        return _get();
    }
}
