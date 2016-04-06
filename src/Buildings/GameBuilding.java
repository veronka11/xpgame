package Buildings;

import entity.Building;

/**
 *
 * @author peto
 */
public class GameBuilding implements Runnable{
    private final Building BuildingData;
    public int level;
    public final GameBuildingController ParrentController;
    
    GameBuilding(Building data, GameBuildingController PC) throws Exception {
        level=0;
        BuildingData=data;
        ParrentController=PC;
        if(!PC.isEnoughResources(BuildingData.getPrice())){
            throw new Exception("Not enough resources!");
        }
    }

    @Override
    public void run() {
        
    }
    
}
