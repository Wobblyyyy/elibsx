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
 * A mode, containing a single Runnable element that's executed many times
 * over and over again.
 *
 * <p>
 * Repeating modes are extensions of the default {@link Mode} class. By going
 * a step further, and creating a mode which is executed very many times as
 * frequently as possible, we can create environments in which the robot is
 * responsive to input - whether that input be from sensors, driver control,
 * pathfinding algorithms, or a million other things.
 * </p>
 *
 * <p>
 * As a result of this added level of abstraction, repeating mode elements
 * don't suffer from some of the same thread safety pitfalls that lower-level
 * modes, such as {@link Mode} and {@link LinearMode} do.
 * </p>
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public class RepeatingMode extends Mode {
    /**
     * Should the runnable be run?
     */
    private static boolean shouldRun = true;

    /**
     * Create a new RepeatingMode with the Runnable element that you input.
     *
     * <p>
     * This Runnable element will not be executed until the parent mode begins
     * its thread execution. This is all handled very nicely for us in the
     * {@link RepeatingMode#start()} method.
     * </p>
     *
     * <p>
     * Longer execution times are generally harmful to the integrity of the
     * loop. As with most other forms of control-based loops, the more accurate
     * the loop is, the better the loop performs. And in this case, accuracy
     * is directly tied to how many executions the loop gets per X amount of
     * time. To improve the accuracy of a loop, such as this one, the code that
     * is executed inside of the loop should be executed as quickly as possible.
     * </p>
     *
     * <p>
     * In the event you seriously do need to do some heavy-lifting during a
     * loop execution, your best bet would be to create another thread that
     * can do any math-intense, etc, things for you.
     * </p>
     *
     * @param runnable the runnable that should be run on a loop. While creating
     *                 a Runnable element to pass as a parameter to this
     *                 constructor, its important to remember that the execution
     *                 time of the Runnable's code should be as minimal as
     *                 possible.
     */
    public RepeatingMode(Runnable runnable) {
        /*
         * Pass a new Runnable to the super constructor.
         *
         * As an extension of the Mode class, RepeatingMode uses a Runnable
         * element with a do/while loop in it.
         */
        super(() -> {
            do {
                /*
                 * While the repeating runnable is active, we execute the
                 * runnable whenever possible.
                 *
                 * Thread.onSpinWait() is used to tell the CPU that this
                 * piece of code isn't really important and doesn't need to
                 * be executed with 100% priority at all times.
                 *
                 * Although not exactly required, this helps to prevent
                 * busy-waiting, which is generally bad for performance.
                 */
                Thread.onSpinWait();

                /*
                 * Run the Runnable element itself! Yay!
                 */
                runnable.run();

                /*
                 * If the Runnable should no longer be run, we can stop running
                 * it.
                 *
                 * I know, that's a hard concept to take in - believe me, it'll
                 * be okay. Somewhat.
                 */
            } while (shouldRun);
        });
    }

    /**
     * Start the repeating mode's execution.
     *
     * <p>
     * Repeating modes may only be started or stopped in between the
     * execution of the runnable. This means that if the runnable you have
     * repeating very frequently is taking a long time to finish, you'll
     * have to wait for it to finish. Sucks for you, y'know?
     * </p>
     */
    @Override
    public void start() {
        /*
         * Set the shouldRun flag to true, meaning the loop will run.
         */
        shouldRun = true;

        /*
         * The thread itself hasn't actually been started yet - we need to
         * go do that here.
         */
        super.start();
    }

    /**
     * Stop the repeating mode's execution.
     *
     * <p>
     * Repeating modes may only be started or stopped in between the
     * execution of the runnable. This means that if the runnable you have
     * repeating very frequently is taking a long time to finish, you'll
     * have to wait for it to finish. Sucks for you, y'know?
     * </p>
     */
    @Override
    public void stop() {
        /*
         * Set the shouldRun flag to false.
         *
         * The execution thread responsible for executing the repeating
         * runnable will get this signal, and while checking the state of this
         * boolean, it'll realize that the boolean has been updated to false.
         *
         * After the boolean is updated to false, the do/while loop that makes
         * up the majority of this class is terminated. Thus, the thread can
         * finish its execution and live the rest of its life happily ever
         * after.
         *
         * Or something like that, anyways...
         */
        shouldRun = false;
    }
}
