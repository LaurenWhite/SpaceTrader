package edu.gatech.macpack.spacetrader.viewmodel;

import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.app.Application;

import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;

/**
 * This creates the TitleScreenViewModel class
 */
public class TitleScreenViewModel extends AndroidViewModel {


    /**
     * This constructor creates an instance of the TitleScreenViewModel class
     * @param application param to pass to the constructor of AndroidViewModel (super)
     */
    public TitleScreenViewModel(@NonNull Application application) { super(application); }

    /**
     * This is a method that creates a new game
     */
    public void newGameButtonPressed() {
        DatabaseInteractor.dbInteractor.createGame();
    }

    /**
     * This is a method that loads the game from the database depending on the user
     * @param user the username to load the game from
     */
    public void loadGameButtonPressed(String user) {
        DatabaseInteractor.dbInteractor.loadGame(user);
    }

}
