package edu.gatech.macpack.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class MarketActivity extends AppCompatActivity {

    Game game = Game.getGameInstance();
    Player player = game.getPlayer();

    // create list view obj
    private ListView lvGoods;
    private Map<TradeGood, MarketItem> market;
    private ArrayList<String> lvGoodsNames;
    private ArrayList<String> lvGoodsPrices;
    private ArrayList<String> lvGoodsQuantities;

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

        // TODO: implement the current planet correctly (apologies in advance for the messy code)
        system = game.getSolarSystems().get(0);
        planet = system.getPlanets().get(0);

        market = planet.getMarket();

        int ctr = 0;
        lvGoodsNames = new ArrayList<String>();
        for(MarketItem good : market.values()) {
//            lvGoodsNames.add(good.getGood().toString()); // adds a good's "name?" to the name arraylist
            lvGoodsNames.add("Placeholder: " + ctr++);
//            lvGoodsPrices.add(String.valueOf(good.getPrice()));
//            lvGoodsQuantities.add(String.valueOf(good.getQuantity()));
        }

        // TODO: Replace ArrayAdapter with a custom marketItemAdapter so more than one view per row can be updated
        // builds the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MarketActivity.this, // context
                R.layout.market_item, // layout of a single market item
                R.id.tvGoodName, // id of goodName text view within marketItem layout
                lvGoodsNames // list of goods names
        );

        // configures list view
        lvGoods = findViewById(R.id.lvGoods);
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
                String message = "You clicked # " + position + ", which is string: " + lvGoodsNames.get(position - 1);
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
