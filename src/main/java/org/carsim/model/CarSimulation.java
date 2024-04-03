package org.carsim.model;

/**
 * Represents a simulated car in the simulation engine.
 */
public class CarSimulation {
    private final Car car;
    private final char[] instructionArray;

    public CarSimulation(Car car, char[] instructionArray) {
        this.car = car;
        this.instructionArray = instructionArray;
    }

    public Car getVehicle() {
        return car;
    }

    public char[] getInstructionArray() {
        return instructionArray;
    }

    public String getCurrentStatus() {
        return car.getStatus();
    }
}
