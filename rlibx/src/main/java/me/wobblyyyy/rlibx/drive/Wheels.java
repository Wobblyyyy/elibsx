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
 * Different wheels on typical drivetrains.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public enum Wheels {
    /**
     * Front-right.
     */
    FR("fr"),

    /**
     * Front-left.
     */
    FL("fl"),

    /**
     * Back-right.
     */
    BR("br"),

    /**
     * Back-left.
     */
    BL("bl"),

    /**
     * Front-right turn.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    FRT("frt"),

    /**
     * Front-left turn.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    FLT("flt"),

    /**
     * Back-right turn.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    BRT("brt"),

    /**
     * Back-left turn.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    BLT("blt"),

    /**
     * Front-right drive.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    FRD("frd"),

    /**
     * Front-left drive.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    FLD("fld"),

    /**
     * Back-right drive.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    BRD("brd"),

    /**
     * Back-left drive.
     *
     * <p>
     * This enum/string combo should typically only be used in swerve
     * drivetrains, as other drivetrains don't have turn and drive motors.
     * </p>
     */
    BLD("bld");

    /**
     * String key.
     */
    String string;

    /**
     * Create new enumerated wheel, with string key.
     *
     * @param string the enum's key.
     */
    Wheels(String string) {
        this.string = string;
    }

    /**
     * Get the string from the enum.
     *
     * @return the string from the enum.
     */
    public String get() {
        return string;
    }
}
