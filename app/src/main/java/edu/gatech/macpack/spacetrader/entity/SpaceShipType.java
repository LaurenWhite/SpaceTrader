package edu.gatech.macpack.spacetrader.entity;

/**
 * This is an enumeration for the type of spaceships
 */
@SuppressWarnings("SameParameterValue")
public enum SpaceShipType {
    FLEA("Flea"),
    GNAT("Gnat", 100, 2, 20),
    FIREFLY("Firefly"),
    MOSQUITO("Mosquito"),
    BUMBLEBEE("Bumblebee"),
    BEETLE("Beetle"),
    HORNET("Hornet"),
    GRASSHOPPER("Grasshopper"),
    TERMITE("Termite"),
    WASP("Wasp");

    private final String shipType;
    public int weightCapacity;
    public int mileage;
    public int fuelCapacity;

    // enum constructor - cannot be public or protected
    SpaceShipType(String shipType) { this.shipType = shipType; }

    SpaceShipType(String shipType, int weightCapacity, int mileage, int fuelCapacity) {
        this.shipType = shipType;
        this.weightCapacity = weightCapacity;
        this.mileage = mileage;
        this.fuelCapacity = fuelCapacity;
    }
}
