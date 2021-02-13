package me.wobblyyyy.rlibx.hardware.encoder;

/**
 * Configuration class for encoders.
 *
 * @author Colin Robertson
 */
public class EncoderConfig {
    /**
     * Default is inverted state.
     */
    public static final boolean IS_INVERTED = false;

    /**
     * Default should zero state.
     */
    public static final boolean SHOULD_ZERO = true;

    /**
     * Should the encoder be inverted?
     */
    private boolean isInverted;

    /**
     * Should the encoder zero on start-up?
     */
    private boolean shouldZero;

    /**
     * Create a new encoder configuration without any parameters - use all
     * the default parameters instead of any custom ones.
     */
    public EncoderConfig() {
        this(
                IS_INVERTED,
                SHOULD_ZERO
        );
    }

    /**
     * Create a new encoder configuration.
     *
     * @param isInverted is the encoder inverted?
     * @param shouldZero should the encoder zero on start-up?
     */
    public EncoderConfig(boolean isInverted,
                         boolean shouldZero) {
        this.isInverted = isInverted;
        this.shouldZero = shouldZero;
    }

    /**
     * Should the encoder zero on start-up?
     *
     * @return whether or not the encoder should zero on start-up.
     */
    public boolean shouldZero() {
        return shouldZero;
    }

    /**
     * Set the should zero state.
     *
     * @param shouldZero whether or not the encoder should zero.
     */
    public void setShouldZero(boolean shouldZero) {
        this.shouldZero = shouldZero;
    }

    /**
     * Get whether or not the encoder is inverted.
     *
     * @return whether or not the encoder is inverted.
     */
    public boolean isInverted() {
        return isInverted;
    }

    /**
     * Set whether or not the encoder is inverted.
     *
     * @param inverted whether or not the encoder should be inverted.
     */
    public void setInverted(boolean inverted) {
        isInverted = inverted;
    }
}
