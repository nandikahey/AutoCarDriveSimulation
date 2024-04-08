package org.carsim.simulation.command;

import org.carsim.exceptions.UndefinedOrientationException;
import org.carsim.model.Car;
import org.carsim.model.SimulationGrid;
import org.carsim.util.Constants;

/**
 * This represents the move forward command.
 * This command moves the car one step forward to facing direction.
 */
public class MoveForwardCommand implements Command {

    private final SimulationGrid simulationGrid;
    public static final Character COMMAND_CHAR = 'F';
    public MoveForwardCommand(SimulationGrid simulationGrid) {
        this.simulationGrid = simulationGrid;
    }

    @Override
    public void execute(Car car) {
        if (simulationGrid.isWithInBounds(car.getCoordinate().getX(), car.getCoordinate().getY())) {
            moveForward(car, simulationGrid.getWidth(), simulationGrid.getHeight());
        }
    }

    /**
     * Moves a given car one step forward based on its current orientation.
     *
     * @param car The car to move.
     * @throws UndefinedOrientationException If the orientation of the car is undefined.
     */

    private void moveForward(Car car, int gridWidth, int gridHeight) {
        switch (car.getOrientation()) {
            case NORTH:
                car.getCoordinate().setY(Math.min(car.getCoordinate().getY() + 1, gridHeight - 1));
                break;
            case EAST:
                car.getCoordinate().setX(Math.min(car.getCoordinate().getX() + 1, gridWidth - 1));
                break;
            case SOUTH:
                car.getCoordinate().setY(Math.max(car.getCoordinate().getY() - 1, 0));
                break;
            case WEST:
                car.getCoordinate().setX(Math.max(car.getCoordinate().getX() - 1, 0));
                break;
            default:
                throw new UndefinedOrientationException(Constants.UNDEFINED_ORIENTATION);
        }
    }

}
