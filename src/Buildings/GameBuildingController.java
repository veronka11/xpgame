package Buildings;

import entity.Building;
import java.util.HashMap;

public class GameBuildingController {
    private HashMap<Integer, Building> BuildingData;
    private HashMap<Integer, GameBuilding> Buildings;
    private double food;
    private double gold;
    private double people;
    private double stone;
    private double wood;
    
    public GameBuildingController(HashMap<Integer, Building> BuildingData) {
        this.BuildingData=BuildingData;
    }
    
    public void CreateBuilding(int id){
        GameBuilding TempBuilding;
        try{
            TempBuilding = new GameBuilding(BuildingData.get(id), this);
            Buildings.put(Buildings.size(), TempBuilding);
            TempBuilding.run();
        }
        catch(Exception e){
        }
    } 

    public boolean isEnoughResources(int[] p) {
        if(p.length!=4){
            return false;
        }
        if(p[0]<food){
            return false;
        }
        if(p[1]<gold){
            return false;
        }
        if(p[2]<stone){
            return false;
        }
        if(p[3]<wood){
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
