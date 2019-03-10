package edu.gatech.macpack.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;




import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DifficultyType;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.viewmodel.UserConfigurationViewModel;


public class UserConfigurationActivity extends AppCompatActivity {

    Player player = new Player();

    private UserConfigurationViewModel viewModel;

    private EditText nameField;
    private TextView skillPoints;
    private TextView pilotPoints;
    private TextView fighterPoints;
    private TextView traderPoints;
    private TextView engineerPoints;

    private Spinner difficultySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_configuration);


        viewModel = ViewModelProviders.of(this).get(UserConfigurationViewModel.class);

        nameField = findViewById(R.id.username_input);
        nameField.setText("Name");

        skillPoints = findViewById(R.id.avail_skillp_label);
        skillPoints.setText("Available Skill Points: " + player.getAvailableSkillPoints());

        pilotPoints = findViewById(R.id.pilot_points);
        pilotPoints.setText(Integer.toString(player.getPilotPoints()));

        fighterPoints = findViewById(R.id.fighter_points);
        fighterPoints.setText(Integer.toString(player.getFighterPoints()));

        traderPoints = findViewById(R.id.trader_points);
        traderPoints.setText(Integer.toString(player.getTraderPoints()));

        engineerPoints = findViewById(R.id.engineer_points);
        engineerPoints.setText(Integer.toString(player.getEngineerPoints()));

        difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<DifficultyType> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, DifficultyType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);
    }


    private void updatePointLabels() {
        skillPoints.setText("Available Skill Points: " + player.getAvailableSkillPoints());
        pilotPoints.setText(Integer.toString(player.getPilotPoints()));
        fighterPoints.setText(Integer.toString(player.getFighterPoints()));
        traderPoints.setText(Integer.toString(player.getTraderPoints()));
        engineerPoints.setText(Integer.toString(player.getEngineerPoints()));
    }




    public void subtractPilotPointPressed(View view) {
        viewModel.subtractPilotPoint(player);
        updatePointLabels();
    }

    public void addPilotPointPressed(View view) {
        viewModel.addPilotPoint(player);
        updatePointLabels();
    }

    public void subtractFighterPointPressed(View view) {
        viewModel.subtractFighterPoint(player);
        updatePointLabels();
    }

    public void addFighterPointPressed(View view) {
        viewModel.addFighterPoint(player);
        updatePointLabels();
    }

    public void subtractTraderPointPressed(View view) {
        viewModel.subtractTraderPoint(player);
        updatePointLabels();
    }

    public void addTraderPointPressed(View view) {
        viewModel.addTraderPoint(player);
        updatePointLabels();
    }

    public void subtractEngineerPointPressed(View view) {
        viewModel.subtractEngineerPoint(player);
        updatePointLabels();
    }

    public void addEngineerPointPressed(View view) {
        viewModel.addEngineerPoint(player);
        updatePointLabels();
    }

    public void createUserPressed(View view) {

        if(isValidUsername(nameField.getText().toString())) {

            if (allPointsAllocated()) {
                viewModel.changeName(player, nameField.getText().toString());
                viewModel.changeDifficulty((DifficultyType) difficultySpinner.getSelectedItem());

                System.out.println("*****NEW PLAYER CREATED*******\n" + player);
                viewModel.updatePlayer(player);

                Intent intent = new Intent(getBaseContext(), GameMainScreenActivity.class);
                startActivity(intent);
            }
        }
    }

    private boolean isValidUsername(String name) {
        if(name.length() > 0 && !name.equals("Name") && !name.equals("player")) {
            return true;
        } else {
            Toast.makeText(this, "Please enter a valid username.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean allPointsAllocated() {
        if (player.getAvailableSkillPoints() == 0) {
            return true;
        } else {
            Toast.makeText(this, "Allocate all skill points.", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
