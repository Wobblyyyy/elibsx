package org.roxbotix.elibs2.drive;

import me.wobblyyyy.ezpos.swerve.SwerveChassisTracker;
import org.roxbotix.elibs2.robot.assem.SwerveModuleV2;

public class SwerveV2 implements Drive {
    private final double L;
    private final double W;
    private final double R;
    public SwerveModuleV2 fr;
    public SwerveModuleV2 fl;
    public SwerveModuleV2 br;
    public SwerveModuleV2 bl;
    public SwerveChassisTracker tracker;

    public SwerveV2(SwerveModuleV2 fr,
                    SwerveModuleV2 fl,
                    SwerveModuleV2 br,
                    SwerveModuleV2 bl,
                    double L,
                    double W) {
        this.fr = fr;
        this.fl = fl;
        this.br = br;
        this.bl = bl;
        this.tracker = new SwerveChassisTracker(
                fr,
                fl,
                br,
                bl
        );
        this.L = L;
        this.W = W;
        this.R = Math.hypot(L, W);
    }

    public void init() {
        tracker.init();
    }

    /**
     * Drive the drivetrain using some controller inputs.
     *
     * <p>
     * I don't know if it's just me,  but it makes just about no sense to do
     * more math than I have to. For that reason, these swerve controls
     * work as follows:
     * <ul>
     *     <li>Left Stick Y controls drive movement.</li>
     *     <li>Right Stick Angle controls turning.</li>
     * </ul>
     * </p>
     *
     * @param lsx LEFT STICK X.
     * @param lsy LEFT STICK Y.
     * @param rsx RIGHT STICK X.
     * @param rsy RIGHT STICK Y.
     */
    @Override
    public void drive(double lsx,
                      double lsy,
                      double rsx,
                      double rsy) {
        double angle = tracker.getAngle();

        double fwd = -lsy;
        double str = lsx;
        double rcw = rsx;

        double temp = (fwd * Math.cos(angle)) + (str * Math.sin(angle));
        str = (-fwd * Math.sin(angle)) + (str * Math.cos(angle));
        fwd = temp;

        double a = str - (rcw * (L / R));
        double b = str + (rcw * (L / R));
        double c = fwd - (rcw * (W / R));
        double d = fwd + (rcw * (W / R));

        // Wheel 1: front-right
        // Wheel 2: front-left
        // Wheel 3: back-left
        // Wheel 4: back-right

        double ws1 = Math.hypot(b, c);
        double ws2 = Math.hypot(b, d);
        double ws3 = Math.hypot(a, d);
        double ws4 = Math.hypot(a, c);

        double wa1 = Math.atan2(b, c) * (180 / Math.PI);
        double wa2 = Math.atan2(b, d) * (180 / Math.PI);
        double wa3 = Math.atan2(a, d) * (180 / Math.PI);
        double wa4 = Math.atan2(a, c) * (180 / Math.PI);

        // Set power to the drive motors.
        // This is easy.
        double frDrive = ws1;
        double flDrive = ws2;
        double brDrive = ws4;
        double blDrive = ws3;

        // Now the more complex PID-related stuff.
        double frTarget = wa1;
        double flTarget = wa2;
        double brTarget = wa4;
        double blTarget = wa3;

        double frCurrent = tracker.getFrPos().getAngle();
        double frDiff = frTarget - frCurrent;
        double flCurrent = tracker.getFlPos().getAngle();
        double flDiff = flTarget - flCurrent;
        double brCurrent = tracker.getBrPos().getAngle();
        double brDiff = brTarget - brCurrent;
        double blCurrent = tracker.getBlPos().getAngle();
        double blDiff = blTarget - blCurrent;

        double frTurn = frDiff;
        double flTurn = flDiff;
        double brTurn = brDiff;
        double blTurn = blDiff;

        // Scalars!
        double driveScale = 1 / Math.max(
                Math.max(frDrive, flDrive),
                Math.max(brDrive, blDrive)
        );
        double turnScale = 1 / Math.max(
                Math.max(frTurn, flTurn),
                Math.max(brTurn, blTurn)
        );

        if (frDrive + flDrive + brDrive + blDrive > 4) {
            frDrive *= driveScale;
            flDrive *= driveScale;
            brDrive *= driveScale;
            blDrive *= driveScale;
        }

        if (frTurn + flTurn + brTurn + blTurn > 4) {
            frTurn *= turnScale;
            flTurn *= turnScale;
            brTurn *= turnScale;
            blTurn *= turnScale;
        }

        fr.setPower(frDrive, frTurn);
        fl.setPower(flDrive, flTurn);
        br.setPower(brDrive, brTurn);
        bl.setPower(blDrive, blTurn);
    }

    public void setDriveMultiplier(double multiplier) {
        fr.getDrive().setMultiplier(multiplier);
        fl.getDrive().setMultiplier(multiplier);
        br.getDrive().setMultiplier(multiplier);
        bl.getDrive().setMultiplier(multiplier);
    }

    public void setTurnMultiplier(double multiplier) {
        fr.getTurn().setMultiplier(multiplier);
        fl.getTurn().setMultiplier(multiplier);
        br.getTurn().setMultiplier(multiplier);
        bl.getTurn().setMultiplier(multiplier);
    }

    public void setDriveDeadzone(double deadzone) {
        fr.getDrive().setDeadzone(deadzone);
        fl.getDrive().setDeadzone(deadzone);
        br.getDrive().setDeadzone(deadzone);
        bl.getDrive().setDeadzone(deadzone);
    }

    public void setTurnDeadzone(double deadzone) {
        fr.getTurn().setDeadzone(deadzone);
        fl.getTurn().setDeadzone(deadzone);
        br.getTurn().setDeadzone(deadzone);
        bl.getTurn().setDeadzone(deadzone);
    }

    public SwerveChassisTracker getTracker() {
        return tracker;
    }
}
