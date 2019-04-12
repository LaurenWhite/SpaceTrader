package edu.gatech.macpack.spacetrader;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.SolarSystem;
import edu.gatech.macpack.spacetrader.entity.TradeGood;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class buyFromPlayerUnitTest {
    private Planet testPlanet;
    private MarketItem purchaseItem;
    private Map<String, MarketItem> market;

    @Before
    /*
     * Set up variables for testing
     */
    public void setUp() {
        testPlanet = new Planet(new SolarSystem());
    }

    @Test
    /*
     * Add a new item to market
     */
    public void addNewItem() {

        purchaseItem = new MarketItem(TradeGood.WATER, 5, 50);
        market = new HashMap<>();
        testPlanet.setMarket(market);

        testPlanet.buyFromPlayer(purchaseItem);

        Map<String, MarketItem> expected = new HashMap<>();
        expected.put("WATER", new MarketItem(TradeGood.WATER, 5, 50));

        assertEquals(expected, market);
    }

    @Test
    /*
     * Do not sell any items if requested item is not in market
     */
    public void addToExisting() {
        market = new HashMap<>();
        MarketItem marketStock = new MarketItem(TradeGood.WATER, 5, 50);
        market.put("WATER", marketStock);
        testPlanet.setMarket(market);

        purchaseItem = new MarketItem(TradeGood.WATER, 3, 50);
        testPlanet.buyFromPlayer(purchaseItem);

        Map<String, MarketItem> expected = new HashMap<>();
        expected.put("WATER", new MarketItem(TradeGood.WATER, 8, 50));


        assertEquals(expected.get("WATER"), market.get("WATER"));
    }
}
