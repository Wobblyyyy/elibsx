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

package me.wobblyyyy.rlibx.util;

/**
 * Storage class used for storing an ARGB value.
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class ARGB {
    /**
     * ALPHA value.
     */
    private final double A;

    /**
     * RED value.
     */
    private final double R;

    /**
     * GREEN value.
     */
    private final double G;

    /**
     * BLUE value.
     */
    private final double B;

    /**
     * Create a new ARGB color.
     *
     * @param a ALPHA value.
     * @param r RED value.
     * @param g GREEN value.
     * @param b BLUE value.
     */
    public ARGB(double a,
                double r,
                double g,
                double b) {
        A = a;
        R = r;
        G = g;
        B = b;
    }

    /**
     * Get ALPHA.
     *
     * @return ALPHA.
     */
    public double getA() {
        return A;
    }

    /**
     * Get RED.
     *
     * @return RED.
     */
    public double getR() {
        return R;
    }

    /**
     * Get GREEN.
     *
     * @return GREEN.
     */
    public double getG() {
        return G;
    }

    /**
     * Get BLUE.
     *
     * @return BLUE.
     */
    public double getB() {
        return B;
    }
}