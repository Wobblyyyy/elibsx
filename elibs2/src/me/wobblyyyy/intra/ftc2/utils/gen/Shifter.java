/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/10/20, 10:58 PM
 * Part of the _1125c library
 *
 * **
 *
 * Permission is granted, free of charge, to any person obtaining
 * a copy of this software and / or any of it's related source code or
 * documentation ("Software") to copy, merge, modify, publish,
 * distribute, sublicense, and / or sell copies of Software.
 *
 * All Software included is provided in an "as is" state, without any
 * type or form of warranty. The Authors and Copyright Holders of this
 * piece of software, documentation, or source code waive all
 * responsibility and shall not be liable for any claim, damages, or
 * other forms of liability, regardless of the form it may take.
 *
 * Any form of re-distribution of Software is required to have this same
 * copyright notice included in any source files or forms of documentation
 * which have stemmed or branched off of the original Software.
 *
 * **
 *
 */

package me.wobblyyyy.intra.ftc2.utils.gen;

/**
 * Basic shifter function.
 * <p>
 * This works similarly to how Toggle works.
 * If you really need this to be explained, there's a little
 * bit of an issue. But, to be fair, why the hell are you looking at this anyway?
 * </p>
 *
 * @author Colin Robertson
 */
public class Shifter {
    /**
     * The current "gear" that the shifter is in.
     * <p>
     * This is an integer which should always stay
     * inside the bounds of min and max gears.
     * </p>
     */
    private int currentGear;

    /**
     * The highest attainable gear.
     * <p>
     * No gear shift above this gear is possible.
     * If cycling is turned on, the gear
     * will immediately drop down to the lowest
     * gear, rather than just staying there.
     * If cycling isn't turned on, trying to shift
     * up at this point will accomplish absolutely
     * nothing at all.
     * </p>
     */
    private int maxGear;

    /**
     * The lowest attainable gear.
     * <p>
     * No gear shift below this gear is possible.
     * If cycling is turned on, the gear
     * will immediately go up to the highest
     * gear, rather than just staying there.
     * If cycling isn't turned on, trying to shift
     * down at this point will accomplish absolutely
     * nothing at all.
     * </p>
     */
    private int minGear;

    /**
     * Similar to how the Toggle class' can shift works.
     *
     * @see Toggle#canBeChanged
     */
    private boolean canShift = true;

    /**
     * Default constructor if no min and max gears
     * are provided.
     * <p>
     * This creates a new shifter with...
     *     <ul>
     *         <li>Minimum gear of 1</li>
     *         <li>Maximum gear of 10</li>
     *         <li>Current gear of 1</li>
     *     </ul>
     * </p>
     */
    public Shifter() {
        this(1, 10, 1);
    }

    /**
     * Regular constructor (not overloaded)
     *
     * @param current the gear the shifter is currently in
     * @param max     the highest gear the shifter can possibly be in
     * @param min     the lowest gear the shifter can possibly be in
     */
    public Shifter(int current, int max, int min) {
        this.currentGear = current;
        this.maxGear = max;
        this.minGear = min;
    }

    /**
     * When the shift up button is pressed.
     *
     * @see Toggle#onPress()
     */
    public void onPressShiftUp() {
        if (canShift && currentGear + 1 <= maxGear) {
            currentGear++;
            canShift = false;
        }
    }

    /**
     * When the shift down button is pressed.
     *
     * @see Toggle#onPress()
     */
    public void onPressShiftDown() {
        if (canShift && currentGear - 1 >= minGear) {
            currentGear--;
            canShift = false;
        }
    }

    /**
     * When neither the shift up nor the shift down button is pressed.
     *
     * @see Toggle#onRelease()
     */
    public void onRelease() {
        canShift = true;
    }

    /**
     * Gets the shifter's current gear.
     *
     * @return the gear the shifter is currently in
     */
    public int getCurrentGear() {
        return currentGear;
    }

    /**
     * Override whatever gear the shifter is in
     *
     * @param currentGear the new gear
     */
    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    /**
     * Gets the highest possible gear.
     *
     * @return max gear
     */
    public int getMaxGear() {
        return maxGear;
    }

    /**
     * Sets the highest possible gear.
     *
     * @param maxGear max gear
     */
    public void setMaxGear(int maxGear) {
        this.maxGear = maxGear;
    }

    /**
     * Gets the lowest possible gear.
     *
     * @return min gear
     */
    public int getMinGear() {
        return minGear;
    }

    /**
     * Sets the lowest possible gear.
     *
     * @param minGear min gear
     */
    public void setMinGear(int minGear) {
        this.minGear = minGear;
    }

    /**
     * Gets whether or not it can shift up / down.
     *
     * @return boolean indicating whether or not it can shift
     */
    public boolean isCanShift() {
        return canShift;
    }

    /**
     * Overrides if it can shift or not
     *
     * @param canShift whether the shifter can shift or not
     */
    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }
}
