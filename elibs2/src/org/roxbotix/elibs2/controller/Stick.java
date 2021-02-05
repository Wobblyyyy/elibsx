package org.roxbotix.elibs2.controller;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A simple joystick with two axes.
 *
 * <p>
 * I'm not 100% on how the controllers for FRC work, so some of this might
 * not make much (or any) sense. With that being said, this is a rather
 * simple implementation and abstraction of a joystick. The joystick has an X
 * and a Y axis - each of which have customizable deadzones.
 * </p>
 *
 * @author Colin Robertson
 */
public class Stick {
    /**
     * The internally-used joystick.
     */
    private final Joystick joystick;

    /**
     * The stick's X deadzone.
     */
    private double deadzone_x = 0.0;

    /**
     * The stick's Y deadzone.
     */
    private double deadzone_y = 0.0;

    /**
     * Create a new Joystick class.
     *
     * @param joystick the WPILIB joystick that's used.
     */
    public Stick(Joystick joystick) {
        this.joystick = joystick;
    }

    /**
     * Get the joystick's X value.
     *
     * @return the joystick's X value.
     */
    public double getX() {
        double x = joystick.getX();
        x = Math.abs(x) < deadzone_x ? 0.0 : x;
        return joystick.getX();
    }

    /**
     * Get the joystick's Y value.
     *
     * @return the joystick's Y value.
     */
    public double getY() {
        double y = joystick.getY();
        y = Math.abs(y) < deadzone_y ? 0.0 : y;
        return joystick.getY();
    }

    /**
     * Set the deadzone for both of the joystick's axes.
     *
     * <p>
     * This sets the deadzone for BOTH the X and Y axis. If you'd like to set
     * the deadzone for ONLY the X axis, there's a method titled the same as
     * this one but with an X on the end. The same goes for the Y deadzone.
     * </p>
     *
     * @param deadzone the new deadzone.
     */
    public void setDeadzone(double deadzone) {
        setDeadzoneX(deadzone);
        setDeadzoneY(deadzone);
    }

    /**
     * Set the deadzone for the X axis.
     *
     * @param deadzone the X axis's deadzone.
     */
    public void setDeadzoneX(double deadzone) {
        deadzone_x = deadzone;
    }

    /**
     * Set the deadzone for the Y axis.
     *
     * @param deadzone the Y axis's deadzone.
     */
    public void setDeadzoneY(double deadzone) {
        deadzone_y = deadzone;
    }
}
