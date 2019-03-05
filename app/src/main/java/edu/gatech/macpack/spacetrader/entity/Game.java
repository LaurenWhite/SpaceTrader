package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // Singleton model?
    private static Game instance = new Game();
    public static Game getGameInstance() { return instance; }


    // ATTRIBUTES
    private ArrayList<SolarSystem> solarSystems;
    private DifficultyType difficulty;



    // CONSTRUCTOR
    public Game() {
        solarSystems = generateSolarSystems();
        difficulty = DifficultyType.BEGINNER;
    }


    // GETTERS
    public ArrayList<SolarSystem> getSolarSystems() { return solarSystems; }

    public DifficultyType getDifficulty() { return difficulty; }


    // SETTERS
    public void setDifficulty(DifficultyType newDifficulty) { difficulty = newDifficulty; }



    // Create the 10 solar systems that will make up this universe
    private ArrayList<SolarSystem> generateSolarSystems() {

        ArrayList<SolarSystem> systems = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            systems.add(new SolarSystem());
        }

        universeToString(systems);

        return systems;
    }

    // Displays the universe in logcat
    public void universeToString(List<SolarSystem> systems) {
        System.out.println("******UNIVERSE GENERATED******");
        for (SolarSystem system : systems) {
            System.out.println("***SOLAR SYSTEM***");
            System.out.println("Solar system: " + system.getName());
            System.out.println("\nLocation: (" + system.getLocation()[0] + ", " + system.getLocation()[1] + ")");
            System.out.println("Planets: (" + system.getPlanets().get(0).getName() + ", " + system.getPlanets().get(0).getName() + ")");
            for (Planet orb : system.getPlanets()) {
                System.out.println("\n***PLANET***");
                System.out.println("Planet: " + orb.getName());
                System.out.println("Tech level: " + orb.getTechLevel());
                System.out.println("Resource: " + orb.getResource());
            }
        }
    }
}
