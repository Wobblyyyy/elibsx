package org.roxbotix.elibs2.controller;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Used to emulate an Xbox controller by using two joysticks.
 *
 * <p>
 * As Controller is designed to be a be-all-end-all type class for managing how
 * a controller works and how its inputs are handled, it would be ineffective
 * to create a new class that emulates a controller but with two sticks. So,
 * instead of being ineffective, this class should (hopefully) emulate a regular
 * Controller using two sticks by updating the controller's state in accordance
 * to the joystick's inputs.
 * </p>
 *
 * <p>
 * In other words, this is an Xbox controller without the buttons - just
 * the joysticks. Presumably, buttons will be needed for something at some
 * point. However, for now, this should work just fine for just two joysticks.
 * </p>
 *
 * @author Colin Robertson
 */
public class DualStickConverter extends Controller {
    /**
     * The left joystick.
     */
    private final Stick left;

    /**
     * The right joystick.
     */
    private final Stick right;

    /**
     * Create a new interface for converting between a pair of joysticks and
     * a fully-functional controller.
     *
     * @param left  the left joystick to be used.
     * @param right the right joystick to be used.
     */
    public DualStickConverter(Joystick left,
                              Joystick right) {
        this.left = new Stick(left);
        this.right = new Stick(right);
    }

    /**
     * Create a new interface for converting between a pair of joysticks and
     * a fully-functional controller.
     *
     * @param left  the left joystick.
     * @param right the right joystick.
     */
    public DualStickConverter(Stick left,
                              Stick right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Update the controller's state.
     */
    @Override
    public void updateState() {
        ControllerState n = new ControllerState();
        n.lsx = left.getX();
        n.lsy = left.getY();
        n.rsx = right.getX();
        n.rsy = right.getY();
        setState(n);
    }
}
