package dataloader;

import entity.Building;
import entity.BuildingUpgrade;
import xpgame.Commodity;

import java.io.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;

public class JSONloader {

    private final static String PATH_TO_SAVE = System.getProperty("user.dir") + "/savedata";

    public static  boolean JSONsaveBuildings(HashMap<Integer, Building> data) {
        System.out.println(PATH_TO_SAVE);
        System.out.println(Commodity.FOOD.ordinal());

        // Init stream writers
        FileWriter writer = null;
        try {
            writer = new FileWriter(PATH_TO_SAVE + "test.json");

            JsonGenerator gen = Json.createGenerator(writer);

            gen.writeStartObject().writeStartArray("buildings");

            // Building data
            Building tempBuilding;
            Iterator it = data.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                tempBuilding = (Building)pair.getValue();

                gen.writeStartObject()
                        .write("id", tempBuilding.getId())
                        .write("name", tempBuilding.getName())
                        .writeStartArray("price")
                            .write(tempBuilding.getPrice()[Commodity.FOOD.ordinal()])
                            .write(tempBuilding.getPrice()[Commodity.GOLD.ordinal()])
                            .write(tempBuilding.getPrice()[Commodity.STONE.ordinal()])
                            .write(tempBuilding.getPrice()[Commodity.WOOD.ordinal()])
                        .writeEnd()
                        .write("productive", tempBuilding.isProductive());


                if (tempBuilding.isProductive()) {
                    gen.writeStartArray("production")
                            .write(tempBuilding.getProduction()[Commodity.FOOD.ordinal()])
                            .write(tempBuilding.getProduction()[Commodity.GOLD.ordinal()])
                            .write(tempBuilding.getProduction()[Commodity.STONE.ordinal()])
                            .write(tempBuilding.getProduction()[Commodity.WOOD.ordinal()])
                        .writeEnd();
                } else {
                    gen.writeStartArray("production").writeEnd();
                }

                gen.writeStartObject("upgrade")
                        .write("upgrade_name", tempBuilding.getUpgrade().getName())
                        .write("upgrade_type", tempBuilding.getUpgrade().getType())
                        .write("upgrade_rate", tempBuilding.getUpgrade().getRate())
                        .write("upgrade_level_increase", tempBuilding.getUpgrade().getLevelIncrease())
                    .writeEnd()
                .writeEnd();

            }

            // End of Array Buildings and closing object }
            gen.writeEnd();

            // TODO: Game status - commodities

            gen.writeEnd();
            gen.close();
        } catch (IOException e) {
            return false;
        } catch (JsonGenerationException jge) {
            return false;
        } catch (JsonException je) {
            return false;
        }

        return true;
    }

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
            id = Integer.valueOf(building.getString("id"));

            // Instance
            Building tempBuilding = new Building(id, name);

            // Price
            price = new int[Commodity.values().length];
            JsonArray tempPrice = building.getJsonArray("price");
            for (int i = 0; i < tempPrice.size(); i++) {
                price[i] = tempPrice.getInt(i);
            }
            tempBuilding.setPrice(price);

            // Production
            productivity = building.getBoolean("productive");
            tempBuilding.setProductivity(productivity);
            if (productivity) {
                JsonArray tempProduction = building.getJsonArray("production");
                production = new int[tempProduction.size()];
                for (int i = 0; i < tempProduction.size(); i++) {
                    production[i] = tempProduction.getInt(i);
                }
                tempBuilding.setProduction(production);
            } else {
                tempBuilding.setProduction(new int[0]);
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
            //System.out.println(tempBuilding);
        }
        return data;
    }

    // Main
    public static void main(String[] args) {
        try {
            HashMap<Integer, Building> hm = JSONloader.JSONloadBuildings("/Users/newnew/IdeaProjects/xpgame/src/dataloader/structure.json");
            //HashMap<Integer, Building> hm = JSONloader.JSONloadBuildings("/Users/newnew/IdeaProjects/xpgame/savedatatest.json");
            //JSONloader.JSONsaveBuildings(hm);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }
    
    
}
