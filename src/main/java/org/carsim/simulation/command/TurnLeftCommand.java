package org.carsim.simulation.command;

import org.carsim.exceptions.UndefinedOrientationException;
import org.carsim.model.Car;
import org.carsim.util.Constants;
import org.carsim.util.enums.Orientation;

/**
 * This represents the left turn command.
 * Turns the car in 90 degrees in anti-clockwise rotation in the same position
 */
public class TurnLeftCommand implements Command {

    public static final Character COMMAND_CHAR = 'L';

    @Override
    public void execute(Car car) {
        turnLeft(car);
    }

    /**
     * Rotates a car anti-clockwise by changing its orientation.
     *
     * @param car The car to turn.
     * @throws UndefinedOrientationException If the orientation of the car is undefined.
     */
    private void turnLeft(Car car) throws UndefinedOrientationException {
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
