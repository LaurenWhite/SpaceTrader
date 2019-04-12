package edu.gatech.macpack.spacetrader.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a class that represents the player's Spaceship
 */
public class SpaceShip {

    private SpaceShipType shipType;
    private final Map<String, CargoItem> cargo;
    private int weight;
    private Planet location;
    private int fuel;

    // CONSTRUCTOR

    /**
     * This isi a constructor that creates a spaceship of the given type for the player
     * @param shipType spaceship type to be created
     */
    public SpaceShip(SpaceShipType shipType, String name){
        this.shipType = shipType;
        cargo = new HashMap<>();
        weight = 0;
        //location = DatabaseInteractor.dbInteractor.game.getStartingLocation();
        fuel = shipType.fuelCapacity;
        // add default location here to prevent crash when selling to market before traveling?
    }

    public SpaceShip(SpaceShipType shipType){
        this.shipType = shipType;
        cargo = new HashMap<>();
        weight = 0;
        location = DatabaseInteractor.dbInteractor.game.getStartingLocation();
        fuel = shipType.fuelCapacity;
        // add default location here to prevent crash when selling to market before traveling?
    }
    /**
     * This is a spaceship constructor that creates a spaceship with the following attributes
     * @param shipType the type of spaceship
     * @param cargo the cargo on the ship
     * @param weight the weight the ship can hold
     * @param location the location of the ship
     * @param fuel the amount of fuel in the ship
     */
    public SpaceShip(SpaceShipType shipType, Map<String, CargoItem> cargo, int weight, Planet location, int fuel){
        this.shipType = shipType;
        this.cargo = cargo;
        this.weight = weight;
        this.location = location;
        this.fuel = shipType.fuelCapacity;
    }

    // GETTERS

    /**
     * This is a getter method that returns a map of the cargo items in the spaceship
     * @return map of the cargo items
     */
    public Map<String, CargoItem> getCargo() { return cargo; }

    /**
     * This is a getter method that returns the type of spaceship
     * @return shipType -> type of spaceship
     */
    public SpaceShipType getShipType() { return shipType; }

    /**
     * This is a getter method that returns the weight of the ship
     * @return the weight of the ship
     */
    public int getWeight() { return weight; }

    /**
     * This is a getter method that returns the location of the spaceship
     * @return the planet on which it is located
     */
    public Planet getLocation() { return location; }

    /**
     * This is a getter method that returns the mileage of the ship
     * @return ship mileage
     */
    public int getMileage() { return shipType.mileage; }

    /**
     * This is a getter method that returns the amount of fuel in the spaceship
     * @return the fuel left in the ship
     */
    public int getFuel() { return fuel; }

    // SETTERS

    /**
     * This is a setter method that changes/sets the ship type
     * @param shipType type of ship to change to
     */
    public void setShipType(SpaceShipType shipType) { this.shipType = shipType; }

    /**
     * This is a setter method that changes/sets the location of the ship
     * @param location the planet to send the ship to
     */
    public void setLocation(Planet location) { this.location = location; }

    /**
     * This is a setter method that sets/changes the amount of fuel in the ship
     * @param fuel the amount of fuel to go in the ship
     */
    public void setFuel(int fuel) { this.fuel = fuel; }

    /**
     * This is a setter that sets the cargo field
     * For testing onluy
     *
     * @param cargo new cargo
     */
    public void setCargo(Map<String, CargoItem> cargo) { this.cargo = cargo; }

    // FUNCTIONALITY

    /**
     * This is a method that returns whether or not there is enough space in the ship
     * @param cargoWeight weight to be checked against the max weight of the ship
     * @return true if there is space, false if not
     */
    public boolean sufficientSpace(int cargoWeight) {
        return (weight + cargoWeight) <= shipType.weightCapacity;
    }

    /**
     * This is a method that adds a given item to the cargo of the ship
     * @param item the item to be added to the cargo
     */
    public void addToCargo(MarketItem item) {

        TradeGood good = item.getGood();

        // If good already in cargo add to quantity, if not add new entry
        if(cargo.containsKey(good.name()))  {
            int currentQuantity = cargo.get(good.name()).getQuantity();
            cargo.get(good.name()).setQuantity(currentQuantity + item.getQuantity());
        } else {
            CargoItem cargoItem = new
                    CargoItem(item.getGood(), item.getQuantity(), item.getPrice());
            cargo.put(good.name(), cargoItem);
        }
        weight += item.getQuantity();
    }

    /**
     * This is a method that removes a given item from the cargo
     * @param item item to be removed from cargo
     */
    public void removeFromCargo(MarketItem item) {

        TradeGood good = item.getGood();

        int currentAmount = cargo.get(good.name()).getQuantity();

        // If removing all of a cargo item, remove the whole cargo item
        // If not, subtract from the current quantity
        if(item.getQuantity() == currentAmount) {
            cargo.remove(good.name());
        } else {
            cargo.get(good.name()).setQuantity(currentAmount - item.getQuantity());
        }

        weight -= item.getQuantity();
    }



}
