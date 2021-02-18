/*
 *
 * Copyright (c) 2020, Colin Robertson (wobblyyyy@gmail.com)
 *
 * This file is part of the elibsx project. The elibsx project is licensed
 * under the GNU General Public License V3.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * license for the specific language governing permissions and limitations
 * under the License.
 *
 * Along with this file, you should have received a license file, containing
 * a copy of the GNU General Public License V3. If you did not receive a copy
 * of that license, you may find one online.
 *
 * elibsx GitHub repository:
 * https://github.com/Wobblyyyy/elibsx
 *
 * GNU General Public License V3:
 * http://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *
 */

package me.wobblyyyy.rlibx.operation;

import me.wobblyyyy.rlibx.manager.ModeExecutor;
import me.wobblyyyy.rlibx.manager.Modes;

import java.util.HashMap;

/**
 * Class used in storing information about multiple different modes.
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public class MultiModeManager {
    /**
     * A map of all of the modes. This map maps a Modes element to a
     * ModeExecutor - meaning you'd have a separate ModeExecutor for
     * each mode, whether that mode be teleop, autonomous, etc.
     */
    private final HashMap<Modes, ModeExecutor> modeMap;
    /**
     * The robot's current mode of operation.
     */
    private Modes currentMode = Modes.ROBOT;

    /**
     * Create a new MultiModeManager without any parameters.
     *
     * <p>
     * This means that you'll have to go back and add modes in order to have
     * any usable modes. You can accomplish this rather easily, actually.
     * The {@link MultiModeManager#addMode(Modes, ModeExecutor)} should help.
     * </p>
     */
    public MultiModeManager() {
        this.modeMap = new HashMap<>();
    }

    /**
     * Create a new MultiModeManager with a map of all of the modes and their
     * functionality.
     *
     * <p>
     * Using this constructor means that you won't have to go back and add in
     * more modes later.
     * </p>
     *
     * @param modeMap a map of all of the modes to use.
     */
    public MultiModeManager(HashMap<Modes, ModeExecutor> modeMap) {
        this.modeMap = modeMap;
    }

    /**
     * Force the activation of a given mode.
     *
     * @param mode the mode to activate.
     */
    public void activateMode(Modes mode) {
        getMode(mode).start();
    }

    /**
     * Force the de-activation of a given mode.
     *
     * <p>
     * If the current mode is ROBOT, nothing happens. Remember, robot is the
     * default state of execution and should be active at all times.
     * </p>
     *
     * @param mode the mode to de-activate.
     */
    public void deactivateMode(Modes mode) {
        /*
         * Check to see whether or not the inputted mode is of the type
         * Modes.ROBOT.
         *
         * If it is, we don't do anything. The robot mode should be active
         * at all times and should not ever be de-activated.
         *
         * However, if the robot's mode is NOT set to Modes.ROBOT, we need
         * to deactivate the mode.
         */
        if (!(mode == Modes.ROBOT)) {
            /*
             * Stop the mode's execution.
             *
             * Let's really hope the mode is a RepeatingMode - although it
             * isn't really harmful, stopping threads in the middle of their
             * execution is typically frowned upon.
             *
             * If you're confused about what that means, you can go check
             * out the JavaDoc documentation in the Mode class - it covers
             * a lot about thread execution and stopping.
             */
            getMode(mode).stop();
        }
    }

    /**
     * Switch between two modes of operation - the current mode, and the
     * inputted mode.
     *
     * <p>
     * This method will de-activate the current mode before switching to
     * another mode.
     * </p>
     *
     * @param mode the mode to switch to.
     */
    public void switchModes(Modes mode) {
        /*
         * Try to switch the robot's current mode.
         *
         * We use a try/catch statement in the (relatively likely) event that
         * an error is thrown.
         *
         * Rather than stopping the execution of the whole robot due to an
         * uncaught exception, we can catch the exception here and give a
         * developer a friendly little error telling them why they're stupid.
         *
         * This does mean that thrown errors won't crash the robot - however,
         * that might make it a bit harder to develop for. If, in the future,
         * it's a common complaint that mode switching is very hard to debug,
         * we can remove this from a try/catch and go from there.
         */
        try {
            /*
             * We need to deactivate the current mode before doing anything
             * else.
             *
             * The deactivation method does more than simply deactivate the mode.
             * Before mode de-activation, we check to see if the current mode is
             * Modes.ROBOT - if that IS the current mode, we don't actually
             * de-activate anything, as the mode that we would be de-activating
             * should always be run.
             */
            deactivateMode(currentMode);

            /*
             * Activate the next mode.
             *
             * This is a lot more simple than the deactivation method - we quite
             * literally just activate the mode we were looking for.
             */
            activateMode(mode);

            /*
             * Set the current mode to the newly-set mode.
             *
             * We need to do this so we know what mode to deactivate when the
             * deactivateMode method is called.
             */
            currentMode = mode;
        } catch (Exception e) {
            /*
             * If we got an exception, print the stack trace.
             *
             * Essentially, tell the developer why they're a complete moron.
             * Haha! Nerd.
             */
            e.printStackTrace();
        }
    }

    /**
     * Get a mode, based on a mode enum.
     *
     * @param mode the enum to query for.
     * @return the mode given for that specific enum.
     */
    public ModeExecutor getMode(Modes mode) {
        return modeMap.get(mode);
    }

    /**
     * Add a mode to the execution map.
     *
     * @param mode     the mode to add an execution protocol for.
     * @param executor the execution code associated with the mode.
     */
    public void addMode(Modes mode,
                        ModeExecutor executor) {
        modeMap.put(mode, executor);
    }
}
