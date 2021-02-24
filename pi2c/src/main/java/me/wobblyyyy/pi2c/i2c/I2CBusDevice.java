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

package me.wobblyyyy.pi2c.i2c;

import jdk.dio.DeviceManager;
import jdk.dio.i2cbus.I2CDevice;
import jdk.dio.i2cbus.I2CDeviceConfig;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class I2CBusDevice {
    public static final int ADDRESS = 0x0;
    public static final int ADDRESS_SIZE = 0x7;
    public static final int CLOCK_FREQUENCY = 0x186A0;

    private static final String CONTROLLER_EXCEPTION =
            "You can't initialize two I2C devices with the same controller ID!";
    private static final String ADDRESS_EXCEPTION =
            "You can't initialize two I2C devices with the same address!";

    private static final ArrayList<Integer> controllerIds = new ArrayList<>();
    private static final ArrayList<Integer> addresses = new ArrayList<>();

    private I2CDeviceConfig config;
    private I2CDevice servant;
    private boolean busInitializedYet = false;

    public I2CBusDevice(int controllerId,
                        int address,
                        int addressSize,
                        int clockFrequency) {
        if (controllerIds.contains(controllerId)) {
            I2CException.throwNew(CONTROLLER_EXCEPTION);
        } else if (addresses.contains(address)) {
            I2CException.throwNew(ADDRESS_EXCEPTION);
        } else {
            this.config = new I2CDeviceConfig(
                    controllerId,
                    address,
                    addressSize,
                    clockFrequency
            );

            controllerIds.add(controllerId);
            addresses.add(address);
        }
    }

    public void initializeBus() {
        if (!busInitializedYet) {
            try {
                servant = DeviceManager.open(config);
                busInitializedYet = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void write(I2CObject i2CObject) {
        initializeBus();

        try {
            servant.write(i2CObject.getByteBuffer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public I2CObject read() {
        initializeBus();

        try {
            I2CObject newObject = new I2CObject("");
            servant.read(newObject.getByteBuffer());
            newObject.setBytes(newObject.getByteBuffer().array());
            return newObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
