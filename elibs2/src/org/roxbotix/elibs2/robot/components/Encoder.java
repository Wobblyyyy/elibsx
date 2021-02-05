package org.roxbotix.elibs2.robot.components;

import me.wobblyyyy.ezpos.EncoderInterface;

/**
 * An abstraction of encoders, designed to provide a bit more utility.
 *
 * @author Colin Robertson
 */
public class Encoder extends RbComponent implements
        EncoderCore,
        EncoderInterface {
    private static final boolean REVERSE_DIRECTION = false;

    /**
     * The encoder's current position.
     *
     * <p>
     * This number is only ever accessed after it's been updated, meaning it's
     * checked for min and max and the offset value is applied.
     * </p>
     */
    private int current = 0;

    /**
     * The encoder's current position.
     *
     * <p>
     * This time, without any modification. This is the number as it's reported
     * from the encoder whenever an update occurs.
     * </p>
     */
    private int organic = 0;

    /**
     * The maximum value the encoder can reach before it's capped.
     */
    private int max = 100000 * 100;

    /**
     * The minimum value the encoder can reach.
     */
    private int min = -100000 * 100;

    private final edu.wpi.first.wpilibj.Encoder encoder;

    /**
     * The offset the encoder uses before its values are accessible.
     *
     * <p>
     * A positive offset means that the output value will be higher than
     * the original input value.
     * </p>
     *
     * <p>
     * A negative offset means that the output value will be lower than
     * the original input value.
     * </p>
     */
    private int offset = 0;

    /**
     * Encoder constructor. Create a new encoder with A and B channels.
     *
     * <p>
     * The encoder will start counting immediately after it's created.
     * </p>
     *
     * @param channelA the A channel DIO channel. Channels 0-9 are on-board,
     *                 channels 10-25 are on the MXP port.
     * @param channelB the B channel DIO channel. Channels 0-9 are on-board,
     *                 channel 10-25 are on the MXP port.
     */
    public Encoder(final int channelA,
                   final int channelB) {
        this(
                channelA,
                channelB,
                REVERSE_DIRECTION
        );
    }

    /**
     * Encoder constructor. Construct a Encoder given a and b channels.
     *
     * <p>The encoder will start counting immediately.
     *
     * @param channelA The a channel DIO channel. 0-9 are on-board, 10-25 are on the MXP port
     * @param channelB The b channel DIO channel. 0-9 are on-board, 10-25 are on the MXP port
     * @param reverseDirection represents the orientation of the encoder and inverts the output values
     *     if necessary so forward represents positive values.
     */
    public Encoder(final int channelA,
                   final int channelB,
                   final boolean reverseDirection) {
        encoder = new edu.wpi.first.wpilibj.Encoder(
                channelA,
                channelB,
                reverseDirection
        );
    }

    /**
     * Encoder constructor. Construct a Encoder given a and b channels. Using an index pulse forces 4x
     * encoding
     *
     * <p>The encoder will start counting immediately.
     *
     * @param channelA The a channel digital input channel.
     * @param channelB The b channel digital input channel.
     * @param indexChannel The index channel digital input channel.
     * @param reverseDirection represents the orientation of the encoder and inverts the output values
     *     if necessary so forward represents positive values.
     */
    public Encoder(final int channelA,
                   final int channelB,
                   final int indexChannel,
                   final boolean reverseDirection) {
        encoder = new edu.wpi.first.wpilibj.Encoder(
                channelA,
                channelB,
                indexChannel,
                reverseDirection
        );
    }

    @Override
    public void init() {

    }

    /**
     * Update the encoder based on a reading from the actual encoder.
     */
    public void update() {
        updateEncoder(encoder.get());
    }

    /**
     * Update the encoder.
     */
    public void updateEncoder(int input) {
        organic = input;

        input = Math.min(input, max);
        input = Math.max(input, min);
        input += offset;

        current = input;
    }

    /**
     * Get the encoder's position.
     *
     * @return the encoder's position, with offset applied.
     */
    public int getPosition() {
        return current;
    }

    /**
     * Get the encoder's organic and unaltered position.
     *
     * @return the encoder's "organic" position.
     */
    public int getOrganic() {
        return organic;
    }

    /**
     * Set the maximum encoder count.
     *
     * @param max the maximum encoder count.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Get the maximum encoder count.
     *
     * @return the maximum encoder count.
     */
    public int getMax() {
        return max;
    }

    /**
     * Set the minimum encoder count.
     *
     * @param min the minimum encoder count.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Get the minimum encoder count.
     *
     * @return the minimum encoder count.
     */
    public int getMin() {
        return min;
    }

    @Override
    public int getCount() {
        return getPosition();
    }

    @Override
    public void setOffset(int offset) {
        setCountOffset(offset);
    }

    /**
     * Set the encoder's offset.
     *
     * @param offset the offset that should be applied to the encoder.
     */
    public void setCountOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Get the encoder's offset.
     *
     * @return the offset that should be applied to the encoder.
     */
    public int getCountOffset() {
        return offset;
    }

    @Override
    public void zeroCount() {
        this.offset = organic;
        updateEncoder(organic);
    }

    public edu.wpi.first.wpilibj.Encoder getEncoder() {
        return encoder;
    }
}
