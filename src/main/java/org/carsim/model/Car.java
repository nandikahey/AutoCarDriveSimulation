package org.carsim.model;

import org.carsim.util.enums.Orientation;

import java.util.Objects;

/**
 * The Car class represents a car in the simulation.
 */
public class Car {

    private final String carLabel;
    private final Coordinate coordinate;
    private Orientation orientation;


    public Car(String carLabel, Coordinate coordinate, Orientation orientation) {
        this.carLabel = carLabel;
        this.coordinate = coordinate;
        this.orientation = orientation;
    }

    protected String getStatus() {
        return coordinate.getX() + " " + coordinate.getY() + " " + orientation.toString();
    }

    public String getCarLabel() {
        return carLabel;
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }


    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Indicates whether two cars are equal by comparing the coordinates.
     *
     * @param o Car object to compare
     * @return true if two cars are at the same coordinate.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        if (car.getCoordinate() == null) return false;
        return coordinate.getX() == car.getCoordinate().getX() && coordinate.getY() == car.getCoordinate().getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(carLabel);
    }
}
