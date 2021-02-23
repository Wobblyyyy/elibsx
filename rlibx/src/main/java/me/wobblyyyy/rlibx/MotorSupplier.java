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

import me.wobblyyyy.rlibx.hardware.motor.Motor;

import java.util.HashMap;

public class MotorSupplier {
    public static HashMap<String, Motor> motors = new HashMap<>();

    public static Motor getMotor(int id) {
        String idAsString = String.valueOf(id);

        return motors.get(idAsString);
    }

    public static Motor getMotor(String id) {
        return motors.get(id);
    }

    public static void register(int id, Motor motor) {
        register(String.valueOf(id), motor);
    }

    public static void register(String id, Motor motor) {
        motors.put(id, motor);
    }
}
