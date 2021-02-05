package org.roxbotix.elibs2.motor;

import com.revrobotics.CANSparkMaxLowLevel;
import me.wobblyyyy.intra.ftc2.utils.Timed;
import me.wobblyyyy.intra.ftc2.utils.async.event.StringEvents;
import org.roxbotix.elibs2.robot.components.Encoder;
import org.roxbotix.elibs2.robot.components.EncoderCore;
import org.roxbotix.elibs2.robot.components.LfComponent;
import org.roxbotix.elibs2.robot.components.MotorComponent;
import org.roxbotix.elibs2.thread.PassiveTickingManager;
import org.roxbotix.elibs2.util.ANSI;
import org.roxbotix.elibs2.util.PID360;

import java.util.ArrayList;

/**
 * The days of Talon SRX vs CAN Spark MAX are over.
 *
 * <p>
 * In a world where two warring classes of motors fought ceaselessly for blood,
 * fame, and glory, one more motor rose above... -
 * </p>
 *
 * <p>
 * Ignoring the *incredibly cringe* introduction "joke" (if you could even
 * call it that...), we see a single class for controlling any type of motor
 * controller! Sadly, "any" does not mean any. As of now, the only three types
 * of motors that have been implemented are...
 * <ul>
 *     <li>Talon SRX</li>
 *     <li>CAN Spark MAX kBrushed</li>
 *     <li>CAN Spark MAX kBrushless</li>
 * </ul>
 * </p>
 *
 * <p>
 * That aside, I'd like to mention the fact that this isn't just any regular
 * motor. No, no, no, no. This is a rather special motor, actually. Not because
 * it eliminates the hassle of having to worry about Talon and Spark motors not
 * playing nice together. We also have a ton of different options that impact
 * how the motor functions. You wanna go over them? I know you do.
 * <ul>
 *     <li>
 *         Multiplier Positive: The number by which the input power is
 *         multiplied, assuming the input number is positive.
 *     </li>
 *     <li>
 *         Multiplier Negative: The number by which the input power is
 *         multiplied, assuming the input number is negative.
 *     </li>
 *     <li>
 *         Multiplier: If a positive and negative multiplier aren't
 *         specified, the motor defaults to using this value for both
 *         positive and negative multiplication.
 *     </li>
 *     <li>
 *         Pre-Multiplier Min: If a number is below this, prior to being
 *         multiplied by the multiplier, the number is automatically set
 *         to this value.
 *     </li>
 *     <li>
 *         Pre-Multiplier Max: If a number is above this, prior to being
 *         multiplied by the multiplier, the number is automatically set
 *         to this value.
 *     </li>
 *     <li>
 *         Min: If a number is below this number, after being multiplied by
 *         the multiplier, the input defaults to the minimum value.
 *     </li>
 *     <li>
 *         Max: If a number is above this number, after being multiplied by
 *         the multiplier, the input defaults to the maximum value.
 *     </li>
 *     <li>
 *         Positive Deadzone: If the input's absolute value is below this
 *         number and the input is greater than zero, the input is set to
 *         be zero.
 *     </li>
 *     <li>
 *         Negative Deadzone: If the input's absolute value is below this
 *         number and the input is less than zero, the input is set to
 *         be zero.
 *     </li>
 *     <li>
 *         Deadzone: If a user doesn't specify both positive and negative
 *         deadzone values, this value is used instead.
 *     </li>
 *     <li>
 *         Direction: FORWARDS means the motor spins forwards.
 *                    BACKWARDS means the motor spins backwards.
 *     </li>
 * </ul>
 * </p>
 *
 * @author Colin Robertson
 */
public class Motor implements
        MotorComponent,
        MotorCore,
        LfComponent,
        EncoderCore {
    private static final String consTag = "[CONS] ";
    private static final String initTag = "[INIT] ";
    private static final String tag = "[MOTOR] ";
    private static final String warn =
            "********** ### [MOTOR] [WARN] ## **********";

    private static final ArrayList<Integer> usedIds = new ArrayList<>();
    private static final ArrayList<Integer> usedEncoderA = new ArrayList<>();
    private static final ArrayList<Integer> usedEncoderB = new ArrayList<>();

    /**
     * Key that's used in StringEvents.
     */
    private static final String key = "Motor Subprocess";

    private boolean isAutomaticallyUpdating;

    /**
     * hardware id
     */
    private final int id;

    /**
     * last set power value
     */
    private double power;

    /**
     * multiplier for positive input
     */
    private double multiplierPositive;

    /**
     * multiplier for negative input
     */
    private double multiplierNegative;

    /**
     * pre multiplier minimum
     */
    private double preMultiplierMin;

    /**
     * pre multiplier maximum
     */
    private double preMultiplierMax;

    /**
     * post multiplier minimum
     */
    private double min;

    /**
     * post multiplier maximum
     */
    private double max;

    /**
     * positive deadzone
     */
    private double deadzonePositive;

    /**
     * negative deadzone
     */
    private double deadzoneNegative;

    /**
     * encoder's a channel
     */
    private final int encoderChannelA;

    /**
     * encoder's b channel
     */
    private final int encoderChannelB;

    /**
     * pid's center angle
     */
    private final double pidCenter;

    /**
     * pid's counts per rotation
     */
    private final double cpr;

    /**
     * direction of motor and encoder
     */
    private Direction direction;

    /**
     * type of motor controller
     */
    private final Type type;

    /**
     * are pre multiplier minimums and maximums considered?
     */
    private boolean usesPreMultiplier;

    /**
     * is the encoder used?
     */
    private boolean usesEncoder;

    /**
     * is the PID360 used?
     */
    private final boolean usesPid;

    /**
     * is motor smoothing on?
     */
    private boolean isSmooth = false;

    /**
     * Our lovely internally-used motor.
     */
    private SharedMotorCore motor;

    /**
     * Our lovely internally-used encoder.
     */
    private Encoder encoder;

    /**
     * Our lovely internally-used PID.
     */
    private PID360 pid;

    public Motor(MotorConfig motorConfig) {
        System.out.println(
                tag + consTag + "New motor constructed with ID " + motorConfig.id
        );
        System.out.println(
                tag + consTag + "- " + "Type: " + motorConfig.type
        );
        System.out.println(
                tag + consTag + "- " + "Direction: " + motorConfig.direction
        );
        System.out.println(
                tag + consTag + "- " + "Encoder channels: " +
                        motorConfig.encoderChannelA + "/" +
                        motorConfig.encoderChannelB
        );
        if (usedIds.contains(motorConfig.id)) {
            System.out.println(warn);
            System.out.println(
                    ANSI.YELLOW +
                            "Motor ID " + motorConfig.id + " has been used " +
                            "before! Check your configurations!" +
                            ANSI.RESET
            );
            System.out.println(warn);
        }
        if (usedIds.contains(motorConfig.encoderChannelA)) {
            if (motorConfig.encoderChannelA != -1) {
                System.out.println(warn);
                System.out.println(
                        ANSI.YELLOW +
                                "Encoder channel A " +
                                motorConfig.encoderChannelA +
                                " has been used " +
                                "before! Check your configurations!" +
                                ANSI.RESET
                );
                System.out.println(warn);
            }
        }
        if (usedIds.contains(motorConfig.encoderChannelB)) {
            if (motorConfig.encoderChannelB != -1) {
                System.out.println(warn);
                System.out.println(
                        ANSI.YELLOW +
                                "Encoder channel B " +
                                motorConfig.encoderChannelB +
                                " has been used " +
                                "before! Check your configurations!" +
                                ANSI.RESET
                );
                System.out.println(warn);
            }
        }
        usedIds.add(motorConfig.id);
        usedEncoderA.add(motorConfig.encoderChannelA);
        usedEncoderB.add(motorConfig.encoderChannelB);
        this.id = motorConfig.id;
        this.multiplierPositive = motorConfig.multiplierPositive;
        this.multiplierNegative = motorConfig.multiplierNegative;
        this.preMultiplierMin = motorConfig.preMultiplierMin;
        this.preMultiplierMax = motorConfig.preMultiplierMax;
        this.min = motorConfig.min;
        this.max = motorConfig.max;
        this.deadzonePositive = motorConfig.deadzonePositive;
        this.deadzoneNegative = motorConfig.deadzoneNegative;
        this.encoderChannelA = motorConfig.encoderChannelA;
        this.encoderChannelB = motorConfig.encoderChannelB;
        this.pidCenter = motorConfig.pidCenter;
        this.cpr = motorConfig.pidCpr;

        this.direction = motorConfig.direction;
        this.type = motorConfig.type;


        this.usesPreMultiplier = motorConfig.usesPreMultiplier;
        this.usesEncoder = motorConfig.usesEncoder;
        this.usesPid = motorConfig.usesPid;
    }

    /**
     * To be run whenever pre-multiplier minimum and maximum checks are
     * enabled by changing a pre-multiplier value.
     */
    private void _pre() {
        usesPreMultiplier = true;
    }

    /**
     * Get the hardware ID of the motor.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the positive multiplier.
     */
    public double getMultiplierPositive() {
        return multiplierPositive;
    }

    /**
     * Set the positive multiplier.
     */
    public void setMultiplierPositive(double multiplierPositive) {
        this.multiplierPositive = multiplierPositive;
    }

    /**
     * Get the negative multiplier.
     */
    public double getMultiplierNegative() {
        return multiplierNegative;
    }

    /**
     * Set the negative multiplier.
     */
    public void setMultiplierNegative(double multiplierNegative) {
        this.multiplierNegative = multiplierNegative;
    }

    /**
     * Set both positive and negative multipliers.
     *
     * @param multiplier the multiplier to set.
     */
    public void setMultiplier(double multiplier) {
        setMultiplierPositive(multiplier);
        setMultiplierNegative(multiplier);
    }

    /**
     * Get the pre-multiplier min.
     */
    public double getPreMultiplierMin() {
        _pre();
        return preMultiplierMin;
    }

    /**
     * Set the pre-multiplier min.
     */
    public void setPreMultiplierMin(double preMultiplierMin) {
        _pre();
        this.preMultiplierMin = preMultiplierMin;
    }

    /**
     * Get the pre-multiplier min.
     */
    public double getPreMultiplierMax() {
        _pre();
        return preMultiplierMax;
    }

    /**
     * Set the pre-multiplier max.
     */
    public void setPreMultiplierMax(double preMultiplierMax) {
        _pre();
        this.preMultiplierMax = preMultiplierMax;
    }

    /**
     * Get the post-multiplier min.
     */
    public double getMin() {
        return min;
    }

    /**
     * Set the post-multiplier min.
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * Get the post-multiplier max.
     */
    public double getMax() {
        return max;
    }

    /**
     * Set the post-multiplier max.
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * Get the positive deadzone.
     */
    public double getDeadzonePositive() {
        return deadzonePositive;
    }

    /**
     * Set the positive deadzone.
     */
    public void setDeadzonePositive(double deadzonePositive) {
        this.deadzonePositive = deadzonePositive;
    }

    /**
     * Get the negative deadzone.
     */
    public double getDeadzoneNegative() {
        return deadzoneNegative;
    }

    /**
     * Set the negative deadzone.
     */
    public void setDeadzoneNegative(double deadzoneNegative) {
        this.deadzoneNegative = deadzoneNegative;
    }

    /**
     * Set both the positive and negative deadzones.
     *
     * @param deadzone the new deadzone
     */
    public void setDeadzone(double deadzone) {
        setDeadzonePositive(deadzone);
        setDeadzoneNegative(deadzone);
    }

    /**
     * Get the encoder's A channel.
     */
    public int getEncoderChannelA() {
        return encoderChannelA;
    }

    /**
     * Get the encoder's B channel.
     */
    public int getEncoderChannelB() {
        return encoderChannelB;
    }

    /**
     * Get the PID's center angle.
     */
    public double getPidCenter() {
        return pidCenter;
    }

    /**
     * Get the PID's CPR.
     */
    public double getCpr() {
        return cpr;
    }

    /**
     * Get the motor's direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set the motor's direction.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Get the motor's type.
     */
    public Type getType() {
        return type;
    }

    /**
     * Does the motor use a pre-multiplier minimum or maximum?
     */
    public boolean usesPreMultiplier() {
        return usesPreMultiplier;
    }

    /**
     * Set whether or not the motor uses a pre-multiplier minimum or maximum.
     */
    public void setUsesPreMultiplier(boolean usesPreMultiplier) {
        this.usesPreMultiplier = usesPreMultiplier;
    }

    /**
     * Does the motor use an encoder?
     */
    public boolean usesEncoder() {
        return usesEncoder;
    }

    /**
     * Does the motor use an integrated PID?
     */
    public boolean usesPid() {
        return usesPid;
    }

    /**
     * Is smoothing applied to the motor?
     */
    public boolean isSmooth() {
        return isSmooth;
    }

    /**
     * Set whether or not smoothing should be applied to the motor.
     */
    public void setSmooth(boolean smooth) {
        isSmooth = smooth;
    }

    /**
     * Get the motor.
     */
    public SharedMotorCore getMotor() {
        return motor;
    }

    /**
     * Get the encoder.
     */
    public Encoder getEncoder() {
        return encoder;
    }

    /**
     * Get the PID.
     */
    public PID360 getPid() {
        return pid;
    }

    /**
     * Initialize the motor.
     *
     * <p>
     * This needs to be called before the motor is at all usable.
     * </p>
     */
    @Override
    public void init() {
        System.out.println(tag + initTag +
                "Started initialization for motor ID " + getId()
        );
        buildMotor();

        if (encoderChannelA == -1 || encoderChannelB == -1) {
            usesEncoder = false;
        }

        if (usesEncoder) {
            System.out.println(tag + initTag +
                    "- " + getId() + "'s Encoder Channel A: " +
                    encoderChannelA
            );
            System.out.println(tag + initTag +
                    "- " + getId() + "'s Encoder Channel B: " +
                    encoderChannelB
            );
            buildEncoder();
        } else {
            System.out.println(tag + initTag +
                    "- This motor DOES NOT use an encoder!"
            );
        }
    }

    /**
     * Create and enable the motor.
     */
    private void buildMotor() {
        switch (type) {
            case TALON:
                motor = new TalonCore(id);
                break;
            case SPARK_BRUSHED:
                motor = new SparkCore(
                        id,
                        CANSparkMaxLowLevel.MotorType.kBrushed
                );
                break;
            case SPARK_BRUSHLESS:
                motor = new SparkCore(
                        id,
                        CANSparkMaxLowLevel.MotorType.kBrushless
                );
                break;
        }

        motor.init();
    }

    /**
     * Create and enable the encoder.
     */
    private void buildEncoder() {
        if (encoderChannelA == -1 || encoderChannelB == -1) {
            usesEncoder = false;
            return;
        }

        encoder = new Encoder(
                encoderChannelA,
                encoderChannelB,
                direction.get()
        );

        encoder.init();
    }

    /**
     * Enable async encoder value updating.
     *
     * <p>
     * By automatically updating values utilizing multithreading and an events
     * queue system, we can just let the encoder do its thing and focus more
     * on making the motor do its job.
     * </p>
     *
     * @param delay the delay (ms) in between encoder updates.
     */
    public void enableAsyncEncoderUpdates(int delay) {
        isAutomaticallyUpdating = true;
        scheduleUpdate(delay);
    }

    /**
     * Check to see if async is still enabled and then reschedule an event.
     *
     * @param delay delay in between updates.
     */
    private void scheduleUpdate(int delay) {
        if (!isAutomaticallyUpdating) return;
        PassiveTickingManager.addToRun(
                () -> StringEvents.schedule(
                        key,
                        delay,
                        0,
                        new Timed() {
                            @Override
                            public Runnable open() {
                                return () -> encoder.update();
                            }

                            @Override
                            public Runnable close() {
                                return () -> scheduleUpdate(delay);
                            }
                        },
                        false
                )
        );
    }

    /**
     * Disable async encoder value updating.
     */
    public void disableAsyncEncoderUpdates() {
        isAutomaticallyUpdating = false;
    }

    /**
     * Internally-used method to set power to the motor.
     *
     * @param power the input power that'll be adjusted before being set.
     */
    private void _set(double power) {
        if (!(Math.abs(power) > 0)) power = 0.0;

        switch (direction) {
            case FORWARDS:
                power = power * 1;
                break;
            case BACKWARDS:
                power = power * -1;
                break;
        }

        if (usesPreMultiplier) {
            power = Math.max(preMultiplierMin, power);
            power = Math.min(preMultiplierMax, power);
        }

        power = power >= 0 ?
                power * multiplierPositive :
                power * multiplierNegative;

        power = Math.max(min, power);
        power = Math.min(max, power);

        power = Math.abs(power) < deadzonePositive && power > 0 ?
                0 :
                power;
        power = Math.abs(power) < deadzoneNegative && power < 0 ?
                0 :
                power;

        power = isSmooth ?
                (power + this.power) / 2 :
                power;

        motor.setPower(power);

        this.power = power;
    }

    /**
     * Internal method to get a motor's power.
     *
     * @return the motor's power.
     */
    private double _get() {
        return Math.abs(power) > 0 ? power : 0;
    }

    /**
     * Get the motor's power value.
     *
     * @return the motor's power value.
     */
    @Override
    public double getPower() {
        return _get();
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * This power value goes through a number of filters and refinements before
     * finally being sent to the motor. Some of these changes include...
     * <ul>
     *     <li>Positive multiplier.</li>
     *     <li>Negative multiplier.</li>
     *     <li>Virtual positive deadzone.</li>
     *     <li>Virtual negative deadzone.</li>
     *     <li>Pre and post multiplier minimum.</li>
     *     <li>Pre and post multiplier maximum.</li>
     * </ul>
     * There's a ton more (obviously very cool) things you can do with your
     * motor - visit this class's JavaDoc (or file) to learn more.
     * </p>
     *
     * @param power a double, between the values of -1 (negative one) and +1
     *              (positive one). This double, when multiplied by 100, would
     *              represent the percentage of power (positive or negative)
     */
    @Override
    public void setPower(double power) {
        _set(power);
    }

    /**
     * Set power to the motor.
     *
     * <p>
     * This power value goes through a number of filters and refinements before
     * finally being sent to the motor. Some of these changes include...
     * <ul>
     *     <li>Positive multiplier.</li>
     *     <li>Negative multiplier.</li>
     *     <li>Virtual positive deadzone.</li>
     *     <li>Virtual negative deadzone.</li>
     *     <li>Pre and post multiplier minimum.</li>
     *     <li>Pre and post multiplier maximum.</li>
     * </ul>
     * There's a ton more (obviously very cool) things you can do with your
     * motor - visit this class's JavaDoc (or file) to learn more.
     * </p>
     *
     * @param power a double, between the values of -1 (negative one) and +1
     *              (positive one). This double, when multiplied by 100, would
     *              represent the percentage of power (positive or negative)
     */
    @Override
    public void set(double power) {
        _set(power);
    }

    /**
     * Get power from the motor.
     *
     * @return the motor's power value.
     */
    @Override
    public double get() {
        return _get();
    }

    /**
     * Get the motor's current reported encoder count.
     *
     * <p>
     * This method ensures that the motor's encoder is enabled before doing
     * anything to actually get a count from the encoder. If no encoder is
     * enabled (meaning it wasn't enabled in the motor's set-up), this method
     * (and any other method that requires the use of encoders) will return
     * a value of "0" and send the user a message saying that they messed
     * something up. If you're here, there's a pretty decent chance you're here
     * from that message. Regardless, hi!
     * </p>
     *
     * @return the motor's current encoder count or zero.
     */
    @Override
    public int getCount() {
        return usesEncoder ? encoder.getCount() : 0;
    }

    /**
     * Get the motor's encoder's internal counting offset.
     *
     * <p>
     * The elibs2 Encoder class contains a piece of functionality named
     * "offset." Offset is a way to manually adjust the values the encoder
     * is reporting by a set amount. Let's take a couple of example encoder
     * values and see what an offset would do to them.
     * <ul>
     *     <li>
     *         500, 600, 700, 800
     *         <ul>
     *             <li>Offset of 100: 600, 700, 800, 900</li>
     *             <li>Offset of 125: 625, 725, 825, 925</li>
     *             <li>Offset of -50: 450, 550, 650, 750</li>
     *         </ul>
     *     </li>
     * </ul>
     * </p>
     *
     * <p>
     * This method ensures that the motor's encoder is enabled before doing
     * anything to actually get a count from the encoder. If no encoder is
     * enabled (meaning it wasn't enabled in the motor's set-up), this method
     * (and any other method that requires the use of encoders) will return
     * a value of "0" and send the user a message saying that they messed
     * something up. If you're here, there's a pretty decent chance you're here
     * from that message. Regardless, hi!
     * </p>
     *
     * @return the current counting offset.
     */
    @Override
    public int getCountOffset() {
        return usesEncoder ? encoder.getCountOffset() : 0;
    }

    /**
     * Set an encoder count offset value.
     *
     * <p>
     * The elibs2 Encoder class contains a piece of functionality named
     * "offset." Offset is a way to manually adjust the values the encoder
     * is reporting by a set amount. Let's take a couple of example encoder
     * values and see what an offset would do to them.
     * <ul>
     *     <li>
     *         500, 600, 700, 800
     *         <ul>
     *             <li>Offset of 100: 600, 700, 800, 900</li>
     *             <li>Offset of 125: 625, 725, 825, 925</li>
     *             <li>Offset of -50: 450, 550, 650, 750</li>
     *         </ul>
     *     </li>
     * </ul>
     * </p>
     *
     * <p>
     * This method ensures that the motor's encoder is enabled before doing
     * anything to actually get a count from the encoder. If no encoder is
     * enabled (meaning it wasn't enabled in the motor's set-up), this method
     * (and any other method that requires the use of encoders) will return
     * a value of "0" and send the user a message saying that they messed
     * something up. If you're here, there's a pretty decent chance you're here
     * from that message. Regardless, hi!
     * </p>
     *
     * @param offset the new offset to set to the encoder.
     */
    @Override
    public void setCountOffset(int offset) {
        if (usesEncoder) {
            encoder.setCountOffset(offset);
        }
    }

    /**
     * Set an offset that's appropriate to the current exact value of the
     * encoder, thus "zeroing" the encoder.
     *
     * <p>
     * The elibs2 Encoder class contains a piece of functionality named
     * "offset." Offset is a way to manually adjust the values the encoder
     * is reporting by a set amount. Let's take a couple of example encoder
     * values and see what an offset would do to them.
     * <ul>
     *     <li>
     *         500, 600, 700, 800
     *         <ul>
     *             <li>Offset of 100: 600, 700, 800, 900</li>
     *             <li>Offset of 125: 625, 725, 825, 925</li>
     *             <li>Offset of -50: 450, 550, 650, 750</li>
     *         </ul>
     *     </li>
     * </ul>
     * </p>
     *
     * <p>
     * This method ensures that the motor's encoder is enabled before doing
     * anything to actually get a count from the encoder. If no encoder is
     * enabled (meaning it wasn't enabled in the motor's set-up), this method
     * (and any other method that requires the use of encoders) will return
     * a value of "0" and send the user a message saying that they messed
     * something up. If you're here, there's a pretty decent chance you're here
     * from that message. Regardless, hi!
     * </p>
     */
    @Override
    public void zeroCount() {
        if (usesEncoder) {
            encoder.zeroCount();
        }
    }
}
