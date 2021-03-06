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
    
    int[] collect() throws Exception {
        if(!isProductive){
            return null;
        }
        if (this.production == null) throw new Exception("No production data is set");
        int[] newCollect = this.production.clone();
        double collectionRate = people * upgradeRate;
        for (int i = 0; i < newCollect.length; i++) {
            newCollect[i] = (int)(newCollect[i] * collectionRate);
            System.out.println(newCollect[i] + " production -> " + this.production[0] + ", " + this.production[1] + ", " + this.production[2] + ", " + this.production[3]);
        }

        return newCollect;
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

    public void setMapPosition(MapPosition mapPosition) {
        this.mapPosition = mapPosition;
    }
    
    public void setWorkers(int n){
        people=n;
    }
}
