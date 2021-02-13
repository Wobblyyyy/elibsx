package me.wobblyyyy.rlibx.interfaces;

/**
 * Interface used for connecting between servos.
 *
 * @author Colin Robertson
 */
public interface ServoCore extends ComponentCore {
    /**
     * Get the servo's position.
     *
     * @return the servo's position.
     */
    double getPosition();

    /**
     * Set the servos' position.
     *
     * @param position the servo's position.
     */
    void setPosition(double position);
}
