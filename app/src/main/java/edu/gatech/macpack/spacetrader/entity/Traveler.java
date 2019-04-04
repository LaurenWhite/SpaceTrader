package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.round;



public class Traveler {

    private List<SolarSystem> solarSystems = DatabaseInteractor.dbInteractor.game.getSolarSystems();

    private SpaceShip ship;
    private int distance;
    private int fuelNeeded;
    private Planet newLocation;
    private Planet currentLocation;


    public Traveler(SpaceShip ship, Planet currentLocation, Planet newLocation) {
        this.ship = ship;
        this.currentLocation = currentLocation;
        this.newLocation = newLocation;
        distance = calculateDistance(currentLocation.getParentLocation(),
                                        newLocation.getParentLocation());
        fuelNeeded = calculateFuelNeeded();
    }

    public Traveler(SpaceShip ship) { this.ship = ship; }

    private int calculateDistance(List<Integer> curCoordinate, List<Integer> newCoordinate) {
//        System.out.println("coord1: " + curCoordinate[0] + ", " + curCoordinate[1]);
//        System.out.println("coord2: " + newCoordinate[0] + ", " + newCoordinate[1]);

        double d = sqrt((abs(newCoordinate.get(0) - curCoordinate.get(0)))^2
                            + (abs(newCoordinate.get(1) - curCoordinate.get(1)))^2);
        int roundedDistance = (int) round(d);
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

    public void travel() {
        System.out.println("Distance: " + distance);
        System.out.println("Fuel Needed: " + fuelNeeded);
        System.out.println("Before fuel: " + ship.getFuel());
        ship.setLocation(newLocation);
        ship.setFuel(ship.getFuel() - fuelNeeded);
        System.out.println("After fuel: " + ship.getFuel());
    }

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
