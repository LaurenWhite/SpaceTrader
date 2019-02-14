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

    private String spaceship;


    // CONSTRUCTOR

    public Player(String aName, int aSkillPoints, int aPilotPoints, int aFighterPoints,
                  int aTraderPoints, int aEngineerPoints) {
        name = aName;
        availableSkillPoints = aSkillPoints;
        pilotPoints = aPilotPoints;
        fighterPoints = aFighterPoints;
        traderPoints = aTraderPoints;
        engineerPoints = aEngineerPoints;
        credits = 1000;
        spaceship = "Gnat";
    }

    public Player() {
        this("player", 16, 0, 0, 0, 0);
    }



    // GETTERS

    public String getName() { return name; }

    public int getAvailableSkillPoints() { return availableSkillPoints; }

    public int getPilotPoints() { return pilotPoints; }

    public int getFighterPoints() { return fighterPoints; }

    public int getTraderPoints() { return traderPoints; }

    public int getEngineerPoints() { return engineerPoints; }


    // SETTERS

    public void setName(String newName) { name = newName; }

    public void setAvailableSkillPoints(int newPoints) { availableSkillPoints = newPoints; }

    public void setPilotPoints(int newPoints) { pilotPoints = newPoints; }

    public void setFighterPoints(int newPoints) { fighterPoints = newPoints; }

    public void setTraderPoints(int newPoints) { traderPoints = newPoints; }

    public void setEngineerPoints(int newPoints) { engineerPoints = newPoints; }


}
