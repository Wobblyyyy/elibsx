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

package me.wobblyyyy.piebus.exception;

/**
 * Helper class for managing exceptions that may be thrown at some point
 * during program execution.
 *
 * @author Colin Robertson
 * @since 0.0.0
 */
public class PiebusException extends Exception {
    public static final String INVALID_ADDRESS_GROUND =
            "The address you entered is reserved as a ground pin.";
    public static final String INVALID_ADDRESS_POWER =
            "The address you entered is reserved as a power pin.";
    public static final String INVALID_MODE =
            "Pins can only be in INPUT or OUTPUT mode (as of now).";

    public PiebusException(String exception) {
        super(exception);
    }

    public static void throwNew(String exception) throws PiebusException {
        throw new PiebusException(exception);
    }
}
