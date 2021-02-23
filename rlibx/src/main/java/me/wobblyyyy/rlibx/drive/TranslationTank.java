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
 * An extension of the Translation class that's customized for tank
 * drivetrains exclusively.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class TranslationTank extends Translation {
    /**
     * Create a new translation.
     *
     * @param x1 how far to move in the X1 direction.
     * @param x2 how far to move in the X2 direction.
     */
    public TranslationTank(double x1, double x2) {
        super(x1, x2);
    }

    /**
     * Get the X component of the translation.
     *
     * @return the translation's X component.
     */
    @Override
    public final double getX() {
        return 0.0;
    }

    /**
     * Get the Y component of the translation.
     *
     * @return the translation's Y component.
     */
    @Override
    public final double getY() {
        return 0.0;
    }

    /**
     * Get the X1 value.
     *
     * @return the x1 value.
     */
    public final double getX1() {
        return super.getX();
    }

    /**
     * Get the X2 value.
     *
     * @return the x2 value.
     */
    public final double getX2() {
        return super.getY();
    }
}
