package edu.gatech.macpack.spacetrader.views;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.viewmodel.CargoListAdapter;
import edu.gatech.macpack.spacetrader.viewmodel.MarketListAdapter;
import edu.gatech.macpack.spacetrader.viewmodel.MarketViewModel;

/**
 * Activity for displaying the planet market
 */
public class MarketActivity extends AppCompatActivity {
    private final Game game = DatabaseInteractor.dbInteractor.game;
    private final Player player = game.getPlayer();

     private MarketViewModel viewModel;

    private ListView lvGoods;
    private ListView lvCargoItems;
    private TextView tvCredits;
    private EditText quantityEditText;

    private MarketListAdapter marketListAdapter;
    private CargoListAdapter cargoListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);

        lvGoods = findViewById(R.id.lvGoods);
        lvCargoItems = findViewById(R.id.lvCargoItems);
        tvCredits = findViewById(R.id.tvCredits);
        quantityEditText = findViewById(R.id.quantityInput);

        populateCurrentMarketListView();
        populateCurrentCargoListView();
        registerClickCallBack();
        updateCreditLabel();
    }

    private void populateCurrentMarketListView() {
        viewModel.populateMarketListView();

        marketListAdapter = new MarketListAdapter(this, R.layout.market_item, viewModel.getMarketList());
        lvGoods.setAdapter(marketListAdapter);
        @SuppressLint("InflateParams") View header = getLayoutInflater().inflate(R.layout.market_header, null);
        lvGoods.addHeaderView(header);

    }

    private void populateCurrentCargoListView() {
        viewModel.populateCargoListView();

        cargoListAdapter = new CargoListAdapter(this, R.layout.market_item, viewModel.getCargoList());

        lvCargoItems.setAdapter(cargoListAdapter);
        @SuppressLint("InflateParams") View header = getLayoutInflater().inflate(R.layout.market_header, null);
        lvCargoItems.addHeaderView(header);
    }

    // action of clicking on a market item
    private void registerClickCallBack() {
        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                viewModel.updateMarketSelectedItem(position);
            }
        });

        lvCargoItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                viewModel.updateCargoSelectedItem(position);
            }
        });
    }

    public void buyButtonPressed(View view) {
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        if (!infoEntered()) return;
        viewModel.buyButtonClicked(view, quantity);
        updateAdapters();
        updateCreditLabel();
    }

    public void sellButtonPressed(View view) {
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        viewModel.sellButtonClicked(view, quantity);
        updateAdapters();
        updateCreditLabel();
    }

    private void updateAdapters() {
        lvGoods.setAdapter(marketListAdapter);
        lvCargoItems.setAdapter(cargoListAdapter);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean infoEntered() {
        // No item selected
        if (viewModel.getSelectedItem() == null) {
            String message = "Select an item";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_SHORT).show();
            return false;
        }

        // No quantity entered
        if (TextUtils.isEmpty(quantityEditText.getText())) {
            String message = "Enter a quantity";
            Toast.makeText(MarketActivity.this, message, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void updateCreditLabel() {
        tvCredits.setText(getString(R.string.credits, player.getCredits()));
    }
}
