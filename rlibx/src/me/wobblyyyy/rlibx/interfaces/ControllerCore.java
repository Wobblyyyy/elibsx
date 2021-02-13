package me.wobblyyyy.rlibx.interfaces;

import me.wobblyyyy.rlibx.input.ControllerState;

/**
 * Core interface used for interfacing with a controller.
 *
 * @author Colin Robertson
 */
public interface ControllerCore extends ComponentCore {
    /**
     * Get the controller's state.
     *
     * @return the controller's state.
     */
    ControllerState getState();
}
