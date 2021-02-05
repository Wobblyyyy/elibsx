package org.roxbotix.elibs2.robot.assem.drive;

/**
 * An extension of the default MotorPower class, including a "source"
 * attribute used for ensuring motor powers are only assigned when
 * it's appropriate for them to be assigned.
 *
 * @author Colin Robertson
 */
public class SourcedMotorPower extends MotorPower {
    public int source;

    public SourcedMotorPower(double fr,
                             double fl,
                             double br,
                             double bl,
                             int source) {
        super(
                fr,
                fl,
                br,
                bl
        );

        this.source = source;
    }
}
