/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/10/20, 10:52 PM
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

package me.wobblyyyy.intra.ftc2.utils.async.event;

import me.wobblyyyy.intra.ftc2.utils.Timed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

//import me.wobblyyyy.intra.ftc2.utils.telem.Telemetry;

/**
 * Provides a method of interacting with events using a string key.
 *
 * <p>
 * This is "the place" to go to schedule asynchronous events. There's a few things you
 * should definitely make note of here.
 * <ul>
 *     <li>These won't run if the robot freezes.</li>
 *     <li>If the robot lags, these will too.</li>
 *     <li>Scheduling a ton of async events (more than 400 per string) will crash.</li>
 *     <li>This doesn't run on a separate thread.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Rather than using Java's built-in async functionality, I'm using a bit of a custom
 * extension of that. My reasoning behind it isn't exactly all that important. You can
 * still go right ahead and use the async functionality Java provides for you - actually,
 * me.wobblyyyy.intra.ftc2.utils.async.tasks includes two classes which allow you to easily schedule
 * both repeating and non-repeating classes. However, that's not the point here. The point
 * is that you're this far into this class, and you're probably reading this because you
 * want to use it. Go on, do it.
 * </p>
 *
 * @author Colin Robertson
 */
public class StringEvents {
    /**
     * The actual map of string to events.
     *
     * <p>
     * String represents a STRING key for the event scheduler,
     * and Events represents an instance of the event scheduler.
     * </p>
     * <p>
     * In general, you'll only want to assign one event, whether
     * repeating or not, to each string / key. This makes it so you
     * can easily schedule and then cancel events, even if
     * they're repeating.
     * </p>
     * <p>
     * If you follow this practice of only assigning a single event to
     * each key, you'll never have to worry about any overlaps caused by
     * key deletion. A reason you might NOT want to follow this guideline
     * is if you have several events which should run non-stop and are
     * all part of the same functionality. If you have a set of 4 repeating
     * events which are supposed to run at the same time, putting them all
     * under a single key makes it easy to stop them all at once.
     * </p>
     */
    public static HashMap<String, Events> events = new HashMap<>();

    /**
     * Tick function which ticks all the event schedulers.
     *
     * <p>
     * Every time this is run, all of the event handler's contained
     * within the 'events' HashMap are 'ticked.' It also adds telemetry
     * saying how many handles whatever thing has.
     * </p>
     * <p>
     * This can be a processing-power consuming operation. Ticking every
     * single HashMap means iterating over a HashMap composed of other
     * HashMaps. Ideally, this should be done every run cycle in order to
     * ensure that events are executed at the right time. However, this could
     * hypothetically be run every two, or three, or even four, or whatever
     * number you could possibly imagine number of times instead if you'd
     * find that to be more helpful.
     * </p>
     */
    public static void tick() {
        for (HashMap.Entry<String, Events> entry : events.entrySet()) {
            entry.getValue().tick();
//            Telemetry.addData("StringEvents_handles_" + entry.getKey(),
//                    "Handles of " + entry.getKey(),
//                    entry.getValue().events.size() + "");
        }
    }

    /**
     * Schedules an event.
     *
     * <p>
     * This essentially just interfaces with one of the event
     * schedulers, based on whichever string key you choose to use.
     * In general, I would suggest you use something generic, such
     * as "scheduler" or something simple.
     * </p>
     *
     * <p>
     * An exert from {@link Events}'s schedule functionality JavaDoc
     * follows.
     * </p>
     *
     * <p>
     * If the delay is anything other than zero, create a new
     * scheduled event, with a duration of however long the
     * delay is. Once that event expires and the close method
     * is run, schedule another event, with the actual duration
     * and the actual event included.
     * </p>
     *
     * <p>
     * If the delay IS zero, however, calculate the expiration
     * date of the event and schedule an event for that long.
     * </p>
     *
     * <p>
     * Don't worry if this doesn't make any sense - this
     * method will handle any confusion you have and just so perfectly
     * schedule everything nicely for you.
     * </p>
     *
     * <p>
     * In general, when scheduling an event, you're going to use
     * the open and close methods contained within Timed.
     * *** MAKE SURE NOT TO FORGET TO STOP ANYTHING ***
     * ***   YOU NEED TO STOP IN THE CLOSE METHOD!  ***
     * </p>
     *
     * <p>
     * Although it might be a little bit annoying, the code
     * to schedule a timed event is a lot longer. This is mostly
     * because every timed event actually has to spawn two events
     * with slightly different expiration dates. And not only that,
     * but because 'ran' is a boolean stored in the Timed element,
     * it has to be manually set to false every single time, which
     * is why I clone the original timed and set the value to false.
     * If you're reading this and you have a better method of doing
     * this, please let me know, I'd much rather not have this poorly
     * written code just lying here.
     * </p>
     *
     * @param name         the string name of the event scheduler you'd like to access.
     *                     It's often a wise idea to store this sting in a variable so you
     *                     can easily access said string and thus easily access
     *                     said event.
     * @param duration     how long the event should last, in milliseconds. Repeating events
     *                     will re-schedule themselves during the "close" portion of this
     *                     duration (at the very end) and regular events will execute normally.
     * @param delay        the delay before the event takes place. The delay will only ever
     *                     have an effect on non-repeating events. If you'd like to modify the
     *                     duration between executions of a repeating event, change the "duration."
     * @param timed        the actual timed element which should be executed. Timed elements are
     *                     fairly complex, so I'd suggest you go check out {@link Timed}.
     * @param shouldRepeat whether or not the event should repeat itself. Repeating events are not
     *                     affected by delay. Additionally, repeating events schedule two separate
     *                     events - one to schedule another execution, and one to actually handle
     *                     the execution of the Timed you've provided.
     */
    public static void schedule(final String name,
                                final long duration,
                                final long delay,
                                final Timed timed,
                                final boolean shouldRepeat) {
        if (events.containsKey(name)) {
            Events ev = events.get(name);
            assert ev != null;
            ev.schedule(duration, (int) delay, timed, shouldRepeat);
        } else {
            Events ev = new Events();
            ev.schedule(duration, (int) delay, timed, shouldRepeat);
            events.put(name, ev);
        }
    }

    /**
     * Delay an event but a set amount of time.
     *
     * @param delay    the length of time to delay the event.
     * @param runnable the event that should be run.
     */
    public static void delay(final long delay,
                             final Runnable runnable) {
        schedule(
                "delay",
                0,
                delay,
                new Timed() {
                    @Override
                    public Runnable open() {
                        return new Runnable() {
                            @Override
                            public void run() {
                                runnable.run();
                            }
                        };
                    }
                },
                false
        );
    }

    /**
     * Delete a string key thingy.
     * <p>
     * This makes it so none of the events contained within that
     * specific event scheduler do anything at all.
     * </p>
     * <p>
     * You can use this to 'cancel' an event you've scheduled
     * in the past.
     * </p>
     *
     * @param name the key to delete
     */
    public static void clear(final String name) {
        events.remove(name);
    }

    /**
     * Return a single event based on a name string.
     * <p>
     * This just returns the first element in the array list
     * of all of the elements, gathered from {@link StringEvents#queryAll(String)}
     * </p>
     *
     * @param name the key to query for
     * @return the first scheduled Timed under a certain query.
     */
    public static Timed query(final String name) {
        return queryAll(name).get(0);
    }

    /**
     * Get an array list of all the scheduled events under a key.
     * <p>
     * If you only want to get a single Timed, which is, most
     * of the time, the case, you should instead use the
     * {@link StringEvents#query(String)} method.
     * </p>
     *
     * @param name the key you'd like to query.
     * @return an ArrayList of Timed elements from the key
     */
    public static ArrayList<Timed> queryAll(final String name) {
        ArrayList<Timed> list = new ArrayList<>();
        if (events.containsKey(name)) {
            for (HashMap.Entry<Long, Timed> entry : Objects.requireNonNull(
                    events.get(name)).events.entrySet()) {
                list.add(entry.getValue());
            }
        }
        return list;
    }
}
