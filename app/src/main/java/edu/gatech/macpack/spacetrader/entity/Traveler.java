package edu.gatech.macpack.spacetrader.entity;

import android.widget.Space;
import android.widget.Toast;

import edu.gatech.macpack.spacetrader.views.MarketActivity;

public class Traveler {

    private SpaceShip ship;
    private int distance;
    private int fuelNeeded;
    private Planet newLocation;
    private Planet currentLocation;

    public Traveler(SpaceShip ship, Planet currentLocation, Planet newLocation) {
        this.currentLocation = currentLocation;
        this.newLocation = newLocation;
        distance = calculateDistance(currentLocation.getLocation(), newLocation.getLocation());
        fuelNeeded = calculateFuelNeeded();
    }

    private int calculateDistance(int[] curCoordinate, int[] newCoordinate) {
        double distance = Math.sqrt((newCoordinate[0] - curCoordinate[0])^2 + (newCoordinate[1] - curCoordinate[1])^2);
        int roundedDistance = (int) Math.round(distance);
        return roundedDistance;
    }

    private int calculateFuelNeeded() {
        return ship.getMileage() * distance;
    }

    public void travel() throws Exception {

        if(ship.getFuel() < fuelNeeded) {
            throw new Exception("Ship doesn't have enough fuel");
        }

        ship.setLocation(newLocation);
        ship.setFuel(ship.getFuel() - fuelNeeded);
    }

}
