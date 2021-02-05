/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/10/20, 5:52 PM
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
 * Standardized implementation of CommandCore.
 *
 * <p>
 * For use in Command mapping (think ControllerMap).
 * I really can't think of too many other purposes, but yeah,
 * you get what I'm saying.
 * </p>
 *
 * @author Colin Robertson
 */
public class Command implements CommandCore {
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
    public Runnable active() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

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
    public Runnable inactive() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    /**
     * Returns whichever runnable is appropriate based on the input.
     *
     * @param state true / false, active / inactive
     * @return active or inactive runnable
     */
    public final Runnable getRunnable(boolean state) {
        return state ? active() : inactive();
    }
}
