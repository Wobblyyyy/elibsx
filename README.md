![elibsx Logo](logo.png)

![Issues](https://img.shields.io/github/issues/Wobblyyyy/elibsx)
![Top Language](https://img.shields.io/github/languages/top/wobblyyyy/elibsx)
![Forks](https://img.shields.io/github/forks/Wobblyyyy/elibsx)
![Stars](https://img.shields.io/github/stars/Wobblyyyy/elibsx)
![GPL-3.0](https://img.shields.io/github/license/Wobblyyyy/elibsx)
![Twitter](https://img.shields.io/twitter/url?style=social&url=https%3A%2F%2Ftwitter.com%2Fwobblyyyy)

Your brand-new, shiny, fancy, exciting, incredible, astounding, astonishing, well-developed,
beautiful, sexy, and "eeeee-lib" robotics library, elibsx!

## Features
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
  
## Getting Started, Part 1
The first thing you'll need to do is figure out which of the modules below you'll
want to download. All of them should have available JAR releases - you'll just
need to pick which one. If you're too lazy to read everything below, here's the
(very breif) run-down.
- If you're in an FTC environment, you should use rlibx-ftc.
- If you're in an FRC environment, you should use rlibx-frc.
- If you're in a non-FIRST robotics environment, you should use rlibx.

## Modules
In case you couldn't tell, there's a ton of different modules in this huge repository.
My reasoning behind this is rather simple - only a stupid person would make 6 different
repositories for 6 of the same projects, and I don't consider myself to be a stupid
person. Regardless, here ya go.
- __elibs2__
  A complete set of utilities and abstractions for _wpilibj_ and other FRC software and
  hardware. 
- __elibs3__
  A more specific, advanced, and specialized toolkit for FRC robot development.
- __ftc2__
  The elibs2 of the FTC world - tons of abstractions, utilities, templates, tools,
  and much more.
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
via Maven or a similiar build system. After downloading the JAR file for whatever you'd
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
