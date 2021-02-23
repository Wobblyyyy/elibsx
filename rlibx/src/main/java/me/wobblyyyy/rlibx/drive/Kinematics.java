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
 * Various forwards kinematics for use in determining wheel speeds on several
 * types of chassis, including tank, swerve, and meccanum.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class Kinematics {
    /**
     * Calculate the power for a meccanum chassis based on a translation.
     *
     * @param translation the translation to calculate based on.
     * @return {@code PowerMeccanum} for the drivetrain.
     */
    public static PowerMeccanum calculateMeccanum(Translation translation) {
        return null;
    }

    /**
     * Calculate the power for a swerve chassis based on a translation.
     *
     * @param translation the translation to calculate based on.
     * @return {@code PowerSwerve} for the drivetrain.
     */
    public static PowerSwerve calculateSwerve(Translation translation) {
        return null;
    }

    /**
     * Calculate the power for a tank chassis based on an inputted translation.
     *
     * <p>
     * As a note, tank drivetrains use a different type of translation than
     * all other drivetrains. "Normal" drivetrains use translations, tank
     * drivetrains use tank translations.
     * </p>
     *
     * @param translation the tank translation to calculate for.
     * @return calculated power values based on the inputted translation.
     */
    public static PowerTank calculateTank(TranslationTank translation) {
        return new PowerTank(
                translation.getX1(),
                translation.getX2()
        );
    }
}
