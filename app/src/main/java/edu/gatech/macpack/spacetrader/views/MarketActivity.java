package edu.gatech.macpack.spacetrader.views;

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
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SolarSystem;
import edu.gatech.macpack.spacetrader.entity.TradeGood;
import edu.gatech.macpack.spacetrader.viewmodel.MarketListAdapter;

public class MarketActivity extends AppCompatActivity {
    Game game = Game.getGameInstance();

    // create list view obj
    private ListView lvGoods;
    private Map<TradeGood, MarketItem> market;
    private ArrayList<MarketItem> marketList;

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
        registerClickCallBack(); // handles clicks

        // initializes the other views in market place screen
        flCredits = findViewById(R.id.flCredits);
        tvCredits = findViewById(R.id.tvCredits);
        btnBuy = findViewById(R.id.btnBuy);
        btnSell = findViewById(R.id.btnSell);

    }

    private void populateMarketListView() {
        marketItem = findViewById(R.id.market_item);
        lvGoods = findViewById(R.id.lvGoods);

        // TODO: implement the current planet/market correctly (apologies in advance for the messy code)
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

    // action of clicking on a market item
    private void registerClickCallBack() {
        lvGoods = findViewById(R.id.lvGoods);
        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                // TODO: highlight item clicked
                LinearLayout linearLayout = (LinearLayout) viewClicked;
                String message = "You clicked # " + position + ", which is string: ";
                Toast.makeText(MarketActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    // TODO: implement buy/sell functionality
    // TODO: Perform buy/sell method on highlighted/selected item
    // Buy:
        // sufficientSpace() -> ?
        // sufficientCredits() -> ?
        // addToCargo()
        // updateCredits()
    // Sell:
        // sufficient#ofGoodsInInventory() -> ?
        // removeFromCargo()
        // updateCredits()

}
