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
import me.wobblyyyy.rlibx.xml.config.RobotConfig;
import me.wobblyyyy.rlibx.xml.config.code.CodeList;
import me.wobblyyyy.rlibx.xml.config.code.Source;
import me.wobblyyyy.rlibx.xml.config.components.Component;
import me.wobblyyyy.rlibx.xml.config.components.ComponentList;
import me.wobblyyyy.rlibx.xml.config.subsystems.Subsystem;
import me.wobblyyyy.rlibx.xml.config.subsystems.SubsystemList;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Test XML code.
 *
 * @author Colin Robertson
 * @since 0.1.0
 */
public class XmlTester {
    /**
     * Create an example robot config and write it to an XML output. This
     * output is then printed to the system's log.
     */
    @Test
    public void testWriteXml() {
        String motorType = ("Motor");
        String motorName1 = ("Motor 1");
        String motorName2 = ("Motor 2");
        String subsystemName = ("Subsystem");
        String sourceName = "java";
        String sourcePath = "java places or something";
        Attribute motorId1 = new Attribute("id", "1");
        Attribute motorId2 = new Attribute("id", "2");

        Component motor1 = new Component(motorType, motorName1, motorId1);
        Component motor2 = new Component(motorType, motorName2, motorId2);
        Subsystem subsystem = new Subsystem(
                subsystemName,
                motorName1,
                motorName2
        );
        Source codeSource = new Source(sourceName, sourcePath);

        ComponentList componentList = new ComponentList(motor1, motor2);
        SubsystemList subsystemList = new SubsystemList(subsystem);
        CodeList codeList = new CodeList(codeSource);

        RobotConfig robotConfig = new RobotConfig();

        robotConfig.setComponents(componentList);
        robotConfig.setSubsystems(subsystemList);
        robotConfig.setCode(codeList);

        try {
            JAXBContext context = JAXBContext.newInstance(RobotConfig.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(robotConfig, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use an example XML string, convert it to an input stream, and attempt
     * to unmarshall the XML.
     */
    @Test
    public void testReadXml() {
        String contents = "<?xml version=\"1.0\" encoding=\"UTF-8\" " +
                "standalone=\"yes\"?><config><code><source name=\"java\" " +
                "path=\"java places or something\"/></code><components>" +
                "<component type=\"Motor\" name=\"Motor 1\"/><component " +
                "type=\"Motor\" name=\"Motor 2\"/></components><subsystems>" +
                "<subsystem name=\"Subsystem\"><component>Motor 1" +
                "</component><component>Motor 2</component></subsystem>" +
                "</subsystems></config>\n";

        try {
            InputStream stream = new ByteArrayInputStream(contents.getBytes());

            JAXBContext context = JAXBContext.newInstance(RobotConfig.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RobotConfig config = (RobotConfig) unmarshaller.unmarshal(stream);
            System.out.println(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
