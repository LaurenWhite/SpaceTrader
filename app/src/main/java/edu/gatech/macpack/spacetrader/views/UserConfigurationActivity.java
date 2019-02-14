package edu.gatech.macpack.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.views.UserConfigurationActivity;
import edu.gatech.macpack.spacetrader.entity.Player;

public class UserConfigurationActivity extends AppCompatActivity {

    Player player = new Player();

    private EditText nameField;
    private TextView skillPoints;
    private TextView pilotPoints;
    private TextView fighterPoints;
    private TextView traderPoints;
    private TextView engineerPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_configuration);

        nameField = findViewById(R.id.username_input);

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
    }


    private void updatePointLabels() {
        skillPoints.setText("Available Skill Points: " + player.getAvailableSkillPoints());
        pilotPoints.setText(Integer.toString(player.getPilotPoints()));
        fighterPoints.setText(Integer.toString(player.getFighterPoints()));
        traderPoints.setText(Integer.toString(player.getTraderPoints()));
        engineerPoints.setText(Integer.toString(player.getEngineerPoints()));
    }




    public void subtractPilotPoint(View view) {
        if (player.getPilotPoints() > 0) {
            player.setPilotPoints(player.getPilotPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
            updatePointLabels();
        }
    }

    public void addPilotPoint(View view) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setPilotPoints(player.getPilotPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
            updatePointLabels();
        }
    }

    public void subtractFighterPoint(View view) {
        if (player.getFighterPoints() > 0) {
            player.setFighterPoints(player.getFighterPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
            updatePointLabels();
        }
    }

    public void addFighterPoint(View view) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setFighterPoints(player.getFighterPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
            updatePointLabels();
        }
    }

    public void subtractTraderPoint(View view) {
        if (player.getTraderPoints() > 0) {
            player.setTraderPoints(player.getTraderPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
            updatePointLabels();
        }
    }

    public void addTraderPoint(View view) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setTraderPoints(player.getTraderPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
            updatePointLabels();
        }
    }

    public void subtractEngineerPoint(View view) {
        if (player.getEngineerPoints() > 0) {
            player.setEngineerPoints(player.getEngineerPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
            updatePointLabels();
        }
    }

    public void addEngineerPoint(View view) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setEngineerPoints(player.getEngineerPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
            updatePointLabels();
        }
    }
}
