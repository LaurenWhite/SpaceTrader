package edu.gatech.macpack.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;

public class GameMainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_screen);
    }

    // temporary market button function for testing
    public void onMarketPressed(View view) {
        Intent intent = new Intent(getBaseContext(), MarketActivity.class);
        startActivity(intent);
    }

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
        Game game = DatabaseInteractor.dbInteractor.game;
        String username = game.getPlayer().getName();
        DatabaseInteractor.dbInteractor.saveGame(username, game);
    }

    public void closeApplication(View view) {
        finish();
        moveTaskToBack(true);
    }
}
