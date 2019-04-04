package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private List<Integer> location;
    private TechLevel techLevel;
    private ResourceType resource;
    private int traderEventChance;
    private Map<String, MarketItem> market;
    private String parentName;
    private List<Integer> parentLocation;



    // CONSTRUCTOR
    public Planet(SolarSystem parentSystem) {
        Random r = new Random();
        name = chooseName(r.nextInt(availableNames.size()));
        location = new ArrayList<>();
        location.add(r.nextInt(40));
        location.add(r.nextInt(40));
        techLevel = TechLevel.values()[r.nextInt(8)];
        resource = ResourceType.values()[r.nextInt(13)];
        traderEventChance = techLevel.getTechNum() + 2; // x 10 = %
        market = generateMarket();
        parentName = parentSystem.getName();
        parentLocation = parentSystem.getLocation();
        printMarket(); // for testing
    }



    // GETTERS
    public String getName() { return name; }

    public List<Integer> getLocation() { return location; }

    public TechLevel getTechLevel() { return techLevel; }

    public ResourceType getResource() { return resource; }

    public int getTraderEventChance() { return traderEventChance; }

    public Map<String, MarketItem> getMarket() { return market; }

    public String getParentName() { return parentName; }

    public List<Integer> getParentLocation() { return parentLocation; }



    // FUNCTIONALITY

    public void sellToPlayer(MarketItem item) {

        TradeGood good = item.getGood();

        if(market.containsKey(good)) {
            int currentAmount = market.get(good).getQuantity();

            if (currentAmount == item.getQuantity()) {
                market.remove(good);
            } else {
                market.get(good).setQuantity(currentAmount - item.getQuantity());
            }
        }
    }

    public void buyFromPlayer(MarketItem item) {
        TradeGood good = item.getGood();

        if(market.containsKey(good.name())) {
            int currentAmount = market.get(good.name()).getQuantity();
            market.get(good.name()).setQuantity(currentAmount + item.getQuantity());
        } else {
            market.put(good.name(), item);
        }
    }

    public boolean canUse(TradeGood good) {
        return techLevel.TechNumber >= good.getMTLU();
    }



    // Return a randomly chosen name from available names list, update list
    private String chooseName(int index) {
        String name = availableNames.get(index);
        availableNames.remove(index);
        return name;
    }

    private Map<String, MarketItem> generateMarket() {

        Map<String, MarketItem> planetGoods = new HashMap<>();
        int planetTechLvl = techLevel.getTechNum();

        for(TradeGood good : TradeGood.values()) {

            if(good.canProduce(planetTechLvl)) {
                int quantity = (int) Math.round(15 * (good.productionProbability(planetTechLvl)));
                int price = good.regionalPrice(planetTechLvl);

                MarketItem newItem = new MarketItem(good, quantity, price);
                planetGoods.put(good.name(), newItem);
            }
        }

        return planetGoods;
    }

    // for testing
    private void printMarket() {
        System.out.println(name.toUpperCase() + " (TL: " + techLevel.TechNumber + ")");
        for(TradeGood good : TradeGood.values()) {
            if(market.containsKey(good.name())) {
                System.out.println("\t\t" + good.name());
                System.out.println("\t\t\tPrice: " + market.get(good.name()).getPrice());
                System.out.println("\t\t\tQuantity: " + market.get(good.name()).getQuantity());
            }
        }
        System.out.println();
    }
}
