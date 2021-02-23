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

package me.wobblyyyy.rlibx.modes;

/**
 * An enumeration of all the modes which the robot can operate in.
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public enum Modes {
    /**
     * Drive-controlled. Tele-operated is intended for functionality that
     * requires a driver to control the robot manually via some form of
     * controller input.
     */
    TELEOP,

    /**
     * Non-driver controlled. Autonomous is the mode category best suited for
     * things that you don't touch. Tele-operated is for driver control,
     * autonomous is for a complete lack of that.
     */
    AUTONOMOUS,

    /**
     * Testing mode. You can put whatever you'd like here, that's entirely
     * up to you.
     */
    TEST,

    /**
     * Simulation mode. This mode is intended for situations in which a robot
     * is being simulated.
     */
    SIMULATION,

    /**
     * Robot main mode. This is the mode that's executed constantly, no matter
     * what. As soon as the program initializes, we enter right here. Any
     * Repeating Modes that are in the Robot mode executor are repeated until
     * the program is fully stopped.
     */
    ROBOT
}
