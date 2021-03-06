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
 * A resource class - represent the power of a meccanum drivetrain.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class PowerMeccanum {
    public final double fr;
    public final double fl;
    public final double br;
    public final double bl;

    public PowerMeccanum(double fr,
                         double fl,
                         double br,
                         double bl) {
        this.fr = fr;
        this.fl = fl;
        this.br = br;
        this.bl = bl;
    }

    public double getFr() {
        return fr;
    }

    public double getFl() {
        return fl;
    }

    public double getBr() {
        return br;
    }

    public double getBl() {
        return bl;
    }
}
