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

package me.wobblyyyy.rlibx.interfaces;

/**
 * Interface used in connecting different types of motors.
 *
 * @author Colin Robertson
 */
public interface MotorCore extends ComponentCore {
    /**
     * Get the motor's power.
     *
     * <p>
     * Power values should always be within the range of (-1) to (+1). -1
     * represents the motor's maximum speed BACKWARDS. +1 represents the
     * motor's maximum speed FORWARDS.
     * </p>
     *
     * @return the motor's power.
     */
    double getPower();

    /**
     * Set the motor's power.
     *
     * <p>
     * Power values should always be within the range of (-1) to (+1). -1
     * represents the motor's maximum speed BACKWARDS. +1 represents the
     * motor's maximum speed FORWARDS.
     * </p>
     *
     * @param power the motor's power.
     */
    void setPower(double power);
}
