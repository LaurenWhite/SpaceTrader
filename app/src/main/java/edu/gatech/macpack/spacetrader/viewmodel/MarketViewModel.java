package edu.gatech.macpack.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.Map;

import edu.gatech.macpack.spacetrader.entity.CargoItem;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;

public class MarketViewModel extends AndroidViewModel {
    private final Game game = DatabaseInteractor.dbInteractor.game;
    private final Player player = game.getPlayer();
    private final SpaceShip ship = player.getSpaceShip();

    private Map<String, MarketItem> market;
    private ArrayList<MarketItem> marketList;

    private Map<String, CargoItem> cargo;
    private ArrayList<CargoItem> cargoList;

    private MarketItem selectedItem;

    private Planet planet;

    /**
     * This constructor creates an instance of the UserConfigurationModel class
     * @param application param to pass to the constructor of AndroidViewModel (super)
     */
    public MarketViewModel(@NonNull Application application) {
        super(application);
    }

    public void populateMarketListView() {
        planet = ship.getLocation();
        market = planet.getMarket();

        marketList = new ArrayList<>();
        marketList.addAll(market.values());


    }

    public void populateCargoListView() {
        cargo = ship.getCargo();

        // make a list of its cargo
        cargoList = new ArrayList<>();
        cargoList.addAll(cargo.values());
    }

    public ArrayList<MarketItem> getMarketList() {
        return marketList;
    }

    public ArrayList<CargoItem> getCargoList() {
        return cargoList;
    }

    public MarketItem getSelectedItem() {
        return selectedItem;
    }

    /**
     * Player purchases item when buy button pressed
     *
     * @param view UI object
     */
    public void buyButtonClicked(View view, int quantity) {

        // input type is defined as number only in xml

        int totalPrice = quantity * selectedItem.getPrice();
        @SuppressWarnings("TooBroadScope") int totalWeight = quantity;

        MarketItem purchaseItem = new
                MarketItem(selectedItem.getGood(), quantity, selectedItem.getPrice());

        // Market doesn't have that many items to sell
        if (purchaseItem.getQuantity() > selectedItem.getQuantity()) {
            String message = "Insufficient amount in market";
            System.out.println(message);
//            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        // Player doesn't have enough funds to make purchase
        if (!player.sufficientFunds(totalPrice)) {
            String message = "You do not have enough credits";
            System.out.println(message);
//            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        // Ship doesn't have enough cargo space to store purchased goods
        if(!ship.sufficientSpace(totalWeight)) {
            String message = "Insufficient cargo space";
            System.out.println(message);
//            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }

        // Make transaction
        planet.sellToPlayer(purchaseItem);
        player.setCredits(game.getPlayer().getCredits() - purchaseItem.getPrice());
        ship.addToCargo(purchaseItem);

        // Reload the list views to display changes
        market = planet.getMarket();
        marketList.clear();
        marketList.addAll(market.values());

        cargo = ship.getCargo();
        cargoList.clear();
        cargoList.addAll(cargo.values());

        selectedItem = null;
    }

    /**
     * Player sells item when sell button pressed
     *
     * @param view UI object
     */
    public void sellButtonClicked(View view, int quantity) {

        int totalPrice = quantity * selectedItem.getPrice();

        MarketItem sellItem = new
                MarketItem(selectedItem.getGood(), quantity, selectedItem.getPrice());

        // Player doesn't have that many items to sell
        //noinspection SuspiciousMethodCalls
        if(sellItem.getQuantity() > ship.getCargo().get(selectedItem.getGood()).getQuantity()) {
            String message = "Insufficient amount in player cargo";
            System.out.println(message);
//            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        // Planet tech level isn't high enough to use that good
        if(!planet.canUse(sellItem.getGood())) {
            String message = "Planet not advanced enough for this good";
            System.out.println(message);
//            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }

        // Make transaction
        planet.buyFromPlayer(sellItem);
        player.setCredits(player.getCredits() + totalPrice);
        ship.removeFromCargo(sellItem);

        // Reload the list views to display changes
        market = planet.getMarket();
        marketList.clear();
        marketList.addAll(market.values());

        cargo = ship.getCargo();
        cargoList.clear();
        cargoList.addAll(cargo.values());

        selectedItem = null;
    }


    public void updateCargoSelectedItem(int position) {
        selectedItem = cargoList.get(position - 1);
    }

    public void updateMarketSelectedItem(int position) {
        selectedItem = marketList.get(position - 1);
    }
}
