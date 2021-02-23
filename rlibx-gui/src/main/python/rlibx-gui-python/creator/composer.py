# composer.py

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

from xml.dom import minidom
from strings import GENERATED_XML_HEADER, COMMENT_HEADER, build_strings

# Set up some 'static' variables.
# I'm rather new to Python programming, but my Java experience would indicate
# to me that it's a good idea to use constants in place of strings in almost
# all cases. If anybody else is ever reading this and has a better idea, you're
# more than welcome to go about implementing it.
CONFIG = 'config'
NAME = 'name'
NAMES = 'names'
VALUE = 'value'
VALUES = 'values'
KIND = 'type'
KINDS = 'types'
SOURCE = 'source'
SOURCES = 'sources'
PATH = 'path'
PATHS = 'paths'
CODE = 'code'
ATTRIBUTE = 'attribute'
ATTRIBUTES = 'attributes'
SUBSYSTEM = 'subsystem'
SUBSYSTEMS = 'subsystems'
COMPONENT = 'component'
COMPONENTS = 'components'
COMPONENT_REFERENCE = COMPONENT


# Internally-used class.
#
# Represent a component's attributes as pairs of strings. These two strings
# are NAME and VALUE. Both of these strings are accessible. Attribute elements
# should be used largely for iteration.
class Attribute:
    def __init__(self, name, value):
        self.name = name
        self.value = value


# Internally-used class.
#
# Represent a component.
#
# name:       The name of the component. This name is used later in subsystems.
# kind:       The type of the component. Type is a keyword in Python, so it
#             couldn't be used here. Kind == type.
# attributes: The component's attributes. These can include things like
#             hardware ID, color, who knows - whatever you want.
class Component:
    def __init__(self, name, kind, attributes):
        self.name = name
        self.kind = kind
        self.attributes = attributes


# Internally-used class.
#
# Represent a code source.
# name: The code source's name.
# path: The path to the code source. This can either be an absolute file path,
#       if the code source is a JavaScript file, or a reference to a class, if
#       the code source is a Java file.
class Source:
    def __init__(self, name, path):
        self.name = name
        self.path = path


# Internally-used class.
#
# Essentially, a fancy string. That's it.
# reference: A string that corresponds to the string name of any of the
#            components that have been declared previously.
class ComponentReference:
    def __init__(self, reference):
        self.reference = reference


# Internally-used class.
#
# name:                 The subsystem's name. This name will be re-used later.
# component_references: All of the component references that this subsystem
#                       depends upon. This parameter is a list.
class Subsystem:
    def __init__(self, name, component_references):
        self.name = name
        self.component_references = component_references


# Create a root, a root element, and append the root element to the child.
#
# I don't know exactly how Python handles EVERYTHING related to XML, but I
# do know this:
# Root - used in generating elements and storing the main element.
# Root element - where all of the other elements are appended to.
root = minidom.Document()
root_element = root.createElement(CONFIG)
root.appendChild(root_element)


# Get an XML attribute based off an inputted instance of the attribute class.
#
# XML attributes and regular attributes are NOT the same thing.
def xml_attribute(attribute):
    element = root.createElement(ATTRIBUTE)

    element.setAttribute(NAME, attribute.name)
    element.setAttribute(VALUE, attribute.value)

    return element


# Get an XML component based off an instance of the component class.
#
# XML components and regular components are NOT the same thing.
# Generally, this method should be called from the xml_components method,
# rather than a direct call.
def xml_component(_component):
    element = root.createElement(COMPONENT)

    element.setAttribute(NAME, _component.name)
    element.setAttribute(KIND, _component.kind)

    for attribute in _component.attributes:
        attributeElement = xml_attribute(attribute)
        element.appendChild(attributeElement)

    return element


# Get a set of XML elements based on a list of non-XML components.
#
# If you try to use XML components as an input to this method, bad things will
# happen - don't do it.
def xml_components(_components):
    element = root.createElement(COMPONENTS)

    for component in _components:
        element.appendChild(xml_component(component))

    return element


# Get a singular XML source element based off a regular source element.
#
# As with the other singular-element methods in this file, this shouldn't
# really be called by anything other than multi-element methods.
def xml_source(source):
    element = root.createElement(SOURCE)

    element.setAttribute(NAME, source.name)
    element.setAttribute(PATH, source.path)

    return element


# Get a set of code sources.
#
# Code elements function differently than any other type of elements, so these
# are arranged like so:
# <code>
#   <source/>
#   <source/>
# <code/>
# Rather than the expected:
# <codes>
#   <code/>
#   <code/>
# <codes/>
def xml_code(sources):
    element = root.createElement(CODE)

    for source in sources:
        element.appendChild(xml_source(source))

    return element


# Create a singular component reference xml element - this element should be
# used when defining subsystems.
def xml_component_reference(component_reference):
    element = root.createElement(COMPONENT_REFERENCE)

    element.setAttribute(NAME, component_reference)

    return element


# Create a new singular subsystem.
#
# In order to use subsystems as they're intended, which is in a list of
# little subsystem elements, we need to create a wrapper element, which can
# be accomplished with the xml_subsystems method provided later in this file.
def xml_subsystem(_subsystem):
    element = root.createElement(SUBSYSTEM)

    element.setAttribute(NAME, _subsystem.name)

    for component_reference in _subsystem.component_references:
        element.appendChild(xml_component_reference(component_reference))

    return element


# Create a wrapped subsystem list.
#
# Although the normal subsystems are cool and all, we need to wrap all of the
# subsystem elements in an additional element, named SUBSYSTEMS.
def xml_subsystems(_subsystems):
    element = root.createElement(SUBSYSTEMS)

    for _subsystem in _subsystems:
        element.appendChild(xml_subsystem(_subsystem))

    return element


# Get a prettified XML string representing the XML structure of a configuration
# that uses the three provided lists.
def get_xml_string(
        sources_list,
        components_list,
        subsystems_list,
):
    sources_element = xml_code(sources_list)
    components_element = xml_components(components_list)
    subsystems_element = xml_subsystems(subsystems_list)

    root_element.appendChild(sources_element)
    root_element.appendChild(components_element)
    root_element.appendChild(subsystems_element)

    return root.toprettyxml(indent="\t")


# Add a header to an XML string.
#
# This header comes from the strings.py file in the root Python directory.
# The header is a little message that gets added every time an XML config
# file is generated by the GUI. In essence, it states that this configuration
# file was generated by the rlibx GUI and editing it by hand is advised
# against, but still entirely possible.
def add_header(xml_string):
    header = build_strings(COMMENT_HEADER)

    xml_string = xml_string.replace(
        GENERATED_XML_HEADER,
        header
    )

    return xml_string
