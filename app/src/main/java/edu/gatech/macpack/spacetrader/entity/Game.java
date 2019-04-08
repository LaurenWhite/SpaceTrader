package edu.gatech.macpack.spacetrader.entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates game object class
 */
public class Game {

    // Singleton model?
    //private static Game instance;
    //public Game getGameInstance() { return instance; }


    // ATTRIBUTES
    private Player player;
    private List<SolarSystem> solarSystems;
    private DifficultyType difficulty;


    // CONSTRUCTOR

    /**
     * Creates a new game from generated values if the game is completely new or
     * create an empty game object if being loaded
     *
     * @param newGame flag for if the game object is new or loaded
     */
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


    // GETTERS

    /**
     * @return solar system object
     */
    public List<SolarSystem> getSolarSystems() { return solarSystems; }

    /**
     * @param newSolarSystems new solar system object
     */
    public void setSolarSystems(List<SolarSystem> newSolarSystems) {
        solarSystems = newSolarSystems;
    }

    /**
     * @return difficulty object
     */
    public DifficultyType getDifficulty() { return difficulty; }

    /**
     * @param newDifficulty new difficulty setting
     */
    public void setDifficulty(DifficultyType newDifficulty) {
        difficulty = newDifficulty;
    }


    // SETTERS

    /**
     * @return player object
     */
    public Player getPlayer() { return player; }

    /**
     * @param newPlayer new player object
     */
    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    /**
     * Gets the first planet in the game to start player on
     *
     * @return first planet created
     */
    public Planet getStartingLocation() {
        SolarSystem firstSolarSystem = solarSystems.get(0);
        Planet firstPlanet = firstSolarSystem.getPlanets().get(0);
        return firstPlanet;
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

  
    /**
     * Prints given solar system in LOGCAT, for testing
     *
     * @param systems solar system to print
     */
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
