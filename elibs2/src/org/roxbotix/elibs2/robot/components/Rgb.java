package org.roxbotix.elibs2.robot.components;

/**
 * Very minimal class used for storing R, G, and B values.
 *
 * @author Colin Robertson
 */
public class Rgb {
    /**
     * red
     */
    int r;

    /**
     * green
     */
    int g;

    /**
     * blue
     */
    int b;

    /**
     * Create a new Rgb color.
     *
     * <p>
     * All inputs are automatically clipped to fit within the range of
     * 0 to 255, inclusive.
     * </p>
     *
     * @param r red value.
     * @param g green value.
     * @param b blue value.
     */
    public Rgb(int r,
               int g,
               int b) {
        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));

        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getG() {
        return g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getB() {
        return b;
    }
}
