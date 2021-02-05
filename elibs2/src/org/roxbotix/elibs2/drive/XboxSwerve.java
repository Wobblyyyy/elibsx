package org.roxbotix.elibs2.drive;

import me.wobblyyyy.intra.ftc2.utils.Timed;
import me.wobblyyyy.intra.ftc2.utils.async.event.StringEvents;
import org.roxbotix.elibs2.controller.Controller;

/**
 * A simple xbox-integrated swerve drive.
 *
 * <p>
 * This is missing a lot of important features such as shifting, MC, FM,
 * and a bunch more. However, it does work (not really, maybe, hopefully) and
 * there's no point in fixing it if it's not broken.
 * </p>
 *
 * @author Colin Robertson
 */
public class XboxSwerve {
    /**
     * The internally-used controller. This controller is used for mapping
     * of controls and what not.
     *
     * <p>
     * Who would've figured? Crazy, right?
     * </p>
     */
    private final Controller controller;

    /**
     * LEFT STICK X
     */
    private double lsx;

    /**
     * LEFT STICK Y
     */
    private double lsy;

    /**
     * RIGHT STICK X
     */
    private double rsx;

    /**
     * RIGHT STICK Y
     */
    private double rsy;

    /**
     * The swerve drivetrain that's responsible for moving the robot.
     *
     * <p>
     * I would certainly suggest that you go over to the Swerve file and
     * read some of the JavaDocs there to get acquainted with how it works.
     * </p>
     *
     * <p>
     * My reasoning behind this isn't that I care if you understand how a
     * swerve drive works. Rather, in the very likely event everything goes
     * wrong and the robot somehow catches on fire, I want someone else to be
     * blamed for it. Well, clearly it can't be my fault! Someone else checked
     * it over and said it was okay!
     * </p>
     */
    public Swerve drive;

    /**
     * Create a new instance of an Xbox-integrated swerve drive.
     *
     * @param controller the controller that's used.
     */
    public XboxSwerve(Controller controller) {
        this.controller = controller;
    }

    /**
     * Initialize the drivetrain. I'd strongly suggest you read the rest of this
     * JavaDoc if you'd like to have an idea as to what's going on here.
     *
     * <p>
     * Each of the inputted arrays should be of length FOUR - meaning there
     * is FOUR different integers in the array. Those integers are as follows.
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Turn motor encoder hardware ID.</li>
     *     <li>Turn motor encoder's CPR (counts per rotation).</li>
     * </ul>
     * </p>
     *
     * @param fris the hardware IDs for the front-right motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param flis the hardware IDs for the front-left motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param bris the hardware IDs for the back-right motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param blis the hardware IDs for the back-left motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     */
    public void init(int[] fris,
                     int[] flis,
                     int[] bris,
                     int[] blis) {
        drive = new Swerve();

        drive.init(
                fris,
                flis,
                bris,
                blis
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
                "XboxSwerve",
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
