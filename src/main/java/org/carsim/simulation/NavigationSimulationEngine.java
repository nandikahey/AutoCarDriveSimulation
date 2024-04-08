package org.carsim.simulation;

import org.carsim.exceptions.UndefinedCommandException;
import org.carsim.model.Car;
import org.carsim.model.CarSimulation;
import org.carsim.model.SimulationGrid;
import org.carsim.simulation.command.Command;
import org.carsim.simulation.command.MoveForwardCommand;
import org.carsim.simulation.command.TurnLeftCommand;
import org.carsim.simulation.command.TurnRightCommand;
import org.carsim.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Navigation simulation engine that handles navigation instructions for a car on simulation grid.
 */
public class NavigationSimulationEngine implements SimulationEngine {

    private final Map<Character, Command> commands = new HashMap<>();

    /**
     * Simulates the navigation of a car on the given simulation grid.
     *
     * @param simulationGrid The simulation grid containing vehicle simulations.
     * @return A string representing the final status of the vehicle after simulation.
     */
    @Override
    public String simulate(SimulationGrid simulationGrid) {
        if (simulationGrid == null || simulationGrid.getVehicleSimulations() == null) {
            return null;
        }
        CommandExecutor commandExecutor = new CommandExecutor(simulationGrid);
        CarSimulation carSimulation = simulationGrid.getVehicleSimulations().get(0);
        for (char command : carSimulation.getInstructionArray()) {
            commandExecutor.executeCommand(carSimulation.getVehicle(), command);
        }
        return carSimulation.getCurrentStatus();
    }

    /**
     * Navigation Simulates for given command for given car.
     *
     * @param car            The car to navigate.
     * @param command        The navigation instruction (FRL).
     */
    void simulateNavigation(Car car, char command) {
        Command cmd = commands.get(command);
        if (cmd != null) {
            cmd.execute(car);
        } else {
            throw new UndefinedCommandException(Constants.COMMAND_NOT_SUPPORTED);
        }
    }

}
