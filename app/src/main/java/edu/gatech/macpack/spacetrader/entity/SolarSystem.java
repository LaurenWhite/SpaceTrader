package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


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



    // GETTERS
    public String getName() { return name; }

    public List<Integer> getLocation() { return location; }

    public List<Planet> getPlanets() { return planets; }




    // Return a randomly chosen name from available names list, update list
    private String chooseName(int index) {
        String name = availableNames.get(index);
        availableNames.remove(index);
        return name;
    }
}
