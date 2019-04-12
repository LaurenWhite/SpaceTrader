package edu.gatech.macpack.spacetrader;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.macpack.spacetrader.entity.CargoItem;
import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;
import edu.gatech.macpack.spacetrader.entity.SpaceShipType;
import edu.gatech.macpack.spacetrader.entity.TradeGood;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * unit test for removeFromCargo method
 */

public class removeFromCargoTest {

  private SpaceShip testShip;
  private MarketItem removeItem;
  private Map<String, CargoItem> cargo;


  @Before

  // this method setups the the above variables before they are used for testing
  public void setUp() {
      testShip = new SpaceShip(SpaceShipType.GNAT);
      cargo = new HashMap<>();
  }


  @Test
  //more than one of the item exists
  public void removeFromCargoMultipleThere() {
      removeItem = new MarketItem(TradeGood.WATER,5,50);

      cargo.put("WATER", new CargoItem(TradeGood.WATER, 7, 50));
      testShip.setCargo(cargo);

      testShip.removeFromCargo(removeItem);

      Map<String, CargoItem> expected = new HashMap<>();
      expected.put("WATER", new CargoItem(TradeGood.WATER, 2, 50));

      assertEquals(expected, cargo);
  }


  @Test
   //only one of the item exists
  public void removeFromCargoOneThere() {
      removeItem = new MarketItem(TradeGood.WATER,1,50);

      cargo.put("WATER", new CargoItem(TradeGood.WATER, 1, 50));
      testShip.setCargo(cargo);

      testShip.removeFromCargo(removeItem);

      assertTrue(cargo.isEmpty());
  }

  @Test
   //the item does not exist in the cargo at all
  // cargo should remain the same
   public void removeFromCargoNotThere() {

      cargo.put("FOOD", new CargoItem(TradeGood.FOOD, 10, 100));
      testShip.setCargo(cargo);

      Map<String, CargoItem> expected = new HashMap<>();
      expected.put("FOOD", new CargoItem(TradeGood.FOOD, 10, 100));

      removeItem = new MarketItem(TradeGood.FURS,3,150);

      assertEquals(expected, cargo);
  }


}
