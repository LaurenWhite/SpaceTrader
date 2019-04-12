package edu.gatech.macpack.spacetrader.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SolarSystem;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;
import edu.gatech.macpack.spacetrader.entity.Traveler;

/**
 * This is the TravelActivity class that displays the Travel Screen
 */
public class TravelActivity extends AppCompatActivity {
    private final Game game = DatabaseInteractor.dbInteractor.game;
    private final Player player = game.getPlayer();
    private final SpaceShip ship = player.getSpaceShip();

    private TextView currentLocationLabel;
    private TextView currentFuelLabel;
    private Spinner planetSpinner;

    private Planet currentPlanet;
    private List<SolarSystem> systems;
    private List<String> solarSystemNames;

    private List<Planet> planets;
    private List<String> planetNames;
    private Planet selectedPlanet;
    private SolarSystem chosenSystem;

    private Traveler traveler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        // Connect UI
        currentLocationLabel = findViewById(R.id.currentLocationLabel);
        currentFuelLabel = findViewById(R.id.currentFuelLabel);
        Spinner solarSystemSpinner = findViewById(R.id.solarSystemSpinner);
        planetSpinner = findViewById(R.id.planetSpinner);

        // Initialize Variables
        traveler = new Traveler(ship);
        systems = traveler.systemsInRange();

        currentPlanet = ship.getLocation();

        solarSystemNames = new ArrayList<>();
        planetNames = new ArrayList<>();

        // Update current location, current fuel labels, and in range solar systems
        updateLabels();

        ArrayAdapter<String> solarAdapter = new
                ArrayAdapter<>(this, R.layout.spinner_item, solarSystemNames);
        solarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        solarSystemSpinner.setAdapter(solarAdapter);

        solarSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // changes the planets spinner accordingly when solar system is clicked

                planetNames.clear();

                chosenSystem = systems.get(position);
                for (Planet planet : chosenSystem.getPlanets()) {
                    planetNames.add(planet.getName());
                }

                ArrayAdapter<String> planetAdapter = new
                        ArrayAdapter<>(
                                TravelActivity.this, R.layout.spinner_item, planetNames);
                planetAdapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item);
                planetSpinner.setAdapter(planetAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        planetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                planets = chosenSystem.getPlanets();
                selectedPlanet = planets.get(position);
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


    private void updateLabels() {
        currentLocationLabel.setText(getString(R.string.current_location_label,
                currentPlanet.getName(),currentPlanet.getParentName()));
        currentFuelLabel.setText(getString(R.string.fuel,ship.getFuel()));

        traveler = new Traveler(ship);
        systems = traveler.systemsInRange();

        solarSystemNames.clear();
        for (SolarSystem system : systems) {
            solarSystemNames.add(system.getName());
        }
    }


    /**
     * This method allows the player to travel to the selected location upon the go button being pressed
     * @param view UI object
     */
    public void goButtonClicked(View view) {
        if (selectedPlanet == null) { return; }

        traveler = new Traveler(ship, currentPlanet, selectedPlanet);

        traveler.travel();

        currentPlanet = selectedPlanet;

        updateLabels();

        if (currentPlanet.getTraderEventChance() == 2
                || currentPlanet.getTraderEventChance() == 3
                || currentPlanet.getTraderEventChance() == 4
                || currentPlanet.getTraderEventChance() == 5) {
            // there was no encounter, show a toast
            String message = "You did not have an encounter!";
            Toast.makeText(TravelActivity.this, message, Toast.LENGTH_SHORT).show();
        } else {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.suspense);
            mp.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // play a sound and wait like 3 seconds
            Intent intent = new Intent(getBaseContext(), EncounterActivity.class);
            startActivity(intent);
        }
    }
}
