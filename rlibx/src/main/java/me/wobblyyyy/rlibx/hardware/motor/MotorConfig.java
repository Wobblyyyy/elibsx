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

package me.wobblyyyy.rlibx.hardware.motor;

import me.wobblyyyy.rlibx.hardware.encoder.Encoder;

/**
 * Configuration used for representing a motor's parameters.
 *
 * <p>
 * After a motor configuration is created and passed to a motor, that motor
 * will need to be controlled DIRECTLY, rather than through a configuration
 * as a sort of proxy class.
 * </p>
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public class MotorConfig {
    /**
     * Default minimum.
     */
    public static final double MIN = -1.0;

    /**
     * Default maximum.
     */
    public static final double MAX = 1.0;

    /**
     * Default multiplier.
     */
    public static final double MULTIPLIER = 1.0;

    /**
     * Default deadzone.
     */
    public static final double DEADZONE = 0.0;

    /**
     * Default lazy state.
     */
    public static final boolean IS_LAZY = true;

    /**
     * Default direction.
     */
    public static final Direction DIRECTION = Direction.FORWARDS;

    /**
     * Default motor run mode.
     */
    public static final MotorRunMode RUN_MODE = MotorRunMode.UNENCODED;

    /**
     * Minimum power value.
     */
    private double min;

    /**
     * Maximum power value.
     */
    private double max;

    /**
     * Power multiplier (before min and max checks).
     */
    private double multiplier;

    /**
     * Power deadzone.
     */
    private double deadzone;

    /**
     * Should the motor use lazy power setting?
     */
    private boolean isLazy;

    /**
     * The motor's direction.
     */
    private Direction direction;

    /**
     * The motor's run mode.
     */
    private MotorRunMode runMode;

    /**
     * The motor's encoder.
     */
    private Encoder encoder;

    /**
     * Create a new MotorConfig - use default values for omitted parameters.
     *
     * @param direction the motor's direction.
     */
    public MotorConfig(Direction direction) {
        this(
                MIN,
                MAX,
                MULTIPLIER,
                DEADZONE,
                IS_LAZY,
                direction
        );
    }

    /**
     * Create a new MotorConfig.
     *
     * @param min        the motor's minimum value.
     * @param max        the motor's maximum value.
     * @param multiplier the motor's multiplier.
     * @param deadzone   the motor's deadzone.
     * @param isLazy     should the motor use lazy power?
     * @param direction  the motor's direction.
     */
    public MotorConfig(double min,
                       double max,
                       double multiplier,
                       double deadzone,
                       boolean isLazy,
                       Direction direction) {
        this(
                min,
                max,
                multiplier,
                deadzone,
                isLazy,
                direction,
                RUN_MODE,
                null
        );
    }

    /**
     * Create a new MotorConfig.
     *
     * @param min        the motor's minimum value.
     * @param max        the motor's maximum value.
     * @param multiplier the motor's multiplier.
     * @param deadzone   the motor's deadzone.
     * @param isLazy     should the motor use lazy power?
     * @param direction  the motor's direction.
     * @param runMode    the motor's mode of operation.
     * @param encoder    the motor's tracking encoder.
     */
    public MotorConfig(double min,
                       double max,
                       double multiplier,
                       double deadzone,
                       boolean isLazy,
                       Direction direction,
                       MotorRunMode runMode,
                       Encoder encoder) {
        if (runMode == MotorRunMode.UNENCODED) {
            this.encoder = null;
        } else {
            this.encoder = encoder;
        }

        this.min = min;
        this.max = max;
        this.multiplier = multiplier;
        this.deadzone = deadzone;
        this.isLazy = isLazy;
        this.direction = direction;
    }

    /**
     * Get the motor's minimum power.
     *
     * @return the motor's minimum power.
     */
    public double getMin() {
        return min;
    }

    /**
     * Set the motor's minimum power.
     *
     * @param min the motor's minimum power.
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * Get the motor's maximum power.
     *
     * @return the motor's maximum power.
     */
    public double getMax() {
        return max;
    }

    /**
     * Set the motor's maximum power.
     *
     * @param max the motor's maximum power.
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * Get the motor's multiplier.
     *
     * @return the motor's multiplier.
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Set the motor's multiplier.
     *
     * @param multiplier the motor's multiplier.
     */
    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Get the motor's deadzone.
     *
     * @return the motor's deadzone.
     */
    public double getDeadzone() {
        return deadzone;
    }

    /**
     * Set the motor's deadzone.
     *
     * @param deadzone the motor's deadzone.
     */
    public void setDeadzone(double deadzone) {
        this.deadzone = deadzone;
    }

    /**
     * Get the motor's run mode.
     *
     * @return the motor's run mode.
     */
    public MotorRunMode getRunMode() {
        return runMode;
    }

    /**
     * Set the motor's run mode.
     *
     * @param runMode the motor's run mode.
     */
    public void setRunMode(MotorRunMode runMode) {
        this.runMode = runMode;
    }

    /**
     * Get the motor's encoder.
     *
     * @return the motor's encoder.
     */
    public Encoder getEncoder() {
        return encoder;
    }

    /**
     * Set the motor's encoder.
     *
     * @param encoder the motor's encoder.
     */
    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Does the motor use lazy mode?
     *
     * @return whether or not the motor uses lazy mode.
     */
    public boolean isLazy() {
        return isLazy;
    }

    /**
     * Set the motor to use lazy mode (on/off).
     *
     * @param lazy whether or not the motor should use lazy mode.
     */
    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    /**
     * Get the motor's direction.
     *
     * @return the motor's direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set the motor's direction.
     *
     * @param direction the motor's new direction.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
