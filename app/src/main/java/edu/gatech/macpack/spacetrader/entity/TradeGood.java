package edu.gatech.macpack.spacetrader.entity;

import java.util.Random;

public enum TradeGood {
    WATER(0, 0, 2, 30, 3, 4),
    FURS(0, 0, 0, 250, 10, 10),
    FOOD(1, 0, 1, 100, 5, 5),
    ORE(2, 2, 3, 350, 20, 10),
    GAMES(3, 1, 6, 250, -10, 5),
    FIREARMS(3, 1, 5, 1250, -75, 100),
    MEDICINE(4, 1, 6, 650, -20, 10),
    MACHINES(4, 3, 5, 900, -30, 5),
    NARCOTICS(5, 0, 5, 3500, -125, 150),
    ROBOTS(6, 4, 7, 5000, -150, 100);


    // ATTRIBUTES
    private int mtlp;
    private int mtlu;
    private int ttp;
    private int basePrice;
    private int ipl;
    private int variance;


    // CONSTRUCTOR
    TradeGood(int mtlp, int mtlu, int ttp, int basePrice, int ipl, int variance) {
        this.mtlp = mtlp;
        this.mtlu = mtlu;
        this.ttp = ttp;
        this.basePrice = basePrice;
        this.ipl = ipl;
        this.variance = variance;
    }


    // FUNCTIONALITY
    public boolean canProduce(int techLevel) {
        return techLevel >= mtlp;
    }

    public double productionProbability(int techLevel) {
        // If optimal tech level max out probability otherwise return random # from 0 - 1
        return (techLevel == ttp) ? 1 : (Math.round(Math.random() * 100.0) / 100.0);
    }

    public int regionalPrice(int techLevel) {
        // (the base price) + (the IPL * (Planet Tech Level - MTLP)) + (variance)
        return (basePrice + ipl * (techLevel - mtlp) + variance);
    }
}
