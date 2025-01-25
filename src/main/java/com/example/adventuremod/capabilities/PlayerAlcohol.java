package com.example.adventuremod.capabilities;

public class PlayerAlcohol implements IPlayerAlcohol {
    private int alcoholLevel = 0;

    @Override
    public int getAlcoholLevel() {
        return alcoholLevel;
    }

    @Override
    public boolean setAlcoholLevel(int level) {
        if(level > 0 && level <= 20) {
            this.alcoholLevel = level;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAlcohol(int amount) {
        int newLevel = Math.min(20, this.alcoholLevel + amount);
        if(this.alcoholLevel != newLevel) {
            this.alcoholLevel = newLevel;
            return true;
        }
        return false;
    }

    @Override
    public boolean reduceAlcohol(int amount) {
        int newLevel = Math.max(0, this.alcoholLevel - amount);
        if(this.alcoholLevel != newLevel) {
            this.alcoholLevel = newLevel;
            return true;
        }
        return false;
    }

}
