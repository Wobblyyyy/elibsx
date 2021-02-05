package me.wobblyyyy.ezpos.common;

import me.wobblyyyy.intra.ftc2.utils.math.Math;

public class Coordinate {
    private PosDeg fix(CommonCoordinate cc) {
        if (cc instanceof PosRad) {
            return new PosDeg(
                    cc.getX(),
                    cc.getX(),
                    cc.getAngleDeg()
            );
        }
        return (PosDeg) cc;
    }

    public static CommonCoordinate rotateRad(CommonCoordinate center,
                                        CommonCoordinate point,
                                        double angle) {
        double c_x = center.getX();
        double c_y = center.getY();
        double p_x = point.getX();
        double p_y = point.getY();
        // Apply the rotation matrix or whatever the FUCK it's called.
        // Rv = [ cos(theta), -sin(theta) ] * [ x ] = [ x cos(theta) - y sin(theta) ]
        //      [ sin(theta),  cos(theta) ]   [ y ]   [ x sin(theta) + y cos(theta) ]
        // x prime = x cos(theta) - y sin(theta)
        // y prime = x sin(theta) + y cos(theta)
        double x1 = p_x - c_x;
        double y1 = p_y - c_y;
        double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
        double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
        return new PosDeg(
                x2 + center.getX(),
                y2 + center.getY()
        );
    }

    public static CommonCoordinate rotateDeg(CommonCoordinate center,
                                      CommonCoordinate point,
                                      double angle) {
        return rotateRad(
                center,
                point,
                Math.toRadians(angle)
        );
    }
}
