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
        if(cargo.containsValue(item))  {
            int currentQuantity = cargo.get(item.getGood()).getQuantity();
            cargo.get(item.getGood()).setQuantity(currentQuantity + item.getQuantity());
        } else {
            CargoItem cargoItem = (CargoItem) item;
            cargo.put(item.getGood(), cargoItem);
        }
        // TODO: decide how weight system will be for each item, 5 current arbitrary value
        weight += 5 * item.getQuantity();
    }

    public void removeFromCargo(MarketItem item) {
        CargoItem cargoItem = (CargoItem) item;
        TradeGood good = cargoItem.getGood();
        int currentAmount = cargo.get(good).getQuantity();

        if(cargoItem.getQuantity() == currentAmount) {
            cargo.remove(good);
        } else {
            cargo.get(good).setQuantity(currentAmount - cargoItem.getQuantity());
        }

        weight -= 5 * cargoItem.getQuantity();
    }



}
