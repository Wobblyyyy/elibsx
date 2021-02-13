package me.wobblyyyy.rlibx.util;

/**
 * Storage class used for storing an ARGB value.
 *
 * @author Colin Robertson
 */
public class ARGB {
    /**
     * ALPHA value.
     */
    private final double A;

    /**
     * RED value.
     */
    private final double R;

    /**
     * GREEN value.
     */
    private final double G;

    /**
     * BLUE value.
     */
    private final double B;

    /**
     * Create a new ARGB color.
     *
     * @param a ALPHA value.
     * @param r RED value.
     * @param g GREEN value.
     * @param b BLUE value.
     */
    public ARGB(double a,
                double r,
                double g,
                double b) {
        A = a;
        R = r;
        G = g;
        B = b;
    }

    /**
     * Get ALPHA.
     *
     * @return ALPHA.
     */
    public double getA() {
        return A;
    }

    /**
     * Get RED.
     *
     * @return RED.
     */
    public double getR() {
        return R;
    }

    /**
     * Get GREEN.
     *
     * @return GREEN.
     */
    public double getG() {
        return G;
    }

    /**
     * Get BLUE.
     *
     * @return BLUE.
     */
    public double getB() {
        return B;
    }
}
