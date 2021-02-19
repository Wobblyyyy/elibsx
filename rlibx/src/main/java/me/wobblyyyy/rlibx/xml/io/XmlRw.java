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

package me.wobblyyyy.rlibx.xml.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XmlRw {
    protected static final String READER_ERROR =
            "There was an issue with trying to read an XML file.";
    protected static final String WRITER_ERROR =
            "There was an issue with trying to write to an XML file.";

    public static File getFile(String fileName) {
        return new File(fileName);
    }

    public static String readAsString(String fileName) {
        File file = getFile(fileName);
        XmlReader reader = XmlReader.newInstance(file);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = reader.getInputStream();

            int currentChar = fileInputStream.read();

            while (currentChar != -1) {
                stringBuilder.append(currentChar);
                currentChar = fileInputStream.read();
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static FileInputStream readAsStream(String fileName) {
        File file = getFile(fileName);
        XmlReader reader = XmlReader.newInstance(file);

        try {
            return reader.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void write(String fileName,
                             String fileContents) {
        File file = getFile(fileName);
        XmlWriter writer = XmlWriter.newInstance(file);

        try {
            FileOutputStream fileOutputStream = writer.getOutputStream();

            fileOutputStream.write(fileContents.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
