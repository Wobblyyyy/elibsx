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

package me.wobblyyyy.rlibx.frc;

import edu.wpi.first.wpilibj.Talon;
import me.wobblyyyy.rlibx.interfaces.MotorCore;

/**
 * A wrapper class that implements the methods of the {@code MotorCore}
 * interface and controls a Talon motor.
 *
 * @author Colin Robertson
 * @since 0.2.0
 * @see FRCMotorTalonSRX
 */
public class FRCMotorTalon implements MotorCore {
    private final Talon talon;

    public FRCMotorTalon(int id) {
        talon = new Talon(id);
    }

    @Override
    public double getPower() {
        return talon.get();
    }

    @Override
    public void setPower(double power) {
        talon.set(power);
    }

    @Override
    public void init() {

    }
}
