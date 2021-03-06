package edu.gatech.macpack.spacetrader.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Planet object class
 */
public class Planet {

    // List of possible solar system names
    static private final List<String> availableNames = new ArrayList<>(
            Arrays.asList("Azelhart", "Ophilia", "Cassardis", "Grigori", "Diomedes", "Torim",
                    "Damascus", "Soren", "Valvatorez", "Fenrich", "Archimedes", "Anri", "Sterling",
                    "Lucis", "Ignis", "Noctis", "Ardyn", "Lunafreya", "Thanatos", "Aranea")
    );


    // ATTRIBUTES
    private final String name;
    private final List<Integer> location;
    private final TechLevel techLevel;
    private final ResourceType resource;
    private final int traderEventChance;
    private Map<String, MarketItem> market;
    private final String parentName;
    private final List<Integer> parentLocation;


    // CONSTRUCTOR

    /**
     * Creates a new planet object with generated values
     *
     * @param parentSystem solar system the planet is in
     */
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

    /**
     * Creates planet object from loaded values
     * @param name loaded name
     * @param location loaded location
     * @param techLevel loaded tech level
     * @param resource loaded resource
     * @param traderEventChance loaded trader event chance
     * @param market loaded market
     * @param parentName loaded parent solar system name
     * @param parentLocation loaded parent solar system location
     */
    public Planet(String name, List<Integer> location, TechLevel techLevel,
                  ResourceType resource, int traderEventChance, Map<String, MarketItem> market,
                  String parentName, List<Integer> parentLocation) {
        this.name = name;
        this.location = location;
        this.techLevel = techLevel;
        this.resource = resource;
        this.traderEventChance = traderEventChance;
        this.market = market;
        this.parentName = parentName;
        this.parentLocation = parentLocation;
    }

    // GETTERS

    /**
     * @return planet name
     */
    public String getName() { return name; }

    /**
     * @return location of planet
     */
    public List<Integer> getLocation() { return location; }

    /**
     * @return planet tech level
     */
    public TechLevel getTechLevel() { return techLevel; }

    /**
     * @return planet resource type
     */
    public ResourceType getResource() { return resource; }

    /**
     * @return trader event chance on planet
     */
    public int getTraderEventChance() { return traderEventChance; }

    /**
     * @return planet market
     */
    public Map<String, MarketItem> getMarket() { return market; }

    /**
     * @param market planet market
     */
    public void setMarket(Map<String, MarketItem> market) {
        this.market = market;
    }

    /**
     * @return parent solar system name
     */
    public String getParentName() { return parentName; }


    // SETTERS

    /**
     * @return parent solar system location
     */
    public List<Integer> getParentLocation() { return parentLocation; }


    // FUNCTIONALITY

    /**
     * Sell market item to a player
     *
     * @param item item to sell
     */
    public void sellToPlayer(MarketItem item) {

        TradeGood good = item.getGood();

        if(market.containsKey(good.name())) {
            int currentAmount = market.get(good.name()).getQuantity();

            if (currentAmount == item.getQuantity()) {
                market.remove(good.name());
            } else {
                market.get(good.name()).setQuantity(currentAmount - item.getQuantity());
            }
        }
    }

    /**
     * Buy a market item from the player
     *
     * @param item item to buy
     */
    public void buyFromPlayer(MarketItem item) {
        TradeGood good = item.getGood();

        if(market.containsKey(good.name())) {
            int currentAmount = market.get(good.name()).getQuantity();
            market.get(good.name()).setQuantity(currentAmount + item.getQuantity());
        } else {
            market.put(good.name(), item);
        }
    }

    /**
     * Determine if a good can be used on this planet
     *
     * @param good good to be determined
     * @return if it can be used
     */
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
