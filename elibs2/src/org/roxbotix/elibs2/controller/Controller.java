package org.roxbotix.elibs2.controller;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

/**
 * An abstraction of the Xbox controller.
 *
 * <p>
 * The FTC library I've created, named _1125c, uses a similar system of
 * controller mapping. In the case of the FTC library, I'm using pathfinding
 * and computer vision to autonomously navigate the field during tele-op,
 * essentially making an entirely self-driving robot. In the case of this FRC
 * library, however, this is used as a common interface between different
 * input methods, just to make sure everything places nice together.
 * </p>
 *
 * <p>
 * There are a TON of fields that aren't exactly needed here. At least they
 * don't seem to be needed. However, integration with FTC code I've written
 * earlier requires the usage of some methods and what-not that aren't very
 * useful in an FTC context.
 * </p>
 *
 * <p>
 * This file is also (as usual) stupidly long due to comments, so I do
 * apologize for that. You don't have to (and probably shouldn't) bother
 * scrolling past like 400ish - that's all FTC nonsense.
 * </p>
 *
 * @author Colin Robertson
 */
public class Controller {
    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean start = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean select = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean a = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean b = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean x = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean y = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean dpad_up = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean dpad_right = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean dpad_down = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean dpad_left = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean right_bumper = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected boolean left_bumper = false;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected double right_trigger = 0.0;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected double left_trigger = 0.0;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected double left_stick_x = 0.0;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected double left_stick_y = 0.0;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected double right_stick_x = 0.0;

    /**
     * A variable used in integrating controller mapping.
     *
     * <p>
     * Because I'm a rather lazy person, the controller map code is mostly
     * the same as the controller map in my FTC library, _1125c. These variables
     * are used to integrate the FTC controller mapping with the FRC controller
     * states - simplifying the process, in a way. Maybe.
     * </p>
     */
    protected double right_stick_y = 0.0;

    /**
     * The Xbox controller itself.
     */
    private XboxController xbc;

    /**
     * The current state of the controller.
     */
    private ControllerState state;

    /**
     * The controller's controller map.
     *
     * <p>
     * Controller maps are actually pretty cool. You should go check out
     * some of the JavaDocs in the ControllerMap.java file to figure out
     * how to navigate around controller maps.
     * </p>
     */
    public final ControllerMap map;

    /**
     * Create a new controller without an Xbox controller core.
     *
     * <p>
     * This should really only ever be used if you're doing something like
     * converting a joystick to an Xbox input. Otherwise, you're better
     * off using the regular constructor.
     * </p>
     */
    public Controller() {
        map = new ControllerMap(this);
    }

    /**
     * Create a new controller, using an Xbox controller as the "core."
     *
     * @param xbc the Xbox controller to use.
     */
    public Controller(XboxController xbc) {
        this.xbc = xbc;
        map = new ControllerMap(this);
    }

    /**
     * Get the controller's map.
     *
     * @return the controller's controller map.
     */
    public ControllerMap getMap() {
        return map;
    }

    /**
     * Update the controller's state.
     *
     * <p>
     * Ideally, this should be run before any input handling is needed.
     * Not running this means that the controller isn't updating, meaning
     * nothing useful happens. Yay... I guess...
     * </p>
     */
    public void updateState() {
        // Handle regular updates.
        ControllerState n = new ControllerState();
        n.a = xbc.getAButton();
        n.b = xbc.getBButton();
        n.x = xbc.getXButton();
        n.y = xbc.getYButton();
        n.rb = xbc.getBumper(GenericHID.Hand.kRight);
        n.lb = xbc.getBumper(GenericHID.Hand.kLeft);
        n.rt = xbc.getTriggerAxis(GenericHID.Hand.kRight);
        n.lt = xbc.getTriggerAxis(GenericHID.Hand.kLeft);
        n.lsx = xbc.getX(GenericHID.Hand.kLeft);
        n.lsy = xbc.getY(GenericHID.Hand.kLeft);
        n.rsx = xbc.getX(GenericHID.Hand.kRight);
        n.rsy = xbc.getY(GenericHID.Hand.kRight);
        state = n;

        // Handle controller mapping required updates.
        a = n.a;
        b = n.b;
        x = n.x;
        y = n.y;
        right_bumper = n.rb;
        left_bumper = n.lb;
        right_trigger = n.rt;
        left_trigger = n.lt;
        left_stick_x = n.lsx;
        left_stick_y = n.lsy;
        right_stick_x = n.rsx;
        right_stick_y = n.rsy;
    }

    /**
     * Set the controller's controller state.
     *
     * <p>
     * Unless you absolutely need to for whatever reason, you should avoid
     * manually setting the state of the controller.
     * </p>
     *
     * @param state the state to set.
     */
    public void setState(ControllerState state) {
        this.state = state;
    }

    /**
     * Get the current state of the controller.
     *
     * @return the state of the controller.
     */
    public ControllerState getState() {
        return state;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isStart() {
        return start;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isSelect() {
        return select;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setSelect(boolean select) {
        this.select = select;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isA() {
        return a;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setA(boolean a) {
        this.a = a;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isB() {
        return b;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setB(boolean b) {
        this.b = b;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isX() {
        return x;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setX(boolean x) {
        this.x = x;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isY() {
        return y;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setY(boolean y) {
        this.y = y;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isDpad_up() {
        return dpad_up;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setDpad_up(boolean dpad_up) {
        this.dpad_up = dpad_up;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isDpad_right() {
        return dpad_right;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setDpad_right(boolean dpad_right) {
        this.dpad_right = dpad_right;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isDpad_down() {
        return dpad_down;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setDpad_down(boolean dpad_down) {
        this.dpad_down = dpad_down;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isDpad_left() {
        return dpad_left;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setDpad_left(boolean dpad_left) {
        this.dpad_left = dpad_left;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isRight_bumper() {
        return right_bumper;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setRight_bumper(boolean right_bumper) {
        this.right_bumper = right_bumper;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public boolean isLeft_bumper() {
        return left_bumper;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setLeft_bumper(boolean left_bumper) {
        this.left_bumper = left_bumper;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public double getRight_trigger() {
        return right_trigger;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setRight_trigger(double right_trigger) {
        this.right_trigger = right_trigger;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public double getLeft_trigger() {
        return left_trigger;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setLeft_trigger(double left_trigger) {
        this.left_trigger = left_trigger;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public double getLeft_stick_x() {
        return left_stick_x;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setLeft_stick_x(double left_stick_x) {
        this.left_stick_x = left_stick_x;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public double getLeft_stick_y() {
        return left_stick_y;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setLeft_stick_y(double left_stick_y) {
        this.left_stick_y = left_stick_y;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public double getRight_stick_x() {
        return right_stick_x;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public void setRight_stick_x(double right_stick_x) {
        this.right_stick_x = right_stick_x;
    }

    /**
     * Used exclusively for FTC related purposes - you should probably
     * entirely ignore this.
     *
     * <p>
     * If you'd like to access the state of the controller, you should use
     * the getControllerState() method instead of this.
     * </p>
     *
     * <p>
     * If you'd like to modify the state of the controller, you should modify
     * the controller class as directly as possible.
     * </p>
     */
    public double getRight_stick_y() {
        return right_stick_y;
    }
}
