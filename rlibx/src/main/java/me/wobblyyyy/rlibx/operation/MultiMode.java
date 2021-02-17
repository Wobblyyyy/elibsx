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

package me.wobblyyyy.rlibx.operation;

/**
 * A type of mode situation in which a robot can operate in - multiple of them.
 *
 * <p>
 * MultiMode is designed to be as extensible as possible. By using different
 * data structures to store information about different modes and all that
 * cool stuff, we can manage to smoothly switch between them, etc.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.1.0
 * @version 1.0.0
 */
public class MultiMode {
    private MultiModeManager manager;

    public MultiMode() {

    }
}
