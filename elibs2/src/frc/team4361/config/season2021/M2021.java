package frc.team4361.config.season2021;

import frc.team4361.config.Common;

import java.util.HashMap;

public class M2021 {
    private static boolean hasUpdated = false;

    public static HashMap<String, Double> map;

    private static void forceUpdate() {
        map = new HashMap<>() {{
            /*
             * Our very lovely controllers!
             */
            put(Common.JOYSTICK_L_A.getName(), 0.0);
            put(Common.JOYSTICK_R_A.getName(), 1.0);

            /*
             * Swerve drive stuff!
             */
            put(Common.SWERVE_DRIVE_FR.getName(), 1.0);
            put(Common.SWERVE_DRIVE_FL.getName(), 2.0);
            put(Common.SWERVE_DRIVE_BR.getName(), 3.0);
            put(Common.SWERVE_DRIVE_BL.getName(), 4.0);

            put(Common.SWERVE_TURN_FR.getName(), 5.0);
            put(Common.SWERVE_TURN_FL.getName(), 6.0);
            put(Common.SWERVE_TURN_BR.getName(), 7.0);
            put(Common.SWERVE_TURN_BL.getName(), 8.0);

            put(Common.SWERVE_ENCODER_DRIVE_FR_A.getName(), 9.0);
            put(Common.SWERVE_ENCODER_DRIVE_FR_B.getName(), 10.0);
            put(Common.SWERVE_ENCODER_DRIVE_FL_A.getName(), 11.0);
            put(Common.SWERVE_ENCODER_DRIVE_FL_B.getName(), 12.0);
            put(Common.SWERVE_ENCODER_DRIVE_BR_A.getName(), 13.0);
            put(Common.SWERVE_ENCODER_DRIVE_BR_B.getName(), 14.0);
            put(Common.SWERVE_ENCODER_DRIVE_BL_A.getName(), 15.0);
            put(Common.SWERVE_ENCODER_DRIVE_BL_B.getName(), 16.0);

            put(Common.SWERVE_ENCODER_TURN_FR_A.getName(), 17.0);
            put(Common.SWERVE_ENCODER_TURN_FR_B.getName(), 18.0);
            put(Common.SWERVE_ENCODER_TURN_FL_A.getName(), 19.0);
            put(Common.SWERVE_ENCODER_TURN_FL_B.getName(), 20.0);
            put(Common.SWERVE_ENCODER_TURN_BR_A.getName(), 21.0);
            put(Common.SWERVE_ENCODER_TURN_BR_B.getName(), 22.0);
            put(Common.SWERVE_ENCODER_TURN_BL_A.getName(), 23.0);
            put(Common.SWERVE_ENCODER_TURN_BL_B.getName(), 24.0);

            put(Common.SWERVE_CPR_A.getName(), 25.0);
            put(Common.SWERVE_CPR_B.getName(), 26.0);
            put(Common.SWERVE_CPR_C.getName(), 27.0);
            put(Common.SWERVE_CPR_D.getName(), 28.0);

            put(Common.SWERVE_DIAMETER_FR.getName(), 29.0);
            put(Common.SWERVE_DIAMETER_FL.getName(), 30.0);
            put(Common.SWERVE_DIAMETER_BR.getName(), 31.0);
            put(Common.SWERVE_DIAMETER_BL.getName(), 32.0);

            put(Common.SWERVE_L_A.getName(), 33.0);
            put(Common.SWERVE_W_A.getName(), 34.0);

            /*
             * Intake stuff!
             */
            put(Common.INTAKE_ROLLER_MOTOR.getName(), 0.0);
            put(Common.INTAKE_ACTUATION_MOTOR.getName(), 0.0);
            put(Common.INTAKE_TOP_LIMIT.getName(), 0.0);
            put(Common.INTAKE_BOTTOM_LIMIT.getName(), 0.0);

            /*
             * Shooter stuff!
             */
            put(Common.SHOOTER_MOTOR_FLYWHEEL.getName(), 0.0);
            put(Common.SHOOTER_MOTOR_ACTUATOR.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_FLYWHEEL_A.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_FLYWHEEL_B.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_ACTUATOR_A.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_ACTUATOR_B.getName(), 0.0);
            put(Common.SHOOTER_TPR_FLYWHEEL.getName(), 0.0);
            put(Common.SHOOTER_TPR_ACTUATOR.getName(), 0.0);
            put(Common.SHOOTER_DIAMETER_FLYWHEEL.getName(), 0.0);
        }};
    }

    public static double get(Common key) {
        return get(key.getName());
    }

    public static double get(String key) {
        if (!hasUpdated) {
           forceUpdate();
           hasUpdated = true;
        }
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return -1;
    }
}
