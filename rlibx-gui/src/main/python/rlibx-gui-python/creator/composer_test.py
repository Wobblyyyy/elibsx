#
#  Copyright (c) 2020, Colin Robertson (wobblyyyy@gmail.com)
#
#  This file is part of the elibsx project. The elibsx project is licensed
#  under the GNU General Public License V3.
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
#  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
#  license for the specific language governing permissions and limitations
#  under the License.
#
#  Along with this file, you should have received a license file, containing
#  a copy of the GNU General Public License V3. If you did not receive a copy
#  of that license, you may find one online.
#
#  elibsx GitHub repository:
#  https://github.com/Wobblyyyy/elibsx
#
#  GNU General Public License V3:
#  http://www.gnu.org/licenses/gpl-3.0.en.html
#
#

from creator.composer import *

motor1 = Component(
    'cool 1 motor',
    'Motor',
    [
        Attribute(
            'speed',
            '2.0'
        )
    ]
)
motor2 = Component(
    'cool 2 motor',
    'Motor',
    [
        Attribute(
            'speed',
            '4.0'
        )
    ]
)
subsystem1 = Subsystem(
    'Awesome Subsystem 1',
    [
        'cool 1 motor',
        'cool 2 motor'
    ]
)
subsystem2 = Subsystem(
    'Awesome Subsystem 2',
    [
        'cool 1 motor',
        'cool 2 motor',
        'cool 1 motor',
        'cool 2 motor'
    ]
)

components = [motor1, motor2]
subsystems = [subsystem1, subsystem2]

# root_element.appendChild(xml_components(components))
# root_element.appendChild(xml_subsystems(subsystems))

# xml_string = root.toprettyxml(indent="\t")
# print(xml_string)
xml_string = add_header(get_xml_string(
    sources_list=[],
    components_list=components,
    subsystems_list=subsystems
))
