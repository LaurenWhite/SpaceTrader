package edu.gatech.macpack.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SolarSystem;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;
import edu.gatech.macpack.spacetrader.entity.Traveler;

public class TravelActivity extends AppCompatActivity {
    Game game = Game.getGameInstance();
    Player player = game.getPlayer();
    SpaceShip ship = player.getSpaceShip();

    private TextView currentLocationLabel;
    private TextView currentLocation;
    private TextView solarSystem;
    private Spinner solarSystemSpinner;
    private TextView planet;
    private Spinner planetSpinner;
    private Button go;

    private SolarSystem currentSystem;
    private Planet currentPlanet;
    private List<SolarSystem> systems;
    private ArrayList<String> solarSystemNames;

    private ArrayList<Planet> planets;
    private List<String> planetNames;
    private Planet selectedPlanet;
    private SolarSystem chosenSystem;

    private Traveler traveler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        currentLocationLabel = findViewById(R.id.currentLocationLabel);
        currentLocation = findViewById(R.id.currentLocation);
        solarSystem = findViewById(R.id.solarSystem);
        solarSystemSpinner = findViewById(R.id.solarSystemSpinner);
        planet = findViewById(R.id.planet);
        planetSpinner = findViewById(R.id.planetSpinner);
        go = findViewById(R.id.go);

        traveler = new Traveler(ship);
        systems = traveler.systemsInRange();

        currentSystem = ship.getLocation().getParentSystem();
        currentPlanet = ship.getLocation();

        currentLocationLabel.setText("Current location: " + currentPlanet.getName() + ", " + currentSystem.getName());

        solarSystemNames = new ArrayList<>();

        for (SolarSystem system : systems) {
            solarSystemNames.add(system.getName());
        }

        ArrayAdapter<String> solarAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, solarSystemNames);

        solarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        solarSystemSpinner.setAdapter(solarAdapter);

        solarSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // changes the planets spinner accordingly when solar system is clicked

                planetNames = new ArrayList<>();

                chosenSystem = systems.get(position);
                for (Planet planet : chosenSystem.getPlanets()) {
                    planetNames.add(planet.getName());
                }

                ArrayAdapter<String> planetAdapter = new ArrayAdapter<String>(TravelActivity.this, R.layout.spinner_item, planetNames);
                planetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                planetSpinner.setAdapter(planetAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        planetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                planets = (ArrayList<Planet>) chosenSystem.getPlanets();
                selectedPlanet = planets.get(position);
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void goButtonClicked(View view) {
        if (selectedPlanet == null) { return; }

        traveler = new Traveler(ship, currentPlanet, selectedPlanet);

        traveler.travel();

        currentPlanet = selectedPlanet;
        currentSystem = currentPlanet.getParentSystem();

        currentLocationLabel.setText("Current location: " + currentPlanet.getName() + ", " + currentSystem.getName());
    }
}
