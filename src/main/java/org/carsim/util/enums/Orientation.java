package org.carsim.util.enums;

import org.carsim.exceptions.UndefinedOrientationException;
import org.carsim.util.Constants;

/**
 * Enum representing four main directions (North, East, South, West).
 */
public enum Orientation {
    NORTH("N"),
    EAST("E"),
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
        throw new UndefinedOrientationException(Constants.UNDEFINED_ORIENTATION);
    }

    @Override
    public String toString() {
        return this.value;
    }

}
