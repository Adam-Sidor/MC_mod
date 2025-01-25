package com.example.adventuremod.capabilities;

public interface IPlayerAlcohol {
    int getAlcoholLevel();
    boolean setAlcoholLevel(int level);
    boolean addAlcohol(int amount);
    boolean reduceAlcohol(int amount);
}
