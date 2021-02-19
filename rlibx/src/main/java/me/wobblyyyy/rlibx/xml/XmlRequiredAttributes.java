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

package me.wobblyyyy.rlibx.xml;

import me.wobblyyyy.rlibx.xml.config.Attribute;

import java.util.ArrayList;
import java.util.HashMap;

public class XmlRequiredAttributes {
    private final HashMap<String, DependLevel> requiredAttributes;

    public XmlRequiredAttributes() {
        requiredAttributes = new HashMap<>();
    }

    public XmlRequiredAttributes(
            HashMap<String, DependLevel> requiredAttributes) {
        this.requiredAttributes = requiredAttributes;
    }

    public HashMap<String, DependLevel> getRequiredAttributes() {
        return requiredAttributes;
    }

    private ArrayList<String> getAttributeNames(
            ArrayList<Attribute> attributes) {
        ArrayList<String> strings = new ArrayList<>();

        for (Attribute a : attributes) {
            strings.add(a.getName());
        }

        return strings;
    }

    private ArrayList<String> getAttributeNames(
            HashMap<String, DependLevel> attributes) {
        ArrayList<String> strings = new ArrayList<>();

        for (HashMap.Entry<String, DependLevel> e :
                attributes.entrySet()) {
            strings.add(e.getKey());
        }

        return strings;
    }

    private boolean areAllStringFromSecondArrayPresent(
            ArrayList<String> firstArrayList,
            ArrayList<String> secondArrayList) {
        for (String a : secondArrayList) {
            if (!firstArrayList.contains(a)) return false;
        }

        return true;
    }

    private ArrayList<String> getStringsFromOnlySecondArray(
            ArrayList<String> firstArrayList,
            ArrayList<String> secondArrayList) {
        for (String a : firstArrayList) {
            secondArrayList.remove(a);
        }

        return secondArrayList;
    }

    public boolean areAllRequiredAttributesPresent(
            ArrayList<Attribute> attributes) {
        ArrayList<String> required = getAttributeNames(requiredAttributes);
        ArrayList<String> present = getAttributeNames(attributes);

        return areAllStringFromSecondArrayPresent(
                present,
                required
        );
    }

    public ArrayList<String> getMissingAttributeNames(
            ArrayList<Attribute> attributes) {
        ArrayList<String> required = getAttributeNames(requiredAttributes);
        ArrayList<String> present = getAttributeNames(attributes);
        ArrayList<String> extra = getStringsFromOnlySecondArray(
                required,
                present
        );

        if (extra.size() > 0) {
            return extra;
        }

        if (areAllRequiredAttributesPresent(attributes))
            return new ArrayList<>();
        else {
            return getStringsFromOnlySecondArray(
                    present,
                    required
            );
        }
    }
}
