package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Planet {

    // List of possible solar system names
    static private List<String> availableNames = new ArrayList<>(
            Arrays.asList("Azelhart", "Ophilia", "Cassardis", "Grigori", "Diomedes", "Torim",
                    "Damascus", "Soren", "Valvatorez", "Fenrich", "Archimedes", "Anri", "Sterling",
                    "Lucis", "Ignis", "Noctis", "Ardyn", "Lunafreya", "Thanatos", "Aranea")
    );


    // ATTRIBUTES
    private String name;
    private int[] location;
    // private TechLevel techLevel;
    // private Resource resource;
    private int traderEventChance;


    // CONSTRUCTOR
    public Planet() {
        Random r = new Random();
        name = chooseName(r.nextInt(availableNames.size()));
        location = new int[] {r.nextInt(40), r.nextInt(40)};
        // techLevel = TechLevel.valueOf(r.nextInt(8));
        // resource = Resource.valueOf(r.nextInt(13));
        // traderEventChance = TechLevel.getNumber() + 2; // x 10 = %
    }

    // GETTERS
    public String getName() { return name; }
    public int[] getLocation() { return location; }
    //public TechLevel getTechLevel() { return techLevel; }
    //public Resource getResource() { return resource; }
    public int getTraderEventChance() { return traderEventChance; }

    // Return a randomly chosen name from available names list, update list
    private String chooseName(int index) {
        String name = availableNames.get(index);
        availableNames.remove(index);
        return name;
    }
}
