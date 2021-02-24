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

package me.wobblyyyy.pi2c.i2c;

import java.nio.ByteBuffer;

public class I2CObject {
    private byte[] bytes = new byte[0];
    private ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

    private void init(byte[] bytes) {
        this.bytes = bytes;
        this.byteBuffer = ByteBuffer.wrap(bytes);
    }

    public I2CObject(String contents) {
        init(contents.getBytes());
    }

    public I2CObject(byte[] bytes) {
        init(bytes);
    }

    public void setBytes(byte[] bytes) {

    }

    public void setByteBuffer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }
}
