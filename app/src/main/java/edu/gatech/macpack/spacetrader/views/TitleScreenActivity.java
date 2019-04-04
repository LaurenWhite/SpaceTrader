package edu.gatech.macpack.spacetrader.views;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;

public class TitleScreenActivity extends AppCompatActivity {

    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        usernameEditText = findViewById(R.id.username);
    }

    public void newGamePressed(View view) {
        Game.getGameInstance();
        Intent intent = new Intent(getBaseContext(), UserConfigurationActivity.class);
        startActivity(intent);
    }

    public void loadGamePressed(View view) {
        String username = usernameEditText.getText().toString();
        DatabaseInteractor dbInteractor = new DatabaseInteractor();
        dbInteractor.loadGame(username);
    }

}
