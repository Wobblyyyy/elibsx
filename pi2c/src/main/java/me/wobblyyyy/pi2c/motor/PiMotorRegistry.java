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

package me.wobblyyyy.pi2c.motor;

import java.util.ArrayList;

public class PiMotorRegistry {
    private static final String templateId = "%m";
    private static final String templatePower = "%p";
    private static final String templateLinker = ":";
    private static final String templateSeparator = "!";
    private static final String template = templateId +
            templateLinker +
            templatePower +
            templateSeparator;

    private static final ArrayList<PiMotor> piMotors = new ArrayList<>();
    private static final ArrayList<Integer> ids = new ArrayList<>();

    public static void registerMotor(PiMotor motor) {
        if (!ids.contains(motor.getId())) {
            piMotors.add(motor);
            ids.add(motor.getId());
        }
    }

    public static ArrayList<PiMotor> getPiMotors() {
        return piMotors;
    }

    public static ArrayList<PiMotor> getLazyPiMotors() {
        return piMotors;
    }

    private static String format(int id,
                          double power) {
        return template
                .replace(templateId, String.valueOf(id))
                .replace(templatePower, String.valueOf(power));
    }

    private static String formatFast(int id,
                              double power) {
        return id + templateLinker + power + templateSeparator;
    }

    private static String formatAll(ArrayList<PiMotor> motors) {
        StringBuilder builder = new StringBuilder();

        for (PiMotor motor : motors) {
            builder.append(formatFast(motor.getId(), motor.getPower()));
        }

        return builder.toString();
    }

    public static String getFormatted() {
        return formatAll(getLazyPiMotors());
    }
}
