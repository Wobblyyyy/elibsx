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

package me.wobblyyyy.rlibx.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager class used in managing the execution of several different modes.
 *
 * <p>
 * In reality, a ModeExecutor is an extension of the LinearMode class. When
 * the ModeExecutor is created, all of the Mode elements that were passed to
 * the executor's constructor will be started. Stopping them can be accomplished
 * by calling the stop method. Re-starting them from there can be accomplished
 * with the start method. It's suggested that you don't stop or start threads
 * if you don't have to, as stopping and starting threads isn't exactly a
 * suggested thing.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class ModeExecutor extends LinearMode {
    /**
     * An internally-used list of all of the Mode elements that should
     * be stopped/started whenever trying to do either of those lovely
     * things with this lovely code.
     */
    private final ArrayList<Mode> modes;

    /**
     * Create a new ModeExecutor based on a single inputted mode.
     *
     * <p>
     * None of these modes will begin execution until the
     * {@link ModeExecutor#start()} method has been called. Until that point,
     * the modes will simply sit dormant.
     * </p>
     *
     * <p>
     * If you'd like to stop the execution of a mode executor before it has
     * reached its natural conclusion, you can use the {@link ModeExecutor#stop()}
     * method to do exactly that.
     * </p>
     *
     * @param mode the mode that should be executed when this ModeExecutor
     *             has its functionality used.
     */
    public ModeExecutor(Mode mode) {
        this((ArrayList<Mode>) List.of(mode));
    }

    /**
     * Create a new ModeExecutor based on an ArrayList of modes that should
     * be executed with the mode executor.
     *
     * <p>
     * None of these modes will begin execution until the
     * {@link ModeExecutor#start()} method has been called. Until that point,
     * the modes will simply sit dormant.
     * </p>
     *
     * <p>
     * If you'd like to stop the execution of a mode executor before it has
     * reached its natural conclusion, you can use the {@link ModeExecutor#stop()}
     * method to do exactly that.
     * </p>
     *
     * @param modes a list of all of the modes that should be executed when
     *              the mode executor's execution functionality is executed.
     *              Damn, that's a lot of execution.
     */
    public ModeExecutor(ArrayList<Mode> modes) {
        /*
         * Pass a new Runnable element to the super-constructor.
         */
        super(() -> {
            /*
             * We want to start each of the inputted modes when the mode
             * executor's functionality is called.
             */
            for (Mode mode : modes) {
                mode.start();
            }
        });

        /*
         * Set the modes ArrayList to the newly-passed modes ArrayList.
         */
        this.modes = modes;
    }

    /**
     * Execute the Runnable.
     *
     * <p>
     * This starts the execution of all of the modes in the mode manager.
     * </p>
     *
     * <p>
     * Execution can't be started more than once - well, it can, but the
     * issue you're going to then encounter is that the execution will
     * restart, because the thread is initialized here.
     * </p>
     */
    @Override
    public void start() {
        for (Mode mode : modes) {
            /*
             * Start each of the modes.
             */
            mode.start();
        }
    }

    /**
     * Stop the runnable execution prematurely.
     *
     * <p>
     * This stops the execution of all of the modes in the mode manager.
     * </p>
     *
     * <p>
     * Stopping thread execution is generally discouraged and will 100% be
     * deprecated soon.
     * </p>
     */
    @Override
    public void stop() {
        for (Mode mode : modes) {
            /*
             * Stop all of the modes.
             *
             * Thread.stop() has been deprecated for ages - we should most
             * certainly try to find another way of stopping linearly
             * executed threads.
             */
            mode.stop();
        }
    }
}
