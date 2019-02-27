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
    private int[] location;
    private ArrayList<Planet> planets;



    // CONSTRUCTOR
    public SolarSystem() {
        Random r = new Random();
        name = chooseName(r.nextInt(availableNames.size()));
        location = new int[] {r.nextInt(150), r.nextInt(100)};
        planets = new ArrayList<>();
        planets.add(new Planet());
        planets.add(new Planet());
    }


    // GETTERS
    public String getName() { return name; }
    public int[] getLocation() { return location; }


    // Return a randomly chosen name from available names list, update list
    private String chooseName(int index) {
        String name = availableNames.get(index);
        availableNames.remove(index);
        return name;
    }
}
