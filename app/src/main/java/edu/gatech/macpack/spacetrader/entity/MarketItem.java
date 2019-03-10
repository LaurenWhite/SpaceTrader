package edu.gatech.macpack.spacetrader.entity;

public class MarketItem {

    private TradeGood good;
    private int quantity;
    private int price;

    public MarketItem(TradeGood good, int quantity, int price) {
        this.good = good;
        this.quantity = quantity;
        this.price = price;
    }


    // GETTERS
    public TradeGood getGood() { return good; }

    public int getQuantity() { return quantity; }

    public int getPrice() { return price; }

    // SETTERS
    public void setGood(TradeGood good) { this.good = good; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setPrice(int price) { this.price = price; }
}