package edu.gatech.macpack.spacetrader.entity;

/**
 * This is an enumeration for the type of tech levels
 */
public enum TechLevel {
    PRE_AGRICULTURE(0),
    AGRICULTURE(1),
    MEDIEVAL(2),
    RENAISSANCE(3),
    EARLY_INDUSTRIAL(4),
    INDUSTRIAL(5),
    POST_INDUSTRIAL(6),
    HI_TECH(7);


    public int TechNumber;

    TechLevel(int TechNumber) {
        this.TechNumber = TechNumber;
    }

    /**
     * This is a getter that returns the tech number of a tech level enum
      * @return tech number
     */
    public int getTechNum() {
        return TechNumber;
    }
}