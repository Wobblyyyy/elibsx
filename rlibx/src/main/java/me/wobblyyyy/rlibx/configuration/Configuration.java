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
 * @version 1.0.1
 * @since 0.1.0
 */
public class Configuration {
    /**
     * All of the stored keys and values.
     *
     * <p>
     * The values contained in this {@code HashMap} shouldn't ever be modified
     * directly - all modifications to the HashMap should come from a getter
     * or setter method defined elsewhere in the {@code Configuration} class.
     * </p>
     *
     * @see Configuration#getDouble(String)
     * @see Configuration#getInt(String)
     * @see Configuration#add(String, double)
     * @see Configuration#Configuration(HashMap)
     */
    private final HashMap<String, Object> values;

    /**
     * Create a new configuration with a set of values.
     *
     * <p>
     * The values that should be set are notated in a HashMap form, with
     * String keys and Double values. These String keys should almost always
     * be abstracted through an enum, just to ensure that there aren't any
     * spelling mistakes, etc. The double values can represent integers as
     * well. To put an integer where a double would normally go, you can
     * simply put the double form of that integer. Ex:
     * <code>
     * <pre>
     * put("Test", 1);   // Not a double, wouldn't work.
     * put("Test", 1.0); // Is a double, everything works fine.
     * </pre>
     * </code>
     * The {@link Configuration} class will handle conversion between doubles
     * and integers for you, so long as you use the correct method when
     * attempting to request a double or an integer. For reference:
     * <ul>
     *     <li>
     *         Doubles should be attained with:
     *         {@link Configuration#getDouble(String)}
     *     </li>
     *     <li>
     *         Integers should be obtained with:
     *         {@link Configuration#getInt(String)}
     *     </li>
     * </ul>
     * </p>
     *
     * @param values the values that should be set.
     * @see Configuration#add(String, double)
     */
    public Configuration(HashMap<String, Object> values) {
        /*
         * Set the local values HashMap to the inputted HashMap.
         *
         * This is where most of the configuration class' configuration should
         * occur - preferably not later on.
         */
        this.values = values;
    }

    /**
     * Check whether or not a given number is an integer.
     *
     * @param number the number to check.
     * @return whether or not that number is an integer.
     */
    private boolean isAnInteger(double number) {
        /*
         * We check to see if a number is an integer by comparing the
         * rounded version of the number to the non-rounded version of the
         * number.
         *
         * If the rounded version is any higher or lower than the original
         * number, we know that the two numbers must be different.
         *
         * These two numbers will only be different if the provided number is
         * not already in its most "round" and integer-like form.
         *
         * Thus, we can determine whether or not the inputted number is an
         * integer and can be casted to one.
         */
        return number == Math.round(number);
    }

    /**
     * Get a double value based on a given key.
     *
     * @param key the key to search for.
     * @return a found double value.
     * @see Configuration#getInt(String)
     * @see Configuration#getString(String)
     * @see Configuration#get(String)
     */
    public double getDouble(String key) {
        return (double) get(key);
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
     * <p>
     * If we can't find an integer value with the given key, we return -1.
     * -1 doesn't have any significance or impact or reasoning behind it,
     * aside from the fact I thought it would be a good option to choose for
     * a situation like this. Just a heads up, y'know?
     * </p>
     *
     * @param key the key to search for.
     * @return an integer, based on the results of searching for the key.
     * @see Configuration#getDouble(String)
     * @see Configuration#isAnInteger(double)
     * @see Configuration#getString(String)
     * @see Configuration#get(String)
     */
    public int getInt(String key) {
        /*
         * Store the double value of the key.
         *
         * We store this as a double so we can run checks on it in a minute.
         */
        double value = getDouble(key);

        /*
         * Check whether or not the requested number is an integer.
         *
         * We verify the number's integer-ness by checking to see if the number
         * is an integer using the isAnInteger() method defined elsewhere.
         */
        try {
            if (isAnInteger(value)) {
                /*
                 * The number is an integer!
                 *
                 * We can now round and cast the double value into an integer
                 * and go on our merry way.
                 */
                return (int) Math.round(value);
            } else {
                /*
                 * Uh oh...
                 *
                 * The number isn't an integer, so we get an error explaining
                 * exactly that.
                 *
                 * NotAnInteger exceptions have to be handled by the user,
                 * so there's not much more we can do here.
                 *
                 * If you're a user, confused as to why the heck your code
                 * isn't working, the most (only) probable answer is that the
                 * code you're attempting to run classifies a double value
                 * as an integer value and thus throws an exception.
                 *
                 * This exception is later caught, of course, but it'll still
                 * mess up the execution of whatever code you were hoping to
                 * run. Hoping, that is.
                 */
                throw new NotAnIntegerException(
                        "You attempted to call the getInt method on a " +
                                "double value."
                );
            }
        } catch (Exception e) {
            /*
             * Catch any exceptions we may have had, print their stack
             * trace, and return -1.
             *
             * -1 means something went wrong, in case you couldn't tell.
             */
            e.printStackTrace();

            /*
             * And now we return -1. Woo-hoo!
             */
            return -1;
        }
    }

    /**
     * Get a {@code String} value from the configuration based on a key.
     *
     * <p>
     * This method attempts to parse whatever stored object corresponds with
     * the inputted key into a string. It may throw exceptions if it fails.
     * Exceptions obviously aren't good.
     * </p>
     *
     * @param key the key to query for.
     * @return the returned value.
     * @see Configuration#getInt(String)
     * @see Configuration#getDouble(String)
     * @see Configuration#get(String)
     */
    public String getString(String key) {
        return (String) get(key);
    }

    /**
     * Get an {@code Object} from the {@link Configuration#values}
     * {@code HashMap}. This object can (and should) be casted into other
     * objects.
     *
     * <p>
     * If you only plan on getting a primitive type, such as a {@code double},
     * {@code int}, {@code boolean}, or even {@code String} (not a primitive,
     * I know, I know), you can use one of the specifically-designed methods
     * to do exactly that. Take a lot at the "see also" section of this JavaDoc.
     * </p>
     *
     * @param key the key to search the {@code HashMap} for.
     * @return the object that corresponds to the given key.
     * @see Configuration#getInt(String)
     * @see Configuration#getDouble(String)
     * @see Configuration#getString(String)
     */
    public Object get(String key) {
        return values.get(key);
    }

    /**
     * Add a configuration option.
     *
     * <p>
     * Attempting to add a new configuration option for something that
     * already exists will simply replace the older configuration option. As
     * a practical example of this, take:
     * <code>
     * <pre>
     * add("test", 1D); // test's value: 1
     * add("test", 2D); // test's value: 2
     * add("aaaa", 3D); // test's value: 2
     * add("test", 4D); // test's value: 4
     * </pre>
     * </code>
     * </p>
     *
     * @param key   the configuration option's key represented as a String.
     *              This is the name of the configuration option and what
     *              will need to be queried later. Configuration options
     *              should typically use enums to ensure that there aren't
     *              any spelling mistakes, etc.
     * @param value the configuration option's value. Integer values should
     *              be entered as doubles without any decimal numbers aside
     *              from zero. Double numbers can be entered as intended.
     * @see Configuration#Configuration(HashMap)
     * @see Configuration#values
     */
    public void add(String key,
                    double value) {
        /*
         * Add the value we're supposed to add to the HashMap.
         */
        values.put(key, value);
    }
}
