package edu.gatech.macpack.spacetrader.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;

/**
 * This is the TitleScreenActivity class that displays the opening screen of the game and inputs/buttons for the user/player
 */
public class TitleScreenActivity extends AppCompatActivity {

    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        MediaPlayer mp = MediaPlayer.create(this, R.raw.spacetrader_intro);
        mp.start();

        usernameEditText = findViewById(R.id.username);
    }

    /**
     * This is a method that creates a new game upon the new game button being pressed
     * @param view UI object
     */
    public void newGamePressed(View view) {
        DatabaseInteractor.dbInteractor.createGame();
        Intent intent = new Intent(getBaseContext(), UserConfigurationActivity.class);
        startActivity(intent);
    }

    /**
     * This is a method that loads a previous game upon the load game button being pressed
     * @param view UI object
     */
    public void loadGamePressed(View view) {
        String username = usernameEditText.getText().toString();
        DatabaseInteractor.dbInteractor.loadGame(username);
        Intent intent = new Intent(getBaseContext(), GameMainScreenActivity.class);
        startActivity(intent);
    }

}
