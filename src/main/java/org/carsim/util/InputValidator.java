package org.carsim.util;

import java.util.logging.Logger;

public class InputValidator {
    Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Validates user input for grid size
     * Accepted Format: [1-9]\d* [1-9]\d*
     *
     * @param input User input string
     * @return true if user input matches the used regular expression
     */
    public boolean validateGridInput(String input) {
        if (input == null) return false;
        return input.matches(Constants.GRID_SIZE_INPUT_REGEX);
    }

    /**
     * Validates user input for car position and orientation in Grid
     * Accepted Format: [0-9]\\d* [0-9]\\d* [NESW]
     *
     * @param input User input string
     * @return true if user input matches the used regular expression
     */
    public boolean validateCarPositionAndOrientation(String input) {
        if (input == null) return false;
        return input.matches(Constants.VEHICLE_POS_ORIENTATION_INPUT_REGEX);
    }

    /**
     * Validates user input for commands for car navigation
     * Accepted Format: [FRL]+
     *
     * @param input User input string
     * @return true if user input matches the used regular expression
     */
    public boolean validateCommands(String input) {
        if (input == null) return false;
        return input.matches(Constants.VEHICLE_COMMAND_INPUT_REGEX);
    }

    /**
     * Validates user input for car label. Only used in collision detection engine.
     * Accepted Format: [a-zA-Z]+
     *
     * @param input User input string
     * @return true if user input matches the used regular expression
     */
    public boolean validateVehicleLabel(String input) {
        if (input == null) return false;
        return input.matches(Constants.VEHICLE_NAME_INPUT_REGEX);
    }


    /**
     * Validates user input number of cars in  collision detection engine.
     * Accepted Format: [1-9]+
     *
     * @param input User input string
     * @return true if user input matches the used regular expression
     */
    public boolean validateVehicleCount(String input) {
        if (input == null) return false;
        return input.matches(Constants.VEHICLE_COUNT_INPUT_REGEX);
    }

    /**
     * Validates user input to select simulation type
     * Accepted Format: int (1 or 2)
     *
     * @param input User input string
     * @return true if user input matches the used regular expression
     */
    public boolean validateSimulationType(String input) {
        if (input == null) return false;
        return input.matches(Constants.SIMULATION_TYPE);
    }

}
