package org.carsim.simulation;

import org.carsim.model.SimulationGrid;
import org.carsim.simulation.base.Movable;
import org.carsim.simulation.base.Turnable;

/**
 * Representing a simulation engine for controlling movements of car in simulation grid.
 */
public interface SimulationEngine extends Movable, Turnable {

    String simulate(SimulationGrid simulationGrid);

}
