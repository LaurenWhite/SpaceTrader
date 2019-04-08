package edu.gatech.macpack.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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


/**
 * This is the UserConfigurationActivity class that displays the configuration screen and player creation
 */
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
        ArrayAdapter<DifficultyType> adapter = new
                ArrayAdapter<>(this, R.layout.spinner_item, DifficultyType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);
    }


    /**
     * This method updates the number of points for each skill on the screen as the player adds them and subtracts
     */
    private void updatePointLabels() {
        skillPoints.setText("Available Skill Points: " + player.getAvailableSkillPoints());
        pilotPoints.setText(Integer.toString(player.getPilotPoints()));
        fighterPoints.setText(Integer.toString(player.getFighterPoints()));
        traderPoints.setText(Integer.toString(player.getTraderPoints()));
        engineerPoints.setText(Integer.toString(player.getEngineerPoints()));
    }



    /**
     * This method calls the subtractPilotPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void subtractPilotPointPressed(View view) {
        viewModel.subtractPilotPoint(player);
        updatePointLabels();
    }

    /**
     * This method calls the addPilotPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void addPilotPointPressed(View view) {
        viewModel.addPilotPoint(player);
        updatePointLabels();
    }

    /**
     * This method calls the subtractFighterPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void subtractFighterPointPressed(View view) {
        viewModel.subtractFighterPoint(player);
        updatePointLabels();
    }

    /**
     * This method calls the addFighterPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void addFighterPointPressed(View view) {
        viewModel.addFighterPoint(player);
        updatePointLabels();
    }

    /**
     * This method calls the subtractTraderPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void subtractTraderPointPressed(View view) {
        viewModel.subtractTraderPoint(player);
        updatePointLabels();
    }

    /**
     * This method calls the addTraderPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void addTraderPointPressed(View view) {
        viewModel.addTraderPoint(player);
        updatePointLabels();
    }

    /**
     * This method calls the subtractEngineerPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void subtractEngineerPointPressed(View view) {
        viewModel.subtractEngineerPoint(player);
        updatePointLabels();
    }

    /**
     * This method calls the addEngineerPoint(Player player) method when the button is pressed
     * @param view UI object
     */
    public void addEngineerPointPressed(View view) {
        viewModel.addEngineerPoint(player);
        updatePointLabels();
    }

    /**
     * This method creates a player upon the create user button being pressed
     * @param view UI object
     */
    public void createUserPressed(View view) {

        if(isValidUsername(nameField.getText().toString())) {

            if (allPointsAllocated()) {
                viewModel.changeName(player, nameField.getText().toString());
                viewModel.changeDifficulty((DifficultyType) difficultySpinner.getSelectedItem());

                Log.i("***NEW PLAYER***","*****NEW PLAYER CREATED*******\n" + player);
                viewModel.updatePlayer(player);

                Intent intent = new Intent(getBaseContext(), GameMainScreenActivity.class);
                startActivity(intent);
            }
        }
    }

    /**
     * This method checks to see if the player/user entered a valid username
     * @param name the string of the inputted name to check
     * @return true if the user is valid, false if user is not
     */
    private boolean isValidUsername(String name) {
        if(name.length() > 0 && !name.equals("Name") && !name.equals("player")) {
            return true;
        } else {
            Toast.makeText(this, "Please enter a valid username.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * This method checks to see if the player has allocated all of their skill points
     * @return true if all points have been allocated, false if not
     */
    private boolean allPointsAllocated() {
        if (player.getAvailableSkillPoints() == 0) {
            return true;
        } else {
            Toast.makeText(this, "Allocate all skill points.", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
