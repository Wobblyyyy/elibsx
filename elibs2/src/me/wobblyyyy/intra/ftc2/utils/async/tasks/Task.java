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

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static me.wobblyyyy.intra.ftc2.utils.async.SharedScheduler.scheduler;

/**
 * Lowest-level task functionality which provides
 * a... well, scheduled task. Not much else to
 * say about this baby.
 *
 * @author Colin Robertson
 */
@Deprecated
public class Task {
    /**
     * Supposed to be an internally used handler.
     */
    protected ScheduledFuture<?> handle;

    /**
     * The executable / runnable thing which is run.
     *
     * @see Task#getExecutable()
     * @see Task#setExecutable(Runnable)
     */
    private Runnable executable;

    /**
     * Constructor, where if no runnable is provided,
     * just make it null.
     * <p>
     * This should (hopefully) throw an error if the
     * user attempts to schedule a task with a null
     * executable, so that should work out
     * pretty nicely and what not.
     * </p>
     */
    public Task() {
        this(null);
    }

    /**
     * Constructor WITH a runnable.
     *
     * @param runnable the runnable to set as the executable
     */
    public Task(Runnable runnable) {
        executable = runnable;
    }

    /**
     * Actually schedule the task to occur in the near future
     *
     * @param time in how much time (ms) it should occur
     */
    public void schedule(int time) {
        handle = scheduler.schedule(executable, time, TimeUnit.MILLISECONDS);
    }

    /**
     * Cancel the scheduled task.
     */
    public void cancel() {
        handle.cancel(true);
    }

    /**
     * Fetches the current Runnable 'executable'
     *
     * @return the current executable
     */
    public Runnable getExecutable() {
        return executable;
    }

    /**
     * Set the Runnable 'executable'
     *
     * @param runnable the new executable
     */
    public void setExecutable(Runnable runnable) {
        executable = runnable;
    }
}
