package org.roxbotix.elibs2.drive;

import me.wobblyyyy.intra.ftc2.utils.Timed;
import me.wobblyyyy.intra.ftc2.utils.async.event.StringEvents;
import org.roxbotix.elibs2.controller.Controller;

/**
 * Xbox-integrated meccanum drive.
 *
 * @author Colin Robertson
 */
public class XboxMeccanum {
    private final Controller controller;

    private double lsx;
    private double lsy;
    private double rsx;
    private double rsy;

    public Meccanum drive;

    public XboxMeccanum(Controller controller) {
        this.controller = controller;
    }

    /**
     * Initialize the drivetrain.
     *
     * @param fr front-right HWID.
     * @param fl front-left HWID.
     * @param br back-right HWID.
     * @param bl back-left HWID.
     */
    public void init(int fr,
                     int fl,
                     int br,
                     int bl) {
        drive.init(
                fr,
                fl,
                br,
                bl
        );
    }

    /**
     * Enable the automatic updating of the drivetrain - the drivetrain's
     * movements will be controlled by the user, but the updating of the
     * input parameters will be handled autonomously.
     *
     * <p>
     * Note that this requires StringEvents to be ticked regularly in order
     * for anything interesting here to happen. If StringEvents is not ticked
     * regularly, or this method is not called, the drivetrain won't actually
     * be updated automatically.
     * </p>
     */
    public void enableAuto() {
        StringEvents.schedule(
                "XboxMeccanum",
                10,
                0,
                new Timed() {
                    @Override
                    public Runnable open() {
                        return new Runnable() {
                            @Override
                            public void run() {
                                update();
                            }
                        };
                    }
                },
                true
        );
    }

    /**
     * Disable the automatically ticking driving update.
     */
    public void disableAuto() {
        StringEvents.clear("XboxMeccanum");
    }

    /**
     * Update the controller's values, get the values, and then update
     * the drivetrain's drive values.
     *
     * <p>
     * Ideally, this should be called as frequently as possible to provide the
     * highest degree of moving accuracy. However, we all know that's not
     * possible. 10ms should be an alright delay.
     * </p>
     */
    public void update() {
        controller.updateState();
        lsx = controller.getState().lsx;
        lsy = controller.getState().lsy;
        rsx = controller.getState().rsx;
        rsy = controller.getState().rsy;
        drive.drive(lsx, lsy, rsx, rsy);
    }
}
