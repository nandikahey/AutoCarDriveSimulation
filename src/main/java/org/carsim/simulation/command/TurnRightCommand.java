package org.carsim.simulation.command;

import org.carsim.exceptions.UndefinedOrientationException;
import org.carsim.model.Car;
import org.carsim.util.Constants;
import org.carsim.util.enums.Orientation;

/**
 * This represents the right turn command.
 * Turns the car in 90 degrees in clockwise rotation in the same position
 */
public class TurnRightCommand implements Command {
    public static final Character COMMAND_CHAR = 'R';

    @Override
    public void execute(Car car) {
        turnRight(car);
    }

    /**
     * Rotates a car clockwise by changing its orientation.
     *
     * @param car The car to turn.
     * @throws UndefinedOrientationException If the orientation of the car is undefined.
     */
    private void turnRight(Car car) {
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
}
