package edu.gatech.macpack.spacetrader.entity;

public enum DifficultyType {

    EASY("Easy"),
    NORMAL("Normal"),
    HARD("Hard");



    public String Difficulty;

    //Enum constructor
    DifficultyType(String shipType) { this.Difficulty = shipType; }

}
