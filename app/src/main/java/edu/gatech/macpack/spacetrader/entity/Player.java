package edu.gatech.macpack.spacetrader.entity;

public class Player {

    // VARIABLES

    private String name;

    private int availableSkillPoints;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;

    private int credits;

    private SpaceShip spaceship;


    // CONSTRUCTOR

    public Player(String aName, int aSkillPoints, int aPilotPoints, int aFighterPoints,
                  int aTraderPoints, int aEngineerPoints) {
        name = aName;
        credits = 1000;
        availableSkillPoints = aSkillPoints;
        pilotPoints = aPilotPoints;
        fighterPoints = aFighterPoints;
        traderPoints = aTraderPoints;
        engineerPoints = aEngineerPoints;
        spaceship = new SpaceShip(SpaceShipType.GNAT);
    }

    public Player() {
        this("player", 16, 0, 0, 0, 0);
    }


    @Override
    public String toString() {
        return "Username: " + name +
                "\nCredits: " + credits +
                "\nSkill Points: " + availableSkillPoints +
                "\nPilot Points: " + pilotPoints +
                "\nFighter Points: " + fighterPoints +
                "\nTrader Points: " + traderPoints +
                "\nEngineer Points: " + engineerPoints +
                "\nSpace Ship: " + spaceship;
    }


    // GETTERS

    public String getName() { return name; }

    public int getCredits() { return credits; }

    public int getAvailableSkillPoints() { return availableSkillPoints; }

    public int getPilotPoints() { return pilotPoints; }

    public int getFighterPoints() { return fighterPoints; }

    public int getTraderPoints() { return traderPoints; }

    public int getEngineerPoints() { return engineerPoints; }

    public SpaceShip getSpaceShip() { return spaceship; }


    // SETTERS

    public void setName(String newName) { name = newName; }

    public void setCredits(int newCredits) { credits = newCredits; }

    public void setAvailableSkillPoints(int newPoints) { availableSkillPoints = newPoints; }

    public void setPilotPoints(int newPoints) { pilotPoints = newPoints; }

    public void setFighterPoints(int newPoints) { fighterPoints = newPoints; }

    public void setTraderPoints(int newPoints) { traderPoints = newPoints; }

    public void setEngineerPoints(int newPoints) { engineerPoints = newPoints; }

    public void setSpaceship(SpaceShipType ship) { spaceship.setShipType(ship);}


    // FUNCTIONALITY
    public boolean sufficientFunds(int price) {
        return price <= credits;
    }
}
