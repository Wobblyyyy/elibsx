package org.roxbotix.elibs2.controller;

import me.wobblyyyy.intra.ftc2.utils.CommandCore;

import java.util.HashMap;
import java.util.Objects;

/**
 * A system I first devised in my FTC library - mapping buttons on a
 * controller to certain functionality.
 *
 * <p>
 * This makes it so you don't have to run hundreds of IF checks to see if the
 * robot's controller is in some certain state. It's all done for you. You
 * just need to use the controller mapping functionality and you'll be all
 * good!
 * </p>
 *
 * <p>
 * If any of the code here is confusing, I do apologize. This is based heavily
 * on some code I wrote nearly two years ago, so it's... not fantastic.
 * </p>
 *
 * <p>
 * Also, this was more of an add-on than a key feature as it was in my FTC
 * library, so this isn't as well-integrated as it probably should be.
 * </p>
 *
 * @author Colin Robertson
 */
public class ControllerMap {
    /**
     * The controller that the map is run off of.
     */
    private final Controller controller;

    /**
     * The map of all of the commands used.
     */
    public HashMap<States, CommandCore> commandMap = new HashMap<>();

    /**
     * Create a new instance of a controller map.
     *
     * @param controller the controller that's being used.
     */
    public ControllerMap(Controller controller) {
        this.controller = controller;
    }

    /**
     * A different... uhh... name...? of the bind function, just to be cool.
     *
     * @param state   a state of the gamepad
     * @param command a function to execute
     */
    public final void map(States state, CommandCore command) {
        bind(state, command);
    }

    /**
     * Used to map a certain state to a function.
     *
     * <p>
     * If the state is already included, and the user is
     * attempting to overwrite it, the original
     * state has to be deleted, which is why we remove it
     * first. If the state isn't there, nothing happens.
     * </p>
     *
     * @param state   a state of the gamepad. These states are all
     *                enumerated in the States enum. Obviously, it's
     *                unlikely that I'll ever be able to include every
     *                single conceivable controller state. In this case,
     *                you can just map something another way, not using
     *                this library.
     * @param command a {@link CommandCore} to execute
     */
    public final void bind(States state, CommandCore command) {
        commandMap.remove(state);
        commandMap.put(state, command);
    }

    /**
     * Un-map or un-bind a certain CommandCore from another certain
     * controller state.
     *
     * @param state the state to unmap.
     */
    public final void unmap(States state) {
        unbind(state);
    }

    /**
     * Used to unmap a certain state from the ControllerMap.
     *
     * @param state the state which should be unmapped
     */
    public final void unbind(States state) {
        commandMap.remove(state);
    }

    /**
     * Run all of the code which is stored in the map.
     *
     * <p>
     * We check to make sure there that the command map actually contains
     * the state we need. If it does, we go ahead and run the runnables
     * for that certain thingy. However, if it doesn't, we do absolutely
     * nothing. Epic, I know.
     * </p>
     *
     * <p>
     * This should (hopefully) be run once every 'tick' (every cycle of
     * the loop). If you have a slow phone... sucks. Doesn't it?
     * </p>
     *
     * <p>
     * I'll put the same warning I've put elsewhere here as well. This code
     * was originally written for the First Tech Challenge, not the First
     * Robotics Competition. As such, some parts of the code don't do anything
     * useful. Guide and select buttons, for example, are currently disabled.
     * With that being said, there's very little harm in keeping the useless
     * code in here. If someone would like to go through and trim all of it
     * out, you can feel free to - but I don't have the energy to.
     * </p>
     */
    public final void runMap() {
        if (commandMap.containsKey(States.START))
            Objects.requireNonNull(commandMap.get(States.START)).getRunnable(controller.start).run();
        if (commandMap.containsKey(States.GUIDE))
            Objects.requireNonNull(commandMap.get(States.GUIDE)).getRunnable(controller.select).run();
        if (commandMap.containsKey(States.A))
            Objects.requireNonNull(commandMap.get(States.A)).getRunnable(controller.a).run();
        if (commandMap.containsKey(States.B))
            Objects.requireNonNull(commandMap.get(States.B)).getRunnable(controller.b).run();
        if (commandMap.containsKey(States.X))
            Objects.requireNonNull(commandMap.get(States.X)).getRunnable(controller.x).run();
        if (commandMap.containsKey(States.Y))
            Objects.requireNonNull(commandMap.get(States.Y)).getRunnable(controller.y).run();
        if (commandMap.containsKey(States.DPAD_UP))
            Objects.requireNonNull(commandMap.get(States.DPAD_UP)).getRunnable(controller.dpad_up).run();
        if (commandMap.containsKey(States.DPAD_RIGHT))
            Objects.requireNonNull(commandMap.get(States.DPAD_RIGHT)).getRunnable(controller.dpad_right).run();
        if (commandMap.containsKey(States.DPAD_DOWN))
            Objects.requireNonNull(commandMap.get(States.DPAD_DOWN)).getRunnable(controller.dpad_down).run();
        if (commandMap.containsKey(States.DPAD_LEFT))
            Objects.requireNonNull(commandMap.get(States.DPAD_LEFT)).getRunnable(controller.dpad_left).run();
        if (commandMap.containsKey(States.RIGHT_BUMPER))
            Objects.requireNonNull(commandMap.get(States.RIGHT_BUMPER)).getRunnable(controller.right_bumper).run();
        if (commandMap.containsKey(States.LEFT_BUMPER))
            Objects.requireNonNull(commandMap.get(States.LEFT_BUMPER)).getRunnable(controller.left_bumper).run();
        if (commandMap.containsKey(States.RIGHT_STICK))
            Objects.requireNonNull(commandMap.get(States.RIGHT_STICK)).getRunnable(controller.right_stick_x != 0 || controller.right_stick_y != 0).run();
        if (commandMap.containsKey(States.LEFT_STICK))
            Objects.requireNonNull(commandMap.get(States.LEFT_STICK)).getRunnable(controller.left_stick_x != 0 || controller.left_stick_y != 0).run();
        if (commandMap.containsKey(States.RIGHT_STICK_X))
            Objects.requireNonNull(commandMap.get(States.RIGHT_STICK_X)).getRunnable(controller.right_stick_x != 0).run();
        if (commandMap.containsKey(States.RIGHT_STICK_Y))
            Objects.requireNonNull(commandMap.get(States.RIGHT_STICK_Y)).getRunnable(controller.right_stick_y != 0).run();
        if (commandMap.containsKey(States.LEFT_STICK_X))
            Objects.requireNonNull(commandMap.get(States.LEFT_STICK_X)).getRunnable(controller.left_stick_x != 0).run();
        if (commandMap.containsKey(States.LEFT_STICK_Y))
            Objects.requireNonNull(commandMap.get(States.LEFT_STICK_Y)).getRunnable(controller.left_stick_y != 0).run();
        if (commandMap.containsKey(States.RIGHT_OR_LEFT_X))
            Objects.requireNonNull(commandMap.get(States.RIGHT_OR_LEFT_Y))
                    .getRunnable(
                            controller.right_stick_y != 0 ||
                                    controller.left_stick_y != 0
                    );
        if (commandMap.containsKey(States.RIGHT_OR_LEFT_Y))
            Objects.requireNonNull(commandMap.get(States.RIGHT_OR_LEFT_X))
                    .getRunnable(
                            controller.right_stick_x != 0 ||
                                    controller.left_stick_x != 0
                    );
        if (commandMap.containsKey(States.STICK))
            Objects.requireNonNull(commandMap.get(States.STICK)).getRunnable(controller.right_stick_x != 0 || controller.right_stick_y != 0 || controller.left_stick_x != 0 || controller.left_stick_y != 0).run();
        if (commandMap.containsKey(States.RIGHT_TRIGGER))
            Objects.requireNonNull(commandMap.get(States.RIGHT_TRIGGER)).getRunnable(controller.right_trigger != 0).run();
        if (commandMap.containsKey(States.LEFT_TRIGGER))
            Objects.requireNonNull(commandMap.get(States.LEFT_TRIGGER)).getRunnable(controller.left_trigger != 0).run();
        if (commandMap.containsKey(States.RIGHT_TRIGGER_HALF))
            Objects.requireNonNull(commandMap.get(States.RIGHT_TRIGGER_HALF)).getRunnable(controller.right_trigger > 0.5);
        if (commandMap.containsKey(States.LEFT_TRIGGER_HALF))
            Objects.requireNonNull(commandMap.get(States.LEFT_TRIGGER_HALF)).getRunnable(controller.left_trigger > 0.5);
        if (commandMap.containsKey(States.RIGHT_TRIGGER_QUARTER))
            Objects.requireNonNull(commandMap.get(States.RIGHT_TRIGGER_QUARTER)).getRunnable(controller.right_trigger > 0.25);
        if (commandMap.containsKey(States.LEFT_TRIGGER_QUARTER))
            Objects.requireNonNull(commandMap.get(States.LEFT_TRIGGER_QUARTER)).getRunnable(controller.left_trigger > 0.25);
        if (commandMap.containsKey(States.RIGHT_TRIGGER_THREE_QUARTERS))
            Objects.requireNonNull(commandMap.get(States.RIGHT_TRIGGER_THREE_QUARTERS)).getRunnable(controller.right_trigger > 0.75);
        if (commandMap.containsKey(States.LEFT_TRIGGER_THREE_QUARTERS))
            Objects.requireNonNull(commandMap.get(States.LEFT_TRIGGER_THREE_QUARTERS)).getRunnable(controller.left_trigger > 0.75);
    }

    /**
     * Enums for every possible "state" the controller can be in.
     *
     * <p>
     * Note that states for stick movements only accept the following conditions:
     *     <ul>
     *         <li>"RIGHT_STICK": Right stick's X <b>or</b> Y is offset by anything at all.</li>
     *         <li>"LEFT_STICK": Left stick's X <b>or</b> Y is offset by anything at all.</li>
     *         <li>"STICK": Right <b>or</b> left stick's X <b>or</b> Y is offset by anything at all</li>
     *     </ul>
     *     This means there is no specific states for left stick offset or right stick offset.
     * </p>
     *
     * <p>
     * NOTICE FOR FRC!!! <br>
     * This code was originally written for the FTC, not the FRC. As a result,
     * a lot of the states here are not actually usable in an FRC situation.
     * Specifically, GUIDE and SELECT are two of the disabled keys. Everything
     * else SHOULD (maybe) work. We'll have to see, I guess.
     * </p>
     */
    public enum States {
        /**
         * disabled
         */
        START,
        /**
         * disabled
         */
        GUIDE,
        /**
         * a button
         */
        A,
        /**
         * b button
         */
        B,
        /**
         * x button
         */
        X,
        /**
         * y button
         */
        Y,
        /**
         * right bumper pressed
         */
        RIGHT_BUMPER,
        /**
         * left bumper pressed
         */
        LEFT_BUMPER,
        /**
         * dpad up pressed
         */
        DPAD_UP,
        /**
         * dpad right pressed
         */
        DPAD_RIGHT,
        /**
         * dpad down pressed
         */
        DPAD_DOWN,
        /**
         * dpad left pressed
         */
        DPAD_LEFT,
        /**
         * right stick non-zero
         */
        RIGHT_STICK,
        /**
         * left stick non-zero
         */
        LEFT_STICK,
        /**
         * disabled
         */
        RIGHT_STICK_BUTTON,
        /**
         * disabled
         */
        LEFT_STICK_BUTTON,
        /**
         * right stick x value non-zero
         */
        RIGHT_STICK_X,
        /**
         * right stick y value non-zero
         */
        RIGHT_STICK_Y,
        /**
         * left stick x value non-zero
         */
        LEFT_STICK_X,
        /**
         * left stick y value non-zero
         */
        LEFT_STICK_Y,
        /**
         * right or left stick x value non-zero
         */
        RIGHT_OR_LEFT_X,
        /**
         * right or left stick y value non-zero
         */
        RIGHT_OR_LEFT_Y,
        /**
         * any stick non-zero
         */
        STICK,
        /**
         * right trigger non-zero
         */
        RIGHT_TRIGGER,
        /**
         * left trigger non-zero
         */
        LEFT_TRIGGER,
        /**
         * right trigger at least 0.5
         */
        RIGHT_TRIGGER_HALF,
        /**
         * left trigger at least 0.5
         */
        LEFT_TRIGGER_HALF,
        /**
         * right trigger at least 0.25
         */
        RIGHT_TRIGGER_QUARTER,
        /**
         * left trigger at least 0.25
         */
        LEFT_TRIGGER_QUARTER,
        /**
         * right trigger at least 0.75
         */
        RIGHT_TRIGGER_THREE_QUARTERS,
        /**
         * left trigger at least 0.75
         */
        LEFT_TRIGGER_THREE_QUARTERS
    }
}
