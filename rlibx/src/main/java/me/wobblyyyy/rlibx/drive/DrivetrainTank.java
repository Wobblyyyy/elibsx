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

import me.wobblyyyy.rlibx.hardware.motor.Motor;

public class DrivetrainTank {
    private final Motor fr;
    private final Motor fl;
    private final Motor br;
    private final Motor bl;

    /**
     * Create a tank drivetrain.
     */
    public DrivetrainTank(Motor fr,
                          Motor fl,
                          Motor br,
                          Motor bl) {
        this.fr = fr;
        this.fl = fl;
        this.br = br;
        this.bl = bl;
    }

    private void setLeft(double power) {
        fl.setPower(power);
        bl.setPower(power);
    }

    private void setRight(double power) {
        fr.setPower(power);
        br.setPower(power);
    }

    private void applyPower(PowerTank power) {
        setLeft(power.getLeft());
        setRight(power.getRight());
    }

    public void setPower(TranslationTank translation) {
        PowerTank power = Kinematics.calculateTank(translation);

        applyPower(power);
    }
}
