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

public class I2CException extends Exception {
    protected static final String CONTROLLER_EXCEPTION =
            "You can't initialize two I2C devices with the same controller ID!";
    protected static final String ADDRESS_EXCEPTION =
            "You can't initialize two I2C devices with the same address!";
    protected static final String ADDRESS_SIZE_EXCEPTION =
            "Invalid address size when creating an I2CBusDevice.";
    protected static final String CLOCK_EXCEPTION =
            "Invalid I2C clock rate - use 0x186A0 if you're unsure.";
    protected static final String EXCEPTION =
            "There was an unspecified error while using pi2c.";

    public I2CException(String exception) {
        super(exception);
    }

    public static void throwNew(String exception) {
        try {
            throw new I2CException(exception);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
