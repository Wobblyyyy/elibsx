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

import me.wobblyyyy.rlibx.modes.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestOpMode extends FRCOperationMode {
    private static final ModeExecutor robot =
            new ModeExecutor(new ArrayList<Mode>() {{
                add(new RepeatingMode(() -> {
                    System.out.println("Running!");
                }));
            }});
    private static final ModeExecutor teleop =
            new ModeExecutor(new ArrayList<Mode>() {{
                add(new RepeatingMode(() -> {
                    System.out.println("Tele-op active!");
                }));
            }});
    private static final ModeExecutor autonomous =
            new ModeExecutor(new ArrayList<Mode>() {{
                add(new LinearMode(() -> {

                }));
            }});

    private static final HashMap<Modes, ModeExecutor> modes = new HashMap<>() {{
        put(Modes.ROBOT, robot);
        put(Modes.TELEOP, teleop);
    }};

    public TestOpMode() {
        super(modes);
    }

    @Test
    public void testTeleop() {
        TestOpMode mode = new TestOpMode();

        mode.robotInit();
        mode.teleopInit();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
