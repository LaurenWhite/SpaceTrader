package edu.gatech.macpack.spacetrader.entity;

import java.util.HashMap;
import java.util.Map;

public class SpaceShip {

    private SpaceShipType shipType;
    private Map<TradeGood, CargoItem> cargo;
    private int weight;

    // CONSTRUCTOR
    public SpaceShip(SpaceShipType shipType){
        this.shipType = shipType;
        cargo = new HashMap<>();
        weight = 0;
    }

    // GETTERS
    public Map<TradeGood, CargoItem> getCargo() { return cargo; }

    public SpaceShipType getShipType() { return shipType; }

    public int getWeight() { return weight; }


    // SETTERS
    public void setShipType(SpaceShipType shipType) { this.shipType = shipType; }



    // FUNCTIONALITY
    public boolean sufficientSpace(int cargoWeight) {
        return (weight + cargoWeight) <= shipType.weightCapacity;
    }

    public void addToCargo(MarketItem item) {

        TradeGood good = item.getGood();

        // If good already in cargo add to quantity, if not add new entry
        if(cargo.containsKey(good))  {
            int currentQuantity = cargo.get(good).getQuantity();
            cargo.get(good).setQuantity(currentQuantity + item.getQuantity());
        } else {
            CargoItem cargoItem = new CargoItem(item.getGood(), item.getQuantity(), item.getPrice());
            cargo.put(good, cargoItem);
        }
        // TODO: decide how weight system will be for each item, 1 for each item
        weight += item.getQuantity();
    }

    public void removeFromCargo(MarketItem item) {

        TradeGood good = item.getGood();

        int currentAmount = cargo.get(good).getQuantity();

        // If removing all of a cargo item, remove the whole cargo item
        // If not, subtract from the current quantity
        if(item.getQuantity() == currentAmount) {
            cargo.remove(good);
        } else {
            cargo.get(good).setQuantity(currentAmount - item.getQuantity());
        }

        weight -= item.getQuantity();
    }



}
