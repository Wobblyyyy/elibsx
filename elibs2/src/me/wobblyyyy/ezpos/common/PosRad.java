package me.wobblyyyy.ezpos.common;

public class PosRad implements CommonCoordinate {
    private final double angle;
    private final double x;
    private final double y;

    public PosRad(final double x,
                  final double y) {
        this.angle = 0;
        this.x = x;
        this.y = y;
    }

    public PosRad(final double angle,
                  final double x,
                  final double y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public double getAngleDeg() {
        return Math.toDegrees(angle);
    }

    @Override
    public double getAngleRad() {
        return angle;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
