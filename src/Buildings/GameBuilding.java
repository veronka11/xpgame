package Buildings;

import entity.Building;
import entity.MapPosition;

/**
 *
 * @author peto
 */
public class GameBuilding{
    public int level;
    public String Name;
    public double upgradeRate;
    public double upgradeLevelIncrease;
    public boolean isProductive;
    public int id;
    public int people;
    public int[] price, production;
    public MapPosition mapPosition;
    
    /*
    cela trieda upravena tak aby nebolo nutne pamatat si rodicovsky BuildingController
    budovy tak uchovavaju len data a celu logiku vykonava BuildingController
    */
    GameBuilding(Building data, MapPosition mp) {
        this.id = data.getId();
        level = 0;
        people = 0;
        mapPosition = mp;
        Name = data.getName();
        price = data.getPrice();
        if (data.hasUpgrade()) {
            upgradeLevelIncrease = data.getUpgrade().getLevelIncrease();
            upgradeRate = data.getUpgrade().getRate();
        }
        isProductive = data.isProductive();
        production = data.getProduction();
    }

    GameBuilding(Building data, int id) {
        this.id = id;
        level = 0;
        people = 0;
        Name = data.getName();
        price = data.getPrice();
        upgradeLevelIncrease = data.getUpgrade().getLevelIncrease();
        upgradeRate = data.getUpgrade().getRate();
        isProductive = data.isProductive();
        production = data.getProduction();
    }

    public GameBuilding(Building data) {
        this.id = data.getId();
        level = 0;
        people = 0;
        Name = data.getName();
        price = data.getPrice();
        if (data.hasUpgrade()) {
            upgradeLevelIncrease = data.getUpgrade().getLevelIncrease();
            upgradeRate = data.getUpgrade().getRate();
        }
        isProductive = data.isProductive();
        production = data.getProduction();
    }

    public void setLevel(int level){
        this.level = level;
    }
    
    public void upLevel(){
        level++;
        upgradeRate += upgradeLevelIncrease;
    }
    
    public int getWorkers(){
        return people;
    }

    public void addWorker(){
        people++;
    }

    public void removeWorker() {
        people--;
    }
    
    public boolean takeWorker(){
        if (people > 0) {
            removeWorker();
            return true;
        }
        return false;
    }
    
    double collect() {
        if(!isProductive){
            return upgradeRate;
        }
        return people * upgradeRate;
    }

    int getId() {
        return this.id;
    }

    int[] getPriceForNextLvl() {
        int[] p = {0, 0, 0, 0};
        for(int i = 0; i < price.length; i++){
            p[i] = price[i] * (level + 1);
        }
        return p;
    }

    public int[] getProduction() {
        return production;
    }

    public boolean isProductive() {
        return isProductive;
    }

    public boolean isAt(int row, int col) {
        return ((this.mapPosition.getRow() == row) && (this.mapPosition.getCol() == col));
    }

    public MapPosition getMapPosition() {
        return this.mapPosition;
    }
}
