package edu.gatech.macpack.spacetrader.entity;

import android.widget.Space;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.macpack.spacetrader.views.MarketActivity;

public class Traveler {

    private SpaceShip ship;
    private int distance;
    private int fuelNeeded;
    private Planet newLocation;
    private Planet currentLocation;
    private List<SolarSystem> solarSystems;

    public Traveler(SpaceShip ship, Planet currentLocation, Planet newLocation) {
        this.ship = ship;
        this.currentLocation = currentLocation;
        this.newLocation = newLocation;

        fuelNeeded = calculateFuelNeeded();
    }

    public Traveler(SpaceShip ship) {
        this.ship = ship;
        solarSystems = Game.getGameInstance().getSolarSystems();
    }

    private int calculateDistance(int[] curCoordinate, int[] newCoordinate) {
        double distance = Math.sqrt((newCoordinate[0] - curCoordinate[0])^2 + (newCoordinate[1] - curCoordinate[1])^2);
        int roundedDistance = (int) Math.round(distance);
        return roundedDistance;
    }

    private int calculateFuelNeeded() {
        return ship.getMileage() * distance;
    }

    private int calculateFuelNeeded(int distance) {
        return ship.getMileage() * distance;
    }

    public void travel() {
        ship.setLocation(newLocation);
        ship.setFuel(ship.getFuel() - fuelNeeded);
    }

    public List<SolarSystem> systemsInRange() {
        List<SolarSystem> inRange = new ArrayList<>();

        for(SolarSystem system : solarSystems) {
            int distance = calculateDistance(ship.getLocation().getParentSystem().getLocation(),
                                                system.getLocation());
            if(ship.getFuel() >= calculateFuelNeeded(distance)) {
                inRange.add(system);
            }
        }
        return inRange;
    }

}
