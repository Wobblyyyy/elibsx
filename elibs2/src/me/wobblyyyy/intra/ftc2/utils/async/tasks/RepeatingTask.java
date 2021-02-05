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

package me.wobblyyyy.intra.ftc2.utils.async.tasks;

import java.util.concurrent.TimeUnit;

import static me.wobblyyyy.intra.ftc2.utils.async.SharedScheduler.scheduler;

/**
 * Higher-level implementation of a scheduled task.
 * <p>
 * This allows you to schedule a task to repeatedly happen, as in
 * over and over again.
 * </p>
 * TODO: add a way to change the initial DELAY before the fixed rate executable runs
 *
 * @author Colin Robertson
 */
@Deprecated
public class RepeatingTask extends Task {
    private int delay;

    public RepeatingTask() {
        super();
    }

    public RepeatingTask(Runnable runnable) {
        super(runnable);
    }

    public void scheduleRepeatingTask(int delay) {
        handle = scheduler.scheduleAtFixedRate(getExecutable(), 0, delay, TimeUnit.MILLISECONDS);
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
