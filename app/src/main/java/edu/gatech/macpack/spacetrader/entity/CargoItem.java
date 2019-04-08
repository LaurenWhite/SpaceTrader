package edu.gatech.macpack.spacetrader.entity;

/**
 * Cargo Item class
 */
public class CargoItem extends MarketItem {

    /**
     * Creates cargo item object
     *
     * @param good     trade good type
     * @param quantity number of items
     * @param price    price of item
     */
    public CargoItem(TradeGood good, int quantity, int price) {
        super(good, quantity, price);
    }
}
