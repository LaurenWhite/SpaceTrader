package edu.gatech.macpack.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.DifficultyType;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;
import edu.gatech.macpack.spacetrader.views.MarketActivity;
import edu.gatech.macpack.spacetrader.views.TravelActivity;

/**
 * This creates the GameMainScreenModel class
 */
public class GameMainScreenViewModel extends AndroidViewModel {

    private final Game game = DatabaseInteractor.dbInteractor.game;
    private final Player player = game.getPlayer();
    private final SpaceShip ship = player.getSpaceShip();

    /**
     * This constructor creates an instance of the GameMainScreenModel class
     * @param application param to pass to the constructor of AndroidViewModel (super)
     */
    public GameMainScreenViewModel(@NonNull Application application) {
        super(application);
    }


    public void saveApplication() {
        Game game = DatabaseInteractor.dbInteractor.game;
        String username = game.getPlayer().getName();
        DatabaseInteractor.dbInteractor.saveGame(username, game);
    }

    public int getShipFuel(){
        return ship.getFuel();
    }

    public String shipTypeToString(){
        return ship.getShipType().toString();
    }
}
