![elibsx Logo](logo.png)

![Issues](https://img.shields.io/github/issues/Wobblyyyy/elibsx)
![Top Language](https://img.shields.io/github/languages/top/wobblyyyy/elibsx)
![Forks](https://img.shields.io/github/forks/Wobblyyyy/elibsx)
![Stars](https://img.shields.io/github/stars/Wobblyyyy/elibsx)
![GPL-3.0](https://img.shields.io/github/license/Wobblyyyy/elibsx)
![Twitter](https://img.shields.io/twitter/url?style=social&url=https%3A%2F%2Ftwitter.com%2Fwobblyyyy)

Your brand-new, shiny, fancy, exciting, incredible, astounding, astonishing, well-developed,
beautiful, sexy, and "eeeee-lib" robotics library, elibsx!

## Documentation Quick-Links
- `rlibx` JavaDoc ([here](https://wobblyyyy.github.io/JavaDocs/elibsx/))
  All project documentation for all modules - every API should be documented
  right here.
- `rlibx` Documentation ([here](https://wobblyyyy.github.io/docs/elibsx))
  General documentation - guides, quickstarts, tutorials, explanations, in-depth
  looks at different parts of the project.
- `rlibx-gui` Documentation ([here](https://wobblyyyy.github.io/docs/gui))
  In-depth documentation on the user-facing frontend of the library, the GUI.

## Features
- Fully compatible with the FIRST Robotics Competition as well as the FIRST
  Tech Challenge. 
- XML configurations and JavaScript programming support - code your entire
  robot in a single XML file with embedded JavaScript, if you so desire.
- Advanced abstraction and utilities for robotics challenges, designed to speed up
  development, tenfold, at the very least.
- Complex and extensible toolkits, allowing for total control over your robot while
  cutting down development time and effort significantly. 
- Easily integrable with my library [Pathfinder](https://github.com/Wobblyyyy/pathfinder),
  allowing for on-the-fly pathfinding, motion prediction, profiling, and
  planning.
- Entirely modular and extensible libraries - ignore what you don't want. 
- An easy way to organize your codeList more clearly - by subsystem, mode of operation,
  autonomous routine, tele-op routine, configurations, controller bindings,
  and much, much, much, more.
- Easy-to-use tools and templates, drag-and-drop solutions, and a significantly
  simpler and more uniform approach to FIRST robotics programming.
- An extensive, intuitive, and interface-able graphical front-end, capable of
  recording robot readings (sensors, joysticks, motor powers, execution time,
  so much more!), modifying XML robot configurations, and changing the robot's
  mode of operation on-the-fly.
- Feature-rich and well-documented classes, methods, and fields, accomplishing
  everything from making LEDs flash like a police car to complex trajectory
  generation and pursuit. 
  
## Getting Started, Part 1
The first thing you'll need to do is figure out which of the modules below you'll
want to download. All of them should have available JAR releases - you'll just
need to pick which one. If you're too lazy to read everything below, here's the
(very brief) run-down.
- If you're in an FTC environment, you should use rlibx-ftc.
- If you're in an FRC environment, you should use rlibx-frc.
- If you're in a non-FIRST robotics environment, you should use rlibx.

When it comes to the available JAR files for `rlibx`, there are several things
to consider. First and foremost of those things is size. There are JAR files
available for `rlibx` that are over 50 MB in size. Of course, you don't need
to use JAR files this large. `rlibx` depends on several other libraries, most
notably of which include:
- intra_utils (always bundled)
- Reflections (optionally bundled)
- GraalJS (optionally bundled) (consists of):
  - `graal-launcher-common.jar`
  - `graal-sdk.jar`
  - `graaljs.jar`
  - `graaljs-launcher.jar`
  - `graaljs-scriptengine.jar`
  - `icu4j.jar`
  - `tregex.jar`
  - `truffle-api.jar`
- JAXB (optionally bundled) (consists of):
  - `codemodel.jar`
  - `dtd-parser.jar`
  - `fastinfoset.jar`
  - `istack-commons-runtime.jar`
  - `istack-commons-tools.jar`
  - `javax.activation-api.jar`
  - `jaxb-api.jar`
  - `jaxb-jxc.jar`
  - `jaxb-runtime.jar`
  - `jaxb-xjc.jar`
  - `relaxng-datatype.jar`
  - `rngom.jar`
  - `stax-ex.jar`
  - `txw2.jar`
  - `xsom.jar`
  
If you already have any of those dependencies installed, or you don't plan on
using the aspect of `rlibx` that requires that dependency, you can forgo
downloading the larger `rlibx.jar` file that includes all the dependencies.
- Every user of `rlibx` needs to have the Reflections library. This can be
  added as a dependency or downloaded as part of a bundle.
- If you plan on using XML configurations, you need JAXB.
- If you plan on using JS features, you need GraalJS.

Essentially, what I'm trying to say is - download the largest JAR you feel
comfortable with. Optimally, you should download the smallest JAR files you
can find and use Gradle or something analogous to manage all the other
depends. However, we know that won't always happen - thus, bundled JAR files
are available for download and your use.

## Modules
In case you couldn't tell, there's a ton of different modules in this huge repository.
My reasoning behind this is rather simple - only a stupid person would make 6 different
repositories for 6 of the same projects, and I don't consider myself to be a stupid
person. Regardless, here ya go.
- __rlibx-gui__
  The front-facing, user-end application used for interacting with rlibx. Unlike
  the other modules in this project, this module is written mostly in Python.
  Additionally, this module isn't designed to be a library or a framework - rather,
  it's a graphical user interface that can be used to generate XML configurations,
  record the robot's position, graph different information about the robot, etc.
- __rlibx-frc__
  The FRC-specific distro of the ultimate and evolved rlibx library. This module/artifact
  contains a copy of the rlibx JAR with FRC utilities thrown on top.
- __rlibx-ftc__
  The FTC-specific distro of the ultimate and evolved rlibx library. This module/artifact
  contains a copy of the rlibx JAR with FTC utilities thrown on top.
- __rlibx__
  A standardized robotics library designed for allowing development between both FTC and
  FRC environments. That's right - you can use the same codeList you used for an FTC robot for
  an FRC robot, of course with a couple of (very minor) changes to account for differences
  in hardware. 
  
## Getting Started, Part 2
Now that you've found which module you'd like to use, you're going to need to download it,
of course. Downloads are available via the "releases" section and may potentially be available
via Maven or a similar build system. After downloading the JAR file for whatever you'd
like, you'll need to add that as a dependency for the project you're working on. If you're
confused about how to do that, these might help.
- [(VIDEO) Installing rlibx-ftc, IntelliJ IDEA](https://google.com)
- [(VIDEO) Installing rlibx-frc, IntelliJ IDEA](https://google.com)
- [(VIDEO) Installing rlibx-frc, VS Code](https://google.com)

## Getting Started, Part 3
After you've installed the library of your choice, you're going to need to learn how to use it.
Lucky you - I've already prepared a bunch of JavaDocs and regular docs just for you. Think of
it as a gift - hand-written, commented, and curated, just specifically for you. How special!
- __rlibx-frc__
  - JavaDoc ([here](https://google.com))
  - Documentation ([here](https://google.com))
- __rlibx-ftc__
  - JavaDoc ([here](https://google.com))
  - Documentation ([here](https://google.com))
- __rlibx__
  - JavaDoc ([here](https://google.com))
  - Documentation ([here](https://google.com))
  
## Abstractions
Here's a brief list of all the major abstractions present in these libraries.
- DcMotor
- TalonSRX
- Talon
- SPARK MAX
- Servos
- CR Servos
- Color Sensors
- Distance Sensors
- Touch Sensors
- Magnetic Limit Switches
- Subsystems
- Encoders
- Entire Robots
- Operation Modes
- Linear Operation Modes
- Non-Linear Operation Modes 
- Motion Paths and Planning
- Encoded Motors
- Swerve Modules
- A variety of different drivetrains
