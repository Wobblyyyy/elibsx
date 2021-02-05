package frc.team4361.config.testing;

import frc.team4361.config.Common;

import java.util.HashMap;

public class TankTesting {
    private static boolean hasUpdated = false;

    public static HashMap<String, Double> map;

    private static void forceUpdate() {
        map = new HashMap<>() {{
            put(Common.DRIVE_FR.getName(), 0.0);
            put(Common.DRIVE_FL.getName(), 1.0);
            put(Common.DRIVE_BR.getName(), 2.0);
            put(Common.DRIVE_BL.getName(), 3.0);
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
