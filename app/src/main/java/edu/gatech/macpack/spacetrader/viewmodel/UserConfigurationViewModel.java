package edu.gatech.macpack.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.DifficultyType;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Player;

/**
 * This creates the UserConfigurationModel class
 */
public class UserConfigurationViewModel extends AndroidViewModel {

    private final Game game = DatabaseInteractor.dbInteractor.game;

    /**
     * This constructor creates an instance of the UserConfigurationModel class
     * @param application param to pass to the constructor of AndroidViewModel (super)
     */
    public UserConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * This method subtracts pilot points from the player's skill points
     * @param player the player to subtract the points from
     */
    public void subtractPilotPoint(Player player) {
        if (player.getPilotPoints() > 0) {
            player.setPilotPoints(player.getPilotPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    /**
     * This method adds pilot points to the player's skill points
     * @param player the player to add the points to
     */
    public void addPilotPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setPilotPoints(player.getPilotPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    /**
     * This method subtracts fighter points from the player's skill points
     * @param player the player to subtract the points from
     */
    public void subtractFighterPoint(Player player) {
        if (player.getFighterPoints() > 0) {
            player.setFighterPoints(player.getFighterPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    /**
     * This method adds fighter points to the player's skill points
     * @param player the player to add the points to
     */
    public void addFighterPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setFighterPoints(player.getFighterPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    /**
     * This method subtracts trader points from the player's skill points
     * @param player the player to subtract the points from
     */
    public void subtractTraderPoint(Player player) {
        if (player.getTraderPoints() > 0) {
            player.setTraderPoints(player.getTraderPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    /**
     * This method adds trader points to the player's skill points
     * @param player the player to add the points to
     */
    public void addTraderPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setTraderPoints(player.getTraderPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    /**
     * This method subtracts engineer points from the player's skill points
     * @param player the player to subtract the points from
     */
    public void subtractEngineerPoint(Player player) {
        if (player.getEngineerPoints() > 0) {
            player.setEngineerPoints(player.getEngineerPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    /**
     * This method adds engineer points to the player's skill points
     * @param player the player to add the points to
     */
    public void addEngineerPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setEngineerPoints(player.getEngineerPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    /**
     * This method changes the name of the player
     * @param player the player to change the name of
     * @param newName the new name for the player
     */
    public void changeName(Player player, String newName) {
        player.setName(newName);
    }

    /**
     * This method sets/changes the difficulty of the game
     * @param newDifficulty difficulty for game to be set in
     */
    public void changeDifficulty(DifficultyType newDifficulty) {
        game.setDifficulty(newDifficulty);
    }

    /**
     * This method sets the player for the given game to the player passed in
     * @param player the user/player created for the game
     */
    public void updatePlayer(Player player) {
        game.setPlayer(player);
    }
}
