package org.carsim.simulation;

import org.carsim.exceptions.UndefinedCommandException;
import org.carsim.model.Car;
import org.carsim.model.CarSimulation;
import org.carsim.model.Coordinate;
import org.carsim.model.SimulationGrid;
import org.carsim.util.Constants;
import org.carsim.util.enums.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NavigationSimulationEngineTest {

    private final int WIDTH = 10, HEIGHT = 10;
    private NavigationSimulationEngine navigationSimulationEngine;

    @BeforeEach
    void setUp() {
        navigationSimulationEngine = new NavigationSimulationEngine();
    }

    @Test
    void simulate_SimulationWhenGridIsNull_ReturnsNull() {
        String simulationResult = navigationSimulationEngine.simulate(null);
        assertNull(simulationResult);
    }

    @Test
    void simulate_SimulationGridWithNullVehicleSimulations_ReturnsNull() {
        SimulationGrid simulationGrid = new SimulationGrid(null, WIDTH, HEIGHT);
        String simulationResult = navigationSimulationEngine.simulate(simulationGrid);
        assertNull(simulationResult);
    }

    @Test
    void simulate_SimulationValidGridVehicleSimulations_ReturnsStatusString() {
        Coordinate coordinate = new Coordinate(1, 2);
        Car car = new Car("A", coordinate, Orientation.NORTH);
        String vehicleInstruction = "FFRFFFRRLF";
        CarSimulation carSimulation = new CarSimulation(car, vehicleInstruction.toCharArray());
        SimulationGrid simulationGrid = new SimulationGrid(Arrays.asList(carSimulation), WIDTH, HEIGHT);

        String simulationResult = navigationSimulationEngine.simulate(simulationGrid);

        assertEquals("4 3 S", simulationResult);
    }

    @Test
    void simulate_SimulationOffLimitGridVehicleSimulations_ReturnsStatusString() {
        Coordinate coordinate = new Coordinate(1, 2);
        Car car = new Car("A", coordinate, Orientation.NORTH);
        String vehicleInstruction = "FFFFFFRRRRLLLLFFFFF";
        CarSimulation carSimulation = new CarSimulation(car, vehicleInstruction.toCharArray());
        SimulationGrid simulationGrid = new SimulationGrid(Arrays.asList(carSimulation), WIDTH, HEIGHT);

        String simulationResult = navigationSimulationEngine.simulate(simulationGrid);

        assertEquals("1 9 N", simulationResult);
    }

    @Test()
    void simulate_SimulationWithUndefinedInstruction_TrowsException() {
        Coordinate coordinate = new Coordinate(1, 2);
        Car car = new Car("A", coordinate, Orientation.NORTH);
        String vehicleInstruction = "FXFF";
        CarSimulation carSimulation = new CarSimulation(car, vehicleInstruction.toCharArray());
        SimulationGrid simulationGrid = new SimulationGrid(Arrays.asList(carSimulation), WIDTH, HEIGHT);

        UndefinedCommandException thrown = assertThrows(UndefinedCommandException.class, () -> navigationSimulationEngine.simulate(simulationGrid));
        assertTrue(thrown.getMessage().contains(Constants.COMMAND_NOT_SUPPORTED));
    }


}