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
 * Basic toggle utility.
 * <p>
 * This should be mapped to a button on a controller, which is run
 * in a constant / never-ending loop environment.
 *     <ul>
 *         <li>
 *             onPress() is triggered when the button is pressed
 *             <ul>
 *                 <li>This means you should add a new mapping to a controller and have it run the function onPress.</li>
 *             </ul>
 *         </li>
 *         <li>
 *             onRelease() is triggered when the button is no longer pressed
 *             <ul>
 *                 <li>This means you should add a new mapping to a controller and have it run the function onRelease.</li>
 *             </ul>
 *         </li>
 *     </ul>
 * </p>
 *
 * @author Colin Robertson
 */
public class Toggle {
    /**
     * The state the toggle is in.
     * <p>
     * This can be either true or false, mostly because
     * it's a boolean, and that's how booleans work
     * in Java, or any language for that matter.
     * If you were to map this to the A button, it would
     * work as so...
     *     <ul>
     *         <li>
     *             It starts off in the TRUE state.
     *         </li>
     *         <li>
     *             Pressing the A button, for however long, will
     *             change that state to be false. It won't become true
     *             again until it's released once again.
     *         </li>
     *         <li>
     *             Releasing the A button will allow it to be 'toggle-able' again.
     *         </li>
     *         <li>
     *             Pressing the A button once more will once again change the state
     *             to be true.
     *         </li>
     *     </ul>
     * </p>
     * <p>
     *     You can also forcibly override the state this is in
     *     by just manually changing it. Because this is a public
     *     boolean, it's accessible from anywhere, and you can do
     *     whatever you'd please with it. Best of luck.
     * </p>
     */
    public boolean state = true;

    /**
     * Indicates whether or not the state can be changed.
     *
     * @see Toggle#onPress() :
     * This is set to FALSE once pressed.
     * @see Toggle#onRelease() :
     * ... and then set to TRUE once released.
     */
    public boolean canBeChanged = true;

    /**
     * Should be called once the button is pressed.
     * <p>
     * This already accounts for the fact that the button
     * is held down for a long time.
     * </p>
     * <p>
     * In the 2018-2019 season, as well as the 2019-2020
     * season, my team had an issue with trying to get toggles
     * to work. For some reason, I was too stupid to figure out
     * the whole solution can be implemented in less than
     * twenty lines of source code. Anyway...
     * <p>
     * This solution already accounts for the fact that,
     * because everything is run inside of a loop, the button
     * is 'pressed,' so to speak, multiple times. If you're
     * for whatever reason really interested in how this works,
     * you can feel free to read over the rest of the code.
     * <p>
     * If you really don't care, however, just trust me when I
     * say that this works A-okay.
     * </p>
     */
    public void onPress() {
        if (canBeChanged) {
            state = !state;
            canBeChanged = false;
        }
    }

    /**
     * Called when the button is not pressed.
     * <p>
     * This accounts for the fact that the button is
     * released for a while at a time (a couple loop
     * evolutions, probably). There's not much this
     * has to do, but it should work fine.
     * </p>
     */
    public void onRelease() {
        canBeChanged = true;
    }
}
