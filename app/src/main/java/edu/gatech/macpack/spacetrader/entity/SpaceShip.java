package edu.gatech.macpack.spacetrader.entity;

import java.util.HashMap;
import java.util.Map;

public class SpaceShip {

    private SpaceShipType shipType;
    private Map<String, CargoItem> cargo;
    private int weight;
    private Planet location;
    private int fuel;

    // CONSTRUCTOR
    public SpaceShip(SpaceShipType shipType){
        this.shipType = shipType;
        cargo = new HashMap<>();
        weight = 0;
        location = DatabaseInteractor.dbInteractor.game.getStartingLocation();
        fuel = shipType.fuelCapacity;
        // add default location here to prevent crash when selling to market before traveling?
    }

    public SpaceShip(SpaceShipType shipType, Map<String, CargoItem> cargo, int weight, Planet location, int fuel){
        this.shipType = shipType;
        this.cargo = cargo;
        this.weight = weight;
        this.location = location;
        this.fuel = shipType.fuelCapacity;
    }

    // GETTERS
    public Map<String, CargoItem> getCargo() { return cargo; }

    public SpaceShipType getShipType() { return shipType; }

    public int getWeight() { return weight; }

    public Planet getLocation() { return location; }

    public int getMileage() { return shipType.mileage; }

    public int getFuel() { return fuel; }

    // SETTERS
    public void setShipType(SpaceShipType shipType) { this.shipType = shipType; }

    public void setLocation(Planet location) { this.location = location; }

    public void setFuel(int fuel) { this.fuel = fuel; }

    // FUNCTIONALITY
    public boolean sufficientSpace(int cargoWeight) {
        return (weight + cargoWeight) <= shipType.weightCapacity;
    }

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
