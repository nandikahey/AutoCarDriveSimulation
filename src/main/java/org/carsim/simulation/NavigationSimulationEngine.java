package org.carsim.simulation;

import org.carsim.exceptions.UndefinedCommandException;
import org.carsim.exceptions.UndefinedOrientationException;
import org.carsim.model.Car;
import org.carsim.model.CarSimulation;
import org.carsim.model.SimulationGrid;
import org.carsim.util.Constants;
import org.carsim.util.enums.Orientation;

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
        CarSimulation carSimulation = simulationGrid.getVehicleSimulations().get(0);
        for (char command : carSimulation.getInstructionArray()) {
            simulateNavigation(simulationGrid, carSimulation.getVehicle(), command);
        }
        return carSimulation.getCurrentStatus();
    }

    /**
     * Navigation Simulates for given command for given car.
     *
     * @param simulationGrid The simulation grid.
     * @param car            The car to navigate.
     * @param command        The navigation instruction (FRL).
     */
    void simulateNavigation(SimulationGrid simulationGrid, Car car, char command) {
        switch (command) {
            case 'F':
                if (simulationGrid.isWithInBounds(car.getCoordinate().getX(), car.getCoordinate().getY())) {
                    moveForward(car,simulationGrid.getWidth(), simulationGrid.getHeight());
                }
                break;
            case 'R':
                turnRight(car);
                break;
            case 'L':
                turnLeft(car);
                break;
            default:
                throw new UndefinedCommandException(Constants.COMMAND_NOT_SUPPORTED);
        }
    }

    /**
     * Moves a given car one step forward based on its current orientation.
     *
     * @param car The car to move.
     * @throws UndefinedOrientationException If the orientation of the car is undefined.
     */

    public void moveForward(Car car, int gridWidth, int gridHeight) {
        switch (car.getOrientation()) {
            case NORTH:
                car.getCoordinate().setY(Math.min(car.getCoordinate().getY() + 1, gridHeight-1));
                break;
            case EAST:
                car.getCoordinate().setX(Math.min(car.getCoordinate().getX() + 1, gridWidth-1));
                break;
            case SOUTH:
                car.getCoordinate().setY(Math.max(car.getCoordinate().getY() - 1, 0));
                break;
            case WEST:
                car.getCoordinate().setX(Math.max(car.getCoordinate().getX() - 1,0));
                break;
            default:
                throw new UndefinedOrientationException(Constants.UNDEFINED_ORIENTATION);
        }
    }

    /**
     * Rotates a car clockwise by changing its orientation.
     *
     * @param car The car to turn.
     * @throws UndefinedOrientationException If the orientation of the car is undefined.
     */
    public void turnRight(Car car) {
        switch (car.getOrientation()) {
            case NORTH:
                car.setOrientation(Orientation.EAST);
                break;
            case EAST:
                car.setOrientation(Orientation.SOUTH);
                break;
            case SOUTH:
                car.setOrientation(Orientation.WEST);
                break;
            case WEST:
                car.setOrientation(Orientation.NORTH);
                break;
            default:
                throw new UndefinedOrientationException(Constants.UNDEFINED_ORIENTATION);
        }
    }

    /**
     * Rotates a car anti-clockwise by changing its orientation.
     *
     * @param car The car to turn.
     * @throws UndefinedOrientationException If the orientation of the car is undefined.
     */
    public void turnLeft(Car car) throws UndefinedOrientationException {
        switch (car.getOrientation()) {
            case NORTH:
                car.setOrientation(Orientation.WEST);
                break;
            case EAST:
                car.setOrientation(Orientation.NORTH);
                break;
            case SOUTH:
                car.setOrientation(Orientation.EAST);
                break;
            case WEST:
                car.setOrientation(Orientation.SOUTH);
                break;
            default:
                throw new UndefinedOrientationException(Constants.UNDEFINED_ORIENTATION);
        }
    }
}
