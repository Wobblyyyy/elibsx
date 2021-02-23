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

import edu.wpi.first.wpilibj.TimedRobot;
import me.wobblyyyy.rlibx.RLib;
import me.wobblyyyy.rlibx.RLibCPI;
import me.wobblyyyy.rlibx.modes.ModeExecutor;
import me.wobblyyyy.rlibx.modes.Modes;
import me.wobblyyyy.rlibx.subsystem.Subsystem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An extension of the provided {@code TimedRobot} class that's designed to
 * easily integrate with {@code rlibx}'s much more refined and obviously
 * superior method of robot management.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class FRCOperationMode extends TimedRobot {
    /**
     * The CPI instance.
     *
     * <p>
     * If you don't know what this means, and you're curious about what it
     * means, you can go check out the documentation on the {@code RLibCPI}
     * class to get a better idea of what's going on.
     * </p>
     */
    private final RLibCPI cpi;

    /**
     * Load an RLib instance based on inputted modes and subsystems.
     *
     * @param modes      a HashMap of all of the mode elements that should be
     *                   added to the robot's execution pool. The keys of this
     *                   HashMap represent the mode that the following executor
     *                   will serve for, and the values represent executors
     *                   that should be executed whenever the given mode is
     *                   activated.
     */
    public FRCOperationMode(HashMap<Modes, ModeExecutor> modes) {
        RLib.load(modes);

        cpi = (RLibCPI) RLib.instance;
    }

    /**
     * Load an RLib instance based on inputted modes and subsystems.
     *
     * @param modes      a HashMap of all of the mode elements that should be
     *                   added to the robot's execution pool. The keys of this
     *                   HashMap represent the mode that the following executor
     *                   will serve for, and the values represent executors
     *                   that should be executed whenever the given mode is
     *                   activated.
     * @param subsystems an ArrayList of all of the subsystems on the robot.
     *                   These are typically pre-configured. The subsystem
     *                   manager inside of the {@link RLibCPI} class handles
     *                   all of the initialization and management of these
     *                   inputted subsystems, but it's still important to
     *                   configure the subsystems prior to passing them as
     *                   parameters to this method.
     */
    public FRCOperationMode(HashMap<Modes, ModeExecutor> modes,
                            ArrayList<Subsystem> subsystems) {
        RLib.load(modes, subsystems);

        cpi = (RLibCPI) RLib.instance;
    }

    /**
     * Called when the robot is initialized.
     */
    @Override
    public final void robotInit() {
        cpi.robot();
    }

    /**
     * Called when the simulation mode is activated.
     */
    @Override
    public final void simulationInit() {
        cpi.simulation();
    }

    /**
     * Called when the disabled mode is activated.
     *
     * <p>
     * As of now, this simply activates the robot mode. We should look into
     * potentially adding another mode for disabled in the future if the need
     * arises, but for now, there's no point in it.
     * </p>
     */
    @Override
    public final void disabledInit() {
        cpi.robot();
    }

    /**
     * Called when the autonomous period is initialized.
     */
    @Override
    public final void autonomousInit() {
        cpi.autonomous();
    }

    /**
     * Called when the teleop period is initialized.
     */
    @Override
    public final void teleopInit() {
        cpi.teleop();
    }

    /**
     * Called when the test period is initialized.
     */
    @Override
    public final void testInit() {
        cpi.testing();
    }

    /**
     * Don't do anything at all.
     */
    @Override
    public final void robotPeriodic() {

    }

    /**
     * Don't do anything at all.
     */
    @Override
    public final void simulationPeriodic() {

    }

    /**
     * Don't do anything at all.
     */
    @Override
    public final void disabledPeriodic() {

    }

    /**
     * Don't do anything at all.
     */
    @Override
    public final void autonomousPeriodic() {

    }

    /**
     * Don't do anything at all.
     */
    @Override
    public final void teleopPeriodic() {

    }

    /**
     * Don't do anything at all.
     */
    @Override
    public final void testPeriodic() {

    }
}
