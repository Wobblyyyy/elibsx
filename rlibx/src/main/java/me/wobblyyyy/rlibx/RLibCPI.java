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

package me.wobblyyyy.rlibx;

import me.wobblyyyy.rlibx.modes.ModeExecutor;
import me.wobblyyyy.rlibx.modes.Modes;
import me.wobblyyyy.rlibx.subsystem.Subsystem;
import me.wobblyyyy.rlibx.subsystem.SubsystemManager;

import java.util.HashMap;

/**
 * An extension of the {@code RLibInstance} class that adds support for
 * subsystems and the management of those subsystems.
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class RLibCPI extends RLibInstance {
    /**
     * The subsystem manager that keeps track of all of the registered
     * subsystems. This manager can be accessed with the register method
     * that's provided by this class.
     *
     * @see RLibCPI#register(Subsystem)
     */
    public SubsystemManager manager;

    /**
     * Create a new RLibCPI instance, using the inputted instance's mode
     * manager's mode HashMap.
     *
     * @param rLibInstance the instance that should be upcasted.
     */
    public RLibCPI(RLibInstance rLibInstance) {
        this(rLibInstance.getMultiMode().getManager().getModes());
    }

    /**
     * Create a new {@code RLibCPI} instance.
     *
     * @param modes a {@code HashMap} of all of the modes that are used or
     *              executed by the robot at any point. This HashMap should
     *              contain any of the modes that will be used and executor
     *              code that should be executed once that mode's init phase
     *              is called. If a {@code Modes} element is omitted, and your
     *              code attempts to activate that mode, you'll get a
     *              NullPointerException, and things won't be very cool at
     *              all. So yeah. Don't do that.
     */
    public RLibCPI(HashMap<Modes, ModeExecutor> modes) {
        super(modes);
    }

    /**
     * Register a subsystem. Woot-woot! My favorite part of the day, to be
     * entirely honest.
     *
     * @param subsystem the subsystem that should be registered.
     */
    public void register(Subsystem subsystem) {
        manager.register(subsystem);
    }

    /**
     * Initialize all of the subsystems on the robot.
     *
     * <p>
     * This method calls the {@link SubsystemManager#init()} method, which
     * initializes all of the registered subsystems.
     * </p>
     */
    public void init() {
        manager.init();
    }
}
