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

package me.wobblyyyy.intra.ftc2.utils;

/**
 * Basic, and really low level, interface for commands.
 * Just ensures that the Command itself isn't missing anything
 * really stupid and simple, like the getRunnable function, which
 * is required for the way I have CommandMaps set up.
 *
 * @author Colin Robertson
 */
public interface CommandCore {
    /**
     * What's run when ACTIVE state is determined.
     *
     * <p>
     * In the case of controller mapping, active is when the condition is met.
     * <ul>
     *     <li>For buttons, this means the button is pressed.</li>
     *     <li>For joysticks, this means the stick is in a non-zero pos.</li>
     *     <li>For triggers, this means the trigger is in a non-zero pos.</li>
     * </ul>
     * </p>
     *
     * @return runnable, for the active state.
     */
    Runnable active();

    /**
     * What's run when INACTIVE state is determined.
     *
     * <p>
     * In the case of controller mapping, active is when the condition isn't met.
     * <ul>
     *     <li>For buttons, this means the button is not pressed.</li>
     *     <li>For joysticks, this means the stick is in a zero pos.</li>
     *     <li>For triggers, this means the trigger is in a zero pos.</li>
     * </ul>
     * </p>
     *
     * @return runnable, for the inactive state.
     */
    Runnable inactive();

    /**
     * Gets whichever runnable is appropriate based on
     * the boolean input.
     *
     * @param state true / false, active / inactive
     * @return runnable, either active or inactive runnable
     */
    Runnable getRunnable(boolean state);
}
