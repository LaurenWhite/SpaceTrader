package edu.gatech.macpack.spacetrader.entity;

public enum SpaceShipType {
    FLEA("Flea"),
    GNAT("Gnat", 100),
    FIREFLY("Firefly"),
    MOSQUITO("Mosquito"),
    BUMBLEBEE("Bumblebee"),
    BEETLE("Beetle"),
    HORNET("Hornet"),
    GRASSHOPPER("Grasshopper"),
    TERMITE("Termite"),
    WASP("Wasp");

    public String shipType;
    public int weightCapacity;

    // enum constructor - cannot be public or protected
    SpaceShipType(String shipType) { this.shipType = shipType; }

    SpaceShipType(String shipType, int weightCapacity) {
        this.shipType = shipType;
        this.weightCapacity = weightCapacity;
    }
}
