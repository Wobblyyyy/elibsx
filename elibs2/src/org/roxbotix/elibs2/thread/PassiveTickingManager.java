package org.roxbotix.elibs2.thread;

import me.wobblyyyy.intra.ftc2.utils.async.event.StringEvents;

import java.util.ArrayList;

/**
 * Passively manage the ticking of the StringEvents system.
 *
 * @author Colin Robertson
 */
public class PassiveTickingManager
        extends RepeatingRunnable
        implements SimpleThreadCore {
    private static ArrayList<Runnable> toRun = new ArrayList<>();
    private static int rpe = 0;
    private static long time = 0;
    private static long exec = 0;
    private static long total = 0;

    @Override
    public void run() {
        while (run) {
            Thread.onSpinWait();

            exec = System.currentTimeMillis() - time;
            time = System.currentTimeMillis();

            ArrayList<Runnable> runNow = new ArrayList<>(toRun);
            toRun.clear();

            for (Runnable r : runNow) {
                try {
                    if (r != null) r.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            rpe = runNow.size();
            total += rpe;

            StringEvents.tick();
        }
    }

    @Override
    public String getName() {
        return "PassiveTickingManager";
    }

    @Override
    public Runnable getRunnable() {
        return this;
    }

    public static void addToRun(Runnable r) {
        toRun.add(r);
    }

    public static int getPerExec() {
        return rpe;
    }

    public static int getMsTime() {
        return (int) exec;
    }

    public static double getPerSec() {
        exec = (long) (exec == 0 ? 1.0 : (double) exec);
        return rpe * (1000 / (exec * 1.0));
    }

    public static long getTotal() {
        return total;
    }
}
