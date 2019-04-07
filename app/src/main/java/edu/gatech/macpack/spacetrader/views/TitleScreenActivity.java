package edu.gatech.macpack.spacetrader.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;

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

    public void newGamePressed(View view) {
        DatabaseInteractor.dbInteractor.createGame();
        Intent intent = new Intent(getBaseContext(), UserConfigurationActivity.class);
        startActivity(intent);
    }

    public void loadGamePressed(View view) {
        String username = usernameEditText.getText().toString();
        DatabaseInteractor.dbInteractor.loadGame(username);
        Intent intent = new Intent(getBaseContext(), GameMainScreenActivity.class);
        startActivity(intent);
    }

}
