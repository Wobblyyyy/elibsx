/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/4/20, 9:11 PM
 * Part of the _1125c library
 *
 * **
 *
 * Permission is granted, free of charge, to any person obtaining
 * a copy of this software and / or any of it's related source code or
 * documentation ("Software") to copy, merge, modify, publish,
 * distribute, sublicense, and / or sell copies of Software.
 *
 * All Software included is provided in an "as is" state, without any
 * type or form of warranty. The Authors and Copyright Holders of this
 * piece of software, documentation, or source code waive all
 * responsibility and shall not be liable for any claim, damages, or
 * other forms of liability, regardless of the form it may take.
 *
 * Any form of re-distribution of Software is required to have this same
 * copyright notice included in any source files or forms of documentation
 * which have stemmed or branched off of the original Software.
 *
 * **
 *
 */

package me.wobblyyyy.intra.ftc2.utils.math;

import java.util.Random;

/**
 * Custom implementation of java.lang.Math.
 * This is used because there's a couple features I wanted to add to the default Math
 * class, but because it's declared final, I can't extend it, so I had to
 * create an entirely new class as a work around.
 * All of the custom methods here are documented in the code.
 */
public class Math {
    public static final double E = 2.718281828459045D;
    public static final double PI = 3.141592653589793D;
    private static final double DEGREES_TO_RADIANS = 0.017453292519943295D;
    private static final double RADIANS_TO_DEGREES = 57.29577951308232D;
    private static final long negativeZeroFloatBits = Float.floatToRawIntBits(-0.0F);
    private static final long negativeZeroDoubleBits = Double.doubleToRawLongBits(-0.0D);
    static double twoToTheDoubleScaleUp = powerOfTwoD(512);
    static double twoToTheDoubleScaleDown = powerOfTwoD(-512);

    private Math() {
    }

    /**
     * Returns the average of all of the parameters.
     *
     * @param arguments a varargs argument for all of the numbers to average
     * @return the average of the double... arguments
     */
    public static double average(double... arguments) {
        double c = 0;
        for (double d : arguments) {
            c += d;
        }
        return c / arguments.length;
    }

    /**
     * Returns the average of all of the parameters.
     *
     * @param arguments a varargs argument for all of the numbers to average
     * @return the average of the int... arguments
     */
    public static int average(int... arguments) {
        int c = 0;
        for (int i : arguments) {
            c += i;
        }
        return c / arguments.length;
    }

    /**
     * Check if all of the arguments in sample are between min and max.
     *
     * @param min    the lowest possible value
     * @param max    the highest possible value
     * @param sample a varargs argument for all of the numbers to check
     * @return a boolean, indicating whether or not all of the arguments passed
     */
    public static boolean isBetween(double min, double max, double... sample) {
        for (double d : sample) {
            if (!(min < d && d < max)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Overload for clip, default min and max values of -1 and 1
     * respectively are provided.
     *
     * @param d number to clip
     * @return the clipped value
     */
    public static double clip(double d) {
        return clip(-1, 1, d);
    }

    /**
     * Get the 'clipped' value of a number (d) and return it.
     * Make sure d fits in between min and max, and if it doesn't,
     * set it to the min or max, depending on what d is equal to.
     *
     * @param min the lowest possible number
     * @param max the highest possible number
     * @param d   the number to clip
     * @return the clipped number
     */
    public static double clip(double min, double max, double d) {
        if (d < min) {
            return min;
        } else {
            return java.lang.Math.min(d, max);
        }
    }

    /**
     * Basic round function. Rounds a number to a set number of digits.
     *
     * @param number the number to round
     * @param digits the amount of digits to round to
     * @return the newly rounded number. very fancy, to say the least.
     */
    public static double round(double number, int digits) {
        return (number * (10 * digits)) / 10 * digits;
    }

    public static double sin(double a) {
        return StrictMath.sin(a);
    }

    public static double cos(double a) {
        return StrictMath.cos(a);
    }

    public static double tan(double a) {
        return StrictMath.tan(a);
    }

    public static double asin(double a) {
        return StrictMath.asin(a);
    }

    public static double acos(double a) {
        return StrictMath.acos(a);
    }

    public static double atan(double a) {
        return StrictMath.atan(a);
    }

    public static double toRadians(double angdeg) {
        return angdeg * 0.017453292519943295D;
    }

    public static double toDegrees(double angrad) {
        return angrad * 57.29577951308232D;
    }

    public static double exp(double a) {
        return StrictMath.exp(a);
    }

    public static double log(double a) {
        return StrictMath.log(a);
    }

    public static double log10(double a) {
        return StrictMath.log10(a);
    }

    public static double sqrt(double a) {
        return StrictMath.sqrt(a);
    }

    public static double cbrt(double a) {
        return StrictMath.cbrt(a);
    }

    public static double IEEEremainder(double f1, double f2) {
        return StrictMath.IEEEremainder(f1, f2);
    }

    public static double ceil(double a) {
        return StrictMath.ceil(a);
    }

    public static double floor(double a) {
        return StrictMath.floor(a);
    }

    public static double rint(double a) {
        return StrictMath.rint(a);
    }

    public static double atan2(double y, double x) {
        return StrictMath.atan2(y, x);
    }

    public static double pow(double a, double b) {
        return StrictMath.pow(a, b);
    }

    public static int round(float a) {
        int intBits = Float.floatToRawIntBits(a);
        int biasedExp = (intBits & 2139095040) >> 23;
        int shift = 149 - biasedExp;
        if ((shift & -32) == 0) {
            int r = intBits & 8388607 | 8388608;
            if (intBits < 0) {
                r = -r;
            }

            return (r >> shift) + 1 >> 1;
        } else {
            return (int) a;
        }
    }

    public static long round(double a) {
        long longBits = Double.doubleToRawLongBits(a);
        long biasedExp = (longBits & 9218868437227405312L) >> 52;
        long shift = 1074L - biasedExp;
        if ((shift & -64L) == 0L) {
            long r = longBits & 4503599627370495L | 4503599627370496L;
            if (longBits < 0L) {
                r = -r;
            }

            return (r >> (int) shift) + 1L >> 1;
        } else {
            return (long) a;
        }
    }

    public static double random(double upper) {
        Random rand = new Random();
        return new Random().nextInt((int) upper);
    }

    public static int addExact(int x, int y) {
        int r = x + y;
        if (((x ^ r) & (y ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        } else {
            return r;
        }
    }

    public static long addExact(long x, long y) {
        long r = x + y;
        if (((x ^ r) & (y ^ r)) < 0L) {
            throw new ArithmeticException("long overflow");
        } else {
            return r;
        }
    }

    public static int subtractExact(int x, int y) {
        int r = x - y;
        if (((x ^ y) & (x ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        } else {
            return r;
        }
    }

    public static long subtractExact(long x, long y) {
        long r = x - y;
        if (((x ^ y) & (x ^ r)) < 0L) {
            throw new ArithmeticException("long overflow");
        } else {
            return r;
        }
    }

    public static int multiplyExact(int x, int y) {
        long r = (long) x * (long) y;
        if ((long) ((int) r) != r) {
            throw new ArithmeticException("integer overflow");
        } else {
            return (int) r;
        }
    }

    public static long multiplyExact(long x, int y) {
        return multiplyExact(x, (long) y);
    }

    public static long multiplyExact(long x, long y) {
        long r = x * y;
        long ax = abs(x);
        long ay = abs(y);
        if ((ax | ay) >>> 31 == 0L || (y == 0L || r / y == x) && (x != -9223372036854775808L || y != -1L)) {
            return r;
        } else {
            throw new ArithmeticException("long overflow");
        }
    }

    public static int incrementExact(int a) {
        if (a == 2147483647) {
            throw new ArithmeticException("integer overflow");
        } else {
            return a + 1;
        }
    }

    public static long incrementExact(long a) {
        if (a == 9223372036854775807L) {
            throw new ArithmeticException("long overflow");
        } else {
            return a + 1L;
        }
    }

    public static int decrementExact(int a) {
        if (a == -2147483648) {
            throw new ArithmeticException("integer overflow");
        } else {
            return a - 1;
        }
    }

    public static long decrementExact(long a) {
        if (a == -9223372036854775808L) {
            throw new ArithmeticException("long overflow");
        } else {
            return a - 1L;
        }
    }

    public static int negateExact(int a) {
        if (a == -2147483648) {
            throw new ArithmeticException("integer overflow");
        } else {
            return -a;
        }
    }

    public static long negateExact(long a) {
        if (a == -9223372036854775808L) {
            throw new ArithmeticException("long overflow");
        } else {
            return -a;
        }
    }

    public static int toIntExact(long value) {
        if ((long) ((int) value) != value) {
            throw new ArithmeticException("integer overflow");
        } else {
            return (int) value;
        }
    }

    public static long multiplyFull(int x, int y) {
        return (long) x * (long) y;
    }

    public static long multiplyHigh(long x, long y) {
        long z1;
        long z0;
        long x1;
        long x2;
        long y1;
        long y2;
        long z2;
        long t;
        if (x >= 0L && y >= 0L) {
            x1 = x >>> 32;
            x2 = y >>> 32;
            y1 = x & 4294967295L;
            y2 = y & 4294967295L;
            z2 = x1 * x2;
            t = y1 * y2;
            z1 = (x1 + y1) * (x2 + y2);
            z0 = z1 - z2 - t;
            return ((t >>> 32) + z0 >>> 32) + z2;
        } else {
            x1 = x >> 32;
            x2 = x & 4294967295L;
            y1 = y >> 32;
            y2 = y & 4294967295L;
            z2 = x2 * y2;
            t = x1 * y2 + (z2 >>> 32);
            z1 = t & 4294967295L;
            z0 = t >> 32;
            z1 += x2 * y1;
            return x1 * y1 + z0 + (z1 >> 32);
        }
    }

    public static int floorDiv(int x, int y) {
        int r = x / y;
        if ((x ^ y) < 0 && r * y != x) {
            --r;
        }

        return r;
    }

    public static long floorDiv(long x, int y) {
        return floorDiv(x, (long) y);
    }

    public static long floorDiv(long x, long y) {
        long r = x / y;
        if ((x ^ y) < 0L && r * y != x) {
            --r;
        }

        return r;
    }

    public static int floorMod(int x, int y) {
        return x - floorDiv(x, y) * y;
    }

    public static int floorMod(long x, int y) {
        return (int) (x - floorDiv(x, y) * (long) y);
    }

    public static long floorMod(long x, long y) {
        return x - floorDiv(x, y) * y;
    }

    public static int abs(int a) {
        return a < 0 ? -a : a;
    }

    public static long abs(long a) {
        return a < 0L ? -a : a;
    }

    public static float abs(float a) {
        return a <= 0.0F ? 0.0F - a : a;
    }

    public static double abs(double a) {
        return a <= 0.0D ? 0.0D - a : a;
    }

    public static int max(int a, int b) {
        return java.lang.Math.max(a, b);
    }

    public static long max(long a, long b) {
        return java.lang.Math.max(a, b);
    }

    public static float max(float a, float b) {
        if (a != a) {
            return a;
        } else if (a == 0.0F && b == 0.0F && (long) Float.floatToRawIntBits(a) == negativeZeroFloatBits) {
            return b;
        } else {
            return java.lang.Math.max(a, b);
        }
    }

    public static double max(double a, double b) {
        if (a != a) {
            return a;
        } else if (a == 0.0D && b == 0.0D && Double.doubleToRawLongBits(a) == negativeZeroDoubleBits) {
            return b;
        } else {
            return java.lang.Math.max(a, b);
        }
    }

    public static int min(int a, int b) {
        return java.lang.Math.min(a, b);
    }

    public static long min(long a, long b) {
        return java.lang.Math.min(a, b);
    }

    public static float min(float a, float b) {
        if (a != a) {
            return a;
        } else if (a == 0.0F && b == 0.0F && (long) Float.floatToRawIntBits(b) == negativeZeroFloatBits) {
            return b;
        } else {
            return java.lang.Math.min(a, b);
        }
    }

    public static double min(double a, double b) {
        if (a != a) {
            return a;
        } else if (a == 0.0D && b == 0.0D && Double.doubleToRawLongBits(b) == negativeZeroDoubleBits) {
            return b;
        } else {
            return java.lang.Math.min(a, b);
        }
    }

    public static float fma(float a, float b, float c) {
        return (float) ((double) a * (double) b + (double) c);
    }

    public static double ulp(double d) {
        int exp = getExponent(d);
        switch (exp) {
            case -1023:
                return 4.9E-324D;
            case 1024:
                return abs(d);
            default:
                assert exp <= 1023 && exp >= -1022;

                exp -= 52;
                return exp >= -1022 ? powerOfTwoD(exp) : Double.longBitsToDouble(1L << exp - -1074);
        }
    }

    public static float ulp(float f) {
        int exp = getExponent(f);
        switch (exp) {
            case -127:
                return 1.4E-45F;
            case 128:
                return abs(f);
            default:
                assert exp <= 127 && exp >= -126;

                exp -= 23;
                return exp >= -126 ? powerOfTwoF(exp) : Float.intBitsToFloat(1 << exp - -149);
        }
    }

    public static double signum(double d) {
        return d != 0.0D && !Double.isNaN(d) ? copySign(1.0D, d) : d;
    }

    public static float signum(float f) {
        return f != 0.0F && !Float.isNaN(f) ? copySign(1.0F, f) : f;
    }

    public static double sinh(double x) {
        return StrictMath.sinh(x);
    }

    public static double cosh(double x) {
        return StrictMath.cosh(x);
    }

    public static double tanh(double x) {
        return StrictMath.tanh(x);
    }

    public static double hypot(double x, double y) {
        return StrictMath.hypot(x, y);
    }

    public static double expm1(double x) {
        return StrictMath.expm1(x);
    }

    public static double log1p(double x) {
        return StrictMath.log1p(x);
    }

    public static double copySign(double magnitude, double sign) {
        return Double.longBitsToDouble(Double.doubleToRawLongBits(sign) & -9223372036854775808L | Double.doubleToRawLongBits(magnitude) & 9223372036854775807L);
    }

    public static float copySign(float magnitude, float sign) {
        return Float.intBitsToFloat(Float.floatToRawIntBits(sign) & -2147483648 | Float.floatToRawIntBits(magnitude) & 2147483647);
    }

    public static int getExponent(float f) {
        return ((Float.floatToRawIntBits(f) & 2139095040) >> 23) - 127;
    }

    public static int getExponent(double d) {
        return (int) (((Double.doubleToRawLongBits(d) & 9218868437227405312L) >> 52) - 1023L);
    }

    public static double nextAfter(double start, double direction) {
        long transducer;
        if (start > direction) {
            if (start != 0.0D) {
                transducer = Double.doubleToRawLongBits(start);
                return Double.longBitsToDouble(transducer + (transducer > 0L ? -1L : 1L));
            } else {
                return -4.9E-324D;
            }
        } else if (start < direction) {
            transducer = Double.doubleToRawLongBits(start + 0.0D);
            return Double.longBitsToDouble(transducer + (transducer >= 0L ? 1L : -1L));
        } else {
            return start == direction ? direction : start + direction;
        }
    }

    public static float nextAfter(float start, double direction) {
        int transducer;
        if ((double) start > direction) {
            if (start != 0.0F) {
                transducer = Float.floatToRawIntBits(start);
                return Float.intBitsToFloat(transducer + (transducer > 0 ? -1 : 1));
            } else {
                return -1.4E-45F;
            }
        } else if ((double) start < direction) {
            transducer = Float.floatToRawIntBits(start + 0.0F);
            return Float.intBitsToFloat(transducer + (transducer >= 0 ? 1 : -1));
        } else {
            return (double) start == direction ? (float) direction : start + (float) direction;
        }
    }

    public static double nextUp(double d) {
        return java.lang.Math.nextUp(d);
    }

    public static float nextUp(float f) {
        return java.lang.Math.nextUp(f);
    }

    public static double nextDown(double d) {
        if (!Double.isNaN(d) && d != -1.0D / 0.0) {
            return d == 0.0D ? -4.9E-324D : Double.longBitsToDouble(Double.doubleToRawLongBits(d) + (d > 0.0D ? -1L : 1L));
        } else {
            return d;
        }
    }

    public static float nextDown(float f) {
        if (!Float.isNaN(f) && f != -1.0F / 0.0) {
            return f == 0.0F ? -1.4E-45F : Float.intBitsToFloat(Float.floatToRawIntBits(f) + (f > 0.0F ? -1 : 1));
        } else {
            return f;
        }
    }

    static double powerOfTwoD(int n) {
        assert n >= -1022 && n <= 1023;

        return Double.longBitsToDouble((long) n + 1023L << 52 & 9218868437227405312L);
    }

    static float powerOfTwoF(int n) {
        assert n >= -126 && n <= 127;

        return Float.intBitsToFloat(n + 127 << 23 & 2139095040);
    }

    private static final class RandomNumberGeneratorHolder {
        static final Random randomNumberGenerator = new Random();

        private RandomNumberGeneratorHolder() {
        }
    }
}
