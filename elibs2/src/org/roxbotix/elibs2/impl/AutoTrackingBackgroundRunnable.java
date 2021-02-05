package org.roxbotix.elibs2.impl;

import me.wobblyyyy.ezpos.link.AutoTrackerRegistry;
import org.roxbotix.elibs2.thread.RepeatingRunnable;
import org.roxbotix.elibs2.thread.SimpleThreadCore;

/**
 * A thread dedicated to tracking the positions of multiple encoders
 * using the ezpos library.
 *
 * @author Colin Robertson
 */
public class AutoTrackingBackgroundRunnable
        extends RepeatingRunnable
        implements SimpleThreadCore {
    @Override
    public void run() {
        while (run) {
            Thread.onSpinWait();
            try {
                AutoTrackerRegistry.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getName() {
        return "AutoTrackingBackgroundRunnable";
    }

    @Override
    public Runnable getRunnable() {
        return this;
    }
}
