package entity;

import xpgame.Commodity;

public class Building {
    private final int id;
    private final String name;
    private int[] price, production;
    private boolean productivity;
    private BuildingUpgrade upgrade;
    
    public Building(int id, String name) {
        this.id = id;
        this.name = name;
        this.upgrade = null;
    }
    
    public void setPrice(int[] priceData) {
        this.price = priceData;
    }
    
    public void setProductivity(boolean state) {
        this.productivity = state;
    }
    
    public void setProduction(int[] productData) {
        this.production = productData;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int[] getPrice() {
        return price;
    }

    public int[] getProduction() {
        return production;
    }

    public boolean isProductive() {
        return productivity;
    }

    public BuildingUpgrade getUpgrade() {
        return upgrade;
    }
    
    public String toString() {
        return ("Building name:" + this.name +
                "| Price:" + this.price[Commodity.FOOD.ordinal()] +
                "-" + this.price[Commodity.GOLD.ordinal()] +
                "-" + this.price[Commodity.STONE.ordinal()] +
                "-" + this.price[Commodity.WOOD.ordinal()] +
                "| Upgrade: " + this.upgrade.getName());
    }

    public void setUpgrade(BuildingUpgrade tempBuildingUpgrade) {
        this.upgrade = tempBuildingUpgrade;
    }

    public boolean hasUpgrade() {
        return !(this.upgrade == null);
    }
}
