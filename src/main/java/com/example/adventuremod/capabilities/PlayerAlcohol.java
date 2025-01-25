package com.example.adventuremod.capabilities;

public class PlayerAlcohol implements IPlayerAlcohol {
    private int alcoholLevel = 0;

    @Override
    public int getAlcoholLevel() {
        return alcoholLevel;
    }

    @Override
    public void setAlcoholLevel(int level) {
        this.alcoholLevel = level;
    }

    @Override
    public void addAlcohol(int amount) {
        this.alcoholLevel += amount;
    }
}
