package edu.gatech.macpack.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
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

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.viewmodel.TravelViewModel;

/**
 * This is the TravelActivity class that displays the Travel Screen
 */
public class TravelActivity extends AppCompatActivity {

    private TravelViewModel viewModel;

    private TextView currentLocationLabel;
    private TextView currentFuelLabel;
    private Spinner planetSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        viewModel = ViewModelProviders.of(this).get(TravelViewModel.class);

        // Connect UI
        currentLocationLabel = findViewById(R.id.currentLocationLabel);
        currentFuelLabel = findViewById(R.id.currentFuelLabel);
        Spinner solarSystemSpinner = findViewById(R.id.solarSystemSpinner);
        planetSpinner = findViewById(R.id.planetSpinner);

        viewModel.initializeFields();

        // Update current location, current fuel labels, and in range solar systems
        updateLabels();

        ArrayAdapter<String> solarAdapter = new
                ArrayAdapter<>(this, R.layout.spinner_item, viewModel.getSolarSystemNames());
        solarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        solarSystemSpinner.setAdapter(solarAdapter);

        solarSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // changes the planets spinner accordingly when solar system is clicked

                viewModel.updatePlanetList(position);

                ArrayAdapter<String> planetAdapter = new
                        ArrayAdapter<>(
                                TravelActivity.this, R.layout.spinner_item, viewModel.getPlanetNames());
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
                viewModel.updateSelectedPlanet(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


    private void updateLabels() {
        currentLocationLabel.setText(getString(R.string.current_location_label,
                viewModel.getCurrentPlanet().getName(), viewModel.getCurrentPlanet().getParentName()));
        currentFuelLabel.setText(getString(R.string.fuel, viewModel.getFuel()));

        viewModel.updateSystemsInRange();
    }


    /**
     * This method allows the player to travel to the selected location upon the go button being pressed
     * @param view UI object
     */
    public void goButtonClicked(View view) {

        if (viewModel.getSelectedPlanet() == null) { return; }

        viewModel.travel();

        updateLabels();

        if (viewModel.eventOccurred()) {
            // there was no encounter, show a toast
            String message = "You did not have an encounter!";
            Toast.makeText(TravelActivity.this, message, Toast.LENGTH_SHORT).show();
        } else {

            // Play a sound and wait like 3 seconds
            MediaPlayer mp = MediaPlayer.create(this, R.raw.suspense);
            mp.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(getBaseContext(), EncounterActivity.class);
            startActivity(intent);
        }
    }
}
