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

package me.wobblyyyy.rlibx.gui;

/**
 * The main class for operating with the rlibx gui.
 *
 * <p>
 * The majority of the gui is written in Python, not Java. The Java code simply
 * handles the execution and operation of that python code.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class RLibGui {
    public static void main(String[] args) {
        // Commands to be run whenever a new user attempts to run the
        // rlibgui. Is there a way we can check to see if the user has the
        // required module, and if they don't, install it for them?
        // pip install numpy
        // pip install matplotlib
        System.out.println("Yay!");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
