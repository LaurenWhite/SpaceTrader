package edu.gatech.macpack.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;

public class EncounterActivity extends AppCompatActivity {

    Game game = DatabaseInteractor.dbInteractor.game;
    Player player = game.getPlayer();
    SpaceShip ship = player.getSpaceShip();

    private TextView tvEncounterLocation;
    private TextView tvEncounterScript;
    private Button continueBtn;

    private String encounterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        computeEncounterType();

        String message = "You had an encounter with " + encounterType + "!";
        Toast.makeText(EncounterActivity.this, message, Toast.LENGTH_SHORT).show();

        tvEncounterLocation = findViewById(R.id.tvEncounterLocation);
        tvEncounterScript = findViewById(R.id.tvEncounterScript);
        continueBtn = findViewById(R.id.continueBtn);

        updateLocationLabel();
        updateEncounterScript();

    }

    private void computeEncounterType() {
        Planet planet = ship.getLocation();
        int eventTypeNumber = planet.getTraderEventChance();
        if (eventTypeNumber == 0
                || eventTypeNumber == 1) {
            // encounter was of type pirate
            encounterType = "pirates";
        } else if (eventTypeNumber == 6) {
            // encounter was of type trader
            encounterType = "traders";
        } else if (eventTypeNumber == 7) {
            encounterType = "police";
        } else {
            // if the number is null for some reason default it to a police encounter
            encounterType = "police";
        }
    }

    private void updateLocationLabel() {
        Planet planet = ship.getLocation();
        tvEncounterLocation.setText(String.format(getString(R.string.encounter_location_label), planet.getName(), encounterType));
    }

    private void updateEncounterScript() {
        if (encounterType == "pirates") {
            tvEncounterScript.setText(String.format(getString(R.string.script_of_police_encounter)));
        } else if (encounterType == "traders") {
            tvEncounterScript.setText(String.format(getString(R.string.script_of_traders_encounter)));
        } else if (encounterType == "police"){
            tvEncounterScript.setText(String.format(getString(R.string.script_of_police_encounter)));
        } else {
            // if it's something other than these three for some odd reason, use this default text as the script
            tvEncounterScript.setText(String.format(getString(R.string.script_of_default_encounter)));
        }
    }

    public void continuePressed(View view) {
        Intent intent = new Intent(getBaseContext(), GameMainScreenActivity.class);
        startActivity(intent);
    }
}
