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

package me.wobblyyyy.rlibx.subsystem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manager-type class used to regulate all of the subsystems.
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class SubsystemManager {
    /**
     * A list of all of the Subsystem elements that have been registered.
     */
    private ArrayList<Subsystem> subsystems = new ArrayList<>();

    /**
     * Create a new SubsystemManager.
     *
     * <p>
     * Any of the subsystems that you throw in right here will be initialized
     * at the same time. Initialization will be callable via the
     * {@link SubsystemManager#init()} method.
     * </p>
     *
     * @param subsystems all of the Subsystem elements that should be registered
     *                   along with the subsystem manager.
     */
    public SubsystemManager(Subsystem... subsystems) {
        this.subsystems.addAll(Arrays.asList(subsystems));
    }

    /**
     * Register a subsystem within the subsystem manager.
     *
     * <p>
     * Registered subsystems will automatically be initialized, along with all
     * other subsystems. Additionally, all subsystems will be accessible and
     * (in terms of system control) be controllable from a single location.
     * </p>
     *
     * @param subsystem the subsystem that should be registered.
     */
    public void register(Subsystem subsystem) {
        subsystems.add(subsystem);
    }

    /**
     * Initialize all of the registered subsystems.
     *
     * <p>
     * Once a subsystem has been registered, all initialization can and should
     * be handled through a subsystem manager. This method (init) initializes
     * all of the subsystems from one location and from one method.
     * </p>
     */
    public void init() {
        for (Subsystem s : subsystems) {
            s.init();
        }
    }
}
