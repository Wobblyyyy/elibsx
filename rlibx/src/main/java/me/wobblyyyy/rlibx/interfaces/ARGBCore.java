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

package me.wobblyyyy.rlibx.interfaces;

import me.wobblyyyy.rlibx.util.ARGB;

/**
 * Core used for interacting with an ARGB module.
 *
 * @author Colin Robertson
 * @version 1.0.0
 * @since 0.1.0
 */
public interface ARGBCore extends ComponentCore {
    /**
     * Set color to a given index.
     *
     * @param index the index to set color to.
     * @param color the color to be set.
     */
    void set(int index, ARGB color);

    /**
     * Set all of the ARGB elements to the same color.
     *
     * @param color the color to set.
     */
    void set(ARGB color);
}
