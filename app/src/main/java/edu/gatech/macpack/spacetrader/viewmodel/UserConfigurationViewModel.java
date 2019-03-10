package edu.gatech.macpack.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.macpack.spacetrader.entity.DifficultyType;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Player;

public class UserConfigurationViewModel extends AndroidViewModel {

    Game game = Game.getGameInstance();

    public UserConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    public void subtractPilotPoint(Player player) {
        if (player.getPilotPoints() > 0) {
            player.setPilotPoints(player.getPilotPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    public void addPilotPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setPilotPoints(player.getPilotPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    public void subtractFighterPoint(Player player) {
        if (player.getFighterPoints() > 0) {
            player.setFighterPoints(player.getFighterPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    public void addFighterPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setFighterPoints(player.getFighterPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    public void subtractTraderPoint(Player player) {
        if (player.getTraderPoints() > 0) {
            player.setTraderPoints(player.getTraderPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    public void addTraderPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setTraderPoints(player.getTraderPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    public void subtractEngineerPoint(Player player) {
        if (player.getEngineerPoints() > 0) {
            player.setEngineerPoints(player.getEngineerPoints() - 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() + 1);
        }
    }

    public void addEngineerPoint(Player player) {
        if (player.getAvailableSkillPoints() > 0) {
            player.setEngineerPoints(player.getEngineerPoints() + 1);
            player.setAvailableSkillPoints(player.getAvailableSkillPoints() - 1);
        }
    }

    public void changeName(Player player, String newName) {
        player.setName(newName);
    }

    public void changeDifficulty(DifficultyType newDifficulty) {
        game.setDifficulty(newDifficulty);
    }

    public void updatePlayer(Player player) {
        game.setPlayer(player);
    }
}
