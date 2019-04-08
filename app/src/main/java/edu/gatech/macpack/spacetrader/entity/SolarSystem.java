package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * This is a class that represents the game's solar system
 */
public class SolarSystem {

    // List of possible solar system names
    static private List<String> availableNames = new ArrayList<>(
            Arrays.asList("Nix", "Morag", "Xena", "Nobiru", "Vesperia", "Xillia", "Ventus",
                    "Lapis", "Altair", "Zeno", "Valor", "Therion", "Cyrus", "Gaius")
    );


    // ATTRIBUTES
    private String name;
    private List<Integer> location;
    private List<Planet> planets;



    // CONSTRUCTOR

    /**
     * This is a constructor that creates a solar system with the following random attributes
     */
    public SolarSystem() {
        Random r = new Random();
        name = chooseName(r.nextInt(availableNames.size()));
        location = new ArrayList<>();
        location.add(r.nextInt(500));
        location.add(r.nextInt(500));
        planets = new ArrayList<>();
        planets.add(new Planet(this));
        planets.add(new Planet(this));
    }

    /**
     * This is a constructor that creates a solar system with the following attributes
     * @param name the name of the solar system
     * @param location list of locations
     * @param planets the list of planets in the solar systems
     */
    public SolarSystem(String name, List<Integer> location, List<Planet> planets) {
        this.name = name;
        this.location = location;
        this.planets = planets;
    }



    // GETTERS

    /**
     * This is a getter method that returns the name of the solar system
     * @return the name
     */
    public String getName() { return name; }

    /**
     * This is a getter method that returns the location of the planet
     * @return planet lcoation
     */
    public List<Integer> getLocation() { return location; }

    /**
     * This is a getter method that returns a list of the planets within the solar system
     * @return list of planets
     */
    public List<Planet> getPlanets() { return planets; }


    // SETTERS

    /**
     * This is a setter method that sets the list of planets for the solar system
     * @param planets the list of planets
     */
    public void setPlanets(List<Planet> planets) { this.planets = planets; }

    // Return a randomly chosen name from available names list, update list
    private String chooseName(int index) {
        String name = availableNames.get(index);
        availableNames.remove(index);
        return name;
    }
}
