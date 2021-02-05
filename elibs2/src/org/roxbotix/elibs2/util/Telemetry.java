package org.roxbotix.elibs2.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

/**
 * A class used for managing all things related to telemetry.
 *
 * <p>
 * In case you were unaware, telemetry is the term that is used in the FTC
 * world to do all sorts of messaging stuff. After looking through some of
 * the code from the 2020 season, it seems that "SmartDashboard" is some kind
 * of a replacement for a telemetry system. In an effort to abstract as much
 * as I can, this class serves as an overlay for anything related to telemetry.
 * </p>
 *
 * <p>
 * This class also contains methods of accessing and writing to SmartDashboard.
 * Because we're just THAT damn cool.
 * </p>
 *
 * @author Colin Robertson
 */
public class Telemetry {
    /**
     * Should SmartDashboard be used or not?
     */
    protected static boolean smartDashboard = true;

    /**
     * An array of all of the messages that need to be printed.
     */
    public static ArrayList<String> messages;

    /**
     * Write messages to SmartDashboard.
     */
    private static void writeToSmartDashboard() {
        for (String message : messages) {
            SmartDashboard.putString("[tlm]", message);
        }
    }

    /**
     * Update messages.
     *
     * <p>
     * We also clear all the messages from the "queue" here.
     * </p>
     */
    private static void updateMessages() {
        if (smartDashboard) {
            writeToSmartDashboard();
        }

        messages = new ArrayList<>();
    }

    /**
     * Add a value to the list of messages to be dispatched.
     *
     * @param a a value / variable that will be written on the screen.
     */
    public static void put(int a) {
        messages.add(a + "");
    }

    /**
     * Add a value to the list of messages to be dispatched.
     *
     * @param a a value / variable that will be written on the screen.
     */
    public static void put(double a) {
        messages.add(a + "");
    }

    /**
     * Add a value to the list of messages to be dispatched.
     *
     * @param a a value / variable that will be written on the screen.
     */
    public static void put(float a) {
        messages.add(a + "");
    }

    /**
     * Add a value to the list of messages to be dispatched.
     *
     * @param a a value / variable that will be written on the screen.
     */
    public static void put(boolean a) {
        messages.add(a + "");
    }

    /**
     * Add a value to the list of messages to be dispatched.
     *
     * @param a a value / variable that will be written on the screen.
     */
    public static void put(long a) {
        messages.add(a + "");
    }

    /**
     * Add a value to the list of messages to be dispatched.
     *
     * @param a a value / variable that will be written on the screen.
     */
    public static void put(String a) {
        messages.add(a + "");
    }
}
