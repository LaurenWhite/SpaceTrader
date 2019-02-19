package edu.gatech.macpack.spacetrader.entity;

public class SpaceShip {

    private SpaceShipType shipType;

    //constructor
    public SpaceShip(SpaceShipType shipType){
        this.shipType = shipType;
    }

    public SpaceShipType getShipType() {return shipType;}

    public void setShipType(SpaceShipType shipType) {this.shipType = shipType;}

}
