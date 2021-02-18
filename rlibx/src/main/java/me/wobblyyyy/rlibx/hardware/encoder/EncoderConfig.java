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

/**
 * Configuration class for encoders.
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
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
