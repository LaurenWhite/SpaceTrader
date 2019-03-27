package edu.gatech.macpack.spacetrader.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.CargoItem;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SolarSystem;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;
import edu.gatech.macpack.spacetrader.entity.TradeGood;
import edu.gatech.macpack.spacetrader.viewmodel.CargoListAdapter;
import edu.gatech.macpack.spacetrader.viewmodel.MarketListAdapter;

public class MarketActivity extends AppCompatActivity {
    Game game = Game.getGameInstance();
    Player player = game.getPlayer();
    SpaceShip ship = player.getSpaceShip();

    // create list view obj for market items
    private ListView lvGoods;
    private Map<TradeGood, MarketItem> market;
    private ArrayList<MarketItem> marketList;
    private MarketListAdapter marketListAdapter;
    private MarketItem selectedItem;

    // create list view obj for cargo items
    private ListView lvCargoItems;
    private Map<TradeGood,CargoItem> cargo;
    private ArrayList<CargoItem> cargoList;
    private CargoListAdapter cargoListAdapter;

    // other information required to generate market
    private SolarSystem system;
    private Planet planet;

    private LinearLayout marketItem;
    private FrameLayout flCredits;
    private TextView tvCredits;
    private EditText quantityEditText;
    private Button btnBuy;
    private Button btnSell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        populateMarketListView(); // populates market
        populateCargoListView();
        registerClickCallBack(); // handles clicks

        // initializes the other views in market place screen
        tvCredits = findViewById(R.id.tvCredits);
        tvCredits.setText("Credits: " + game.getPlayer().getCredits());
        btnBuy = findViewById(R.id.btnBuy);
        btnSell = findViewById(R.id.btnSell);
        quantityEditText = findViewById(R.id.quantityInput);

    }

    private void populateMarketListView() {
        marketItem = findViewById(R.id.market_item);
        lvGoods = findViewById(R.id.lvGoods);

        // TODO: implement the current planet/market user is in correctly
        system = game.getSolarSystems().get(0);
        planet = system.getPlanets().get(0);
        market = planet.getMarket();

        marketList = new ArrayList<>();
        marketList.addAll(market.values());

        // Builds adapter
        marketListAdapter = new MarketListAdapter(this, R.layout.market_item, marketList);

        // configures list view
        lvGoods.setAdapter(marketListAdapter);

        // adds header to the list view
        View header = getLayoutInflater().inflate(R.layout.market_header, null);
        lvGoods.addHeaderView(header);

    }

    private void populateCargoListView() {
        marketItem = findViewById(R.id.market_item);
        lvCargoItems = findViewById(R.id.lvCargoItems);

        cargo = ship.getCargo();

        // make a list of its cargo
        cargoList = new ArrayList<>();
        cargoList.addAll(cargo.values());

        cargoListAdapter = new CargoListAdapter(
                this,
                R.layout.market_item,
                cargoList
        );

        lvCargoItems.setAdapter(cargoListAdapter);

        View header = getLayoutInflater().inflate(R.layout.market_header, null);
        lvCargoItems.addHeaderView(header);

    }

    // action of clicking on a market item
    private void registerClickCallBack() {
        lvGoods = findViewById(R.id.lvGoods);
        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                selectedItem = marketList.get(position - 1);
                String message = "You clicked # " + position + ", which is item: " + selectedItem;
                Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        lvCargoItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                selectedItem = cargoList.get(position - 1);
                String message = "You clicked # " + position + ", which is item: " + selectedItem;
                Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }



    public void buyButtonClicked(View view) {

        if(!infoEntered()) return;

        // input type is defined as number only in xml
        int quantity = Integer.parseInt(quantityEditText.getText().toString());

        int totalPrice = quantity * selectedItem.getPrice();
        // TODO: figure out better weight system?
        int totalWeight = quantity;

        MarketItem purchaseItem = new MarketItem(selectedItem.getGood(), quantity, selectedItem.getPrice());

        // Market doesn't have that many items to sell
        if (purchaseItem.getQuantity() > selectedItem.getQuantity()) {
            String message = "Insufficient amount in market";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            return;
        }
        // Player doesn't have enough funds to make purchase
        if (!player.sufficientFunds(totalPrice)) {
            String message = "You do not have enough credits";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            return;
        }
        // Ship doesn't have enough cargo space to store purchased goods
        if(!ship.sufficientSpace(totalWeight)) {
            String message = "Insufficient cargo space";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            return;
        }

        // Make transaction
        planet.sellToPlayer(purchaseItem);
        player.setCredits(game.getPlayer().getCredits() - purchaseItem.getPrice());
        tvCredits.setText("Credits: " + player.getCredits());
        ship.addToCargo(purchaseItem);

        // Reload the list views to display changes
        reloadMarketListView();
        reloadCargoListView();
        selectedItem = null;
    }

    public void sellButtonClicked(View view) {

        if(!infoEntered()) return;

        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        int totalPrice = quantity * selectedItem.getPrice();

        MarketItem sellItem = new MarketItem(selectedItem.getGood(), quantity, selectedItem.getPrice());

        // Player doesn't have that many items to sell
        if(sellItem.getQuantity() > ship.getCargo().get(selectedItem.getGood()).getQuantity()) {
            String message = "Insufficient amount in player cargo";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            return;
        }
        // Planet tech level isn't high enough to use that good
        if(!planet.canUse(sellItem.getGood())) {
            String message = "Planet not advanced enough for this good";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            return;
        }

        // Make transaction
        planet.buyFromPlayer(sellItem);
        player.setCredits(player.getCredits() + totalPrice);
        tvCredits.setText("Credits: " + player.getCredits());
        ship.removeFromCargo(sellItem);

        // Reload the list views to display changes
        reloadMarketListView();
        reloadCargoListView();
        selectedItem = null;
    }

    private boolean infoEntered() {
        // No item selected
        if (selectedItem == null) {
            String message = "Select an item";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            return false;
        }

        // No quantity entered
        if (TextUtils.isEmpty(quantityEditText.getText())) {
            String message = "Enter a quantity";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


    private void reloadMarketListView() {
        market = planet.getMarket();

        marketList.clear();
        marketList.addAll(market.values());

        lvGoods.setAdapter(marketListAdapter);
    }

    private void reloadCargoListView() {
        cargo = ship.getCargo();

        cargoList.clear();
        cargoList.addAll(cargo.values());

        lvCargoItems.setAdapter(cargoListAdapter);
    }


}
