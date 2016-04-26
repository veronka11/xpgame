package Buildings;

import entity.Building;
import xpgame.Commodity;

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
    public int type;
    public int people;
    public int[] price;
    
    /*
    cela trieda upravena tak aby nebolo nutne pamatat si rodicovsky BuildingController
    budovy tak uchovavaju len data a celu logiku vykonava BuildingController
    */
    GameBuilding(Building data, int id) {
        type=id;
        level=0;
        people=0;
        Name=data.getName();
        price=data.getPrice();
        upgradeLevelIncrease=data.getUpgrades().get(0).getLevelIncrease();
        upgradeRate=data.getUpgrades().get(0).getRate();
        isProductive=data.isProductive();
    }
    
    public void setLevel(int level){
        this.level=level;
    }
    
    public void upLevel(){
        level++;
        upgradeRate+=upgradeLevelIncrease;
    }
    
    public int getWorkers(){
        return people;
    }

    public void addWorker(){
        people++;
    }
    
    public void takeWorker() throws Exception{
        if(people==0){
            throw new Exception();
        }
        people--;
    }
    
    double collect() {
        if(!isProductive){
            //TODO exception?
            return upgradeRate;
        }
        return people*upgradeRate;
    }

    int getType() {
        return type;
    }

    //vypocita cenu z povodnej ceny * level
    // nwm ci to takto chceme
    int[] getPriceForNextLvl() {
        int[] p = {0,0,0,0};
        for(int i=0; i<4; i++){
            p[i] = price[i]*(level+1);
        }
        return p;
    }
}
