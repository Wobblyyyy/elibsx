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

/**
 * A template mode, which teleop/autonomous/etc will extend.
 *
 * <p>
 * Mode elements are the very core of multi (and even single) mode-d robot
 * operation. The initial idea behind the creation of a Mode class was to make
 * it so that FRC and FTC robots can have a single autonomous program written
 * for the both of them. However, I've decided to go a step further - modes
 * are entirely accessible from anywhere. You can have a dozen modes running
 * at the same time if you'd like.
 * </p>
 *
 * <p>
 * Mode execution is largely handled higher up, such as in one of these
 * classes:
 * <ul>
 *     <li>{@link me.wobblyyyy.rlibx.operation.MultiModeManager}</li>
 *     <li>{@link me.wobblyyyy.rlibx.operation.MultiMode}</li>
 *     <li>{@link me.wobblyyyy.rlibx.operation.SingleMode}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Modes, at their core, are just fancy Runnable elements that get placed on
 * their own thread. They're not very difficult to conceptualize or understand,
 * but its very important that you have a solid understanding of how modes
 * function (and especially how modes function in relation to each other) before
 * attempting to write code for a multi-mode-d robot.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class Mode {
    /**
     * The mode's executable.
     */
    private final Runnable runnable;

    /**
     * The mode's execution thread.
     */
    private Thread executionThread;

    /**
     * Create a new mode, using a runnable.
     *
     * <p>
     * While creating a mode, it's important to remember that mode elements
     * aren't designed to execute for a solid minute. Mode elements, in
     * most cases, are designed to execute for a short amount of time, thus
     * allowing some objective to be accomplished without as much human input
     * as you would have needed otherwise.
     * </p>
     *
     * <p>
     * Non-repeating modes (such as {@link LinearMode}) don't have the ability
     * to stop and start suddenly, because the threads that they use don't have
     * soft start and stop methods - stopping a thread kills it as quickly as
     * possible. Thread stopping has been deprecated since Java 1.8ish, so
     * unless you really need it, it should be entirely avoided.
     * </p>
     *
     * <p>
     * Thus, it's very important to remember to keep the length of code put
     * into a non-{@link RepeatingMode} mode very short if you don't know if
     * the mode will suddenly be stopped or started.
     * </p>
     *
     * <p>
     * If you were, for instance, writing an autonomous program which would
     * perform a set of actions for a given amount of time, it would be
     * perfectly acceptable to write a single mode that takes upwards of
     * dozens of seconds. Assuming this mode won't be interrupted, as it
     * probably won't, the length of the mode is irrelevant.
     * </p>
     *
     * @param runnable the mode's runnable.
     */
    public Mode(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * Execute the Runnable.
     *
     * <p>
     * As Thread elements, by default, run until they're done, this thread
     * will do exactly that. You can stop and start the thread as suddenly
     * as you'd like by using the stop and start methods provided in the Mode
     * class.
     * </p>
     *
     * <p>
     * Generally speaking, it's advised against stopping threads in the
     * middle of their execution. Unless you absolutely need to do so, you
     * should most certainly try to figure out a way to avoid doing exactly
     * that right there.
     * </p>
     *
     * <p>
     * Execution can't be started more than once - well, it can, but the
     * issue you're going to then encounter is that the execution will
     * restart, because the thread is initialized here.
     * </p>
     */
    public void start() {
        /*
         * Initialize the execution thread.
         *
         * Because a new one is constructed, the previous one loses any
         * sort of progress that it had towards finishing its execution.
         */
        executionThread = new Thread(runnable);

        /*
         * Start the execution thread.
         */
        executionThread.start();
    }

    /**
     * Stop the runnable execution prematurely.
     *
     * <p>
     * Unless you have a very strong need to, you should try to veer away from
     * stopping threads too suddenly. While an implementation of the Mode
     * class, such as the {@link RepeatingMode} class handles all of the
     * issues with stopping and starting threads for you, regular modes
     * and {@link LinearMode} elements don't have the luxury of having custom
     * soft starters and stoppers.
     * </p>
     *
     * <p>
     * Stopping thread execution is generally discouraged and will 100% be
     * deprecated soon.
     * </p>
     */
    public void stop() {
        /*
         * We need to figure out another way that doesn't involving calling
         * the Thread#stop method, as that method... kinda ain't very good.
         */
        executionThread.stop();
    }
}
