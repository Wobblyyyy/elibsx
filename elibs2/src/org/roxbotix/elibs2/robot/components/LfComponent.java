package org.roxbotix.elibs2.robot.components;

/**
 * The lowest-level interface for a component.
 *
 * <p>
 * As with RbComponent, unless you have a very strong need to, you should
 * probably entirely ignore this file, as it's all but useless.
 * </p>
 *
 * @author Colin Robertson
 */
public interface LfComponent {
    /**
     * Initialize the component.
     *
     * <p>
     * All components must be initialized before use. Not initializing a
     * component means it won't work. Having a component that doesn't work...
     * Well, let's just say that's not exactly beneficial to you.
     * </p>
     */
    void init();
}
