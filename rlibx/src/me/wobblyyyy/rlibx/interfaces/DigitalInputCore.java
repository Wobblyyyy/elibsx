package me.wobblyyyy.rlibx.interfaces;

/**
 * Interface for connecting between different digital inputs.
 *
 * @author Colin Robertson
 */
public interface DigitalInputCore extends ComponentCore {
    /**
     * Get the digital input's state.
     *
     * @return whether or not the digital input is active.
     */
    boolean get();
}
