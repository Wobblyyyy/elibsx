package me.wobblyyyy.ezpos.common;

public class RobotPosition implements CommonCoordinate {
    private final PosDeg fr;
    private final PosDeg fl;
    private final PosDeg br;
    private final PosDeg bl;
    private final PosDeg center;

    public RobotPosition(final double horizontal,
                         final double vertical,
                         final double degrees,
                         final double x,
                         final double y) {
        double h2 = horizontal / 2;
        double v2 = vertical / 2;

        PosDeg center = new PosDeg(
                x,
                y,
                degrees
        );
        PosDeg fr = new PosDeg(
                x + h2,
                y + v2,
                degrees
        );
        PosDeg fl = new PosDeg(
                x - h2,
                y + v2,
                degrees
        );
        PosDeg br = new PosDeg(
                x + h2,
                y - v2,
                degrees
        );
        PosDeg bl = new PosDeg(
                x - h2,
                y - v2,
                degrees
        );

        PosDeg[] poss = rotatePoints(
                new PosDeg[]{
                        fr,
                        fl,
                        br,
                        bl
                },
                center,
                degrees
        );

        fr = poss[0];
        fl = poss[1];
        br = poss[2];
        bl = poss[3];

        this.fr = fr;
        this.fl = fl;
        this.br = br;
        this.bl = bl;
        this.center = center;
    }

    public PosDeg getFr() {
        return fr;
    }

    public PosDeg getFl() {
        return fl;
    }

    public PosDeg getBr() {
        return br;
    }

    public PosDeg getBl() {
        return bl;
    }

    public PosDeg getCenter() {
        return center;
    }

    private PosDeg[] rotatePoints(PosDeg[] coordinates,
                                  PosDeg center,
                                  double angle) {
        PosDeg[] r = new PosDeg[4];

        for (PosDeg c : coordinates) {
            r[0] = (PosDeg) Coordinate.rotateDeg(center, coordinates[0], angle);
            r[1] = (PosDeg) Coordinate.rotateDeg(center, coordinates[1], angle);
            r[2] = (PosDeg) Coordinate.rotateDeg(center, coordinates[2], angle);
            r[3] = (PosDeg) Coordinate.rotateDeg(center, coordinates[3], angle);
        }

        return r;
    }

    @Override
    public double getAngle() {
        return center.getAngle();
    }

    @Override
    public double getAngleRad() {
        return center.getAngleRad();
    }

    @Override
    public double getAngleDeg() {
        return center.getAngleDeg();
    }

    @Override
    public double getX() {
        return center.getX();
    }

    @Override
    public double getY() {
        return center.getY();
    }
}
