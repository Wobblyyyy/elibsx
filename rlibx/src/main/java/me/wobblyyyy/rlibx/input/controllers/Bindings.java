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

/**
 * Bindings used for converting between a controller and an input device.
 *
 * <p>
 * This class is in no way complex - it's just very lengthy. As you've probably
 * seen by now, I document absolutely everything - every single public,
 * protected, private, you name it, all of it - should be documented. Anyways.
 * </p>
 *
 * <p>
 * The general gist of this class is to provide a simple interface of connecting
 * between different types of controllers. Playstation controllers, for example,
 * are undoubtedly different than xbox controllers. Likewise, all sorts of
 * controllers will have all sorts of inputs and names.
 * </p>
 *
 * <p>
 * By using a class like this, we can thus standardize and simplify the process
 * of writing code for different controllers. One is greater than two!
 * </p>
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @see Controller
 * @see ControllerEncoder
 * @see ControllerState
 * @since 0.1.0
 */
public class Bindings {
    /**
     * Channel for left stick x.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_LSX = 0;

    /**
     * Channel for left stick y.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_LSY = 1;

    /**
     * Channel for right stick x.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_RSX = 2;

    /**
     * Channel for right stick y.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_RSY = 3;

    /**
     * Channel for right trigger.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_RT = 4;

    /**
     * Channel for left trigger.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_LT = 5;

    /**
     * Channel for right bumper.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_RB = 6;

    /**
     * Channel for left bumper.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_LB = 7;

    /**
     * Channel for A button.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_A = 8;

    /**
     * Channel for B button.
     * <p>
     * This value is NOT the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_B = 9;

    /**
     * Channel for X button.
     * <p>
     * This value is NOT the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_X = 10;

    /**
     * Channel for Y button.
     * <p>
     * This value is NOT the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_Y = 11;

    /**
     * Channel for D-pad up.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_U = 12;

    /**
     * Channel for D-pad down.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_D = 13;

    /**
     * Channel for D-pad left.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_L = 14;

    /**
     * Channel for D-pad right.
     * <p>
     * This value is the same for Xbox and Playstation controllers.
     */
    public static final int CHANNEL_R = 15;

    /**
     * Get the integer channel of a given input type.
     *
     * @param query the input type to get the channel for.
     * @return the channel, based on the query input type.
     * @see Xbox
     */
    public static int getChannel(Xbox query) {
        return getChannel(query.getId());
    }

    /**
     * Get the integer channel of a given input type.
     *
     * @param query the input type to get the channel for.
     * @return the channel, based on the query input type.
     * @see Playstation
     */
    public static int getChannel(Playstation query) {
        return getChannel(query.getId());
    }

    /**
     * Get the integer channel of a given input type.
     *
     * @param query the input type to get the channel for.
     * @return the channel, based on the query input type.
     * @see Keyboard
     */
    public static int getChannel(Keyboard query) {
        return getChannel(query.getId());
    }

    /**
     * Get the integer channel of a given input type.
     *
     * @param query the input type to get the channel for.
     * @return the channel, based on the query input type.
     * @see Core
     */
    public static int getChannel(Core query) {
        return query.getId();
    }

    /**
     * Native controller class.
     *
     * <p>
     * This enum contains all of the channel values that are used internally.
     * Externally, there are several different types of values - Xbox and
     * Playstation are the first two I can think of.
     * </p>
     */
    public enum Core {
        /**
         * Left stick x.
         */
        LSX(CHANNEL_LSX),

        /**
         * Left stick y.
         */
        LSY(CHANNEL_LSY),

        /**
         * Right stick x.
         */
        RSX(CHANNEL_RSX),

        /**
         * Right stick y.
         */
        RSY(CHANNEL_RSY),

        /**
         * Right trigger.
         */
        RT(CHANNEL_RT),

        /**
         * Left trigger.
         */
        LT(CHANNEL_LT),

        /**
         * Right bumper.
         */
        RB(CHANNEL_RB),

        /**
         * Left bumper.
         */
        LB(CHANNEL_LB),

        /**
         * A button.
         */
        A(CHANNEL_A),

        /**
         * B button.
         */
        B(CHANNEL_B),

        /**
         * X button.
         */
        X(CHANNEL_X),

        /**
         * Y button.
         */
        Y(CHANNEL_Y),

        /**
         * D-pad up.
         */
        U(CHANNEL_U),

        /**
         * D-pad down.
         */
        D(CHANNEL_D),

        /**
         * D-pad left.
         */
        L(CHANNEL_L),

        /**
         * D-pad right.
         */
        R(CHANNEL_R);

        int id;

        Core(int id) {
            this.id = id;
        }

        int getId() {
            return id;
        }
    }

    /**
     * Controller-specific bindings for an Xbox controller.
     */
    public enum Xbox {
        /**
         * Left stick x.
         */
        LEFT_STICK_X(Core.LSX),

        /**
         * Left stick y.
         */
        LEFT_STICK_Y(Core.LSY),

        /**
         * Right stick x.
         */
        RIGHT_STICK_X(Core.RSX),

        /**
         * Right stick y.
         */
        RIGHT_STICK_Y(Core.RSY),

        /**
         * Right trigger.
         */
        RIGHT_TRIGGER(Core.RT),

        /**
         * Left trigger.
         */
        LEFT_TRIGGER(Core.LT),

        /**
         * Right bumper.
         */
        RIGHT_BUMPER(Core.RB),

        /**
         * Left bumper.
         */
        LEFT_BUMPER(Core.LB),

        /**
         * A button.
         */
        A(Core.A),

        /**
         * B button.
         */
        B(Core.B),

        /**
         * X button.
         */
        X(Core.X),

        /**
         * Y button.
         */
        Y(Core.Y),

        /**
         * D-pad up.
         */
        DPAD_UP(Core.U),

        /**
         * D-pad down.
         */
        DPAD_DOWN(Core.D),

        /**
         * D-pad left.
         */
        DPAD_LEFT(Core.L),

        /**
         * D-pad right.
         */
        DPAD_RIGHT(Core.R);

        private final Core id;

        Xbox(Core id) {
            this.id = id;
        }

        Core getId() {
            return id;
        }
    }

    /**
     * Controller-specific bindings for a keyboard.
     * <p>
     * Yes, you read that right. A keyboard.
     *
     * <p>
     * <pre>
     * WS axis: left stick Y
     * AD axis: left stick X
     * IK axis: right stick Y
     * JL axis: right stick X
     *
     * Right bumper: Q
     * Left bumper: O
     * Right trigger: E
     * Left trigger: U
     *
     * A: F
     * B: C
     * X: H
     * Y: N
     * </pre>
     * </p>
     */
    public enum Keyboard {
        /**
         * Left stick y.
         *
         * <p>
         *     <ul>
         *         <li>
         *             W is towards -1.
         *         </li>
         *         <li>
         *             S is towards +1
         *         </li>
         *     </ul>
         * </p>
         */
        WS(Core.LSY),

        /**
         * Left stick x.
         *
         * <p>
         *     <ul>
         *         <li>
         *             A is towards -1.
         *         </li>
         *         <li>
         *             D is towards +1.
         *         </li>
         *     </ul>
         * </p>
         */
        AD(Core.LSX),

        /**
         * Right stick y.
         *
         * <p>
         *     <ul>
         *         <li>
         *             I is towards -1.
         *         </li>
         *         <li>
         *             K is towards +1.
         *         </li>
         *     </ul>
         * </p>
         */
        IK(Core.RSY),

        /**
         * Right stick x.
         *
         * <p>
         *     <ul>
         *         <li>
         *             J is towards -1.
         *         </li>
         *         <li>
         *             L is towards +1.
         *         </li>
         *     </ul>
         * </p>
         */
        JL(Core.RSX),

        /**
         * Right bumper.
         */
        Q(Core.RB),

        /**
         * Left bumper.
         */
        O(Core.LB),

        /**
         * Right trigger.
         */
        E(Core.RT),

        /**
         * Left trigger.
         */
        U(Core.LT),

        /**
         * A button.
         */
        F(Core.A),

        /**
         * C button.
         */
        C(Core.A),

        /**
         * H button.
         */
        H(Core.X),

        /**
         * N button.
         */
        N(Core.Y);

        private final Core id;

        Keyboard(Core id) {
            this.id = id;
        }

        Core getId() {
            return id;
        }
    }

    /**
     * Controller-specific bindings for a Playstation controller.
     */
    public enum Playstation {
        /**
         * Left stick x.
         */
        LEFT_STICK_X(Core.LSX),

        /**
         * Left stick y.
         */
        LEFT_STICK_Y(Core.LSY),

        /**
         * Right stick x.
         */
        RIGHT_STICK_X(Core.RSX),

        /**
         * Right stick y.
         */
        RIGHT_STICK_Y(Core.RSY),

        /**
         * Right trigger.
         */
        RIGHT_TRIGGER(Core.RT),

        /**
         * Left trigger.
         */
        LEFT_TRIGGER(Core.LT),

        /**
         * Right bumper.
         */
        RIGHT_BUMPER(Core.RB),

        /**
         * Left bumper.
         */
        LEFT_BUMPER(Core.LB),

        /**
         * X button.
         */
        X(Core.A),

        /**
         * Circle button.
         */
        CIRCLE(Core.B),

        /**
         * Square button.
         */
        SQUARE(Core.X),

        /**
         * Triangle button.
         */
        TRIANGLE(Core.Y),

        /**
         * D-pad up.
         */
        DPAD_UP(Core.U),

        /**
         * D-pad down.
         */
        DPAD_DOWN(Core.D),

        /**
         * D-pad left.
         */
        DPAD_LEFT(Core.L),

        /**
         * D-pad right.
         */
        DPAD_RIGHT(Core.R);

        private final Core id;

        Playstation(Core id) {
            this.id = id;
        }

        Core getId() {
            return id;
        }
    }
}
