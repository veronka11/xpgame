package Buildings;

import entity.Building;
import java.util.HashMap;
import java.util.Map;

public  class GameBuildingController {
    
    private final HashMap<Integer, Building> BuildingData;
    private HashMap<Integer, GameBuilding> Buildings;
    private double food;
    private double gold;
    private double stone;
    private double wood;
    private int people;
    private int workingPeople;
    
    /*
    zaciatok hry, inicializuju sa zdroje, vytvori sa jeden dom aby boli ludia? sa moze zmenit 
    */
    public GameBuildingController(HashMap<Integer, Building> BuildingData) {
        this.BuildingData=BuildingData;
        workingPeople=0;
        people=0;
        food=100;
        gold=100;
        stone=100;
        wood=100;
        this.CreateBuilding(4);
    }
    
    /*
        najprv zisti ci je dostatok zdrojov a potom vytvori budovu, budova je najprv przdna bez ludi
    */
    public final void CreateBuilding(int id){
        GameBuilding TempBuilding;
        Building BD = BuildingData.get(id);
        if(isEnoughResources(BD.getPrice())){
            TempBuilding = new GameBuilding(BuildingData.get(id), id);
            Buildings.put(Buildings.size(), TempBuilding);
            useResources(BD.getPrice());
        }
    } 
    
    /*
    ak je dostatok zdrojov tak upgraduje budovu s danym, id
    */
    public void upgradeBuilding(int id){
        GameBuilding tempBuilding;
        tempBuilding =  Buildings.get(id);
        if(isEnoughResources(tempBuilding.getPriceForNextLvl())){
            useResources(tempBuilding.getPriceForNextLvl());
            tempBuilding.upLevel();
        }
    }
    
    /*
    vola ju XPgame kazdu sekundu, updatne zdroje, a aj pocet ludi k dispozicii a pocet pracujucich ludi
    typ budovy zavisi od jeho umiestnania v jsone,, teda podla idcka v Building.java 
    MAYBE NOT THREAD SAFE
    */
    public void updateResources(){
        int peopleCount=0;
        int peopleWorkingCount=0;
        for (Map.Entry<Integer, GameBuilding> entry : Buildings.entrySet()) {
            GameBuilding building = entry.getValue();
            int type= building.getType();
            switch(type){
                case 0:       
                    food+=building.collect();
                    peopleWorkingCount+=building.getWorkers();
                    break;
                case 1:
                    gold+=building.collect();
                    peopleWorkingCount+=building.getWorkers();
                    break;
                case 2:
                    stone+=building.collect();
                    peopleWorkingCount+=building.getWorkers();
                    break;
                case 3:
                    wood+=building.collect();
                    peopleWorkingCount+=building.getWorkers();
                    break;
                case 4: 
                    people+=building.collect();
                    break;
            }
        }
        people=peopleCount;
        workingPeople=peopleWorkingCount;
        
        printResourcesStatus();
    }
    
    /*
       prida pracovnika k budove
    */
    public void addWorker(int id){
        if((people-workingPeople)>0){
            Buildings.get(id).addWorker();
            workingPeople++;
        }
    }
    
    public void printResourcesStatus(){
        System.out.println(
            "Food: "+food+", "+
            "Gold: "+gold+", "+
            "Stone: "+stone+", "+
            "Wood: "+wood
        );
    }
    
    /*
        odoberie pracovnika od budovy
    */
    public void takeWorker(int id) throws Exception{
        Buildings.get(id).takeWorker();
        workingPeople--;
    }

    public boolean isEnoughResources(int[] p) {
        if(p.length!=4){
            return false;
        }
        else if(p[0]<food){
            return false;
        }
        else if(p[1]<gold){
            return false;
        }
        else if(p[2]<stone){
            return false;
        }
        else if(p[3]<wood){
            return false;
        }
        return true;
    }
    
   
    public void useResources(int[] p){
        if(p.length!=4){
            return;
        }
        food-=p[0];
        gold-=p[1];
        stone-=p[2];
        wood-=p[3];
    }    
}
