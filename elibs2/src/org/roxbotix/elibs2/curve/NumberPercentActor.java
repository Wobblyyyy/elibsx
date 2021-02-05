package org.roxbotix.elibs2.curve;

import me.wobblyyyy.intra.ftc2.utils.math.Math;

import java.util.HashMap;

/**
 * A utility class used in making something follow a predefined curve.
 *
 * @author Colin Robertson
 */
public class NumberPercentActor {
    /**
     * Used in allocating new numbers.
     */
    private static final double BIG = 100000000000000000000.0;

    /**
     * Key-to-value.
     * The first double is KEY.
     * The second double is VALUE.
     */
    private final HashMap<Double, Double> ktv = new HashMap<>();

    /**
     * Value-to-key.
     * The first double is VALUE.
     * The second double is KEY.
     */
    private final HashMap<Double, Double> vtk = new HashMap<>();

    /**
     * Should smoothing be applied?
     */
    private final boolean isSmooth;

    /**
     * Create a new NumberPercentActor instance.
     *
     * <p>
     * By default, new NumberPercentActor instances will have the smoothing
     * attribute set to true. If you'd like to disable this, you should go
     * to the other constructor for this class, which takes two parameters.
     * </p>
     *
     * @param values a HashMap of values. The first value represents the
     *               percentage of completion the object is currently at.
     *               This value should be between 0 and 100 at all times.
     *               The second value represents the returned value.
     */
    public NumberPercentActor(HashMap<Double, Double> values) {
        this(values, true);
    }

    /**
     * Create a new NumberPercentActor instance.
     *
     * @param values   a HashMap of values. The first value represents the
     *                 percentage of completion the object is currently at.
     *                 This value should be between 0 and 100 at all times.
     *                 The second value represents the returned value.
     * @param isSmooth should the returned values of the percent actor be
     *                 "smoothed" or not? Smooth values are always dynamic -
     *                 if you get the value at 0.5 percent and there's only
     *                 an assigned value at 0.0 percent and 1.0 percent, you'll
     *                 get the average of the assigned values for 0 and 1.
     */
    public NumberPercentActor(HashMap<Double, Double> values,
                              boolean isSmooth) {
        // For each and every one of the values in the input HashMap,
        // add those values to the KTV and VTK HashMaps.
        for (HashMap.Entry<Double, Double> e : values.entrySet()) {
            ktv.put(e.getKey(), e.getValue());
            vtk.put(e.getValue(), e.getKey());
        }

        this.isSmooth = isSmooth;
    }

    /**
     * Get a value based on a percentage of completion.
     *
     * @param percent the percentage of completion attained.
     * @return the value based on that percentage.
     */
    public double getValue(double percent) {
        if (ktv.containsKey(percent)) {
            return ktv.get(percent);
        } else {
            double nextL = BIG;
            double nextH = BIG;
            double ratio;
            double irati;
            double gap;

            for (HashMap.Entry<Double, Double> e : ktv.entrySet()) {
                double distance = Math.abs(percent - e.getKey());

                if (e.getKey() <= percent) {
                    nextL = Math.min(nextL, distance);
                }

                if (e.getKey() >= percent) {
                    nextH = Math.min(nextH, distance);
                }
            }

            ratio = nextL / nextH;
            irati = nextH / nextL;
            gap = nextH - nextL;

            if (!isSmooth) {
                // L is closer, so we round down.
                if (ratio <= 1) {
                    return ktv.get(nextL);
                }

                // H is closer, so we round up.
                else {
                    return ktv.get(nextH);
                }
            } else {
                double vL = ktv.get(percent - nextL);
                double vH = ktv.get(percent + nextH);
                return Math.average(
                        vL * ratio,
                        vH * irati
                );
            }
        }
    }

    /**
     * Get the percentage of completion based on a value.
     *
     * @param value the input value.
     * @return the percent complete the actor is.
     */
    public double getPercent(double value) {
        return 0;
    }
}
