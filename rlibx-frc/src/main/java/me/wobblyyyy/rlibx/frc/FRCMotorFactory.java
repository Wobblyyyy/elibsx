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
import me.wobblyyyy.rlibx.hardware.motor.Motor;
import me.wobblyyyy.rlibx.hardware.motor.MotorConfig;
import me.wobblyyyy.rlibx.interfaces.MotorCore;

/**
 * Create new {@link Motor} instances specific to FRC robotics.
 *
 * <p>
 * When dealing with motors, it's often very important to remember to set
 * the correct operation mode. The two operation modes that we can choose
 * between include:
 * <ul>
 *     <li>{@code Brushed} - For motors with brushes.</li>
 *     <li>{@code Brushless} - For motors without brushes.</li>
 * </ul>
 * If you choose the wrong type of motor, you may accidentally permanently
 * damage your motor! Be sure not to do that. If you'd like, you can play it
 * safe by setting every motor to be brushless and adapting from there. However,
 * the easiest way to tell if a motor is brushed or brushless is to look at
 * how many wires the motor has. If the motor is connected via two wires -
 * often red and black - the motor is brushed. However, if the motor is
 * connected via three wires - often red, black, and white - the motor is
 * brushless, not brushed.
 * </p>
 *
 * @author Colin Robertson
 * @see FRCMotorSparkBrushed
 * @see FRCMotorSparkBrushless
 * @see FRCMotorTalon
 * @see FRCMotorTalonSRX
 * @since 0.2.0
 */
public class FRCMotorFactory {
    /**
     * The default idle mode for all SPARK MAX motors.
     *
     * <p>
     * By default, CAN Spark Max motors will be set to use the idle mode
     * {@code kCoast}, which essentially means that the motor will do nothing
     * when its powered, compared to actively braking.
     * </p>
     */
    public static final CANSparkMax.IdleMode IDLE_MODE =
            CANSparkMax.IdleMode.kCoast;

    /**
     * Get a motor based on the motor's core and the motor's configuration.
     *
     * @param core   the motor's core.
     * @param config the motor's configuration.
     * @return a new Motor element.
     */
    public static Motor getMotor(MotorCore core,
                                 MotorConfig config) {
        return new Motor(core, config);
    }

    /**
     * Create a new brushless CAN Spark Max motor.
     *
     * @param id     the motor's CAN ID.
     * @param config the motor's configuration.
     * @return a new CAN Spark Max motor.
     * @see FRCMotorSparkBrushless
     * @see FRCMotorSpark
     * @see FRCMotorFactory#newSparkBrushless(int, CANSparkMax.IdleMode, MotorConfig)
     */
    public static Motor newSparkBrushless(int id,
                                          MotorConfig config) {
        return newSparkBrushless(id, IDLE_MODE, config);
    }

    /**
     * Create a new brushed CAN Spark Max motor.
     *
     * @param id     the motor's CAN ID.
     * @param config the motor's configuration.
     * @return a new CAN Spark Max motor.
     * @see FRCMotorSparkBrushed
     * @see FRCMotorSpark
     * @see FRCMotorFactory#newSparkBrushed(int, CANSparkMax.IdleMode, MotorConfig)
     */
    public static Motor newSparkBrushed(int id,
                                        MotorConfig config) {
        return newSparkBrushed(id, IDLE_MODE, config);
    }

    /**
     * Create a new brushless CAN Spark Max motor.
     *
     * @param id       the motor's CAN ID.
     * @param idleMode the motor's idle mode - by default, kCoast.
     * @param config   the motor's configuration.
     * @return a new CAN Spark Max motor.
     * @see FRCMotorSparkBrushless
     * @see FRCMotorSpark
     * @see FRCMotorFactory#newSparkBrushless(int, MotorConfig)
     */
    public static Motor newSparkBrushless(int id,
                                          CANSparkMax.IdleMode idleMode,
                                          MotorConfig config) {
        return getMotor(
                new FRCMotorSparkBrushless(id, idleMode),
                config
        );
    }

    /**
     * Create a new brushed CAN Spark Max motor.
     *
     * @param id       the motor's CAN ID.
     * @param idleMode the motor's idle mode - by default, kCoast.
     * @param config   the motor's configuration.
     * @return a new CAN Spark Max motor.
     * @see FRCMotorSparkBrushed
     * @see FRCMotorSpark
     * @see FRCMotorFactory#newSparkBrushed(int, MotorConfig)
     */
    public static Motor newSparkBrushed(int id,
                                        CANSparkMax.IdleMode idleMode,
                                        MotorConfig config) {
        return getMotor(
                new FRCMotorSparkBrushed(id, idleMode),
                config
        );
    }

    /**
     * Create a new non-can Talon motor.
     *
     * @param id     the motor's ID.
     * @param config the motor's configuration.
     * @return a new Talon motor.
     * @see FRCMotorTalon
     * @see FRCMotorFactory#newTalonSrx(int, MotorConfig)
     */
    public static Motor newTalon(int id,
                                 MotorConfig config) {
        return getMotor(
                new FRCMotorTalon(id),
                config
        );
    }

    /**
     * Create a new CAN-enabled Talon SRX motor.
     *
     * @param id     the motor's CAN ID.
     * @param config the motor's configuration.
     * @return a new Talon SRX motor.
     * @see FRCMotorTalonSRX
     * @see FRCMotorFactory#newTalon(int, MotorConfig)
     */
    public static Motor newTalonSrx(int id,
                                    MotorConfig config) {
        return getMotor(
                new FRCMotorTalonSRX(id),
                config
        );
    }
}
