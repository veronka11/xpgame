package Buildings;

import entity.Building;
import xpgame.Commodity;
import java.util.HashMap;
import java.util.Map;

public  class GameBuildingController {
    private final HashMap<Integer, Building> BuildingData;
    private HashMap<Integer, GameBuilding> Buildings;
    private HashMap<Commodity, Double> resourcesMap;
    private int people, workingPeople;

    public GameBuildingController(HashMap<Integer, Building> BuildingData) {
        this.BuildingData = BuildingData;
        Buildings = new HashMap<>();
        workingPeople = 0;
        people = 10;
        resourcesMap = new HashMap<>();
        for(Commodity c : Commodity.values()){
            resourcesMap.put(c, 100.0);
        }
    }
    public double getFood() {
        return resourcesMap.get(Commodity.FOOD);
    }
    public double getGold() {
        return resourcesMap.get(Commodity.GOLD);
    }
    public double getStone() {
        return resourcesMap.get(Commodity.STONE);
    }
    public double getWood() {
        return resourcesMap.get(Commodity.WOOD);
    }
    public int getPeople() {
        return people;
    }
    public int getWorkingPeople() {
        return workingPeople;
    }
    public void useFood(double food) {
        resourcesMap.put(Commodity.FOOD, getFood() - food);
    }
    public void useGold(double gold) {
        resourcesMap.put(Commodity.GOLD, getGold() - gold);
    }
    public void useStone(double stone) {
        resourcesMap.put(Commodity.STONE, getStone() - stone);
    }
    public void useWood(double wood) {
        resourcesMap.put(Commodity.WOOD, getWood() - wood);
    }
    public void addFood(double food) {
        resourcesMap.put(Commodity.FOOD, getFood() + food);
    }
    public void addGold(double gold) {
        resourcesMap.put(Commodity.GOLD, getGold() + gold);
    }
    public void addStone(double stone) {
        resourcesMap.put(Commodity.STONE, getStone() + stone);
    }
    public void addWood(double wood) {
        resourcesMap.put(Commodity.WOOD, getWood() + wood);
    }

    public String toString(){
        return "Food: " + getFood() + ", " +
               "Gold: " + getGold() + ", " +
               "Stone: " + getStone() + ", " +
               "Wood: " + getWood();
    }

    public void printResourcesStatus(){
        System.out.println(
                toString()
        );
    }

    public final void CreateBuilding(int id){
        GameBuilding TempBuilding;
        Building BD = BuildingData.get(id);
        if(isEnoughResources(BD.getPrice())) {
            TempBuilding = new GameBuilding(BuildingData.get(id), id);
            Buildings.put(Buildings.size(), TempBuilding);
            useResources(BD.getPrice());
        }
    }

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
    typ budovy zavisi od jeho umiestnania v jsone, teda podla idcka v Building.java
    MAYBE NOT THREAD SAFE
    */
    public void updateResources(){
        for (GameBuilding building : Buildings.values()) {
            if (building.isProductive()) {
                int[] producedCommodities = building.getProduction();
                addFood(producedCommodities[Commodity.FOOD.ordinal()]);
                addGold(producedCommodities[Commodity.GOLD.ordinal()]);
                addStone(producedCommodities[Commodity.STONE.ordinal()]);
                addWood(producedCommodities[Commodity.WOOD.ordinal()]);
            }
        }
    }

    public void addWorker(int id){
        if((people - workingPeople) > 0){
            Buildings.get(id).addWorker();
            workingPeople++;
        }
    }

    public void takeWorker(int id) throws Exception{
        Buildings.get(id).takeWorker();
        workingPeople--;
    }

    public boolean isEnoughResources(int[] price) {
        return (price.length == 4 &&
                price[Commodity.FOOD.ordinal()] <= getFood() &&
                price[Commodity.GOLD.ordinal()] <= getGold() &&
                price[Commodity.STONE.ordinal()] <= getStone() &&
                price[Commodity.WOOD.ordinal()] <= getWood());
    }

    public void useResources(int[] price){
        if(price.length != 4){
            return;
        }
        useFood(price[Commodity.FOOD.ordinal()]);
        useGold(price[Commodity.GOLD.ordinal()]);
        useStone(price[Commodity.STONE.ordinal()]);
        useWood(price[Commodity.WOOD.ordinal()]);
    }

    private boolean hasEnoughCommodities(int selectedBuilding) {
        Building requestedBuilid = BuildingData.get(selectedBuilding);
        return isEnoughResources(requestedBuilid.getPrice());

    }

    public int build(int selectedBuilding) {
        if (hasEnoughCommodities(selectedBuilding)) {
            Building data = BuildingData.get(selectedBuilding);
            Buildings.put(Buildings.size(), new GameBuilding(data));
            useResources(data.getPrice());
            return 1;
        }

        return -1;
    }
}
