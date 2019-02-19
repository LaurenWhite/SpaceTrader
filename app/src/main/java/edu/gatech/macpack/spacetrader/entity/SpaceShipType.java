package edu.gatech.macpack.spacetrader.entity;

public enum SpaceShipType {
    FLEA("Flea"),
    GNAT("Gnat"),
    FIREFLY("Firefly"),
    MOSQUITO("Mosquito"),
    BUMBLEBEE("Bumblebee"),
    BEETLE("Beetle"),
    HORNET("Hornet"),
    GRASSHOPPER("Grasshopper"),
    TERMITE("Termite"),
    WASP("Wasp");

    public String shipType;

    // enum constructor - cannot be public or protected
    SpaceShipType(String shipType) { this.shipType = shipType; }
}
