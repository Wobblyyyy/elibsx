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

package me.wobblyyyy.rlibx.pi;

import me.wobblyyyy.rlibx.configuration.SubsystemConfiguration;
import me.wobblyyyy.rlibx.drive.DrivetrainTank;
import me.wobblyyyy.rlibx.drive.TranslationTank;
import me.wobblyyyy.rlibx.drive.Wheels;
import me.wobblyyyy.rlibx.hardware.motor.Motor;
import me.wobblyyyy.rlibx.subsystem.Subsystem;

/**
 * A simple tank drive.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class PiDriveTank extends Subsystem {
    /**
     * The tank drive's drivetrain.
     *
     * <p>
     * This drivetrain, of course, is used in actually driving the robot. Who
     * could have possibly seen that coming? It's insane, isn't it?
     * </p>
     */
    private final DrivetrainTank drivetrain;

    public PiDriveTank(SubsystemConfiguration configuration,
                       int frId,
                       int flId,
                       int brId,
                       int blId) {
        super(configuration);

        Motor fr = PiMotorFactory.newMotor(frId);
        Motor fl = PiMotorFactory.newMotor(flId);
        Motor br = PiMotorFactory.newMotor(brId);
        Motor bl = PiMotorFactory.newMotor(blId);

        drivetrain = new DrivetrainTank(fr, fl, br, bl);
    }

    public PiDriveTank(SubsystemConfiguration configuration) {
        super(configuration);

        Motor fr = PiMotorFactory
                .newMotor(configuration.getInt(Wheels.FR.get()));
        Motor fl = PiMotorFactory
                .newMotor(configuration.getInt(Wheels.FL.get()));
        Motor br = PiMotorFactory
                .newMotor(configuration.getInt(Wheels.BR.get()));
        Motor bl = PiMotorFactory
                .newMotor(configuration.getInt(Wheels.BL.get()));

        drivetrain = new DrivetrainTank(fr, fl, br, bl);
    }

    /**
     * Create a new PiDriveTank instance.
     *
     * @param configuration the subsystem's configuration.
     * @param fr            front-right motor.
     * @param fl            front-left motor.
     * @param br            back-right motor.
     * @param bl            back-left motor.
     */
    public PiDriveTank(SubsystemConfiguration configuration,
                       Motor fr,
                       Motor fl,
                       Motor br,
                       Motor bl) {
        super(configuration);

        drivetrain = new DrivetrainTank(fr, fl, br, bl);
    }

    /**
     * Drive the drivetrain.
     *
     * @param left  power to set to the left side.
     * @param right power to set to the right side.
     */
    public void drive(double left,
                      double right) {
        drive(new TranslationTank(left, right));
    }

    /**
     * Drive the drivetrain.
     *
     * @param translation the chassis' desired translation.
     */
    public void drive(TranslationTank translation) {
        drivetrain.setPower(translation);
    }
}
