package org.carsim.util;

import org.carsim.model.Car;
import org.carsim.model.CarSimulation;
import org.carsim.model.Coordinate;
import org.carsim.model.SimulationGrid;
import org.carsim.util.enums.Orientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulationGridCreator {

    private SimulationGridCreator() {
    }

    public static SimulationGrid createCoordinateField(Scanner scanner, int vehicleCount) {
        InputValidator inputValidator = new InputValidator();
        InputPrompter inputPrompter = new InputPrompter(inputValidator);
        int[] gridSize = inputPrompter.gridSizePrompt(scanner);

        List<CarSimulation> carSimulations = new ArrayList<>();
        for (int i = 0; i < vehicleCount; i++) {
            String vehicleName = null;
            if (vehicleCount > 1) {
                vehicleName = inputPrompter.vehicleNamePrompt(scanner, (i + 1));
            }
            String[] vehiclePositionOrientation = inputPrompter.vehicleLocationPrompt(scanner, gridSize, (i + 1));
            Coordinate vehiclePosition = new Coordinate(Integer.parseInt(vehiclePositionOrientation[0]), Integer.parseInt(vehiclePositionOrientation[1]));
            char[] vehicleInstruction = inputPrompter.instructionPrompt(scanner, (i + 1));
            Car car = new Car(vehicleName, vehiclePosition, Orientation.valueOfEnum(vehiclePositionOrientation[2]));
            CarSimulation carSimulation = new CarSimulation(car, vehicleInstruction);
            carSimulations.add(carSimulation);
        }
        return new SimulationGrid(carSimulations, gridSize[0], gridSize[1]);
    }
}
