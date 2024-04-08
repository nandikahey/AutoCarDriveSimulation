package org.carsim.model;

import java.util.List;

/**
 * Represents the grid on which the simulation takes place.
 * It contains information such as the list of car simulations, width, and height of the grid.
 */
public class SimulationGrid {

    private final List<CarSimulation> carSimulations;
    private final int width;
    private final int height;

    public SimulationGrid(List<CarSimulation> carSimulations, int width, int height) {
        this.carSimulations = carSimulations;
        this.width = width;
        this.height = height;
    }

    public List<CarSimulation> getVehicleSimulations() {
        return carSimulations;
    }

    public boolean isWithInBounds(int x, int y) {
        return x < width &&
                x >= 0 &&
                y < height &&
                y >= 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
