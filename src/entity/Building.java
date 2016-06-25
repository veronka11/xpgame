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
    
    public void setPrice(int[] priceData) throws Exception {
        if (priceData.length != 4) throw new Exception("Price data size can't be size:" + priceData.length + ", expecting 4.");
        this.price = priceData;
    }
    
    public void setProductivity(boolean state) {
        this.productivity = state;
    }
    
    public void setProduction(int[] productData) {
        this.production = productData;
        if (productData.length != 0) {
            this.productivity = true;
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append("Building name:" + this.name);
        sb.append("| Price:" + this.price[Commodity.FOOD.ordinal()]);
        sb.append("-" + this.price[Commodity.GOLD.ordinal()]);
        sb.append("-" + this.price[Commodity.STONE.ordinal()]);
        sb.append("-" + this.price[Commodity.WOOD.ordinal()]);
        if (this.upgrade != null) {
            sb.append("| Upgrade: " + this.upgrade.getName());
        }

        return sb.toString();
    }

    public void setUpgrade(BuildingUpgrade tempBuildingUpgrade) {
        this.upgrade = tempBuildingUpgrade;
    }

    public boolean hasUpgrade() {
        return !(this.upgrade == null);
    }
}
