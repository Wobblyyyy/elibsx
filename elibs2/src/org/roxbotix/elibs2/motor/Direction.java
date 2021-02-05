package org.roxbotix.elibs2.motor;

public enum Direction {
    FORWARDS(true),
    BACKWARDS(false);

    private final boolean b;

    Direction(boolean b) {
        this.b = b;
    }

    public boolean get() {
        return b;
    }
}
