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

package me.wobblyyyy.rlibx.frc;

import me.wobblyyyy.rlibx.configuration.SubsystemConfiguration;
import me.wobblyyyy.rlibx.drive.DrivetrainTank;
import me.wobblyyyy.rlibx.drive.TranslationTank;
import me.wobblyyyy.rlibx.hardware.motor.Motor;
import me.wobblyyyy.rlibx.subsystem.Subsystem;

public class FRCDriveTank extends Subsystem {
    private final DrivetrainTank drivetrain;

    public FRCDriveTank(SubsystemConfiguration configuration,
                        Motor fr,
                        Motor fl,
                        Motor br,
                        Motor bl) {
        super(configuration);

        drivetrain = new DrivetrainTank(fr, fl, br, bl);
    }

    public void drive(double left,
                      double right) {
        drive(new TranslationTank(left, right));
    }

    public void drive(TranslationTank translation) {
        drivetrain.setPower(translation);
    }
}
