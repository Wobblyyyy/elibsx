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

package me.wobblyyyy.rlibx.input.controllers;

import me.wobblyyyy.rlibx.interfaces.KeyboardControllerCore;

/**
 * An emulated controller that goes off keyboard input.
 *
 * <p>
 * In addition to allowing for keyboard input - this would be useful if you
 * were to, say, need to test a drivetrain without even having a controller to
 * test the drivetrain's operation - keyboard controllers give you the ability
 * to manipulate input values.
 * </p>
 *
 * <p>
 * This is extremely useful in testing how the robot reacts to very specific
 * values. For example, if I want to see how the robot responds to driving at
 * 0.5 power, it would be pretty hard to balance a joystick at exactly 0.5
 * power. To circumvent this issue, we can use a keyboard controller, from
 * where we can manually set values.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class KeyboardController extends Controller {
    /**
     * Create a new controller, using a controller core.
     *
     * @param controller the controller's core.
     */
    public KeyboardController(KeyboardControllerCore controller) {
        super(controller);
    }
}
