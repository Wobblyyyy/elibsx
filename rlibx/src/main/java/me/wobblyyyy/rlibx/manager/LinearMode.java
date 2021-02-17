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
 * An operation mode that operates entirely linearly.
 *
 * <p>
 * If you have an operation mode that should execute in a pre-defined order,
 * this is the class you'd like to use. If you'd like it to repeat as
 * frequently as possible, the RepeatingMode might be more appealing to you.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class LinearMode extends Mode {
    /**
     * Create a new mode, using a runnable.
     *
     * <p>
     * A LinearMode will execute and then stop after its execution has
     * ended. Unlike the {@link RepeatingMode} class, LinearMode instances
     * do not repeat. They're also a lot harder to interrupt - while
     * RepeatingMode instances can be interrupted by stopping repetition,
     * linear modes need to be interrupted by interrupting the thread itself,
     * which has been deprecated since Java 1.8. Uh oh.
     * </p>
     *
     * <p>
     * Much of the same rules that apply to the {@link Mode} class still apply
     * here. Code should be short and sweet in most cases. If you'd like to
     * read a bit more into that, you can feel free to read the JavaDocs for
     * the {@link Mode} class.
     * </p>
     *
     * @param runnable the mode's runnable.
     * @see Mode
     */
    public LinearMode(Runnable runnable) {
        /*
         * Pass the runnable to the super-constructor.
         *
         * LinearMode is really a wrapper class designed to clean up the
         * nomenclature of code - it has very little functional purpose other
         * than doing exactly that.
         */
        super(runnable);
    }
}
