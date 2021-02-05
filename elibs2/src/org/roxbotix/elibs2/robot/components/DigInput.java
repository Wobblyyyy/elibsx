package org.roxbotix.elibs2.robot.components;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Digital input abstraction class - designed for things like magnetic
 * limit switches, etc.
 *
 * @author Colin Robertson
 */
public class DigInput extends RbComponent {
    private DigitalInput digitalInput;

    private boolean isInverted = false;

    /**
     * Create a new digital input.
     *
     * @param hwId the digital input's hardware ID.
     */
    public DigInput(int hwId) {
        this.hwId = hwId;
    }

    /**
     * Initialize the component.
     */
    @Override
    public void init() {
        digitalInput = new DigitalInput(hwId);
    }

    /**
     * Test whether or not the switch is on.
     *
     * @return whether or not the switch is activated.
     */
    private boolean test() {
        return isInverted != digitalInput.get();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean get() {
        return test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean isOn() {
        return test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean isOff() {
        return !test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean isTrue() {
        return test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean isFalse() {
        return !test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean getState() {
        return test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean is() {
        return test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean on() {
        return test();
    }

    /**
     * Get the digital input's current state.
     *
     * <p>
     * If the digital input is inverted, this value will be exactly opposite
     * of what's reported by the sensor itself.
     * </p>
     *
     * @return the digital input's current state.
     */
    public boolean off() {
        return !test();
    }

    /**
     * Invert the digital input.
     *
     * <p>
     * If the invert is OFF when you call this method, the invert will become
     * enabled. If the invert is ON when you call this method, the input
     * will become disabled.
     * </p>
     */
    public void invert() {
        isInverted = !isInverted;
    }

    /**
     * Set the digital input to be inverted.
     */
    public void setInverted() {
        isInverted = true;
    }

    /**
     * Set the digital input to be un-inverted.
     */
    public void setUninverted() {
        isInverted = false;
    }

    /**
     * Set the digital input's inversion state.
     *
     * @param inverted whether or not the input should be inverted.
     */
    public void setInverted(boolean inverted) {
        isInverted = inverted;
    }

    /**
     * Is the digital input inverted?
     *
     * @return whether or not the digital input is inverted.
     */
    public boolean isInverted() {
        return isInverted;
    }

    /**
     * Is the digital input an analog trigger?
     *
     * @return whether or not the digital input is an analog trigger.
     */
    public boolean isAnalogTrigger() {
        return digitalInput.isAnalogTrigger();
    }

    /**
     * Close the digital input.
     */
    public void close() {
        digitalInput.close();
    }

    /**
     * Get the digital input's channel.
     *
     * @return the digital input's channel.
     */
    public int getChannel() {
        return digitalInput.getChannel();
    }

    /**
     * Whatever that means...
     *
     * @return no idea, honestly.
     */
    public int getAnalogTriggerTypeForRouting() {
        return digitalInput.getAnalogTriggerTypeForRouting();
    }

    public int getPortHandleForRouting() {
        return digitalInput.getPortHandleForRouting();
    }

    public void initSendable(SendableBuilder builder) {
        digitalInput.initSendable(builder);
    }

    /**
     * Get the digital input element itself.
     *
     * @return the core digital input.
     */
    public DigitalInput getDigitalInput() {
        return digitalInput;
    }
}
