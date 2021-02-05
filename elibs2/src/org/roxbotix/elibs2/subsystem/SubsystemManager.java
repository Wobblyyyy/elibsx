package org.roxbotix.elibs2.subsystem;

import me.wobblyyyy.intra.ftc2.utils.jrep.ListWrapper;
import org.roxbotix.elibs2.util.Telemetry;

import java.util.ArrayList;

/**
 * A class used for managing multiple subsystems.
 *
 * @author Colin Robertson
 */
public class SubsystemManager {
    private static final String tag = "[sub] ";
    /**
     * A list of all the subsystems.
     */
    private final ListWrapper<Subsystem> subsystems =
            new ListWrapper<>(new ArrayList<>());

    /**
     * Initialize all of the subsystems.
     */
    public void init() {
        subsystems.forEach(
                new Runnable() {
                    @Override
                    public void run() {
                        Subsystem subsystem =
                                subsystems.list.get(subsystems.index);

                        Telemetry.put(
                                tag +
                                        "Subsystem {" +
                                        subsystem.getName() +
                                        "} initializing..."
                        );
                        subsystem.init();
                        Telemetry.put(
                                tag +
                                        "Subsystem {" +
                                        subsystem.getName() +
                                        "} initialized!"
                        );
                    }
                }
        );
    }

    /**
     * Add a subsystem to the initialization list.
     *
     * @param subsystem the subsystem to be added.
     */
    public void addSubsystem(Subsystem subsystem) {
        subsystems.add(subsystem);
    }
}
