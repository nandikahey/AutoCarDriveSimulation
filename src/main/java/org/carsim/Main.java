package org.carsim;

import org.carsim.model.SimulationGrid;
import org.carsim.simulation.CollisionDetectionSimulationEngine;
import org.carsim.simulation.NavigationSimulationEngine;
import org.carsim.simulation.SimulationContext;
import org.carsim.util.Constants;
import org.carsim.util.InputPrompter;
import org.carsim.util.InputValidator;
import org.carsim.util.SimulationGridCreator;

import java.util.Scanner;
import java.util.logging.Logger;

import static org.carsim.util.ExceptionHandler.handleException;


/**
 * <p>
 * Application is for Auto Driving Car Simulation.
 * It consists of two types of simulation engines.
 * Navigation Sim Engine (Type 1) can simulate movements of a single vehicle on a coordinates grid.
 * Collision Detection Sim Engine (Type 2) is capable of simulating navigation of multiple vehicles and detecting collision.
 * </p>
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vehicleCount = 1;
        InputPrompter inputPrompter = new InputPrompter(new InputValidator());
        int simulationType = inputPrompter.simulationTypePrompt(scanner);
        SimulationContext simulationContext = new SimulationContext();
        if (simulationType == Constants.NAVIGATION_SIMULATION) {
            simulationContext.setSimulationEngine(new NavigationSimulationEngine());
        } else if (simulationType == Constants.COLLISION_DETECTION_SIMULATION) {
            vehicleCount = inputPrompter.vehicleCountPrompt(scanner);
            simulationContext.setSimulationEngine(new CollisionDetectionSimulationEngine());
        }
        SimulationGrid simulationGrid = SimulationGridCreator.createCoordinateField(scanner, vehicleCount);
        try {
            System.out.println("\n" + simulationContext.executeEngine(simulationGrid));
        } catch (Exception e) {
            handleException(e);

        }
        scanner.close();
    }
}
