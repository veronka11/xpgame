package entity;

import java.util.ArrayList;

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
        this.price = priceData; // TODO throw exception if price data is not length 4
    }
    
    public void setProduction(boolean state) {
        this.productivity = false; // TODO remove state parameter, or use it
    }
    
    public void setProduction(int[] productData) {
        this.production = productData; // TODO throw exception if product data is not length 4
        this.productivity = true;
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
        return ("Building name:" + this.name + "| Price:" + this.price[0] + "-" + this.price[1] + "-" + this.price[2] + "-" + this.price[3] + "| Upgrade: " + this.upgrade.getName());
    }

    public void setUpgrade(BuildingUpgrade tempBuildingUpgrade) {
        this.upgrade = tempBuildingUpgrade;
    }
}
