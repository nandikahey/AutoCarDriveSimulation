package org.carsim.model;

import java.util.List;

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
        return x < width - 1 &&
                x >= 0 &&
                y < height - 1 &&
                y >= 0;
    }
}
