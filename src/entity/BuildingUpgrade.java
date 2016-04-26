package entity;

public class BuildingUpgrade {
    private final String name;
    private final int type;
    private final double rate, levelIncrease;
    
    public BuildingUpgrade(String name, int type, double rate, double levelIncrease) {
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.levelIncrease = levelIncrease;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public double getLevelIncrease() {
        return levelIncrease;
    }
    
}
