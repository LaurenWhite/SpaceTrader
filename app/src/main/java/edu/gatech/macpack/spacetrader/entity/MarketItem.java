package edu.gatech.macpack.spacetrader.entity;

/**
 * Creates market item object
 */
public class MarketItem {

    private TradeGood good;
    private int quantity;
    private int price;

    /**
     * Creates new market item
     *
     * @param good     trade good type
     * @param quantity number of items
     * @param price    price of item
     */
    public MarketItem(TradeGood good, int quantity, int price) {
        this.good = good;
        this.quantity = quantity;
        this.price = price;
    }


    // GETTERS

    /**
     * @return trade good
     */
    public TradeGood getGood() { return good;
    }

    /**
     * @param good new trade good
     */
    public void setGood(TradeGood good) {
        this.good = good;
    }

    /**
     * @return quantity of item
     */
    public int getQuantity() { return quantity; }

    // SETTERS

    /**
     * @param quantity new quantity
     */
    public void setQuantity(int quantity) { this.quantity = quantity;
    }

    /**
     * @return price of item
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price new price
     */
    public void setPrice(int price) { this.price = price; }


    @Override
    public String toString() {
        return good.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }

        if (!(obj instanceof MarketItem)) { return false; }

        MarketItem item = (MarketItem) obj;

        return this.good.equals(item.good)
                && this.price == item.price
                && this.quantity == item.quantity;
    }
}