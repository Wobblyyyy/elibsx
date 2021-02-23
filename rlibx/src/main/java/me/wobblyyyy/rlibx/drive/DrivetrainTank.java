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

/**
 * An implementation of a tank drivetrain.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class DrivetrainTank {
    /**
     * The drivetrain's front-right motor.
     */
    private final Motor fr;

    /**
     * The drivetrain's front-left motor.
     */
    private final Motor fl;

    /**
     * The drivetrain's back-right motor.
     */
    private final Motor br;

    /**
     * The drivetrain's back-left motor.
     */
    private final Motor bl;

    /**
     * Create a new tank drivetrain.
     *
     * @param fr the front-right motor.
     * @param fl the front-left motor.
     * @param br the back-right motor.
     * @param bl the back-left motor.
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

    /**
     * Set power to the left side of the drivetrain.
     *
     * @param power power to set.
     */
    private void setLeft(double power) {
        fl.setPower(power);
        bl.setPower(power);
    }

    /**
     * Set power to the right side of the drivetrain.
     *
     * @param power power to set.
     */
    private void setRight(double power) {
        fr.setPower(power);
        br.setPower(power);
    }

    /**
     * Apply power to the entire drivetrain.
     *
     * @param power power to apply to the entire drivetrain.
     * @see DrivetrainTank#setLeft(double)
     * @see DrivetrainTank#setRight(double)
     */
    private void applyPower(PowerTank power) {
        setLeft(power.getLeft());
        setRight(power.getRight());
    }

    /**
     * Set power to the tank drivetrain based on a tank translation.
     *
     * <p>
     * Unlike all of the other drivetrains in this class, the tank drivetrain
     * uses a special type of translation - that type of translation, of course,
     * being {@link TranslationTank}. Functionally, it's identical to the
     * {@link Translation} class - however, renaming it to have the word
     * {@code Tank} in the name makes it even harder to be confused.
     * </p>
     *
     * @param translation the translation which the tank drivetrain should
     *                    attempt to follow along.
     */
    public void setPower(TranslationTank translation) {
        PowerTank power = Kinematics.calculateTank(translation);

        applyPower(power);
    }
}
