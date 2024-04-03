package org.carsim.util;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Reads user inputs, validate it and converts user input
 *
 * <p>
 * The user is prompted to enter different inputs.
 * The methods continuously prompts the user until a valid input is provided.
 * Validation is performed using an {@code InputValidator} instance.
 * </p>
 */
public class InputPrompter {

    private final Logger logger = Logger.getLogger(getClass().getName());
    InputValidator inputValidator;

    public InputPrompter(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public int simulationTypePrompt(Scanner scanner) {
        String simulationTypeInput;
        boolean isInputValid;
        do {
            logger.log(Level.INFO, "Enter Simulation Type (Accepted Values: 1,2): ");
            simulationTypeInput = scanner.nextLine();
            isInputValid = inputValidator.validateSimulationType(simulationTypeInput);
            if (!isInputValid) {
                logger.log(Level.SEVERE, "Invalid Input - Accepted Values: 1,2 ");
            }
        } while (!isInputValid);

        return Integer.parseInt(simulationTypeInput);
    }

    public int[] gridSizePrompt(Scanner scanner) {
        String gridSizeInput;
        boolean isInputValid;
        do {
            logger.log(Level.INFO, "Enter grid width and height (format: width height): ");
            gridSizeInput = scanner.nextLine();
            isInputValid = inputValidator.validateGridInput(gridSizeInput);
            if (!isInputValid) {
                logger.log(Level.SEVERE, "Invalid Input - Accepted Values: positive integers");
            }
        } while (!isInputValid);

        return Arrays.stream(gridSizeInput.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public String[] vehicleLocationPrompt(Scanner scanner) {
        String vehicleLocationOrientationInput;
        boolean isInputValid;
        do {
            logger.log(Level.INFO, "Enter vehicle current position and direction (format: int int [NESW]): ");
            vehicleLocationOrientationInput = scanner.nextLine();
            isInputValid = inputValidator.validateCarPositionAndOrientation(vehicleLocationOrientationInput);
            if (!isInputValid) {
                logger.log(Level.SEVERE, "Invalid Input - Accepted Values: int int [NESW]");
            }
        } while (!isInputValid);


        return vehicleLocationOrientationInput.split(" ");
    }

    public char[] instructionPrompt(Scanner scanner) {
        String instruction;
        boolean isInputValid;
        do {
            logger.log(Level.INFO, "Enter Instructions (format: LRF): ");
            instruction = scanner.nextLine();
            isInputValid = inputValidator.validateCommands(instruction);
            if (!isInputValid) {
                logger.log(Level.SEVERE, "Invalid Input - Accepted Values: LFR");
            }
        } while (!isInputValid);

        return instruction.toCharArray();
    }

    public int vehicleCountPrompt(Scanner scanner) {
        String vehicleCount;
        boolean isInputValid;
        do {
            logger.log(Level.INFO, "Number of vehicles in simulation: ");
            vehicleCount = scanner.nextLine();
            isInputValid = inputValidator.validateVehicleCount(vehicleCount);
            if (!isInputValid) {
                logger.log(Level.SEVERE, "Invalid Input - Accepted Values: positive integers");
            }
        } while (!isInputValid);

        return Integer.parseInt(vehicleCount);
    }

    public String vehicleNamePrompt(Scanner scanner) {
        String vehicleName;
        boolean isInputValid;
        do {
            logger.log(Level.INFO, "Enter Vehicle Name: ");
            vehicleName = scanner.nextLine();
            isInputValid = inputValidator.validateVehicleLabel(vehicleName);
            if (!isInputValid) {
                logger.log(Level.SEVERE, "Invalid Input - Accepted Values: English Letters without space");
            }
        } while (!isInputValid);

        return vehicleName;
    }

}
