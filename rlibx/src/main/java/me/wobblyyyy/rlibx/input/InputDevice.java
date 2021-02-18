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

package me.wobblyyyy.rlibx.input;

import java.util.HashMap;

/**
 * A generic type of input device that's designed to work with everything
 * out of the box, with very little configuration.
 *
 * <p>
 * Although controllers are nice and all, going up a step and using an
 * input device expands the horizons of robot input. Simply using a controller
 * class and nothing else makes it so that only a joystick can control the
 * values of a motor's power or something like that.
 * </p>
 *
 * <p>
 * By using a more generic and abstract InputDevice, we no longer need to
 * worry about controller mishaps or mismatches. We have input channels, and
 * each of these input channels accomplishes a single goal, and do so pretty
 * well.
 * </p>
 *
 * <p>
 * Input devices function by using an array of length 100 that stores data that
 * pertains to... well, input. Examples include:
 * <ul>
 *     <li>Color sensors.</li>
 *     <li>Touch sensors.</li>
 *     <li>Controller inputs.</li>
 *     <li>Limit switches.</li>
 * </ul>
 * ... you get the point - there's a lot of stuff you can put on a single
 * input device.
 * </p>
 *
 * <p>
 * The function of a class, such as this one, might seem to be a bit of a
 * mystery. Why would you possibly use a more complex implementation of input
 * when you can simply use... well, a simpler version? In this case, sticking
 * with Controller would have been way more simple to implement.
 * </p>
 *
 * <p>
 * However, this isn't a simple FTC and FRC library - this is much more of
 * a robotics as a whole library. Robotics as a whole isn't only operated with
 * controllers. Creating a separate class, a more abstract and generic class,
 * allows us to interlock with many, many, many different input devices,
 * compared to just two types of controllers.
 * </p>
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public class InputDevice {
    /**
     * All of the available input channels.
     *
     * <p>
     * In total, there's 100 input channels that can be used - realistically,
     * I need to know. When are you ever going to need more than 100 input
     * channels for a single input device?
     * </p>
     */
    public double[] channels = new double[100];

    /**
     * Update the value of a single input channel.
     *
     * @param index the index of the input channel that should be updated.
     * @param value the value of the input channel.
     */
    public synchronized void update(int index,
                                    double value) {
        channels[index] = value;
    }

    /**
     * Update the value of the entire channel array.
     *
     * @param channels a new array of doubles - this will replace the current
     *                 channel array entirely.
     */
    public synchronized void update(double[] channels) {
        this.channels = channels;
    }

    /**
     * Update several channels at once by using a HashMap to provide a list
     * of channels and their values.
     *
     * @param map a map. This map should represent the channels that should be
     *            updated and the new values that they should have.
     */
    public synchronized void updateAll(HashMap<Integer, Double> map) {
        for (HashMap.Entry<Integer, Double> e : map.entrySet()) {
            update(
                    e.getKey(),
                    e.getValue()
            );
        }
    }
}
