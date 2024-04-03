package org.carsim.simulation;

import org.carsim.model.SimulationGrid;

public class SimulationContext {

    private SimulationEngine simulationEngine;

    public void setSimulationEngine(SimulationEngine simulationEngine) {
        this.simulationEngine = simulationEngine;
    }

    public String executeEngine(SimulationGrid simulationGrid) {
        return simulationEngine.simulate(simulationGrid);
    }


}
