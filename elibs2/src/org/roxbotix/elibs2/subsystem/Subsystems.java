package org.roxbotix.elibs2.subsystem;

import frc.team4361.config.Common;

import java.util.HashMap;

/**
 * Provides a method of managing all of the subsystems on the robot.
 *
 * <p>
 * Rather than having to manually manage all of the robot's different systems,
 * this class allows you to do something entirely different, and a lot more
 * simple. Or at least in my opinion.
 * </p>
 *
 * <p>
 * Subsystems can be created by extending the Subsystem interface and
 * implementing its required methods. However, these subsystems aren't yet
 * registered. Registering a subsystem basically makes it so it's available
 * for use from any other piece of code. This, in effect, means that you can
 * have 10 different types of drivetrains available for use at any given moment,
 * and all you have to do to use a different drivetrain is replace a single line
 * of code that's used for importing the subsystem.
 * </p>
 *
 * <p>
 * To register a subsystem, you need to write some code like this.
 * <pre>
 * <code>
 * static {
 *     Subsystems.registerSubsystem(
 *         "drivetrain_swerve",
 *         new SwerveSystem {{{{ the name of the class goes here}}}} (
 *             {{{{ init parameters and what not }}}}
 *         );
 *     );
 * }
 * </code>
 * </pre>
 * <p>
 * To access a subsystem, you need to write some code like this.
 * <pre>
 * <code>
 * SwerveSystem swerve = (SwerveSystem) Subsystems
 *                                         .getSubsystem("drivetrain_swerve");
 * </code>
 * </pre>
 * </p>
 *
 * @author Colin Robertson
 */
public class Subsystems {
    /**
     * A HashMap containing subsystem names and the subsystem itself.
     */
    public static HashMap<String, Subsystem> subsystems = new HashMap<>();

    /**
     * A subsystem manager, used to make managing subsystems a tad bit
     * easier. Hopefully, that is.
     */
    public static SubsystemManager manager = new SubsystemManager();

    /**
     * Register a subsystem.
     *
     * <p>
     * Once a subsystem is registered, it can be used from anywhere at any
     * given time. Because the subsystems are not initialized when they're
     * registered, you shouldn't have to worry about errors regarding looking
     * for components that don't exist.
     * </p>
     *
     * @param name      the subsystem's name. You need to remember this name
     *                  in order to recall the subsystem later.
     * @param subsystem the subsystem itself. The subsystem needs to be
     *                  instantiated before you pass it, as passing a null
     *                  subsystem will only give you NullPointerException(s).
     */
    public static void registerSubsystem(Common name,
                                         Subsystem subsystem) {
        subsystems.put(name.getName(), subsystem);
        manager.addSubsystem(getSubsystem(name.getName()));
    }
    /**
     * Register a subsystem.
     *
     * <p>
     * Once a subsystem is registered, it can be used from anywhere at any
     * given time. Because the subsystems are not initialized when they're
     * registered, you shouldn't have to worry about errors regarding looking
     * for components that don't exist.
     * </p>
     *
     * @param name      the subsystem's name. You need to remember this name
     *                  in order to recall the subsystem later.
     * @param subsystem the subsystem itself. The subsystem needs to be
     *                  instantiated before you pass it, as passing a null
     *                  subsystem will only give you NullPointerException(s).
     */
    public static void registerSubsystem(String name,
                                         Subsystem subsystem) {
        subsystems.put(name, subsystem);
        manager.addSubsystem(getSubsystem(name));
    }

    /**
     * Get a subsystem.
     *
     * <p>
     * Subsystems, unless they HAVE been initialized, will not have been
     * initialized. Before a subsystem is initialized, it can't be used.
     * </p>
     *
     * @param name the subsystem's name.
     * @return the subsystem, all ready to go! Wonderful!
     */
    public static Subsystem getSubsystem(String name) {
        return subsystems.get(name);
    }

    /**
     * Get several subsystems and import them into a subsystem manager.
     *
     * <p>
     * Basically a mass registerSubsystem replacement. Only really useful
     * if you're planning on writing some code like this:
     * <pre>
     * <code>
     * Subsystems.importSubsystems(
     *     "drivetrain_swerve",
     *     "color_sensors",
     *     "intake",
     *     "shooter",
     * );
     * </code>
     * </pre>
     * ... to set up four subsystems at once in only a couple lines of code,
     * compared to the (probably more) it would take to do via register.
     * </p>
     *
     * @param names a varargs argument that contains the names of all of the
     *              subsystems you'd like to get and put into a manager.
     */
    public static void importSubsystems(String... names) {
        for (String name : names) {
            manager.addSubsystem(getSubsystem(name));
        }
    }

    /**
     * Initialize all of the SubsystemManager's subsystems.
     *
     * <p>
     * This MUST be run before any of the subsystems can effectively be
     * accessed or used.
     * </p>
     */
    public static void init() {
        manager.init();
    }
}
