package entity;

import java.util.ArrayList;

public class Building {
    private final int id;
    private final String name;
    private int[] price, production;
    private boolean productivity;
    private ArrayList<BuildingUpgrade> upgrades;
    
    public Building(int id, String name) {
        this.id = id;
        this.name = name;
        this.upgrades = new ArrayList<>();
    }
    
    public void addUpgrade(BuildingUpgrade upgradeData) {
        this.upgrades.add(upgradeData);
    }
    
    public void setPrice(int[] priceData) {
        this.price = priceData;
    }
    
    public void setProduction(boolean state) {
        this.productivity = false;
    }
    
    public void setProduction(int[] productData) {
        this.production = productData;
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

    public ArrayList<BuildingUpgrade> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(ArrayList<BuildingUpgrade> buildingUpgrades) {
        this.upgrades = buildingUpgrades;
    }
    
    public String toString() {
        return ("Building name:" + this.name + "| Price:" + this.price[0] + "-" + this.price[1] + "-" + this.price[2] + "-" + this.price[3] + "| Total Upgrades: " + this.upgrades.size());
    }

}
