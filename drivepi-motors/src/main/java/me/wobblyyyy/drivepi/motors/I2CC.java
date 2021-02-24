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

package me.wobblyyyy.drivepi.motors;

public class I2CC {
    public static final byte CHAR_A = 'a';
    public static final byte CHAR_B = 'b';
    public static final byte CHAR_C = 'c';
    public static final byte CHAR_D = 'd';
    public static final byte CHAR_E = 'e';
    public static final byte CHAR_F = 'f';
    public static final byte CHAR_G = 'g';
    public static final byte CHAR_H = 'h';
    public static final byte CHAR_I = 'i';
    public static final byte CHAR_J = 'j';
    public static final byte CHAR_K = 'k';
    public static final byte CHAR_L = 'l';
    public static final byte CHAR_M = 'm';
    public static final byte CHAR_N = 'n';
    public static final byte CHAR_O = 'o';
    public static final byte CHAR_P = 'p';
    public static final byte CHAR_Q = 'q';
    public static final byte CHAR_R = 'r';
    public static final byte CHAR_S = 's';
    public static final byte CHAR_T = 't';
    public static final byte CHAR_U = 'u';
    public static final byte CHAR_V = 'v';
    public static final byte CHAR_W = 'w';
    public static final byte CHAR_X = 'x';
    public static final byte CHAR_Y = 'y';
    public static final byte CHAR_Z = 'z';
    public static final byte CHAR_1 = '1';
    public static final byte CHAR_2 = '2';
    public static final byte CHAR_3 = '3';
    public static final byte CHAR_4 = '4';
    public static final byte CHAR_5 = '5';
    public static final byte CHAR_6 = '6';
    public static final byte CHAR_7 = '7';
    public static final byte CHAR_8 = '8';
    public static final byte CHAR_9 = '9';
    public static final byte CHAR_0 = '0';
    public static final byte DOT = '.';
    public static final byte COLON = ':';
    
    public static String doubleToString(double d) {
        return d + "";
    }
    
    public static byte[] stringToBytes(String s) {
        return s.getBytes();
    }
    
    public static byte[] doubleToBytes(double d) {
        return stringToBytes(doubleToString(d));
    }
}
