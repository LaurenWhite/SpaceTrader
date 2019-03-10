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

    public SpaceShipType getShipType() {return shipType;}

    public void setShipType(SpaceShipType shipType) { this.shipType = shipType; }



    // FUNCTIONALITY
    public boolean sufficientSpace(int cargoWeight) {
        return (weight + cargoWeight) <= shipType.weightCapacity;
    }

}
