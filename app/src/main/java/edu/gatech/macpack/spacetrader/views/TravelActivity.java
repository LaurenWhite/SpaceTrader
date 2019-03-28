package edu.gatech.macpack.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.macpack.spacetrader.R;

public class TravelActivity extends AppCompatActivity {

    private TextView currentLocationLabel;
    private TextView currentLocation;
    private TextView solarSystem;
    private Spinner solarSystemSpinner;
    private TextView planet;
    private Spinner planetSpinner;
    private Button go;

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
    }
}
