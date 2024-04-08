package org.carsim.simulation;

import org.carsim.model.CarSimulation;
import org.carsim.model.Collision;
import org.carsim.model.Coordinate;
import org.carsim.model.SimulationGrid;
import org.carsim.util.Constants;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Navigation and Collision simulation engine that handles
 * navigation instructions for multiple car on simulation grid.
 */
public class CollisionDetectionSimulationEngine extends NavigationSimulationEngine {

    private final Map<String, Coordinate> vehicleLocationMap = new HashMap<>();
    private final List<Collision> collisionList = new ArrayList<>();
    private int time = 0;
    private int maxSimulationTime;

    /**
     * Simulates navigation and collision detection for vehicles on the simulation grid.<br><br>
     * <p>
     * {@code time} - acts as index to instruction array.<br><br>
     * {@code maxSimulationTime} - If the cars have variable length instructions, this will represent the total count of
     * simulation ticks<br><br>
     * {@code vehicleLocationMap} - Holds the global state of the simulation, where key is car label and value is location<br><br>
     * <p>
     * The simulation is run till the {@code maxSimulationTime} <br>
     * In each iteration it navigates the cars and check for collisions. Once a collision is detected simulation will
     * end and return collision information
     *
     * @param simulationGrid Simulation grid containing vehicle simulations.
     * @return A string result of the simulation, collision details or "No collisions".
     */
    @Override
    public String simulate(SimulationGrid simulationGrid) {
        if (simulationGrid == null || simulationGrid.getVehicleSimulations() == null) {
            return null;
        }
        OptionalInt optionalInt = simulationGrid.getVehicleSimulations().stream().mapToInt(e -> e.getInstructionArray().length).max();
        if (optionalInt.isPresent()) {
            maxSimulationTime = optionalInt.getAsInt();
        }
        // Set initial positions
        for (CarSimulation carSimulation : simulationGrid.getVehicleSimulations()) {
            vehicleLocationMap.put(carSimulation.getVehicle().getCarLabel(), carSimulation.getVehicle().getCoordinate());
        }
        // Check for initial collisions
        collisionList.addAll(detectCollisions());
        while (time < maxSimulationTime && collisionList.isEmpty()) {
            for (CarSimulation carSimulation : simulationGrid.getVehicleSimulations()) {
                if (time < carSimulation.getInstructionArray().length) {
                    simulateNavigation(simulationGrid, carSimulation.getVehicle(), carSimulation.getInstructionArray()[time]);
                    vehicleLocationMap.put(carSimulation.getVehicle().getCarLabel(), carSimulation.getVehicle().getCoordinate());
                }
            }
            time += 1;
            collisionList.addAll(detectCollisions());
            if (!collisionList.isEmpty()) {
                break;
            }
        }
        if (!collisionList.isEmpty()) {
            return collisionList.get(0).toString();
        } else {
            return Constants.NO_COLLISIONS;
        }
    }


    /**
     * Detects collisions among the vehicles.
     * Uses the {@code vehicleLocationMap} to find cars that have same coordinates (values)
     * To identify collisions create a reverse map containing coordinates -> car labels list
     *
     * @return A list of collision instances representing the detected collisions.
     */
    public List<Collision> detectCollisions() {
        List<Collision> collisions = new ArrayList<>();
        Map<Coordinate, List<String>> vehiclesGroupedByLocation = vehicleLocationMap.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        vehiclesGroupedByLocation.forEach((cord, vehicleNames) -> {
            if (vehicleNames.size() > 1) {
                collisions.add(new Collision(vehicleNames.toArray(new String[0]), cord, time));
            }
        });
        return collisions;
    }
}
