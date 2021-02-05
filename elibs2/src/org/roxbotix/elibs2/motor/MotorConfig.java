package org.roxbotix.elibs2.motor;

public class MotorConfig {
    public static double MULTIPLIER = 1.0;
    public static double PRE_MULTIPLIER_MIN = -1000;
    public static double PRE_MULTIPLIER_MAX = 1000;
    public static double MIN = -1.0;
    public static double MAX = 1.0;
    public static double DEADZONE = 0.0;
    public static Direction DIRECTION = Direction.FORWARDS;
    public static boolean USES_ENCODER = false;
    public static boolean USES_PID = false;
    public static double PID_CENTER = 0;
    public static double PID_CPR = 0;
    public static int ENCODER_ID = -1;

    public int id;
    public double power;
    public double multiplier;
    public double multiplierPositive;
    public double multiplierNegative;
    public double preMultiplierMin;
    public double preMultiplierMax;
    public double min;
    public double max;
    public double deadzonePositive;
    public double deadzoneNegative;
    public double deadzone;
    public int encoderChannelA;
    public int encoderChannelB;
    public double pidCenter;
    public double pidCpr;

    public Direction direction;
    public Type type;

    public boolean usesIndividualMultipliers = false;
    public boolean usesIndividualDeadzones = false;
    public boolean usesPreMultiplier = false;
    public boolean usesEncoder = false;
    public boolean usesPid = false;

    public MotorConfig(int id,
                       Type type) {
        this(
                id,
                MULTIPLIER,
                MIN,
                MAX,
                DEADZONE,
                DIRECTION,
                type
        );
    }

    public MotorConfig(int id,
                       double multiplier,
                       double min,
                       double max,
                       double deadzone,
                       Direction direction,
                       Type type) {
        this(
                id,
                multiplier,
                min,
                max,
                deadzone,
                direction,
                type,
                ENCODER_ID,
                ENCODER_ID
        );
    }

    public MotorConfig(int id,
                       double multiplier,
                       double min,
                       double max,
                       double deadzone,
                       Direction direction,
                       Type type,
                       int encoderChannelA,
                       int encoderChannelB) {
        this(
                id,
                multiplier,
                min,
                max,
                deadzone,
                direction,
                type,
                encoderChannelA,
                encoderChannelB,
                PID_CENTER,
                PID_CPR
        );

        usesEncoder = true;
    }

    public MotorConfig(int id,
                       double multiplier,
                       double min,
                       double max,
                       double deadzone,
                       Direction direction,
                       Type type,
                       int encoderChannelA,
                       int encoderChannelB,
                       double pidCenter,
                       double pidCpr) {
        this(
                id,
                multiplier,
                multiplier,
                PRE_MULTIPLIER_MIN,
                PRE_MULTIPLIER_MAX,
                min,
                max,
                deadzone,
                deadzone,
                direction,
                type,
                encoderChannelA,
                encoderChannelB,
                pidCenter,
                pidCpr
        );
    }

    public MotorConfig(int id,
                       double multiplierPositive,
                       double multiplierNegative,
                       double preMultiplierMin,
                       double preMultiplierMax,
                       double min,
                       double max,
                       double deadzonePositive,
                       double deadzoneNegative,
                       Direction direction,
                       Type type,
                       int encoderChannelA,
                       int encoderChannelB,
                       double pidCenter,
                       double pidCpr) {
        this.id = id;
        this.multiplierPositive = multiplierPositive;
        this.multiplierNegative = multiplierNegative;
        this.preMultiplierMin = preMultiplierMin;
        this.preMultiplierMax = preMultiplierMax;
        this.min = min;
        this.max = max;
        this.deadzonePositive = deadzonePositive;
        this.deadzoneNegative = deadzoneNegative;
        this.direction = direction;
        this.type = type;
        this.encoderChannelA = encoderChannelA;
        this.encoderChannelB = encoderChannelB;
        this.pidCenter = pidCenter;
        this.pidCpr = pidCpr;

        usesEncoder = encoderChannelA != -1 && encoderChannelB != -1;
    }
}
