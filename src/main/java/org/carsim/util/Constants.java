package org.carsim.util;

public final class Constants {

    public static final String ACCEPTED_COMMANDS = "FLR";
    public static final String ACCEPTED_DIRECTIONS = "NSEW";
    public static final String GRID_SIZE_INPUT_REGEX = "[1-9]\\d* [1-9]\\d*";
    public static final String VEHICLE_POS_ORIENTATION_INPUT_REGEX = "[0-9]\\d* [0-9]\\d* [" + Constants.ACCEPTED_DIRECTIONS + "]";
    public static final String VEHICLE_COMMAND_INPUT_REGEX = "[" + Constants.ACCEPTED_COMMANDS + "]+";
    public static final String VEHICLE_NAME_INPUT_REGEX = "[a-zA-Z]+";
    public static final String VEHICLE_COUNT_INPUT_REGEX = "[1-9]\\d*";
    public static final String SIMULATION_TYPE = "[12]";
    public static final String NO_COLLISIONS = "no collision";
    public static final String UNDEFINED_ORIENTATION = "Undefined Vehicle Orientation";
    public static final String COMMAND_NOT_SUPPORTED = "Command Not Supported";
    public static final int NAVIGATION_SIMULATION = 1;
    public static final int COLLISION_DETECTION_SIMULATION = 2;


    private Constants() {
    }
}
