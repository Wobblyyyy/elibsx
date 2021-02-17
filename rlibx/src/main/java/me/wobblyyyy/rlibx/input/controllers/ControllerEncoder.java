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

package me.wobblyyyy.rlibx.input.controllers;

import java.util.ArrayList;

/**
 * Utilities used to encode a controller's state into a much more useful
 * state: an input channel array.
 *
 * <p>
 * Encoding isn't a difficult concept to grasp - you simply convert one way
 * of storing data to another way of storing data. Although it isn't hard to
 * do, encoding a controller state into an array of values for an input device
 * to use takes up a lot of words.
 * </p>
 *
 * @author Colin Robertson
 * @see ControllerState
 * @see me.wobblyyyy.rlibx.input.InputDevice
 * @since 0.1.0
 * @version 1.0.0
 */
public class ControllerEncoder {
    /**
     * Unbox an array of complex Double elements to an array of primitives.
     *
     * @param doubles the complex elements to unbox.
     * @return an array of primitive doubles.
     */
    private static double[] unboxArray(Double[] doubles) {
        double[] simplified = new double[doubles.length];

        int counter = 0;

        for (Double d : doubles) {
            simplified[counter] = d;
            counter++;
        }

        return simplified;
    }

    /**
     * Transform a boolean into a double.
     *
     * <p>
     * The following ruleset applies here:
     * <ul>
     *     <li>1.0 = true</li>
     *     <li>0.0 = false</li>
     * </ul>
     * </p>
     *
     * @param input the input boolean.
     * @return the output double.
     */
    private static double booleanToDouble(boolean input) {
        return input ? 1.0 : 0.0;
    }

    /**
     * Get the pair's value, then transform it into a boolean.
     *
     * <p>
     * The following ruleset applies here:
     * <ul>
     *     <li>1.0 = true</li>
     *     <li>0.0 = false</li>
     * </ul>
     * </p>
     *
     * @param pair the pair to transform.
     * @return the transformed boolean.
     */
    private static boolean pairToBoolean(Pair pair) {
        return doubleToBoolean(pair.getValue());
    }

    /**
     * Transform a double into a boolean.
     *
     * <p>
     * The following ruleset applies here:
     * <ul>
     *     <li>1.0 = true</li>
     *     <li>0.0 = false</li>
     * </ul>
     * </p>
     *
     * @param input the input double.
     * @return the output boolean.
     */
    private static boolean doubleToBoolean(double input) {
        return input == 1D;
    }

    /**
     * Get the maximum ID out of a set of pair elements.
     *
     * @param pairs the pairs to search.
     * @return the maximum ID out of those pairs.
     */
    private static int getMaxId(Pair... pairs) {
        int max = 0;

        for (Pair p : pairs) {
            max = Math.max(p.getIndex(), max);
        }

        return max;
    }

    /**
     * Encode a variable amount of pairs into a double array.
     *
     * @param pairs the pairs to encode.
     * @return the encoded pairs.
     */
    private static double[] encodePairs(Pair... pairs) {
        /*
         * Create a list.
         *
         * We need this list to store information and positions about the
         * doubles we extract from the pairs.
         */
        ArrayList<Double> list = new ArrayList<>();

        /*
         * For each pair in the inputted pair array, we add that pair to the
         * list. We use the pair's index as the list element's position, and
         * we use the pair's value as the list element's value.
         */
        for (Pair p : pairs) {
            list.add(
                    p.getIndex(),
                    p.getValue()
            );
        }

        /*
         * Create an array - this is used for casting.
         */
        Double[] array = new Double[getMaxId(pairs)];

        /*
         * Return our encoded double values.
         */
        return unboxArray(list.toArray(array));
    }

    /**
     * Decode an array of doubles into an array of pairs.
     *
     * @param z the array to decode.
     * @return the decoded pairs.
     */
    private static Pair[] decodePairs(double[] z) {
        /*
         * A list of pairs to be returned.
         */
        ArrayList<Pair> pairs = new ArrayList<>();

        /*
         * Counter, used in for loop.
         */
        int i = 0;

        /*
         * For each double, create a new pair.
         *
         * This pair's index is determined by the counter defined just a few
         * lines above.
         *
         * This pair's value is (obviously) determined by the value of the
         * double that's being iterated over.
         */
        for (double x : z) {
            pairs.add(new Pair(i, x));
            i++;
        }

        /*
         * Create an array of pair elements.
         *
         * The length of this array is i + 1.
         *
         * By the time we reach this code right here, i will represent the
         * maximum possible count. Thus, we can add 1 to that value to get the
         * length of the array we'd need to return.
         */
        Pair[] array = new Pair[i + 1];

        /*
         * Return the array.
         *
         * We need to use the toArray method, as array lists and arrays aren't
         * entirely identical.
         */
        return pairs.toArray(array);
    }

    /**
     * Encode a ControllerState into an InputDevice-friendly array of double
     * values.
     *
     * @param z the controller state to encode.
     * @return an encoded double array, representing the input controller state.
     */
    public static double[] encode(ControllerState z) {
        /*
         * Left and right sticks.
         *
         * These are all doubles.
         */
        Pair lsx = new Pair(
                Bindings.getChannel(Bindings.Core.LSX),
                z.getLsx()
        );
        Pair lsy = new Pair(
                Bindings.getChannel(Bindings.Core.LSY),
                z.getLsy()
        );
        Pair rsx = new Pair(
                Bindings.getChannel(Bindings.Core.RSX),
                z.getRsx()
        );
        Pair rsy = new Pair(
                Bindings.getChannel(Bindings.Core.RSY),
                z.getRsy()
        );

        /*
         * Triggers!
         *
         * Like the sticks, these are all doubles.
         */
        Pair rt = new Pair(
                Bindings.getChannel(Bindings.Core.RT),
                z.getRt()
        );
        Pair lt = new Pair(
                Bindings.getChannel(Bindings.Core.LT),
                z.getLt()
        );

        /*
         * Bumpers!
         *
         * Not as fun as triggers.
         *
         * These are booleans.
         */
        Pair rb = new Pair(
                Bindings.getChannel(Bindings.Core.RB),
                booleanToDouble(z.isRightBumper())
        );
        Pair lb = new Pair(
                Bindings.getChannel(Bindings.Core.LB),
                booleanToDouble(z.isLeftBumper())
        );

        /*
         * A, B, X, and Y buttons.
         *
         * Like the bumpers, these are booleans.
         */
        Pair a = new Pair(
                Bindings.getChannel(Bindings.Core.A),
                booleanToDouble(z.isA())
        );
        Pair b = new Pair(
                Bindings.getChannel(Bindings.Core.B),
                booleanToDouble(z.isB())
        );
        Pair x = new Pair(
                Bindings.getChannel(Bindings.Core.X),
                booleanToDouble(z.isX())
        );
        Pair y = new Pair(
                Bindings.getChannel(Bindings.Core.Y),
                booleanToDouble(z.isY())
        );

        /*
         * D-pad buttons.
         *
         * In case you couldn't tell:
         * - U means UP
         * - D means DOWN
         * - L means LEFT
         * - R means RIGHT
         *
         * These are also all booleans.
         */
        Pair u = new Pair(
                Bindings.getChannel(Bindings.Core.U),
                booleanToDouble(z.isDpadUp())
        );
        Pair d = new Pair(
                Bindings.getChannel(Bindings.Core.D),
                booleanToDouble(z.isDpadDown())
        );
        Pair l = new Pair(
                Bindings.getChannel(Bindings.Core.L),
                booleanToDouble(z.isDpadLeft())
        );
        Pair r = new Pair(
                Bindings.getChannel(Bindings.Core.R),
                booleanToDouble(z.isDpadRight())
        );

        /*
         * Now that we've generated some pairs, we need to encode them.
         *
         * We create an array and set the value of that array to the result
         * of the encodePairs() method.
         *
         * All of the actual encoding happens there - if something isn't
         * working, go check out that bad boy.
         */

        /*
         * Array definition.
         *
         * IDEA was giving me a hard time about some kind of warning - I don't
         * really know, but the yellow was annoying me and I wanted to get it
         * off my screen.
         *
         * In order to accomplish that goal, I had to split the declaration of
         * the array and the definition of the array into two separate
         * statements.
         */
        double[] encoded;

        /*
         * Encode the pairs and set the value of the array.
         */
        encoded = encodePairs(
                lsx, lsy,
                rsx, rsy,
                rb, lb,
                rt, lt,
                a, b, x, y,
                u, d, l, r
        );

        /*
         * We're done! Return the encoded array.
         */
        return encoded;
    }

    /**
     * Decode an encoded array of double values, used by an input channel,
     * into a much more human-readable ControllerState.
     *
     * @param z the array of doubles to be decoded.
     * @return the ControllerState of the decoded double array.
     * @see ControllerEncoder#decodePairs(double[])
     * @see ControllerState
     */
    public static ControllerState decode(double[] z) {
        /*
         * First, we have to decode the double array into more normalized
         * pairs.
         */
        Pair[] pairs = decodePairs(z);

        /*
         * Left and right sticks.
         */
        double lsx = pairs[Bindings.getChannel(Bindings.Core.LSX)].getValue();
        double lsy = pairs[Bindings.getChannel(Bindings.Core.LSY)].getValue();
        double rsx = pairs[Bindings.getChannel(Bindings.Core.RSX)].getValue();
        double rsy = pairs[Bindings.getChannel(Bindings.Core.RSY)].getValue();

        /*
         * Triggers.
         */
        double rt = pairs[Bindings.getChannel(Bindings.Core.RT)].getValue();
        double lt = pairs[Bindings.getChannel(Bindings.Core.LT)].getValue();

        /*
         * Bumpers.
         */
        boolean rb = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.RB)]);
        boolean lb = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.LB)]);

        /*
         * A, B, X, Y - buttons.
         */
        boolean a = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.A)]);
        boolean b = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.B)]);
        boolean x = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.X)]);
        boolean y = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.Y)]);

        /*
         * D-pad buttons.
         */
        boolean u = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.U)]);
        boolean d = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.D)]);
        boolean l = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.L)]);
        boolean r = pairToBoolean(pairs[Bindings.getChannel(Bindings.Core.R)]);

        /*
         * Create a new ControllerState with the values and return it.
         *
         * ControllerState should be a pretty static and unchanging class, so
         * it's fairly unlikely that we'll have to worry about an OOS with
         * this code right here.
         */
        return new ControllerState(
                a, b, x, y,
                u, r, d, l,
                rb, lb,
                false, false,
                rsx, rsy,
                lsx, lsy,
                rt, lt
        );
    }

    /**
     * Private class used to simplify passing around so many damn parameters.
     */
    private static class Pair {
        /**
         * The pair's value.
         */
        private final double value;

        /**
         * The pair's index (position in array).
         */
        private final int index;

        /**
         * Create a new pair.
         *
         * @param index the pair's index.
         * @param value the pair's value.
         */
        public Pair(int index,
                    double value) {
            this.index = index;
            this.value = value;
        }

        /**
         * Get the pair's index.
         *
         * @return the pair's index.
         */
        public int getIndex() {
            return index;
        }

        /**
         * Get the pair's value.
         *
         * @return the pair's value.
         */
        public double getValue() {
            return value;
        }
    }
}
