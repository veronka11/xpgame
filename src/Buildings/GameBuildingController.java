package Buildings;

import entity.Building;
import entity.MapPosition;
import xpgame.Commodity;
import xpgame.graphics.GameCanvasPanel;
import xpgame.graphics.JBuildingButton;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public  class GameBuildingController {
    private static final String STATS_REPRESENTATION_SEPARATOR = " | ";
    private static final String STATS_REPRESENTATION_RELATION = " : ";
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

    public void collectResources() {
        for (GameBuilding building : Buildings.values()) {
            if (building.isProductive()) {
                try {
                    int[] producedCommodities = building.collect();
                    addFood(producedCommodities[Commodity.FOOD.ordinal()]);
                    addGold(producedCommodities[Commodity.GOLD.ordinal()]);
                    addStone(producedCommodities[Commodity.STONE.ordinal()]);
                    addWood(producedCommodities[Commodity.WOOD.ordinal()]);
                } catch (Exception e) {}
            }
        }
    }

    public void addWorker(int id){
        if(people > 0) {
            Buildings.get(id).addWorker();
            people--;
            workingPeople++;
        }

    }

    public void takeWorker(int id){
        if (Buildings.get(id).takeWorker()) {
            people++;
            workingPeople--;
        }

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

    public int build(int selectedBuilding, int row, int col) {
        if (hasEnoughCommodities(selectedBuilding)) {
            Building data = BuildingData.get(selectedBuilding);
            Buildings.put(Buildings.size(), new GameBuilding(data, new MapPosition(row, col)));
            useResources(data.getPrice());
            return 1;
        }

        return -1;
    }

    public static JBuildingButton createButtonFromData(Building buildingData) {

        // TODO update resources that we do not need to increment buildingData->ID, that's TEMPORARY fix!!

        String pathPrepared = "xpgame/resources/sprites/" + GameCanvasPanel.BUILDING_PREFIX + (buildingData.getId() + 1) +  GameCanvasPanel.IMAGE_SUFFIX;
        String path = GameBuildingController.class.getClassLoader().getResource(pathPrepared).getPath();

        ImageIcon buildingIcon = new ImageIcon(path);
        JBuildingButton result = new JBuildingButton(buildingData.getName(), buildingIcon, buildingData);

        return result;
    }

    public String getStats() {
        StringBuilder stats = new StringBuilder();
        stats.append(Commodity.FOOD.name() + STATS_REPRESENTATION_RELATION + String.valueOf((int)getFood()));
        stats.append(STATS_REPRESENTATION_SEPARATOR);
        stats.append(Commodity.WOOD.name() + STATS_REPRESENTATION_RELATION+ String.valueOf((int)getWood()));
        stats.append(STATS_REPRESENTATION_SEPARATOR);
        stats.append(Commodity.STONE.name() + STATS_REPRESENTATION_RELATION +  String.valueOf((int)getStone()));
        stats.append(STATS_REPRESENTATION_SEPARATOR);
        stats.append(Commodity.GOLD.name() + STATS_REPRESENTATION_RELATION + String.valueOf((int)getGold()));
        stats.append(STATS_REPRESENTATION_SEPARATOR);
        stats.append("PEOPLE:" + STATS_REPRESENTATION_RELATION + String.valueOf(getPeople()));
        return stats.toString();
    }

    public GameBuilding foundBuildingOnMap(int row, int col) {

        Iterator it = Buildings.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            GameBuilding result = (GameBuilding)pair.getValue();
            if (result.isAt(row, col)) {
                return result;
            }
        }

        return null;
    }

    public int foundBuildingOnMapInt(int row, int col) {
        Iterator it = Buildings.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            GameBuilding result = (GameBuilding)pair.getValue();
            if (result.isAt(row, col)) {
                return (int) pair.getKey();
            }
        }
        return -1;
    }

    public void destroy(GameBuilding latestData) {
        int key = foundBuildingOnMapInt(latestData.getMapPosition().getRow(), latestData.getMapPosition().getCol());
        if (key != -1) {
            int removedPeople = Buildings.get(key).people;
            Buildings.remove(key);
            people += removedPeople;
        }
    }

    public void addWorker(GameBuilding latestData) {
        int key = foundBuildingOnMapInt(latestData.getMapPosition().getRow(), latestData.getMapPosition().getCol());
        if (key != -1) {
            addWorker(key);
        }
    }

    public int removeWorker(GameBuilding latestData) {
        int key = foundBuildingOnMapInt(latestData.getMapPosition().getRow(), latestData.getMapPosition().getCol());
        if (key != -1) {
            takeWorker(key);
            return 0;
        }
        return -1;
    }

    public String getWorkersCountAt(int row, int col) {
        int key = foundBuildingOnMapInt(row, col);
        return String.valueOf(Buildings.get(key).people);
    }
}
