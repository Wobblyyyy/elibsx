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

package me.wobblyyyy.rlibx.input;

import java.util.HashMap;

/**
 * A generic type of input device that's designed to work with everything
 * out of the box, with very little configuration.
 *
 * <p>
 * Although controllers are nice and all, going up a step and using an
 * input device expands the horizons of robot input. Simply using a controller
 * class and nothing else makes it so that only a joystick can control the
 * values of a motor's power or something like that.
 * </p>
 *
 * <p>
 * By using a more generic and abstract InputDevice, we no longer need to
 * worry about controller mishaps or mismatches. We have input channels, and
 * each of these input channels accomplishes a single goal, and do so pretty
 * well.
 * </p>
 *
 * <p>
 * Input devices function by using an array of length 100 that stores data that
 * pertains to... well, input. Examples include:
 * <ul>
 *     <li>Color sensors.</li>
 *     <li>Touch sensors.</li>
 *     <li>Controller inputs.</li>
 *     <li>Limit switches.</li>
 * </ul>
 * ... you get the point - there's a lot of stuff you can put on a single
 * input device.
 * </p>
 *
 * <p>
 * The function of a class, such as this one, might seem to be a bit of a
 * mystery. Why would you possibly use a more complex implementation of input
 * when you can simply use... well, a simpler version? In this case, sticking
 * with Controller would have been way more simple to implement.
 * </p>
 *
 * <p>
 * However, this isn't a simple FTC and FRC library - this is much more of
 * a robotics as a whole library. Robotics as a whole isn't only operated with
 * controllers. Creating a separate class, a more abstract and generic class,
 * allows us to interlock with many, many, many different input devices,
 * compared to just two types of controllers.
 * </p>
 *
 * <p>
 * InputDevices rely on the {@link InputDevice#inputs} {@code HashMap} to keep
 * track of input values. The design philosophy behind the {@code InputDevice}
 * class was to centralize all things related to robot input, thus making it
 * incredibly difficult to get lost on what isn't working and why not.
 * </p>
 *
 * <p>
 * For a common user, or a user who just wants to get a controller to work,
 * regardless of anything else, {@code InputDevice} elements are just extra
 * abstractions on top of the already rather uncomfortable
 * {@link me.wobblyyyy.rlibx.input.controllers.Controller} class. However,
 * when it comes to managing things like multiple streams of input, sensors,
 * encoders, controllers, etc - using an {@code InputDevice} might start to
 * seem like a more appealing option.
 * </p>
 *
 * @author Colin Robertson
 * @version 1.1.0
 * @since 0.1.0
 */
public class InputDevice {
    /**
     * A HashMap that stores all of the useful information that's needed to
     * manage multiple inputs at the same time.
     *
     * <p>
     * Although this {@code HashMap} does support the use of Objects as
     * values, meaning you can technically store whatever you'd like here,
     * I'd like to say quickly that the intention of this class is to provide
     * simple inputs. Not outputs. Not products. Not points. If a primitive
     * value can't encapsulate whatever you're attempting to do, you're almost
     * certainly trying to store way too much information in the input
     * device you're working with.
     * </p>
     *
     * <p>
     * A string key is used to track down different objects that are stored
     * in the hash map from different locations in the codebase.
     * </p>
     *
     * <p>
     * The values stored in the HashMap are all notated in Object form. These
     * values can easily be converted into doubles, integers, strings, etc.
     * </p>
     *
     * @see InputDevice#getInt(String)
     * @see InputDevice#getDouble(String)
     * @see InputDevice#getString(String)
     * @see InputDevice#getObject(String)
     * @see InputDevice#getBoolean(String)
     * @see InputDevice#set(String, Object)
     * @see InputDevice#setAll(HashMap)
     * @see InputDevice#setMultiple(HashMap)
     */
    private volatile HashMap<String, Object> inputs = new HashMap<>();

    /**
     * Create a new InputDevice.
     */
    public InputDevice() {

    }

    /**
     * Set a given input key to have a certain value.
     *
     * <p>
     * This only sets a single key - not multiple keys. If you'd like to set
     * multiple keys at once, or import a HashMap and set it as the current
     * input stream, you can feel free to do so with some other methods.
     * </p>
     *
     * @param key   the key that should be set.
     * @param value the value that the key should hold.
     * @see InputDevice#inputs
     * @see InputDevice#setAll(HashMap)
     * @see InputDevice#setMultiple(HashMap)
     */
    public void set(String key,
                    Object value) {
        /*
         * All we have to do is put the inputted parameters into a new
         * HashMap entry for the inputs HashMap.
         */
        inputs.put(key, value);
    }

    /**
     * Set the {@code inputs} HashMap to an entirely new HashMap that's
     * passed as a parameter to this method call.
     *
     * <p>
     * This method will reset the entire HashMap and replace it with whatever
     * HashMap you provided as a parameter to this method.
     * </p>
     *
     * @param inputs the new HashMap that should replace the existing inputs
     *               HashMap.
     * @see InputDevice#inputs
     * @see InputDevice#set(String, Object)
     * @see InputDevice#setMultiple(HashMap)
     */
    public void setAll(HashMap<String, Object> inputs) {
        /*
         * We entirely over-write the existing inputs HashMap here.
         *
         * Instead of having to manually transfer all of the input keys and
         * values over to a new HashMap, it's a wiser idea to just re-set
         * the HashMap.
         */
        this.inputs = inputs;
    }

    /**
     * Set multiple input channels to given values at the same time.
     *
     * <p>
     * Unlike the {@link InputDevice#setAll(HashMap)} method, this method does
     * not clear the original HashMap when adding values.
     * </p>
     *
     * <p>
     * Keys that are already contained within the HashMap will be overwritten
     * by any new values that you put in here - just something to keep in
     * the back of your head.
     * </p>
     *
     * @param inputs a HashMap of String and Object pairs that will be added
     *               to the existing input HashMap.
     * @see InputDevice#inputs
     * @see InputDevice#set(String, Object)
     * @see InputDevice#setAll(HashMap)
     */
    public void setMultiple(HashMap<String, Object> inputs) {
        /*
         * For each of the entries in the given HashMap, we need to call
         * the set() method.
         *
         * We set the actual inputs HashMap to have a key and value that comes
         * straight from the input HashMap.
         */
        for (HashMap.Entry<String, Object> e : inputs.entrySet()) {
            /*
             * Call the set method.
             *
             * In case we ever do some refactoring on the internals for
             * the InputDevice class, which is fairly likely, it's a better
             * idea to set values through a setter.
             */
            set(
                    e.getKey(),
                    e.getValue()
            );
        }
    }

    /**
     * Get an integer value from the HashMap of input streams.
     *
     * @param key the key to query.
     * @return a value, based on the query key.
     * @see InputDevice#inputs
     * @see InputDevice#getDouble(String)
     * @see InputDevice#getString(String)
     * @see InputDevice#getObject(String)
     * @see InputDevice#getBoolean(String)
     */
    public int getInt(String key) {
        /*
         * Cast the value to an integer.
         */
        return (int) inputs.get(key);
    }

    /**
     * Get a double value from the HashMap of input streams.
     *
     * @param key the key to query.
     * @return a value, based on the query key.
     * @see InputDevice#inputs
     * @see InputDevice#getInt(String)
     * @see InputDevice#getString(String)
     * @see InputDevice#getObject(String)
     * @see InputDevice#getBoolean(String)
     */
    public double getDouble(String key) {
        /*
         * Cast the value to a double.
         */
        return (double) inputs.get(key);
    }

    /**
     * Get a String value from the HashMap of input streams.
     *
     * @param key the key to query.
     * @return a value, based on the query key.
     * @see InputDevice#inputs
     * @see InputDevice#getInt(String)
     * @see InputDevice#getDouble(String)
     * @see InputDevice#getObject(String)
     * @see InputDevice#getBoolean(String)
     */
    public String getString(String key) {
        /*
         * Cast the value to a string.
         */
        return (String) inputs.get(key);
    }

    /**
     * Get a boolean value from the HashMap of input streams.
     *
     * @param key the key to query.
     * @return a value, based on the query key.
     * @see InputDevice#inputs
     * @see InputDevice#getInt(String)
     * @see InputDevice#getDouble(String)
     * @see InputDevice#getString(String)
     * @see InputDevice#getObject(String)
     */
    public boolean getBoolean(String key) {
        /*
         * Cast the value to a boolean.
         */
        return (boolean) inputs.get(key);
    }

    /**
     * Get an object value from the HashMap of input streams.
     *
     * <p>
     * This method isn't particularly useful unless you cast the object that's
     * stored in the {@code HashMap} into a more usable form of object. If
     * you're attempting to get one of the following:
     * <ul>
     *     <li>String</li>
     *     <li>Double</li>
     *     <li>Boolean</li>
     *     <li>Integer</li>
     * </ul>
     * ... methods for those casts already exist and are linked in the
     * {@code @see} tags down below. If you're storing a custom object in the
     * HashMap, you can cast it however you'd like.
     * </p>
     *
     * @param key the key to query.
     * @return a value, based on the query key.
     * @see InputDevice#inputs
     * @see InputDevice#getInt(String)
     * @see InputDevice#getDouble(String)
     * @see InputDevice#getString(String)
     * @see InputDevice#getBoolean(String)
     */
    public Object getObject(String key) {
        /*
         * Don't cast the object - just return it as-is.
         *
         * Objects in the inputs HashMap exist normally as... well, objects.
         */
        return inputs.get(key);
    }

    /**
     * Get the input HashMap, which can then be used to observe or modify
     * the state.
     *
     * <p>
     * It's advisable that you don't directly modify the {@link HashMap}, but
     * rather, you modify it through some of the setter methods provided in the
     * {@link InputDevice} class.
     * </p>
     *
     * @return the input HashMap.
     * @see InputDevice#inputs
     * @see InputDevice#set(String, Object)
     * @see InputDevice#setAll(HashMap)
     * @see InputDevice#setMultiple(HashMap)
     */
    public HashMap<String, Object> getInputs() {
        /*
         * All that needs to be done here is returning the input HashMap.
         *
         * No modifications are needed - we give the caller the HashMap they
         * want and get on with our day.
         */
        return inputs;
    }

    /**
     * Get a cloned copy of the inputs HashMap. Unlike simply copying the
     * HashMap, deep cloning it entirely separates the objects and values.
     *
     * <p>
     * If you would prefer to access the {@link HashMap} directly, without the
     * use of a clone as a sort of proxy, you can use the method named
     * {@link InputDevice#getInputs()}, which will return the HashMap used
     * directly inside of the {@link InputDevice} class.
     * </p>
     *
     * @return a newly deep cloned version of the inputs HashMap.
     * @see InputDevice#inputs
     * @see InputDevice#getInputs()
     */
    public HashMap<String, Object> getClonedInputs() {
        /*
         * Create a new HashMap that will be used to store the cloned
         * HashMap entries.
         */
        HashMap<String, Object> clone = new HashMap<>();

        /*
         * For each entry in the real (non-cloned, that is) input HashMap,
         * we want to add a corresponding entry to the cloned HashMap.
         */
        for (HashMap.Entry<String, Object> e : inputs.entrySet()) {
            clone.put(e.getKey(), e.getValue());
        }

        /*
         * Return our now-complete cloned input HashMap.
         */
        return clone;
    }
}
