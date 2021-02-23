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

package me.wobblyyyy.rlibx;

import me.wobblyyyy.rlibx.modes.ModeExecutor;
import me.wobblyyyy.rlibx.modes.Modes;
import me.wobblyyyy.rlibx.subsystem.Subsystem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The main class used for interacting with rlibx through code.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class RLib {
    /**
     * The locally-stored instance of an {@code rlibx} mode.
     */
    public static RLibInstance instance;

    /**
     * Load an RLib instance based on inputted modes and subsystems.
     *
     * @param modes      a HashMap of all of the mode elements that should be
     *                   added to the robot's execution pool. The keys of this
     *                   HashMap represent the mode that the following executor
     *                   will serve for, and the values represent executors
     *                   that should be executed whenever the given mode is
     *                   activated.
     * @param subsystems an ArrayList of all of the subsystems on the robot.
     *                   These are typically pre-configured. The subsystem
     *                   manager inside of the {@link RLibCPI} class handles
     *                   all of the initialization and management of these
     *                   inputted subsystems, but it's still important to
     *                   configure the subsystems prior to passing them as
     *                   parameters to this method.
     */
    public static void load(HashMap<Modes, ModeExecutor> modes,
                            ArrayList<Subsystem> subsystems) {
        instance = new RLibCPI(modes);

        for (Subsystem s : subsystems) {
            ((RLibCPI) (instance)).register(s);
        }
    }

    /**
     * Load an RLib instance based on inputted modes and subsystems.
     *
     * @param modes a HashMap of all of the mode elements that should be
     *              added to the robot's execution pool. The keys of this
     *              HashMap represent the mode that the following executor
     *              will serve for, and the values represent executors
     *              that should be executed whenever the given mode is
     *              activated.
     */
    public static void load(HashMap<Modes, ModeExecutor> modes) {
        instance = new RLibInstance(modes);
    }
}
