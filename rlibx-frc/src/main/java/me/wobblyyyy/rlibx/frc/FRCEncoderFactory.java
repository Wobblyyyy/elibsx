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

package me.wobblyyyy.rlibx.frc;

import me.wobblyyyy.rlibx.hardware.encoder.Encoder;
import me.wobblyyyy.rlibx.hardware.encoder.EncoderConfig;
import me.wobblyyyy.rlibx.interfaces.EncoderCore;

/**
 * Factory for creating new FRC encoders.
 *
 * @author Colin Robertson
 * @see FRCEncoder
 * @since 0.2.0
 */
public class FRCEncoderFactory {
    /**
     * The default encoder configuration.
     *
     * <p>
     * This is only here so that encoders created without a configuration
     * function exactly as they'd be expected to normally.
     * </p>
     */
    public static EncoderConfig CONFIG = new EncoderConfig(
            false, // The encoder is not inverted.
            false  // The encoder should not automatically zero itself.
    );

    /**
     * Get an encoder based on an encoder core and an encoder configuration.
     *
     * @param core   the encoder's encoder core.
     * @param config the encoder's configuration.
     * @return a newly-created encoder.
     */
    public static Encoder getEncoder(EncoderCore core,
                                     EncoderConfig config) {
        return new Encoder(core, config);
    }

    /**
     * Create a brand-new encoder.
     *
     * @param channelA the encoder's A channel input.
     * @param channelB the encoder's B channel input.
     * @param cpr      the encoder's CPR (counts per rotation). This is the
     *                 amount of encoder ticks that are tracked per one
     *                 complete rotation of the encoder. If you're confused
     *                 about where to find this value, you should consult
     *                 someone on your mechanical team or find the website of
     *                 whoever manufactured your encoder. If I had to give
     *                 a default value, 1024 would be it.
     * @param config   the encoder's configuration class. If no configuration
     *                 class is provided, the default one is used. Encoder
     *                 configuration isn't very important, as it doesn't
     *                 exactly do much.
     * @return a newly-created encoder.
     * @see FRCEncoderFactory#newEncoder(int, int) 
     */
    public static Encoder newEncoder(int channelA,
                                     int channelB,
                                     double cpr,
                                     EncoderConfig config) {
        return getEncoder(
                new FRCEncoder(
                        channelA,
                        channelB,
                        cpr
                ),
                config
        );
    }

    /**
     * Create a brand-new encoder.
     * 
     * @param channelA the encoder's A channel input.
     * @param channelB the encoder's B channel input.
     * @return a newly-created encoder.
     * @see FRCEncoderFactory#newEncoder(int, int, double, EncoderConfig) 
     */
    public static Encoder newEncoder(int channelA,
                                     int channelB) {
        return getEncoder(
                new FRCEncoder(
                        channelA,
                        channelB,
                        -1
                ),
                CONFIG
        );
    }
}
