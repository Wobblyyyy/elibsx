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

package me.wobblyyyy.piebus.bus;

public class Word {
    private final boolean a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    public Word(boolean a,
                boolean b,
                boolean c,
                boolean d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Word(boolean[] bits) {
        this(
                bits[0],
                bits[1],
                bits[2],
                bits[3]
        );
    }

    public boolean[] getBits() {
        return new boolean[] {a, b, c, d};
    }
}
