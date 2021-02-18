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
 * A class used for representing the state of a controller.
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public class ControllerState {
    /**
     * a button state
     */
    private boolean A;

    /**
     * b button state
     */
    private boolean B;

    /**
     * x button state
     */
    private boolean X;

    /**
     * y button state
     */
    private boolean Y;

    /**
     * dpu state
     */
    private boolean dpadUp;

    /**
     * dpr state
     */
    private boolean dpadRight;

    /**
     * dpd state
     */
    private boolean dpadDown;

    /**
     * dpl state
     */
    private boolean dpadLeft;

    /**
     * rb state
     */
    private boolean rightBumper;

    /**
     * lb state
     */
    private boolean leftBumper;

    /**
     * rs state
     */
    private boolean rightStick;

    /**
     * ls state
     */
    private boolean leftStick;

    /**
     * rsx state
     */
    private double rsx;

    /**
     * rsy state
     */
    private double rsy;

    /**
     * lsx value
     */
    private double lsx;

    /**
     * lsy value
     */
    private double lsy;

    /**
     * rt value
     */
    private double rt;

    /**
     * lt value
     */
    private double lt;

    /**
     * Create a new controller state.
     *
     * @param a           a state
     * @param b           b state
     * @param x           x state
     * @param y           y state
     * @param dpadUp      dpu state
     * @param dpadRight   dpr state
     * @param dpadDown    dpd state
     * @param dpadLeft    dpl state
     * @param rightBumper rb state
     * @param leftBumper  lb state
     * @param rightStick  rs state
     * @param leftStick   ls state
     * @param rsx         rsx state
     * @param rsy         rsy state
     * @param lsx         lsx state
     * @param lsy         lsy state
     * @param rt          rt val
     * @param lt          lt val
     */
    public ControllerState(boolean a,
                           boolean b,
                           boolean x,
                           boolean y,
                           boolean dpadUp,
                           boolean dpadRight,
                           boolean dpadDown,
                           boolean dpadLeft,
                           boolean rightBumper,
                           boolean leftBumper,
                           boolean rightStick,
                           boolean leftStick,
                           double rsx,
                           double rsy,
                           double lsx,
                           double lsy,
                           double rt,
                           double lt) {
        A = a;
        B = b;
        X = x;
        Y = y;
        this.dpadUp = dpadUp;
        this.dpadRight = dpadRight;
        this.dpadDown = dpadDown;
        this.dpadLeft = dpadLeft;
        this.rightBumper = rightBumper;
        this.leftBumper = leftBumper;
        this.rightStick = rightStick;
        this.leftStick = leftStick;
        this.rsx = rsx;
        this.rsy = rsy;
        this.lsx = lsx;
        this.lsy = lsy;
        this.rt = rt;
        this.lt = lt;
    }

    /**
     * Is the a button pressed?
     *
     * @return is the a button pressed.
     */
    public boolean isA() {
        return A;
    }

    /**
     * Set the a button's state.
     *
     * @param a a button state.
     */
    public void setA(boolean a) {
        A = a;
    }

    /**
     * Is the b button pressed?
     *
     * @return is the b button pressed?
     */
    public boolean isB() {
        return B;
    }

    /**
     * Set b button state.
     *
     * @param b new b button state.
     */
    public void setB(boolean b) {
        B = b;
    }

    /**
     * Is the x button pressed?
     *
     * @return is the x button pressed.
     */
    public boolean isX() {
        return X;
    }

    /**
     * Set the x state.
     *
     * @param x x state.
     */
    public void setX(boolean x) {
        X = x;
    }

    /**
     * Is the y button pressed?
     *
     * @return is the y button pressed.
     */
    public boolean isY() {
        return Y;
    }

    /**
     * Set Y button state.
     *
     * @param y y button state.
     */
    public void setY(boolean y) {
        Y = y;
    }

    /**
     * Is the dpad up button active?
     *
     * @return is the dpad up button active?
     */
    public boolean isDpadUp() {
        return dpadUp;
    }

    /**
     * Set the dpad up button's state.
     *
     * @param dpadUp the dpad up button's state.
     */
    public void setDpadUp(boolean dpadUp) {
        this.dpadUp = dpadUp;
    }

    /**
     * Is the dpad right button pressed?
     *
     * @return whether or not the dpad right button is pressed.
     */
    public boolean isDpadRight() {
        return dpadRight;
    }

    /**
     * Is the dpad right button pressed?
     *
     * @param dpadRight is the dpad right button pressed.
     */
    public void setDpadRight(boolean dpadRight) {
        this.dpadRight = dpadRight;
    }

    /**
     * Is the dpad down button pressed?
     *
     * @return is the dpad down button pressed?
     */
    public boolean isDpadDown() {
        return dpadDown;
    }

    /**
     * Set the dpad down state.
     *
     * @param dpadDown new dpad down state.
     */
    public void setDpadDown(boolean dpadDown) {
        this.dpadDown = dpadDown;
    }

    /**
     * Is the left dpad button pressed?
     *
     * @return left dpad button pressed.
     */
    public boolean isDpadLeft() {
        return dpadLeft;
    }

    /**
     * Is the left dpad button pressed down?
     *
     * @param dpadLeft whether or not the left dpad button is pressed down.
     */
    public void setDpadLeft(boolean dpadLeft) {
        this.dpadLeft = dpadLeft;
    }

    /**
     * Is the right bumper pressed down?
     *
     * @return whether or not the right bumper is pressed down.
     */
    public boolean isRightBumper() {
        return rightBumper;
    }

    /**
     * Set the right bumper state.
     *
     * @param rightBumper right bumper state.
     */
    public void setRightBumper(boolean rightBumper) {
        this.rightBumper = rightBumper;
    }

    /**
     * Is the left bumper pressed down?
     *
     * @return is the left bumper pressed down?
     */
    public boolean isLeftBumper() {
        return leftBumper;
    }

    /**
     * Set the left bumper state.
     *
     * @param leftBumper left bumper state.
     */
    public void setLeftBumper(boolean leftBumper) {
        this.leftBumper = leftBumper;
    }

    /**
     * Is the right stick pressed down?
     *
     * @return right stick pressed down.
     */
    public boolean isRightStick() {
        return rightStick;
    }

    /**
     * Set right stick button.
     *
     * @param rightStick right stick button.
     */
    public void setRightStick(boolean rightStick) {
        this.rightStick = rightStick;
    }

    /**
     * Is the left stick pressed down?
     *
     * @return left stick pressed down.
     */
    public boolean isLeftStick() {
        return leftStick;
    }

    /**
     * Set left stick button.
     *
     * @param leftStick left stick button.
     */
    public void setLeftStick(boolean leftStick) {
        this.leftStick = leftStick;
    }

    /**
     * Get right stick x.
     *
     * @return right stick x.
     */
    public double getRsx() {
        return rsx;
    }

    /**
     * Set right stick x.
     *
     * @param rsx right stick x.
     */
    public void setRsx(double rsx) {
        this.rsx = rsx;
    }

    /**
     * Get right stick y.
     *
     * @return right stick y.
     */
    public double getRsy() {
        return rsy;
    }

    /**
     * Get right stick y.
     *
     * @param rsy right stick y.
     */
    public void setRsy(double rsy) {
        this.rsy = rsy;
    }

    /**
     * Get left stick x.
     *
     * @return left stick x.
     */
    public double getLsx() {
        return lsx;
    }

    /**
     * Set left stick x.
     *
     * @param lsx left stick x.
     */
    public void setLsx(double lsx) {
        this.lsx = lsx;
    }

    /**
     * Get left stick y.
     *
     * @return left stick y.
     */
    public double getLsy() {
        return lsy;
    }

    /**
     * Set left stick y.
     *
     * @param lsy left stick y.
     */
    public void setLsy(double lsy) {
        this.lsy = lsy;
    }

    /**
     * Get the right trigger value.
     *
     * @return the right trigger value.
     */
    public double getRt() {
        return rt;
    }

    /**
     * Set the right trigger value.
     *
     * @param rt right trigger value.
     */
    public void setRt(double rt) {
        this.rt = rt;
    }

    /**
     * Get the left trigger value.
     *
     * @return left trigger value.
     */
    public double getLt() {
        return lt;
    }

    /**
     * Set the left trigger value.
     *
     * @param lt left trigger value.
     */
    public void setLt(double lt) {
        this.lt = lt;
    }
}
