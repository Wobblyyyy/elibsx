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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import me.wobblyyyy.rlibx.interfaces.MotorCore;

/**
 * A CAN-enabled SPARK motor.
 *
 * <p>
 * This is the most generic Spark motor available. Both the
 * {@link FRCMotorSparkBrushless} and the {@link FRCMotorSparkBrushed} extend
 * this class and specify whether the motor is brushed or brushless.
 * </p>
 *
 * <p>
 * This additional layer of abstraction happens for one reason and one reason
 * only - we want to make SURE that the user knows if they have a brushed
 * or a brushless motor. Using the wrong mode can permanently harm the motor.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.2.0
 * @see FRCMotorSparkBrushed
 * @see FRCMotorSparkBrushless
 */
public class FRCMotorSpark implements MotorCore {
    /**
     * BRUSHED motor type.
     */
    public static final CANSparkMaxLowLevel.MotorType BRUSHED =
            CANSparkMaxLowLevel.MotorType.kBrushed;

    /**
     * BRUSHLESS motor type.
     */
    public static final CANSparkMaxLowLevel.MotorType BRUSHLESS =
            CANSparkMaxLowLevel.MotorType.kBrushless;

    /**
     * A reference to the internally-used Spark.
     */
    private final CANSparkMax spark;

    /**
     * Create a new Spark motor.
     *
     * @param id the motor's ID.
     * @param type the motor's type - brushed or brushless.
     * @param idleMode the motor's idle mode.
     */
    public FRCMotorSpark(int id,
                         CANSparkMaxLowLevel.MotorType type,
                         CANSparkMax.IdleMode idleMode) {
        spark = new CANSparkMax(id, type);

        spark.setIdleMode(idleMode);
    }

    /**
     * Get the Spark motor's power.
     *
     * @return the motor's power.
     */
    @Override
    public double getPower() {
        return spark.get();
    }

    /**
     * Set the Spark motor's power.
     *
     * @param power the motor's power.
     */
    @Override
    public void setPower(double power) {
        spark.set(power);
    }

    /**
     * Initialize the motor.
     *
     * <p>
     * Note that we don't actually need to initialize anything here - all of
     * the initialization should be handled on construction.
     * </p>
     */
    @Override
    public void init() {

    }
}
