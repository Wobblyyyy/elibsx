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

package me.wobblyyyy.rlibx.hardware.encoder;

import me.wobblyyyy.rlibx.hardware.Component;
import me.wobblyyyy.rlibx.interfaces.EncoderCore;

/**
 * You know 'em - you love 'em - encoders!
 *
 * <p>
 * Encoders aren't actually all that difficult a concept to understand - it's
 * a tracker that tracks a single number. I can't exactly say that's very
 * complicated.
 * </p>
 *
 * @author Colin Robertson
 */
public class Encoder implements Component {
    /**
     * Is the encoder inverted?
     */
    private boolean isInverted;

    /**
     * Should the encoder zero itself on start-up?
     */
    private final boolean shouldZero;

    /**
     * The encoder's count offset.
     */
    private int offset;

    /**
     * The encoder's internally-used zero offset.
     */
    private int zeroOffset;

    /**
     * The encoder's counts per rotation.
     */
    private double cpr;

    /**
     * The internally-used encoder core.
     */
    private final EncoderCore encoder;

    /**
     * Create a new encoder.
     *
     * @param encoder the encoder core.
     */
    public Encoder(EncoderCore encoder) {
        this(
                encoder,
                new EncoderConfig()
        );
    }

    /**
     * Create a new encoder.
     *
     * @param encoder the encoder core.
     * @param config  the encoder's configuration.
     */
    public Encoder(EncoderCore encoder,
                   EncoderConfig config) {
        this.encoder = encoder;
        this.isInverted = config.isInverted();
        this.shouldZero = config.shouldZero();
        this.cpr = encoder.getCpr();
    }

    /**
     * Internally-used method to get the encoder's value, adjusted for
     * the encoder's start value.
     *
     * @return the encoder's zero-d value.
     */
    public int _getZero() {
        return encoder.getCount() + zeroOffset;
    }

    /**
     * Internally-used method to get the encoder's count.
     *
     * @return the encoder's adjusted count.
     */
    public int _get() {
        if (isInverted) return (_getZero() * -1) + offset;
        else return _getZero() + offset;
    }

    /**
     * Get the encoder's count.
     *
     * @return the encoder's count.
     */
    public int getCount() {
        return _get();
    }

    /**
     * Set the encoder's offset.
     *
     * @param offset the encoder's offset.
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Get the encoder's offset.
     *
     * @return the encoder's offset.
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Is the encoder inverted?
     *
     * @return whether or not the encoder is inverted.
     */
    public boolean isInverted() {
        return isInverted;
    }

    /**
     * Set the encoder to be inverted.
     *
     * @param isInverted whether or not the encoder should be inverted.
     */
    public void setInverted(boolean isInverted) {
        this.isInverted = isInverted;
    }

    /**
     * Initialize the component.
     */
    @Override
    public void init() {
        encoder.init();
        zeroOffset = -encoder.getCount();
    }
}
