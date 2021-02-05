package me.wobblyyyy.ezpos;

/**
 * An interface used to access an encoder and modify its offset.
 *
 * @author Colin Robertson
 */
public interface EncoderInterface {
    /**
     * Get the encoder's count.
     *
     * @return the encoder's count.
     */
    int getCount();

    /**
     * Set the encoder's offset.
     */
    void setOffset(int offset);
}
