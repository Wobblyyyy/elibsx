package me.wobblyyyy.rlibx.input;

import me.wobblyyyy.rlibx.interfaces.ControllerCore;

/**
 * Class used for storing information about a controller input device.
 *
 * <p>
 * Controller states are cached and updated whenever the state is requested.
 * To access the state, or any information about the controller's current
 * state, you should use the {@link Controller#getState()} method.
 * </p>
 *
 * @author Colin Robertson
 */
public class Controller {
    /**
     * The controller's current state - updated whenever information about
     * the encoder's state is polled.
     */
    private ControllerState state;

    /**
     * The controller's central controller - y'know, like, the thing that
     * actually tells us the input values.
     */
    private final ControllerCore controller;

    /**
     * Create a new controller, using a controller core.
     *
     * @param controller the controller's core.
     */
    public Controller(ControllerCore controller) {
        this.controller = controller;
    }

    /**
     * Refresh the cached controller state.
     */
    private void update() {
        state = controller.getState();
    }

    /**
     * Get the controller's current state.
     *
     * @return the encoder's current state.
     */
    public ControllerState getState() {
        update();
        return state;
    }
}
