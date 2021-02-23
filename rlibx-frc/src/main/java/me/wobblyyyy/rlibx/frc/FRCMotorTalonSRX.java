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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import me.wobblyyyy.rlibx.interfaces.MotorCore;

/**
 * A wrapper class that implements the methods of the {@code MotorCore}
 * interface and controls a Talon SRX motor.
 *
 * @author Colin Robertson
 * @since 0.2.0
 * @see FRCMotorTalon
 */
public class FRCMotorTalonSRX implements MotorCore {
    private final TalonSRX talon;
    private double power;

    public FRCMotorTalonSRX(int id) {
        talon = new TalonSRX(id);
    }

    @Override
    public double getPower() {
        return power;
    }

    @Override
    public void setPower(double power) {
        this.power = power;

        talon.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void init() {

    }
}
