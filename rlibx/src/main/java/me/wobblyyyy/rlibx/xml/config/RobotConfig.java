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

package me.wobblyyyy.rlibx.xml.config;

import me.wobblyyyy.rlibx.xml.config.code.CodeList;
import me.wobblyyyy.rlibx.xml.config.components.ComponentList;
import me.wobblyyyy.rlibx.xml.config.subsystems.SubsystemList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")
public class RobotConfig {
    private ComponentList componentList;
    private SubsystemList subsystemList;
    private CodeList codeList;

    public CodeList getCode() {
        return codeList;
    }

    public void setCode(CodeList codeList) {
        this.codeList = codeList;
    }

    public ComponentList getComponents() {
        return componentList;
    }

    public void setComponents(ComponentList componentList) {
        this.componentList = componentList;
    }

    public SubsystemList getSubsystems() {
        return subsystemList;
    }

    public void setSubsystems(SubsystemList subsystemList) {
        this.subsystemList = subsystemList;
    }
}
