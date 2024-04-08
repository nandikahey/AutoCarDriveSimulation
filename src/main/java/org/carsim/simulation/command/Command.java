package org.carsim.simulation.command;

import org.carsim.model.Car;

/**
 * This interface represents a command to be executed on a Car.
 */
public interface Command {

    void execute(Car car);
}
