package edu.gatech.macpack.spacetrader.entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // Singleton model?
    //private static Game instance;
    //public Game getGameInstance() { return instance; }


    // ATTRIBUTES
    private Player player;
    private List<SolarSystem> solarSystems;
    private DifficultyType difficulty;



    // CONSTRUCTOR
    public Game(Boolean newGame) {
        if(newGame) {
            solarSystems = generateSolarSystems();
            difficulty = DifficultyType.BEGINNER;
        } else {
            player = null;
            solarSystems = null;
            difficulty = null;
        }
    }

    public Game(Game game) {
        player = game.getPlayer();
        solarSystems = game.getSolarSystems();
        difficulty = game.getDifficulty();
    }


    // GETTERS
    public List<SolarSystem> getSolarSystems() { return solarSystems; }

    public DifficultyType getDifficulty() { return difficulty; }

    public Player getPlayer() { return player; }

    public Planet getStartingLocation() {
        SolarSystem firstSolarSystem = solarSystems.get(0);
        Planet firstPlanet = firstSolarSystem.getPlanets().get(0);
        return firstPlanet;
    }


    // SETTERS
    public void setDifficulty(DifficultyType newDifficulty) { difficulty = newDifficulty; }

    public void setPlayer(Player newPlayer) { player = newPlayer; }

    public void setSolarSystems(List<SolarSystem> newSolarSystems) {
        solarSystems = newSolarSystems;
    }

    // Create the 10 solar systems that will make up this universe
    private List<SolarSystem> generateSolarSystems() {

        List<SolarSystem> systems = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            systems.add(new SolarSystem());
        }

        universeToString(systems);

        return systems;
    }

    // Displays the universe in logcat, for testing
    public void universeToString(List<SolarSystem> systems) {
        Log.i("INFO","******UNIVERSE GENERATED******");
        for (SolarSystem system : systems) {
            Log.i("INFO","***SOLAR SYSTEM***");
            Log.i("INFO","Solar system: " + system.getName());
            Log.i("INFO","\nLocation: (" + system.getLocation().get(0) +
                    ", " + system.getLocation().get(1) +
                    ")")
            ;
            Log.i("INFO","Planets: (" +
                    system.getPlanets().get(0).getName() +
                    ", " +
                    system.getPlanets().get(0).getName() +
                    ")"
            );
            for (Planet orb : system.getPlanets()) {
                Log.i("INFO","\n***PLANET***");
                Log.i("INFO","Planet: " + orb.getName());
                Log.i("INFO","Tech level: " + orb.getTechLevel());
                Log.i("INFO","Resource: " + orb.getResource());
            }
        }
    }
}
