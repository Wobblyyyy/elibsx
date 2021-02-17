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

import me.wobblyyyy.rlibx.hardware.Component;

import java.util.ArrayList;

/**
 * Low-level class used for doing cool stuff with subsystems.
 *
 * <p>
 * A subsystem is defined as a system that can function largely independently
 * of any other subsystems, but is still part of the same big "system." In our
 * case, that would be a robot. Subsystems are small components that represent
 * logical groupings of motors, sensors, servos, buttons, and whatever other
 * gadgets you'd like to imagine. Each subsystem accomplishes a different
 * purpose.
 * </p>
 *
 * <p>
 * All Subsystems can and should be added to a SubsystemManager, thus allowing
 * any subsystem on the robot to be initialized and managed from the same
 * place.
 * </p>
 *
 * <p>
 * Subsystems are not a required component of a robot - you don't need to make
 * a virtual subsystem if you'd really prefer not to. However, I do believe that
 * subsystems are incredibly helpful for a few reasons.
 * <ul>
 *     <li>
 *         Better logical organization. Do you think it would make more sense
 *         in a codebase to call "Shooter.shoot()" or "shootShooter()"?
 *         Undeniably, the first of the two is vastly preferable, as class
 *         structure is maintained well.
 *     </li>
 *     <li>
 *         Simpler initialization. Generally speaking, is it easier to have
 *         to initialize 50 different things in 50 different lines of code,
 *         or 50 different things in 1 line of code? Without a doubt, the
 *         second of the two options is the better one.
 *     </li>
 * </ul>
 * </p>
 *
 * <p>
 * When working with subsystems, it's important to understand the relationship
 * that subsystems and subsystem configurations have. Configurations, although
 * entirely un-needed - you really could fully forgo them if you'd prefer -
 * provide an easy and standardized way to provide subsystems with all the
 * parameters they need for their initialization and function.
 * </p>
 *
 * @author Colin Robertson
 * @see SubsystemConfiguration
 * @see SubsystemCore
 * @see SubsystemManager
 * @since 0.1.0
 * @version 1.0.0
 */
public class Subsystem implements SubsystemCore {
    /**
     * An ArrayList of all of the subsystem's components.
     */
    private final ArrayList<Component> components = new ArrayList<>();

    /**
     * The subsystem's configuration class.
     */
    private final SubsystemConfiguration configuration;

    /**
     * Create a new Subsystem.
     *
     * @param configuration the subsystem's configuration.
     */
    public Subsystem(SubsystemConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Get a list of the subsystem's components.
     *
     * @return a list of the subsystem's components.
     */
    @Override
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * Add a component to the subsystem's register.
     *
     * @param component the component to add to the subsystem's register.
     */
    public void addComponent(Component component) {
        components.add(component);
    }

    /**
     * Get a double value based on a given key.
     *
     * @param key the key to search for.
     * @return a found double value.
     */
    public double getDouble(String key) {
        return configuration.getDouble(key);
    }

    /**
     * Get an integer value based on a key.
     *
     * <p>
     * If the value requested is NOT an integer, a NotAnInteger exception is
     * thrown to warn the user that the number they've requested is not an
     * integer, and, as a result, will not work.
     * </p>
     *
     * @param key the key to search for.
     * @return an integer, based on the results of searching for the key.
     */
    public int getInt(String key) {
        return configuration.getInt(key);
    }

    /**
     * Initialize all of the subsystem's components.
     *
     * <p>
     * In most cases, some components will not function at all until they're
     * initialized.
     * </p>
     */
    public void init() {
        /*
         * For all of the components in the component registry...
         */
        for (Component c : getComponents()) {
            /*
             * Initialize it. At least, try to, that is.
             */
            try {
                /*
                 * Who knows what kind of exceptions will be thrown here.
                 *
                 * Who cares, actually, is the better question.
                 */
                c.init();
            } catch (Exception e) {
                /*
                 * Print the stack trace for whatever magical exception we
                 * could have possibly gotten.
                 */
                e.printStackTrace();
            }
        }
    }
}
