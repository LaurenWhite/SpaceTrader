package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;

public class Game {


    private static Game instance = new Game();

    public static Game getGameInstance() { return instance; }


    // ATTRIBUTES
    private ArrayList<SolarSystem> solarSystems;
    //private Difficulty difficulty;


    // CONSTRUCTOR
    public Game() {
        solarSystems = generateSolarSystems();
        // difficulty = Difficulty.BEGINNER;
    }


    // GETTERS
    public ArrayList<SolarSystem> getSolarSystems() { return solarSystems; }
    //public Difficulty getDifficulty() { return difficulty; }


    // Create the 10 solar systems that will make up this universe
    private ArrayList<SolarSystem> generateSolarSystems() {

        ArrayList<SolarSystem> systems = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            systems.add(new SolarSystem());
        }

        return systems;
    }

}
