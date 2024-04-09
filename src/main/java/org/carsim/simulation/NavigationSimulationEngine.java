package org.carsim.simulation;

import org.carsim.model.CarSimulation;
import org.carsim.model.SimulationGrid;

/**
 * Navigation simulation engine that handles navigation instructions for a car on simulation grid.
 */
public class NavigationSimulationEngine implements SimulationEngine {

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

}
