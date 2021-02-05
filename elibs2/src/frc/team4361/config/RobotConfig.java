package frc.team4361.config;

import java.util.HashMap;

public abstract class RobotConfig {
    private static HashMap<String, Double> config = new HashMap<>();

    public RobotConfig(Element... elements) {
        for (Element e : elements) {
            config.put(
                    e.getName(),
                    e.getId()
            );
        }
    }

    public static int get(Common name) {
        return (int) Math.round(get(name.getName()));
    }

    public static double get(String name) {
        return config.get(name);
    }
}
