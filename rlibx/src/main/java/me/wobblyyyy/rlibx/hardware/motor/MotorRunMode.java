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

/**
 * Different modes that motors can run in.
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public enum MotorRunMode {
    /**
     * The motor is NOT encoded - no encoder is used, no counts are updated,
     * none of that. Un-encoded is the default run mode for any motor. Unless
     * otherwise specified via configuration or something else, motors will
     * always run without encoders.
     */
    UNENCODED,

    /**
     * The motor IS encoded - you can track the position of the motor, etc,
     * etc. In order for a motor to run in encoded mode, it must be enabled
     * in the motor's configuration by passing an encoder as a parameter.
     */
    ENCODED,

    /**
     * The motor targets a setpoint. The motor tracks the encoder's position
     * and attempts to go to that point to the best of its ability.
     */
    SETPOINT,
}
