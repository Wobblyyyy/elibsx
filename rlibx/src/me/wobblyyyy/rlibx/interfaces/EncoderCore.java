package me.wobblyyyy.rlibx.interfaces;

/**
 * Interface used for connecting between different encoders.
 *
 * @author Colin Robertson
 */
public interface EncoderCore extends ComponentCore {
    /**
     * Get the encoder's current count.
     *
     * @return the encoder's current count.
     */
    int getCount();

    /**
     * Get the encoder's CPR.
     *
     * @return the encoder's CPR.
     */
    double getCpr();
}
