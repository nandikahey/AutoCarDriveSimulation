package org.carsim.simulation;

import org.carsim.model.SimulationGrid;

/**
 * The SimulationContext class represents the context for running a simulation.
 * It holds SimulationEngine instance and provides a common method to execute the simulation.
 */
public class SimulationContext {

    private SimulationEngine simulationEngine;

    public void setSimulationEngine(SimulationEngine simulationEngine) {
        this.simulationEngine = simulationEngine;
    }

    public String executeEngine(SimulationGrid simulationGrid) {
        return simulationEngine.simulate(simulationGrid);
    }

}
