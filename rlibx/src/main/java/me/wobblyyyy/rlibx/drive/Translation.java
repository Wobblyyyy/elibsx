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

package me.wobblyyyy.rlibx.drive;

/**
 * A representation of a two-dimensional translation.
 *
 * <p>
 * Translations are made up of several components:
 * <ul>
 *     <li>X translation (movement along the X axis)</li>
 *     <li>Y translation (movement along the Y axis)</li>
 * </ul>
 * </p>
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class Translation {
    /**
     * How far to move along the X axis.
     */
    private final double x;

    /**
     * How far to move along the Y axis.
     */
    private final double y;

    /**
     * Create a new translation.
     *
     * @param x how far to move in the X direction.
     * @param y how far to move in the Y direction.
     */
    public Translation(double x,
                       double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the X component of the translation.
     *
     * @return the translation's X component.
     */
    public double getX() {
        return x;
    }

    /**
     * Get the Y component of the translation.
     *
     * @return the translation's Y component.
     */
    public double getY() {
        return y;
    }
}
