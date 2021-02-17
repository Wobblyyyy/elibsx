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

import me.wobblyyyy.rlibx.interfaces.ControllerCore;

/**
 * Class used for storing information about a controller input device.
 *
 * <p>
 * Controller states are cached and updated whenever the state is requested.
 * To access the state, or any information about the controller's current
 * state, you should use the {@link Controller#getState()} method.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class Controller {
    /**
     * The controller's current state - updated whenever information about
     * the encoder's state is polled.
     */
    private ControllerState state;

    /**
     * The controller's central controller - y'know, like, the thing that
     * actually tells us the input values.
     */
    private final ControllerCore controller;

    /**
     * Create a new controller, using a controller core.
     *
     * @param controller the controller's core.
     */
    public Controller(ControllerCore controller) {
        this.controller = controller;
    }

    /**
     * Refresh the cached controller state.
     */
    private void update() {
        state = controller.getState();
    }

    /**
     * Get the controller's current state.
     *
     * @return the encoder's current state.
     */
    public ControllerState getState() {
        update();
        return state;
    }
}
