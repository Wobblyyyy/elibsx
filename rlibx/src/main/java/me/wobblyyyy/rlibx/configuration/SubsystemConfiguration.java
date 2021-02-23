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

package me.wobblyyyy.rlibx.configuration;

import java.util.HashMap;

/**
 * A template class, used in configuring subsystems.
 *
 * <p>
 * Speaking generally, each subsystem should have its own extension of this
 * class. And each configuration that goes on top of that should have its own
 * extension of that class.
 * </p>
 *
 * <p>
 * This ensures that all subsystems are kept at the same level of abstraction.
 * Additionally, this ensures that all subsystems are uniform, in the sense
 * that they operate in the same way, take similar parameters, and,
 * ultimately, function as intended. Hopefully.
 * </p>
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public class SubsystemConfiguration extends Configuration {
    /**
     * Create a new configuration with a set of values.
     *
     * <p>
     * The input HashMap - the HashMap that contains all of the values we -
     * should have String keys and Double values. These string keys are strings
     * that the subsystem has access to (using an enum for these keys is the
     * best idea, if at all possible). The double values are values that
     * the subsystem can read from its configuration class. These values can
     * actually be either integers or doubles, but they're stored as doubles,
     * regardless of their integer status.
     * </p>
     *
     * @param values the values that should be set.
     * @see Configuration
     * @see Configuration#Configuration(HashMap)
     * @see Configuration#add(String, double) 
     */
    public SubsystemConfiguration(HashMap<String, Object> values) {
        super(values);
    }
}
