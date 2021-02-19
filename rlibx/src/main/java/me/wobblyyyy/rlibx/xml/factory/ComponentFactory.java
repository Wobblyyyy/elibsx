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

package me.wobblyyyy.rlibx.xml.factory;

import me.wobblyyyy.rlibx.error.NoComponentFoundException;
import me.wobblyyyy.rlibx.reflections.ReflectionsWrapper;
import me.wobblyyyy.rlibx.xml.ExtensibleComponent;
import me.wobblyyyy.rlibx.xml.XmlComponent;
import me.wobblyyyy.rlibx.xml.config.components.Component;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class ComponentFactory {
    private static ComponentFactory instance;
    private final HashMap<String, Class<XmlComponent>> componentExecutors;
    private final Reflections reflections;

    public ComponentFactory() {
        componentExecutors = new HashMap<>();
        reflections = ReflectionsWrapper.getReflections();

        loadExtensibleComponents();
    }

    private void loadExtensibleComponents() {
        ArrayList<Field> detectedFields =
                new ArrayList<>(reflections
                        .getFieldsAnnotatedWith(ExtensibleComponent.class));

        for (Field f : detectedFields) {
            componentExecutors.put(
                    f.getAnnotation(ExtensibleComponent.class).type(),
                    f.getAnnotation(ExtensibleComponent.class).executingClass()
            );
        }
    }

    public XmlComponent getXmlComponent(String key,
                                        Component component) {
        try {
            if (componentExecutors.containsKey(key)) {
                return componentExecutors
                        .get(key)
                        .getDeclaredConstructor(Component.class)
                        .newInstance(component);
            } else {
                throw new NoComponentFoundException(
                        "Could not find requested component executor: " + key
                );
            }
        } catch (NoComponentFoundException |
                NoSuchMethodException |
                IllegalAccessException |
                InstantiationException |
                InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ComponentFactory getInstance() {
        if (instance == null) instance = new ComponentFactory();
        return instance;
    }
}
