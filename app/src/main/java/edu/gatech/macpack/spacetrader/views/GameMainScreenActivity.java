package edu.gatech.macpack.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;
import edu.gatech.macpack.spacetrader.viewmodel.GameMainScreenViewModel;

/**
 * Activity for displaying the main game screen
 */
public class GameMainScreenActivity extends AppCompatActivity {


    private GameMainScreenViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_screen);

        viewModel = ViewModelProviders.of(this).get(GameMainScreenViewModel.class);

        ProgressBar fuelTank = findViewById(R.id.fuelTankBar);
        ProgressBar shipHealthBar = findViewById(R.id.shipHealthBar);
        TextView shipType = findViewById(R.id.shipType);

        fuelTank.setProgress(viewModel.getShipFuel());
        // lol never implemented health in ship
        int DEFAULT_HEALTH = 1000;
        shipHealthBar.setProgress(DEFAULT_HEALTH);
        shipType.setText(viewModel.shipTypeToString());
    }

    /**
     * Transitions to market activity when Market button pressed
     *
     * @param view UI object
     */
    public void onMarketPressed(View view) {
        Intent intent = new Intent(getBaseContext(), MarketActivity.class);
        startActivity(intent);
    }

    /**
     * Transitions to travel activity when Travel button pressed
     *
     * @param view UI object
     */
    public void onTravelPressed(View view) {
        Intent intent = new Intent(getBaseContext(), TravelActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close:
                Log.d("DEBUG: ", "Clicked close button in menu bar");
                closeApplication(findViewById(item.getItemId()));
                break;
            case R.id.save:
                Log.d("DEBUG", "Clicked save button in the game main screen menu bar");
                saveApplication();
                break;
        }

        return true;
    }

    private void saveApplication() {
        viewModel.saveApplication();
    }

    /**
     * Closes application when Close option selected
     *
     * @param view UI object
     */
    private void closeApplication(View view) {
        finish();
        moveTaskToBack(true);
    }
}
