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
 * Interface used for connecting to a gyroscope.
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public interface GyroscopeCore extends ComponentCore {
    /**
     * Get the gyroscope's X value.
     *
     * @return the gyroscope's X value.
     */
    double getX();

    /**
     * Get the gyroscope's Y value.
     *
     * @return the gyroscope's Y value.
     */
    double getY();

    /**
     * Get the velocity of the robot.
     *
     * @return the robot's velocity.
     */
    double getVelocity();

    /**
     * The heading of the gyroscope (in degrees).
     *
     * @return the gyroscope's heading in degrees.
     */
    double getHeading();
}
