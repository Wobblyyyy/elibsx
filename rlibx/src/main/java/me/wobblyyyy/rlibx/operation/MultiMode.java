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

import me.wobblyyyy.rlibx.modes.ModeExecutor;
import me.wobblyyyy.rlibx.modes.Modes;

import java.util.HashMap;

/**
 * A type of mode situation in which a robot can operate in - multiple of them.
 *
 * <p>
 * MultiMode is designed to be as extensible as possible. By using different
 * data structures to store information about different modes and all that
 * cool stuff, we can manage to smoothly switch between them, etc.
 * </p>
 *
 * <p>
 * If you'd like to only execute a single mode, you can instead use the
 * {@link SingleMode} class, which provides a much easier interface for
 * operating a single mode. If you have so much as 2 modes, you'll have to
 * opt for the {@code MultiMode} class.
 * </p>
 *
 * @author Colin Robertson
 * @version 1.1.0
 * @since 0.1.0
 * @see MultiModeManager
 */
public class MultiMode {
    /**
     * The {@code MultiModeManager} class that's used in managing the
     * execution of multiple different modes.
     *
     * <p>
     * Most of the code that handles multi-moded execution actually happens in
     * the {@code MultiModeManager} class.
     * </p>
     */
    private final MultiModeManager manager;

    /**
     * Create a new {@code MultiMode} mode execution "platform." (?)
     *
     * <p>
     * The {@code MultiMode} that is created doesn't do much on its own.
     * Rather, it relies on the {@link MultiModeManager} class to do much
     * of the mode management and operation switching.
     * </p>
     *
     * <p>
     * As a result, anything that's not added to this list on creation will
     * need to be added using the
     * </p>
     *
     * @param modes a {@code HashMap} containing enumerated modes and their
     *              executors. These enumerated modes are detailed in the
     *              {@link Modes} class. The executor values that are passed
     *              in this {@code HashMap} are generally references to
     *              executors - they should not be defined anonymously during
     *              construction.
     * @see MultiModeManager#MultiModeManager(HashMap)
     */
    public MultiMode(HashMap<Modes, ModeExecutor> modes) {
        manager = new MultiModeManager(modes);
    }

    /**
     * Set the multi mode system to run a given mode.
     *
     * <p>
     * Whenever a new mode is set, all other modes (excluding the robot mode,
     * of course) are de-activated using the {@code deactivateMode()} method
     * in the {@link MultiModeManager} class.
     * </p>
     *
     * <p>
     * The robot mode should not ever be de-activated while calling this method.
     * We run a check to ensure that the mode we're about to deactivate is NOT
     * the {@code ROBOT} mode before de-activating it.
     * </p>
     *
     * @param mode the new mode that should be run.
     * @see MultiModeManager#activateMode(Modes)
     * @see MultiModeManager#deactivateMode(Modes)
     */
    public void setMode(Modes mode) {
        /*
         * For each of the modes contained in the HashMap of modes...
         *
         * We use the entry set of the mode manager's inner HashMap. This way,
         * we only de-activate the modes that could potentially be active.
         */
        for (Modes m : manager.getModes().keySet()) {
            /*
             * Check to see if the mode that's about to be de-activated should
             * actually be de-activated.
             *
             * Remember, the ROBOT mode should always stay active.
             */
            if (!(m == Modes.ROBOT)) {
                /*
                 * If the mode is active, we de-activate it.
                 *
                 * If the mode is not active, we do nothing, because there's
                 * no mode to de-activate.
                 *
                 * Would it be a better idea to, instead of checking to see
                 * if they're to be de-activated, just run the de-activation
                 * method anyways? Is there any harm in that?
                 */
                if (manager.isModeActive(m)) {
                    manager.deactivateMode(m);
                }
            }
        }

        /*
         * Activate the mode we were searching for.
         */
        manager.activateMode(mode);
    }

    /**
     * Add a mode to the mode executor's execution list. In almost all cases,
     * it's preferable to use the constructor provided to add all of the modes
     * that you'll need at initialization.
     *
     * <p>
     * It's vastly preferable to use the provided constructor for the
     * {@code MultiMode} to initialize all of the modes that can be executed.
     * Using that constructor keeps all of the code that does the same thing
     * in the same place, which is pretty great for code organization.
     * </p>
     *
     * <p>
     * Attempting to add a {@link Modes} mode to the {@code MultiMode} manager
     * when that mode already exists will simply overwrite the existing mode.
     * Keep this in your mind when adding a mode post-construction.
     * </p>
     *
     * @param mode     the type of mode that should be added. If the mode has
     *                 already been added to the {@code HashMap} of mode
     *                 elements, that mode element will be overwritten by the
     *                 new mode element and executor you've added.
     * @param executor the mode's executor. This executor should contain code
     *                 that will be executed upon a given mode's activation.
     *                 For more information on what the executor class does,
     *                 check out {@link ModeExecutor}.
     * @see MultiMode#MultiMode(HashMap)
     * @see MultiModeManager#addMode(Modes, ModeExecutor)
     */
    public void add(Modes mode,
                    ModeExecutor executor) {
        /*
         * Add the mode to the MultiModeManager's list of modes.
         *
         * In terms of how the added mode is handled, all of that's available
         * in the Manager#addMode() class.
         */
        manager.addMode(
                mode,
                executor
        );
    }
}
