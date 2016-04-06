package dataloader;

import entity.Building;
import entity.BuildingUpgrade;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JSONloader {
    
    public static HashMap<Integer, Building> JSONloadBuildings(String path) {
        // Result variables
        HashMap<Integer, Building> data = new HashMap<Integer, Building>();
        
        try {
            // Get File from path and init JSONparser
            InputStream in = new FileInputStream(new File(path));
            JsonReader jreader = Json.createReader(in);
            
            JsonObject obj = jreader.readObject();
            
            // Building processing variables
            String name;
            boolean productivity;
            int[] price, production;
            ArrayList<BuildingUpgrade> buildingUpgrades;
            BuildingUpgrade tempBuildingUpgrade;
            
            // Buildings
            int id = 0;
            JsonArray buildings = obj.getJsonArray("buildings");
            for (JsonObject building : buildings.getValuesAs(JsonObject.class)) {
                // Name
                name = building.getString("name");
                
                // Instance
                Building tempBuilding = new Building(id, name);
                
                // Price
                price = new int[building.size()];
                JsonArray tempPrice = building.getJsonArray("price");
                for (int i = 0; i < tempPrice.size(); i++) {
                        price[i] = tempPrice.getInt(i);
                }
                tempBuilding.setPrice(price);
                
                // Production
                productivity = building.getBoolean("productive");
                if (productivity) {
                    JsonArray tempProduction = building.getJsonArray("production");
                    production = new int[tempProduction.size()];
                    for (int i = 0; i < tempProduction.size(); i++) {
                        production[i] = tempProduction.getInt(i);
                    }
                    tempBuilding.setProduction(production);
                } else {
                    tempBuilding.setProduction(productivity);
                }
                
                // BuildingUpgrades
                buildingUpgrades = new ArrayList<>();
                JsonArray tempUpgrades = building.getJsonArray("upgrades");
                for (JsonObject upgr : tempUpgrades.getValuesAs(JsonObject.class)) {
                    tempBuildingUpgrade = new BuildingUpgrade(upgr.getString("upgrade_name"), upgr.getInt("upgrade_type"), Double.valueOf(upgr.get("upgrade_rate").toString()), Double.valueOf(upgr.get("upgrade_level_increase").toString()));
                    buildingUpgrades.add(tempBuildingUpgrade);
                }
                tempBuilding.setUpgrades(buildingUpgrades);
                
                // ----------------------------
                // Add building
                data.put(id, tempBuilding);
                id++;
                
                System.out.println(tempBuilding.toString());
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        return data;
    }
    
    
    // Main
    public static void main(String[] args) {
        JSONloader.JSONloadBuildings("/Users/newnew/NetBeansProjects/xpgame/XPgame/src/dataloader/structure.json");
    }
    
    
}
