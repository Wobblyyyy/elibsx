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
 * @author Colin Robertson
 */
public class RepeatingMode extends Mode {
    /**
     * Should the runnable be run?
     */
    private static boolean shouldRun = true;

    /**
     * Create a new RepeatingMode with the Runnable element that you input.
     *
     * @param runnable the runnable that should be run on a loop.
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
        shouldRun = true;
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
        shouldRun = false;
    }
}
