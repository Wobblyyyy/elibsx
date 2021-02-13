package me.wobblyyyy.rlibx.interfaces;

import me.wobblyyyy.rlibx.util.ARGB;

/**
 * Core used for interacting with an ARGB module.
 *
 * @author Colin Robertson
 */
public interface ARGBCore extends ComponentCore {
    /**
     * Set color to a given index.
     *
     * @param index the index to set color to.
     * @param color the color to be set.
     */
    void set(int index, ARGB color);

    /**
     * Set all of the ARGB elements to the same color.
     *
     * @param color the color to set.
     */
    void set(ARGB color);
}
