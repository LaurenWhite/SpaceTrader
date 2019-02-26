package edu.gatech.macpack.spacetrader.entity;

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

    public int getTechNum() {
        return TechNumber;
    }
}