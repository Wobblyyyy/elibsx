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

import me.wobblyyyy.rlibx.xml.config.RobotConfig;
import me.wobblyyyy.rlibx.xml.io.XmlRw;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConfigLoader {
    private static JAXBContext context;
    private static Marshaller marshaller;
    private static Unmarshaller unmarshaller;

    static {
        try {
            context = JAXBContext.newInstance(RobotConfig.class);
            marshaller = context.createMarshaller();
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load an XML configuration file.
     *
     * @param file the XML configuration file's path.
     * @return a (hopefully loaded) XML file.
     */
    public static RobotConfig loadConfig(String file)
            throws JAXBException {
        return (RobotConfig) unmarshaller.unmarshal(XmlRw.readAsStream(file));
    }

    /**
     * Save an XML configuration file.
     *
     * @param file the XML configuration file's path.
     * @param robotConfig the contents to save to the XML file.
     */
    public static void saveConfig(String file,
                                  RobotConfig robotConfig)
            throws JAXBException {
        marshaller.marshal(
                robotConfig,
                XmlRw.getFile(file)
        );
    }
}
