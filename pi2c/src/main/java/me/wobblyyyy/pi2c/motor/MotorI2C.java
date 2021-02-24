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

package me.wobblyyyy.pi2c.motor;

import me.wobblyyyy.pi2c.i2c.I2CBusDevice;
import me.wobblyyyy.pi2c.i2c.I2CDriver;
import me.wobblyyyy.pi2c.i2c.I2CObject;

import java.util.ArrayList;

public class MotorI2C implements I2CDriver {
    private static final int CONTROLLER_ID = 0x01;
    private static final int ADDRESS = 0x3C;
    private static final int ADDRESS_SIZE = 0x7;
    private static final int CLOCK_FREQUENCY = 0x186A0;

    private final I2CBusDevice device;

    public MotorI2C() {
        device = new I2CBusDevice(
                CONTROLLER_ID,
                ADDRESS,
                ADDRESS_SIZE,
                CLOCK_FREQUENCY
        );
    }

    @Override
    public void write() {
        I2CObject encoded = new I2CObject(PiMotorRegistry.getFormatted());
        device.write(encoded);
    }

    @Override
    public String read() {
        return "";
    }
}
