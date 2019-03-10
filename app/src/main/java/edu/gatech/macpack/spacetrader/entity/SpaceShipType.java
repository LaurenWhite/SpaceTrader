package edu.gatech.macpack.spacetrader.entity;

public enum SpaceShipType {
    FLEA("Flea"),
    GNAT("Gnat", 20),
    FIREFLY("Firefly"),
    MOSQUITO("Mosquito"),
    BUMBLEBEE("Bumblebee"),
    BEETLE("Beetle"),
    HORNET("Hornet"),
    GRASSHOPPER("Grasshopper"),
    TERMITE("Termite"),
    WASP("Wasp");

    public String shipType;
    public int cargoCapacity;

    // enum constructor - cannot be public or protected
    SpaceShipType(String shipType) { this.shipType = shipType; }

    SpaceShipType(String shipType, int cargoCapacity) {
        this.shipType = shipType;
        this.cargoCapacity = cargoCapacity;
    }
}
