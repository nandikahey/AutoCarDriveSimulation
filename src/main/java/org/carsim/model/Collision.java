package org.carsim.model;

/**
 * Represent a collision in the system.
 * Hold collided vehicle names, collision location and command execution step number when collision occurred.
 */
public class Collision {
    private final String[] vehicleNames;
    private final Coordinate coordinate;
    private final int time;

    public Collision(String[] vehicleNames, Coordinate coordinate, int time) {
        this.vehicleNames = vehicleNames;
        this.coordinate = coordinate;
        this.time = time;
    }

    /**
     * @return A string formatted according to the expected output of a collision event
     */
    @Override
    public String toString() {
        return String.join(" ", vehicleNames) + "\n" + coordinate.getX() + " " + coordinate.getY() + "\n" + time;
    }
}
