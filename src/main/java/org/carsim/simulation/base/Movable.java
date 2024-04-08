package org.carsim.simulation.base;

import org.carsim.model.Car;

public interface Movable {
    void moveForward(Car car, int gridWidth, int gridHeight);
}
