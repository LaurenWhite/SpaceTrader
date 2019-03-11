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
    private int[] location;
    private TechLevel techLevel;
    private ResourceType resource;
    private int traderEventChance;
    private Map<TradeGood, MarketItem> market;



    // CONSTRUCTOR
    public Planet() {
        Random r = new Random();
        name = chooseName(r.nextInt(availableNames.size()));
        location = new int[] {r.nextInt(40), r.nextInt(40)};
        techLevel = TechLevel.values()[r.nextInt(8)];
        resource = ResourceType.values()[r.nextInt(13)];
        traderEventChance = techLevel.getTechNum() + 2; // x 10 = %
        market = generateMarket();
        printMarket(); // for testing
    }



    // GETTERS
    public String getName() { return name; }

    public int[] getLocation() { return location; }

    public TechLevel getTechLevel() { return techLevel; }

    public ResourceType getResource() { return resource; }

    public int getTraderEventChance() { return traderEventChance; }

    public Map<TradeGood, MarketItem> getMarket() { return market; }



    // FUNCTIONALITY

    public void sellToPlayer(TradeGood good) {
        int currentAmount = market.get(good).getQuantity();
        if(currentAmount > 0) {
            market.get(good).setQuantity(currentAmount - 1);
        }
    }

    public void buyFromPlayer(TradeGood good) {
        int currentAmount = market.get(good).getQuantity();
        market.get(good).setQuantity(currentAmount - 1);
    }




    // Return a randomly chosen name from available names list, update list
    private String chooseName(int index) {
        String name = availableNames.get(index);
        availableNames.remove(index);
        return name;
    }

    private Map<TradeGood, MarketItem> generateMarket() {

        Map<TradeGood, MarketItem> planetGoods = new HashMap<>();
        int planetTechLvl = techLevel.getTechNum();

        for(TradeGood good : TradeGood.values()) {

            if(good.canProduce(planetTechLvl)) {
                int quantity = (int) Math.round(15 * (good.productionProbability(planetTechLvl)));
                int price = good.regionalPrice(planetTechLvl);

                MarketItem newItem = new MarketItem(good, quantity, price);
                planetGoods.put(good, newItem);
            }
        }

        return planetGoods;
    }

    // for testing
    private void printMarket() {
        System.out.println(name.toUpperCase() + " (TL: " + techLevel.TechNumber + ")");
        for(TradeGood good : TradeGood.values()) {
            if(market.containsKey(good)) {
                System.out.println("\t\t" + good.name());
                System.out.println("\t\t\tPrice: " + market.get(good).getPrice());
                System.out.println("\t\t\tQuantity: " + market.get(good).getQuantity());
            }
        }
        System.out.println();
    }
}
