/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/10/20, 5:09 PM
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

import me.wobblyyyy.intra.ftc2.utils.async.event.StringEvents;

/**
 * A type of command which runs while a condition is true.
 * <p>
 * I'm sorry this is written so incredibly poorly, but hey,
 * it still works, so who's really complaining?
 * </p>
 * <p>
 * This ***should*** help with event-driven stuff, because it
 * lets you run something while and only while something else
 * is working.
 * <p>
 * Look, I have to say, I'm really into this whole event system
 * I created. I may or may not be stroking my cock over some shitty
 * code, but, hey. It's okay.
 * </p>
 *
 * @author Colin Robertson
 */
public abstract class WhileCommand {
    /**
     * Somewhat deprecated, used to make sure no two
     * events are scheduled at the same time by mistake.
     * <p>
     * After the implementation of StringEvents, we no longer
     * have a need to be so sure no two events are scheduled at the
     * same time, mostly because the StringEvents compartmentalizes
     * where events are scheduled, and, more importantly, the updated
     * Events manager class makes sure nothing is ever scheduled at the
     * same exact time.
     * </p>
     */
    public static int count = 0;

    /**
     * Each and every one of these sexy motherfuckers makes sure there's
     * no conflicts at all.
     * <p>
     * Automatically generate a new string to make sure no two StringEvents
     * are created, so nothing overlaps. In hindsight, this is probably
     * a little bit stupid, but hey, don't worry about it!
     * </p>
     */
    private String stringName = "_wc_" + "17398729385"; // StringGenerator.getSaltString();

    /**
     * Internally used running command.
     * <p>
     * This is the part which actually interacts with the
     * StringEvents interface to schedule events which run about
     * 10 MS later.
     * </p>
     * <p>
     * In order to make sure nothing ever overlaps, each and every
     * WhileCommand has its own string name.
     * </p>
     * <p>
     * If the condition is no longer the case, remove the StringEvent from
     * the static string events manager to OPTIMIZE everything, of course.
     * </p>
     *
     * @param runnable the Runnable which should be run if check() is true.
     */
    private void _run(Runnable runnable) {
        if (check()) {
            StringEvents.schedule(
                    stringName,
                    10,
                    0,
                    new Timed() {
                        @Override
                        public Runnable close() {
                            return new Runnable() {
                                @Override
                                public void run() {
                                    active().run();
                                    _run(active());
                                }
                            };
                        }
                    },
                    false
            );
        } else {
            StringEvents.clear(stringName);
        }
    }

    /**
     * Abstract so the user has to override it.
     * <p>
     * Returns a runnable, which is what should be run
     * while the condition is still true.
     * TODO: maybe used a Timed() or something similar instead for more event options?
     * </p>
     *
     * @return a Runnable which should be run if active.
     */
    public abstract Runnable active();

    /**
     * Abstract so the user has to override it.
     * <p>
     * Returns a condition (true or false).
     * If the condition is true, run active(). However,
     * if the condition is not true, deallocate the
     * string event handler thingy.
     * </p>
     *
     * @return whether or not the event should keep going.
     */
    public abstract boolean check();

    /**
     * 'Schedules' the while command.
     * <p>
     * Schedule is in quotes because that's not exactly what it
     * does, as the WhileCommands don't really work like regularly
     * scheduled asynchronous events.
     * </p>
     */
    public final void scheduleWhileCommand() {
        _run(active());
        count++;
    }
}
