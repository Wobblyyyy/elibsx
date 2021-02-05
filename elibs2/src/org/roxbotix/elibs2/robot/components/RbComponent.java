package org.roxbotix.elibs2.robot.components;

/**
 * A component abstraction that allows for a programmatic way of
 * interlocking different physical components.
 *
 * <p>
 * Unless you really need to, you should probably ignore this file.
 * </p>
 *
 * @author Colin Robertson
 */
public abstract class RbComponent implements LfComponent {
    /**
     * The hardware identification integer of the component.
     */
    int hwId;
}
