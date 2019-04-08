package edu.gatech.macpack.spacetrader;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.SolarSystem;
import edu.gatech.macpack.spacetrader.entity.TradeGood;

import static org.junit.Assert.*;

/**
 * Unit test for sellToPlayer method
 */
public class sellToPlayerTest {

    private Planet testPlanet;
    private MarketItem purchaseItem;
    private Map<String, MarketItem> market;

    @Before
    /**
     * Set up variables for testing
     */
    public void setUp() {
        testPlanet = new Planet(new SolarSystem());

        market = new HashMap<>();
        MarketItem marketStock = new MarketItem(TradeGood.FOOD, 10, 100);
        market.put("FOOD", marketStock);
        testPlanet.setMarket(market);
    }

    @Test
    /**
     * Sell part of market stock of an item
     */
    public void sellSomeOfItemStock() {
        purchaseItem = new MarketItem(TradeGood.FOOD, 3, 100);

        testPlanet.sellToPlayer(purchaseItem);

        Map<String, MarketItem> expected = new HashMap<>();
        expected.put("FOOD", new MarketItem(TradeGood.FOOD, 7, 100));

        assertEquals(expected.get("FOOD"), market.get("FOOD"));
    }

    @Test
    /**
     * Sell all of the stock of a market item
     */
    public void sellAllOfItemStock() {
        purchaseItem = new MarketItem(TradeGood.FOOD, 10, 100);

        testPlanet.sellToPlayer(purchaseItem);

        assertTrue(market.isEmpty());
    }

    @Test
    /**
     * Do not sell any items if requested item is not in market
     */
    public void sellNoneItemUnavailable() {
        purchaseItem = new MarketItem(TradeGood.WATER, 4, 50);

        Map<String, MarketItem> expected = new HashMap<>();
        expected.put("FOOD", new MarketItem(TradeGood.FOOD, 10, 100));
        testPlanet.sellToPlayer(purchaseItem);

        assertEquals(expected, market);
    }
}
