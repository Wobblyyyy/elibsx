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

package me.wobblyyyy.piebus.gpio;

/**
 * Resource class for a set of pin addresses that are used later in connecting
 * with GPIO pins on a Raspberry Pi.
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
public class PinSet {
    private final Pin a;
    private final Pin b;
    private final Pin c;
    private final Pin d;
    private final Pin clock;

    public PinSet(PinAddress aA,
                  PinAddress aB,
                  PinAddress aC,
                  PinAddress aD,
                  PinAddress aClock) {
        a = new Pin(aA);
        b = new Pin(aB);
        c = new Pin(aC);
        d = new Pin(aD);
        clock = new Pin(aClock);
    }

    public Pin getA() {
        return a;
    }

    public Pin getB() {
        return b;
    }

    public Pin getC() {
        return c;
    }

    public Pin getD() {
        return d;
    }

    public Pin getClock() {
        return clock;
    }
}
