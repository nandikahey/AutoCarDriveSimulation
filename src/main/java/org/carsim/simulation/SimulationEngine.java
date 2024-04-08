package org.carsim.simulation;

import org.carsim.model.SimulationGrid;

/**
 * Representing a simulation engine for controlling movements of car in simulation grid.
 */
public interface SimulationEngine {

    String simulate(SimulationGrid simulationGrid);

}
