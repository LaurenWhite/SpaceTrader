package edu.gatech.macpack.spacetrader.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

    // create list view obj for market items
    private ListView lvGoods;
    private Map<TradeGood, MarketItem> market;
    private ArrayList<MarketItem> marketList;

    // create list view obj for cargo items
    private ListView lvCargoItems;
    private Map<TradeGood,CargoItem> cargo;
    private ArrayList<CargoItem> cargoList;

    // other information required to generate market
    private SolarSystem system;
    private Planet planet;

    private LinearLayout marketItem;
    private FrameLayout flCredits;
    private TextView tvCredits;
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

    }

    private void populateMarketListView() {
        marketItem = findViewById(R.id.market_item);
        lvGoods = findViewById(R.id.lvGoods);

        // TODO: implement the current planet/market user is in correctly (apologies in advance for the messy code)
        system = game.getSolarSystems().get(0);
        planet = system.getPlanets().get(0);
        market = planet.getMarket();

        marketList = new ArrayList<>();
        marketList.addAll(market.values());

        // builds the adapter
        MarketListAdapter adapter = new MarketListAdapter(
                this,
                R.layout.market_item,
                marketList
        );

        // configures list view
        lvGoods.setAdapter(adapter);

        // adds header to the list view
        View header = getLayoutInflater().inflate(R.layout.market_header, null);
        lvGoods.addHeaderView(header);

    }

    private void populateCargoListView() {
        marketItem = findViewById(R.id.market_item);
        lvCargoItems = findViewById(R.id.lvCargoItems);

        // get the current player's ship
        SpaceShip ship = game.getPlayer().getSpaceShip();

        // make a list of its cargo
        cargoList = new ArrayList<>();
        cargoList.addAll(ship.getCargo().values());

        CargoListAdapter adapter = new CargoListAdapter(
                this,
                R.layout.market_item,
                cargoList
        );

        lvCargoItems.setAdapter(adapter);

        View header = getLayoutInflater().inflate(R.layout.market_header, null);
        lvCargoItems.addHeaderView(header);

    }

    // action of clicking on a market item
    private void registerClickCallBack() {
        lvGoods = findViewById(R.id.lvGoods);
        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                // TODO: store the currently highlighted item

                LinearLayout linearLayout = (LinearLayout) viewClicked;
                String message = "You clicked # " + position + ", which is string: ";
                Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    // (oops didn't see buy/sell in planet)
    // TODO: Perform buy/sell method on highlighted/selected item
    // sellToPlayer:
        // sufficientSpace() -> ?
        // sufficientCredits() -> ?
        // addToCargo()
        // updateCredits()
    // buyFromPlayer:
        // sufficient#ofGoodsInInventory() -> ?
        // removeFromCargo()
        // updateCredits()

}
