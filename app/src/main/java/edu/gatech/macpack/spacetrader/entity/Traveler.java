package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.round;


/**
 * This class represents a Traveler as the player goes to different places on the map
 */
public class Traveler {

    private final List<SolarSystem> solarSystems = DatabaseInteractor.dbInteractor.game.getSolarSystems();

    private final SpaceShip ship;
    private int distance;
    private int fuelNeeded;
    private Planet newLocation;


    /**
     * This is a constructor that creates a traveler with the following attributes
     * @param ship the Spaceship the player is using
     * @param currentLocation the current location of where the player is located
     * @param newLocation the location where the player wants to travel to
     */
    public Traveler(SpaceShip ship, Planet currentLocation, Planet newLocation) {
        this.ship = ship;
        this.newLocation = newLocation;
        distance = calculateDistance(currentLocation.getParentLocation(),
                                        newLocation.getParentLocation());
        fuelNeeded = calculateFuelNeeded();
    }

    /**
     * This constructor is used to calculate the locations in range of the passed in ship
     * @param ship ship to find locations in the proximity of.
     */
    public Traveler(SpaceShip ship) { this.ship = ship; }

    private int calculateDistance(List<Integer> curCoordinate, List<Integer> newCoordinate) {

        double d = sqrt((abs(newCoordinate.get(0) - curCoordinate.get(0)))^2
                            + (abs(newCoordinate.get(1) - curCoordinate.get(1)))^2);
        @SuppressWarnings("UnnecessaryLocalVariable") int roundedDistance = (int) round(d);
        return roundedDistance;
    }

    private int calculateFuelNeeded() {
        double fuel = (double) distance / (double) ship.getMileage();
        //System.out.println("Req fuel: " + fuel);
        //int roundedFuel = (int) round(fuel);
        //System.out.println("rounded fuel: " + roundedFuel);
        return (int) round(fuel);
    }

    private int calculateFuelNeeded(int d) {
        double fuel = (double) d / (double) ship.getMileage();
        return (int) round(fuel);
    }

    /**
     * This method puts the traveler in the new location
     */
    public void travel() {
        System.out.println("Distance: " + distance);
        System.out.println("Fuel Needed: " + fuelNeeded);
        System.out.println("Before fuel: " + ship.getFuel());
        ship.setLocation(newLocation);
        ship.setFuel(ship.getFuel() - fuelNeeded);
        System.out.println("After fuel: " + ship.getFuel());
    }

    /**
     * This method checks for solar systems in range of the spaceship
     * @return a list of the solar systems in range
     */
    public List<SolarSystem> systemsInRange() {
        List<SolarSystem> inRange = new ArrayList<>();

        for(SolarSystem system : solarSystems) {
            int d = calculateDistance(ship.getLocation().getParentLocation(),
                                                system.getLocation());
            if(ship.getFuel() >= calculateFuelNeeded(d)) {
                inRange.add(system);
            }
        }
        return inRange;
    }

}
