package edu.gatech.macpack.spacetrader.entity;

/**
 * Enumeration for different types of planet resources
 */
public enum ResourceType {
    NON_SPECIAL_RESOURCES(0),
    MINERAL_RICH(1),
    MINERAL_POOR(2),
    DESERT(3),
    LOTS_OF_WATER(4),
    RICH_SOIL(5),
    POOR_SOIL(6),
    RICH_FAUNA(7),
    LIFELESS(8),
    WEIRD_MUSHROOMS(9),
    LOTS_OF_HERBS(10),
    ARTISTIC(11),
    WARLIKE(12);


    public int ResourceNumber;

    ResourceType(int ResourceNumber) {
        this.ResourceNumber = ResourceNumber;
    }

    /**
     * @return resource number of type
     */
    public int getResourceNumber() {
        return ResourceNumber;
    }
}
