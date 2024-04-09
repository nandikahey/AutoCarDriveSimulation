package org.carsim.util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


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
    InputValidator inputValidator;

    public InputPrompter(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public int simulationTypePrompt(Scanner scanner) {
        String simulationTypeInput;
        boolean isInputValid;
        PrintStream out = new PrintStream(System.out);
        do {
            out.printf("Select Simulation Type, Accepted Values (1-Navigation Simulation. 2-Collision Detection Simulation) %nEnter Value: ");
            simulationTypeInput = scanner.nextLine();
            isInputValid = inputValidator.validateSimulationType(simulationTypeInput);
            if (!isInputValid) {
                out.println("Invalid Input - Accepted Values: 1,2 ");
            }
        } while (!isInputValid);
        int engineType = Integer.parseInt(simulationTypeInput);
        if (engineType == Constants.NAVIGATION_SIMULATION) {
            out.println("Navigation Simulation Engine Selected\n");
        } else {
            out.println("Collision Detection Simulation Engine Selected\n");
        }
        return Integer.parseInt(simulationTypeInput);
    }

    public int[] gridSizePrompt(Scanner scanner) {
        String gridSizeInput;
        boolean isInputValid;
        do {
            PrintStream out = new PrintStream(System.out);
            out.print("Enter Grid Size [Format: int int]: ");
            gridSizeInput = scanner.nextLine();
            isInputValid = inputValidator.validateGridInput(gridSizeInput);
            if (!isInputValid) {
                out.println("Invalid Input - Accepted Values: positive integers");
            }
        } while (!isInputValid);

        return Arrays.stream(gridSizeInput.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public String[] vehicleLocationPrompt(Scanner scanner, int[] gridSize, int vehicleIndex) {
        String vehicleLocationOrientationInput;
        boolean isInputValid;
        boolean isLocationValid = false;
        do {
            PrintStream out = new PrintStream(System.out);
            out.printf("Enter Vehicle %s Position and Facing Direction [Format: int int (N,E,S,W)]: ", vehicleIndex);
            vehicleLocationOrientationInput = scanner.nextLine();
            isInputValid = inputValidator.validateCarPositionAndOrientation(vehicleLocationOrientationInput);
            if (isInputValid) {
                isLocationValid = inputValidator.validateInitialVehiclePosition(vehicleLocationOrientationInput, gridSize);
                if (!isLocationValid) {
                    out.println("Invalid Initial Location - Out of Grid");
                }
            } else {
                out.println("Invalid Input - Accepted Format: int int [N,E,S,W]");
            }
        } while (!isInputValid || !isLocationValid);
        return vehicleLocationOrientationInput.split(" ");
    }

    public char[] instructionPrompt(Scanner scanner, int vehicleIndex) {
        String instruction;
        boolean isInputValid;
        do {
            PrintStream out = new PrintStream(System.out);
            out.printf("Enter Vehicle %s Instructions [Allowed Characters: (R,L,F)]: ", vehicleIndex);
            instruction = scanner.nextLine();
            isInputValid = inputValidator.validateCommands(instruction);
            if (!isInputValid) {
                out.println("Invalid Input - Allowed Characters: R,L,F");
            }
        } while (!isInputValid);

        return instruction.toCharArray();
    }

    public int vehicleCountPrompt(Scanner scanner) {
        String vehicleCount;
        boolean isInputValid;
        do {
            PrintStream out = new PrintStream(System.out);
            out.print("Number of vehicles in simulation [Format: Integer larger Than 1]: ");
            vehicleCount = scanner.nextLine();
            isInputValid = inputValidator.validateVehicleCount(vehicleCount);
            if (!isInputValid) {
                out.println("Invalid Input - Number of vehicles should be more than one to detect collisions");
            }
        } while (!isInputValid);

        return Integer.parseInt(vehicleCount);
    }

    public String vehicleNamePrompt(Scanner scanner, int vehicleIndex) {
        String vehicleName;
        boolean isInputValid;
        do {
            PrintStream out = new PrintStream(System.out);
            out.printf("Enter Vehicle %s Name: ", vehicleIndex);
            vehicleName = scanner.nextLine();
            isInputValid = inputValidator.validateVehicleLabel(vehicleName);
            if (!isInputValid) {
                out.println("Invalid Input - Accepted Values: English Letters without space");
            }
        } while (!isInputValid);

        return vehicleName;
    }

}
