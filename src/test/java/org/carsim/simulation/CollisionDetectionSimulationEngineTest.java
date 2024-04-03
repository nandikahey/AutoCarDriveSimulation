package org.carsim.simulation;

import org.carsim.model.Car;
import org.carsim.model.CarSimulation;
import org.carsim.model.Coordinate;
import org.carsim.model.SimulationGrid;
import org.carsim.util.Constants;
import org.carsim.util.enums.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CollisionDetectionSimulationEngineTest {

    private final int WIDTH = 10, HEIGHT = 10;

    private CollisionDetectionSimulationEngine collisionDetectionSimulationEngine;

    @BeforeEach
    void setUp() {
        collisionDetectionSimulationEngine = new CollisionDetectionSimulationEngine();
    }

    @Test
    void simulate_SimulationWhenGridIsNull_ReturnsNull() {
        String simulationResult = collisionDetectionSimulationEngine.simulate(null);
        assertNull(simulationResult);
    }

    @Test
    void simulate_SimulationValidGridVehicleSimulations_ReturnsCollisionString() {
        Coordinate coordinate = new Coordinate(1, 2);
        Car car = new Car("A", coordinate, Orientation.NORTH);
        String vehicleInstruction = "FFRFFFFRRL";
        CarSimulation carSimulation = new CarSimulation(car, vehicleInstruction.toCharArray());

        Coordinate coordinate2 = new Coordinate(7, 8);
        Car car2 = new Car("B", coordinate2, Orientation.WEST);
        String vehicleInstruction2 = "FFLFFFFFFF";
        CarSimulation carSimulation2 = new CarSimulation(car2, vehicleInstruction2.toCharArray());
        SimulationGrid simulationGrid = new SimulationGrid(Arrays.asList(carSimulation, carSimulation2), WIDTH, HEIGHT);

        String result = collisionDetectionSimulationEngine.simulate(simulationGrid);

        assertEquals("A B" + "\n" + 5 + " " + 4 + "\n" + 7 + "\n", result);
    }

    @Test
    void simulate_SimulationValidGridVehicleSimulations_ReturnsNoCollisionString() {
        Coordinate coordinate = new Coordinate(2, 2);
        Car car = new Car("A", coordinate, Orientation.NORTH);
        String vehicleInstruction = "FFRL";
        CarSimulation carSimulation = new CarSimulation(car, vehicleInstruction.toCharArray());

        Coordinate coordinate2 = new Coordinate(2, 8);
        Car car2 = new Car("B", coordinate2, Orientation.WEST);
        String vehicleInstruction2 = "FFFF";
        CarSimulation carSimulation2 = new CarSimulation(car2, vehicleInstruction2.toCharArray());

        SimulationGrid simulationGrid = new SimulationGrid(Arrays.asList(carSimulation, carSimulation2), WIDTH, HEIGHT);

        String result = collisionDetectionSimulationEngine.simulate(simulationGrid);

        assertEquals(Constants.NO_COLLISIONS, result);
    }


}