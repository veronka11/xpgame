package entity;

import xpgame.NullNameBuildingUpgradeException;

public class BuildingUpgrade {
    private final String name;
    private final double rate, levelIncrease;
    
    public BuildingUpgrade(String name, double rate, double levelIncrease) throws NullNameBuildingUpgradeException {
        if (name == null) throw new NullNameBuildingUpgradeException("Building Upgrade parameter name cannot be null!");
        this.name = name;
        this.rate = rate;
        this.levelIncrease = levelIncrease;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getLevelIncrease() {
        return levelIncrease;
    }
    
}
