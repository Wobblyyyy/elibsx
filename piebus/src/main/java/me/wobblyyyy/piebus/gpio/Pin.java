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

import com.pi4j.io.gpio.*;
import me.wobblyyyy.piebus.exception.PiebusException;

/**
 * A virtual representation of a general purpose input/output pin. In simple
 * terms, this is a class that is designed to extend the functionality of the
 * lower-level GPIO pin provided by the pi4j library.
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
public class Pin {
    private PinAddress address;
    private GpioPinDigital pin;

    public Pin(PinAddress address) {
        instantiatePin(address);
    }

    private void instantiatePin(PinAddress address) {
        try {
            this.address = address;

            switch (address.getMode()) {
                case INPUT -> pin = PinController
                        .getController()
                        .provisionDigitalInputPin(address.getGpioPin());
                case OUTPUT -> pin = PinController
                        .getController()
                        .provisionDigitalOutputPin(address.getGpioPin());
                default -> PiebusException.throwNew(PiebusException.INVALID_MODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PinAddress getAddress() {
        return address;
    }

    public GpioPinDigital getPin() {
        return pin;
    }

    public GpioPinDigitalInput getInput() {
        return (GpioPinDigitalInput) getPin();
    }

    public GpioPinDigitalOutput getOutput() {
        return (GpioPinDigitalOutput) getPin();
    }
}
