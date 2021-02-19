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

import me.wobblyyyy.rlibx.error.MissingAttributeException;
import me.wobblyyyy.rlibx.xml.config.Attribute;
import me.wobblyyyy.rlibx.xml.config.components.Component;

import java.util.ArrayList;

public class ComponentAttributes {
    protected static final String GENERAL =
            "There was an error while retrieving component attributes.";
    protected static final String MISSING_ATTRIBUTE =
            "You're missing an attribute for a component. The component that " +
                    "you were trying to load is named >name<, and the " +
                    "attribute that you were missing is >missing<.";

    private final XmlComponent xmlComponent;
    private final Component component;
    private final XmlRequiredAttributes requiredAttributes;
    private final ArrayList<Attribute> attributes;

    private static String formatMissingAttributeException(
            String name,
            String missing) {
        return MISSING_ATTRIBUTE
                .replaceFirst(">name<", name)
                .replaceFirst(">missing<", missing);
    }

    private MissingAttributeException getNewException(String missing) {
        return new MissingAttributeException(
                formatMissingAttributeException(
                        this.component.getName(),
                        missing
                )
        );
    }

    public ComponentAttributes(XmlComponent xmlComponent) {
        this.xmlComponent = xmlComponent;
        this.component = xmlComponent.getComponent();
        this.requiredAttributes = xmlComponent.getRequiredAttributes();

        this.attributes = loadAttributes();
    }

    private ArrayList<Attribute> loadAttributes() {
        try {
            return tryLoadingAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Attribute> tryLoadingAttributes()
            throws MissingAttributeException {
        if (requiredAttributes.areAllRequiredAttributesPresent(attributes)) {
            return component.getAttribute();
        } else {
            for (String s : requiredAttributes
                    .getMissingAttributeNames(attributes)) {
                throw getNewException(s);
            }
        }

        throw new MissingAttributeException(GENERAL);
    }
}
