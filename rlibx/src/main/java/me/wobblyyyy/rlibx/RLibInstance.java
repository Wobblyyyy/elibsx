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
import me.wobblyyyy.rlibx.operation.MultiMode;

import java.util.HashMap;

/**
 * An instance of an RLib-mode-enabled robot operation.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class RLibInstance {
    /**
     * The multi-mode switcher that's used to switch between the various
     * modes that are available.
     */
    private final MultiMode mode;

    /**
     * Create a new {@code RLibInstance} instance.
     *
     * @param modes a {@code HashMap} of all of the modes that are used or
     *              executed by the robot at any point.
     */
    public RLibInstance(HashMap<Modes, ModeExecutor> modes) {
        mode = new MultiMode(modes);
    }

    /**
     * Should be called whenever the robot enters tele-op mode.
     */
    public void teleop() {
        mode.setMode(Modes.TELEOP);
    }

    /**
     * Should be called whenever the robot enters autonomous mode.
     */
    public void autonomous() {
        mode.setMode(Modes.AUTONOMOUS);
    }

    /**
     * Should be called whenever the robot enters testing mode.
     */
    public void testing() {
        mode.setMode(Modes.TEST);
    }

    /**
     * Should be called whenever the robot enters simulation mode.
     */
    public void simulation() {
        mode.setMode(Modes.SIMULATION);
    }

    /**
     * Should be called whenever the robot enters robot mode.
     */
    public void robot() {
        mode.setMode(Modes.ROBOT);
    }

    /**
     * Get the instance's mode execution HashMap.
     *
     * @return the instance's mode execution HashMap.
     */
    protected MultiMode getMultiMode() {
        return mode;
    }
}
