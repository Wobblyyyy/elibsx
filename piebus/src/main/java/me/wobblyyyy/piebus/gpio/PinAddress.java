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

package me.wobblyyyy.piebus.gpio;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * A wrapper, surrounding an integer, that's used in determining pin addresses.
 *
 * <p>
 * Although yes, an integer technically would have worked, using this class
 * allows us to show any potential users all of the preset addresses.
 * </p>
 *
 * @author Colin Robertson
 */
public class PinAddress {
    public static final int GROUND = -1;
    public static final int POWER = -2;

    public static final int[] J8A = new int[] {
            POWER,
            8,
            9,
            7,
            GROUND,
            0,
            2,
            3,
            POWER,
            12,
            13,
            14,
            GROUND,
            30,
            21,
            22,
            23,
            24,
            25,
            GROUND
    };

    public static final int[] J8B = new int[] {
            POWER,
            POWER,
            GROUND,
            15,
            16,
            1,
            GROUND,
            4,
            5,
            GROUND,
            6,
            10,
            11,
            31,
            GROUND,
            26,
            GROUND,
            27,
            28,
            29
    };

    private final int address;
    private final PinMode mode;
    private final Pin gpioPin;

    public PinAddress(int address,
                      PinMode mode) {
        this.address = address;
        this.mode = mode;
        gpioPin = RaspiPin.getPinByAddress(address);
    }

    public int getAddress() {
        return address;
    }

    public PinMode getMode() {
        return mode;
    }

    public Pin getGpioPin() {
        return gpioPin;
    }
}
