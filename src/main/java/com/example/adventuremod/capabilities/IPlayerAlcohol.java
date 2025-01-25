package com.example.adventuremod.capabilities;

public interface IPlayerAlcohol {
    int getAlcoholLevel();
    void setAlcoholLevel(int level);
    void addAlcohol(int amount);
    void reduceAlcohol(int amount);
}
