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

package me.wobblyyyy.rlibx.xml.config.components;

import me.wobblyyyy.rlibx.xml.config.Attribute;
import me.wobblyyyy.rlibx.xml.config.Name;
import me.wobblyyyy.rlibx.xml.config.Type;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Arrays;

public class Component {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String name;
    private ArrayList<Attribute> attribute;

    public Component() {

    }

    public Component(String type,
                     String name,
                     Attribute... attribute) {
        this.type = type;
        this.name = name;
        this.attribute = new ArrayList<>(Arrays.asList(attribute));
    }

    @XmlTransient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Attribute> getAttribute() {
        return attribute;
    }

    public void setAttribute(ArrayList<Attribute> attribute) {
        this.attribute = attribute;
    }
}
