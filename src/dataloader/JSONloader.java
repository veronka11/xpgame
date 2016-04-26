package dataloader;

import entity.Building;
import entity.BuildingUpgrade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JSONloader {
    
    public static HashMap<Integer, Building> JSONloadBuildings(String path) throws FileNotFoundException {
        // Result variables
        HashMap<Integer, Building> data = new HashMap<Integer, Building>();


        // Get File from path and init JSONparser
        InputStream in = new FileInputStream(new File(path));
        JsonReader jreader = Json.createReader(in);

        JsonObject obj = jreader.readObject();

        // Building processing variables
        int id;
        String name;
        boolean productivity;
        int[] price, production;
        BuildingUpgrade tempBuildingUpgrade;

        // Buildings
        JsonArray buildings = obj.getJsonArray("buildings");
        for (JsonObject building : buildings.getValuesAs(JsonObject.class)) {
            // Name
            name = building.getString("name");
            id = building.getInt("id");

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
            JsonObject tempUpgrade = (JsonObject) building.get("upgrade");
            tempBuildingUpgrade = new BuildingUpgrade(tempUpgrade.getString("upgrade_name"),
                                                        tempUpgrade.getInt("upgrade_type"),
                                                        Double.valueOf(tempUpgrade.get("upgrade_rate").toString()),
                                                        Double.valueOf(tempUpgrade.get("upgrade_level_increase").toString())
                                                        );
            tempBuilding.setUpgrade(tempBuildingUpgrade);

            // ----------------------------
            // Add building
            data.put(id, tempBuilding);
        }
        return data;
    }

    // Main
    public static void main(String[] args) {
        try {
            JSONloader.JSONloadBuildings("/Users/newnew/IdeaProjects/xpgame/src/dataloader/structure.json");
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }
    
    
}
