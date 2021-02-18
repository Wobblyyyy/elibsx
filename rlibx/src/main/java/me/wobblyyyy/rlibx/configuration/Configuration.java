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

import me.wobblyyyy.intra.ftc2.utils.math.Math;
import me.wobblyyyy.rlibx.error.NotAnIntegerException;

import java.util.HashMap;

/**
 * A template class for anything related to configurations.
 *
 * <p>
 * In essence, this class is a wrapper for the HashMap class we all know
 * and love. My reasoning behind this abstraction was that a custom class
 * we can construct easily is significantly more maneuverable in a situation
 * like this one.
 * </p>
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public class Configuration {
    /**
     * All of the stored keys and values.
     */
    private final HashMap<String, Double> values;

    /**
     * Create a new configuration with a set of values.
     *
     * @param values the values that should be set.
     */
    public Configuration(HashMap<String, Double> values) {
        this.values = values;
    }

    /**
     * Check whether or not a given number is an integer.
     *
     * @param number the number to check.
     * @return whether or not that number is an integer.
     */
    private boolean isAnInteger(double number) {
        return number == Math.round(number);
    }

    /**
     * Get a double value based on a given key.
     *
     * @param key the key to search for.
     * @return a found double value.
     */
    public double getDouble(String key) {
        return values.get(key);
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
        double value = getDouble(key);

        try {
            if (isAnInteger(value)) {
                return (int) Math.round(value);
            } else {
                throw new NotAnIntegerException(
                        "You attempted to call the getInt method on a " +
                                "double value."
                );
            }
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Add a configuration option.
     *
     * @param key   the configuration option's key.
     * @param value the configuration option's value.
     */
    public void add(String key,
                    double value) {
        values.put(key, value);
    }
}
