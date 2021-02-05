package org.roxbotix.elibs2.robot.components;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

/**
 * Used for controlling addressable RGB light strips.
 *
 * <p>
 * For the sake of brevity, I'd like to quickly put the names of all of the
 * different modes of operation this thing has here.
 * <ul>
 *     <li>Static color. setSolidColor() (read the parameters)</li>
 *     <li>Alternating colors. setAlternatingColors() (rgb1, rgb2)</li>
 *     <li>Flashing color. setFlashingColor() (rgb, duration, interval)</li>
 *     <li>Flashing alternating colors. setFlashingColors()</li>
 * </ul>
 * </p>
 *
 * <p>
 * This isn't anywhere near the top of the list of things that need to get
 * done in terms of programming. Unless anybody else would like to do this,
 * I'll probably end up pushing off finishing this for a very long while.
 * You're more than welcome (please do, actually LOL) to do this for me.
 * </p>
 *
 * @author Colin Robertson
 */
public class ARGB extends RbComponent {
    /**
     * An internally-used buffer.
     */
    private Rgb[] buffer;

    /**
     * The length (in LEDs) of the ARGB strip.
     */
    private int length;

    /**
     * The addressable LED that's used.
     */
    private AddressableLED aled;

    /**
     * The addressable LED's buffer.
     */
    private AddressableLEDBuffer aled_buffer;

    /**
     * Create a new ARGB controller.
     *
     * @param pwmHeader the PWM header the addressable RGB is plugged into.
     * @param length    the length (in LEDs) of the ARGB strip.
     */
    public ARGB(int pwmHeader,
                int length) {
        hwId = pwmHeader;
        this.length = length;
    }

    /**
     * Initialize the ARGB strip.
     */
    @Override
    public void init() {
        aled = new AddressableLED(hwId);
        aled_buffer = new AddressableLEDBuffer(length);
        aled.setLength(aled_buffer.getLength());

        buffer = new Rgb[length];

        aled.setData(aled_buffer);
        aled.start();
    }

    /**
     * Sync our own custom buffer with the default buffer.
     */
    private void syncBuffer() {
        for (int i = 0; i < length; i++) {
            Rgb rgb = buffer[i];
            aled_buffer.setRGB(
                    i,
                    rgb.r,
                    rgb.g,
                    rgb.b
            );
        }

        aled.setData(aled_buffer);
    }

    /**
     * Set the entire strip to a single color.
     *
     * <p>
     * This set color will not change, nor will it turn off, until another
     * color is set. The buffer used to store the ARGB's data won't update
     * unless you tell it to - the only way to change the color or turn the
     * strip off is to set another color pattern.
     * </p>
     *
     * @param rgb RGB value of the strip.
     */
    public void setSolidColor(Rgb rgb) {
        for (int i = 0; i < length; i++) {
            buffer[i] = rgb;
        }

        syncBuffer();
    }

    /**
     * Set the entire strip to two alternating colors.
     *
     * @param rgb1 the first color.
     * @param rgb2 the second color.
     */
    public void setAlternatingColors(Rgb rgb1,
                                     Rgb rgb2) {
        boolean step = false;

        for (int i = 0; i < length; i++) {
            buffer[i] = step ? rgb1 : rgb2;
            step = !step;
        }

        syncBuffer();
    }

    /**
     * Set a flashing color pattern.
     *
     * @param rgb the RGB color.
     * @param duration the duration of each flash on/off.
     * @param interval the interval between each flash on/off.
     */
    public void setFlashingColor(Rgb rgb,
                                 int duration,
                                 int interval) {

    }
}
