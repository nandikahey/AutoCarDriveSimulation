package org.carsim.util.enums;

public enum Orientation {
    NORTH("N"),
    EAST("N"),
    SOUTH("S"),
    WEST("W");

    private final String value;

    Orientation(String s) {
        value = s;
    }

    public static Orientation valueOfEnum(String label) {
        for (Orientation e : values()) {
            if (e.value.equals(label)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
