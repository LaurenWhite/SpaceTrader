package edu.gatech.macpack.spacetrader.entity;

/**
 * Player class
 */
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

    /**
     * Creates new player object
     *
     * @param aName           given name
     * @param aSkillPoints    default skill points
     * @param aPilotPoints    selected pilot points
     * @param aFighterPoints  selected fighter points
     * @param aTraderPoints   selected trader points
     * @param aEngineerPoints selected engineer points
     */
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

    /**
     * Creates new player object before skill point assignment
     */
    public Player() {
        this("player", 16, 0, 0, 0, 0);
    }

    /**
     * Creates player object to be loaded to
     *
     * @param aName loaded name
     * @param aSkillPoints loaded skill points
     * @param aPilotPoints loaded pilot points
     * @param aFighterPoints loaded fighter points
     * @param aTraderPoints loaded trader points
     * @param aEngineerPoints loaded engineer points
     * @param spaceship loaded space ship object
     */
    public Player(String aName, int aSkillPoints, int aPilotPoints, int aFighterPoints,
                  int aTraderPoints, int aEngineerPoints, SpaceShip spaceship) {
        name = aName;
        credits = 1000;
        availableSkillPoints = aSkillPoints;
        pilotPoints = aPilotPoints;
        fighterPoints = aFighterPoints;
        traderPoints = aTraderPoints;
        engineerPoints = aEngineerPoints;
        this.spaceship = spaceship;
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

    /**
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * @param newName new player name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * @return player credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param newCredits new player credits
     */
    public void setCredits(int newCredits) { credits = newCredits;
    }

    /**
     * @return player available skill points
     */
    public int getAvailableSkillPoints() {
        return availableSkillPoints;
    }

    /**
     * @param newPoints new player points
     */
    public void setAvailableSkillPoints(int newPoints) { availableSkillPoints = newPoints;
    }

    /**
     * @return player pilot points
     */
    public int getPilotPoints() {
        return pilotPoints;
    }

    /**
     * @param newPoints new pilot points
     */
    public void setPilotPoints(int newPoints) { pilotPoints = newPoints;
    }


    // SETTERS

    /**
     * @return player fighter points
     */
    public int getFighterPoints() {
        return fighterPoints;
    }

    /**
     * @param newPoints new fighter points
     */
    public void setFighterPoints(int newPoints) { fighterPoints = newPoints;
    }

    /**
     * @return player trader points
     */
    public int getTraderPoints() {
        return traderPoints;
    }

    /**
     * @param newPoints new trader points
     */
    public void setTraderPoints(int newPoints) { traderPoints = newPoints;
    }

    /**
     * @return player engineer points
     */
    public int getEngineerPoints() {
        return engineerPoints;
    }

    /**
     * @param newPoints new engineer points
     */
    public void setEngineerPoints(int newPoints) { engineerPoints = newPoints;
    }

    /**
     * @return player space ship object
     */
    public SpaceShip getSpaceShip() {
        return spaceship;
    }

    /**
     * @param ship new ship object
     */
    public void setSpaceShip(SpaceShipType ship) { spaceship.setShipType(ship);}


    // FUNCTIONALITY

    /**
     * Determines if player has enough funds to purchase at that price
     *
     * @param price given price
     * @return if the player can afford it
     */
    public boolean sufficientFunds(int price) {
        return price <= credits;
    }
}
