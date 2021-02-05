package org.roxbotix.elibs2.op.template;

import org.roxbotix.elibs2.impl.AutoTrackingBackgroundRunnable;

/**
 * A multithreading-enabled template OpMode that makes use of ezpos
 * to track the robot's virtualized pose estimate.
 *
 * @author Colin Robertson
 */
public abstract class TrackingTemplate extends ThreadedController {
    static {
        threadManager.registerThread(new AutoTrackingBackgroundRunnable());
    }

    /**
     * Create a new ThreadedController instance.
     *
     * @param joystick1_id  the port of the LEFT joystick.
     * @param joystick2_id  the port of the RIGHT joystick.
     * @param controller_id the port of the Xbox controller.
     */
    public TrackingTemplate(int joystick1_id, int joystick2_id, int controller_id) {
        super(joystick1_id, joystick2_id, controller_id);
    }
}
