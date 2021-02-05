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
 * Wrapper over Command which adds toggle functionality.
 * <p>
 * Useful for things like temporarily disabling user
 * input or autonomous functionality.
 * Whenever asynchronous events are dispatched from the
 * Events class, user input can possibly override that.
 * We don't want that to happen, so toggleable commands
 * make user input temporarily not matter.
 * </p>
 * <p>
 * Through the enable and disable methods, you can control
 * when input matters. ie. user input doesn't matter unless
 * the user does something, at which point the user input
 * starts to matter again (manual override functionality)
 * </p>
 *
 * @author Colin Robertson
 */
public class ToggleableCommand implements CommandCore {
    /**
     * The boolean which determines whether or not
     * the runnable is actually active.
     */
    private boolean isActive = true;

    /**
     * Enable the toggle, making the runnable
     * do whatever it was originally going to.
     */
    public void enable() {
        isActive = true;
    }

    /**
     * Disable the toggle, making the runnable
     * do absolutely nothing.
     */
    public void disable() {
        isActive = false;
    }

    /**
     * See whether or not the runnable is in it's active mode.
     *
     * @return isActive
     */
    public boolean isActive() {
        return isActive;
    }

    public Runnable overrideActive() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    public Runnable active() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    public Runnable inactive() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    /**
     * Gets a runnable
     *
     * @param state true / false, active / inactive
     * @return either a new runnable if inactive or the actual runnable if active
     */
    public final Runnable getRunnable(boolean state) {
        overrideActive().run();
        if (isActive) {
            return state ? active() : inactive();
        } else {
            return new Runnable() {
                @Override
                public void run() {

                }
            };
        }
    }
}
