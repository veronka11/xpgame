package dataloader;

import Buildings.GameBuilding;
import Buildings.GameBuildingController;
import entity.Building;
import entity.BuildingUpgrade;
import entity.MapPosition;
import xpgame.Commodity;
import xpgame.GameInicializaitonFailedException;
import xpgame.NullNameBuildingUpgradeException;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.*;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;

public class JSONloader {

    private final static String PATH_TO_SAVE = System.getProperty("user.dir") + "\\savedata";

    public static  boolean JSONsaveBuildings(HashMap<Integer, Building> data, HashMap<Integer, GameBuilding> gameData, HashMap<Commodity, Double> resourcesmap, int people) {
        System.out.println(PATH_TO_SAVE);
        System.out.println(Commodity.FOOD.ordinal());

        // Init stream writers
        FileWriter writer = null;
        try {
            writer = new FileWriter(PATH_TO_SAVE + "savedGame.json");

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
                
                System.out.println(tempBuilding.getName());
                System.out.println(tempBuilding.getUpgrade());
                System.out.println(tempBuilding.getUpgrade().getName());
                gen.writeStartObject("upgrade")
                        .write("upgrade_name", tempBuilding.getUpgrade().getName())
                        .write("upgrade_rate", tempBuilding.getUpgrade().getRate())
                        .write("upgrade_level_increase", tempBuilding.getUpgrade().getLevelIncrease())
                    .writeEnd()
                .writeEnd();

            }

            // End of Array Buildings and closing object }
            gen.writeEnd();

            gen.writeStartObject("saved_data")
                    .writeStartObject("stats");
                    gen.write(Commodity.FOOD.name(), resourcesmap.get(Commodity.FOOD))
                            .write(Commodity.WOOD.name(), resourcesmap.get(Commodity.WOOD))
                            .write(Commodity.STONE.name(), resourcesmap.get(Commodity.STONE))
                            .write(Commodity.GOLD.name(), resourcesmap.get(Commodity.GOLD))
                            .write("PEOPLE", people);
                    gen.writeEnd(); // END OF OBJECT [stats]
                    gen.writeStartArray("builded_buildings");
                        Iterator itt = gameData.entrySet().iterator();
                        while (itt.hasNext()) {
                            Map.Entry pair = (Map.Entry) itt.next();
                            GameBuilding gb = (GameBuilding) pair.getValue();
                            gen.writeStartObject();     // Gamebuilding object
                                gen.write("id", gb.id)
                                        .write("positionRow", gb.getMapPosition().getRow())
                                        .write("positionCol", gb.getMapPosition().getCol())
                                        .write("people", gb.people)
                                        .write("level", gb.level);
                            gen.writeEnd();             // End of gamebuilding object
                        }
                    gen.writeEnd(); // End of array [builded_buildings]
            gen.writeEnd();

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
    
    public static ArrayList<Object> JSONloadSavedGame() throws FileNotFoundException{
//        ArrayList<Object> 
//        HashMap<Integer, Building> data, 
//        HashMap<Integer, GameBuilding> gameData, 
//        HashMap<Commodity, Double> resourcesmap, 
//        int people
        String path =  System.getProperty("user.dir") + "\\savedata"+ "savedGame.json";
        System.out.println("Loading game from: "+path);
        //get building data
        ArrayList<Object> data = new ArrayList<>();
        
        HashMap<Integer, Building> buildingData = JSONloadBuildings(path);
        
        data.add(buildingData);
        
        InputStream in = new FileInputStream(new File(path));
        JsonReader jreader = Json.createReader(in);

        JsonObject obj = jreader.readObject();
        
        // get saved_data from json
        JsonObject savedData = obj.getJsonObject("saved_data");
        HashMap<Commodity, Double> resourcesmap = new HashMap<>();
        int people=0;
        if (!savedData.isEmpty()) {
            //getting saved stats
            JsonObject stats = savedData.getJsonObject("stats");
            System.out.println("Loading stats");
            
            if (!stats.isEmpty()) {
                resourcesmap.put(Commodity.FOOD, Double.valueOf(stats.get(Commodity.FOOD.name()).toString()));
                System.out.println("FOOD "+stats.get(Commodity.FOOD.name()).toString());
                resourcesmap.put(Commodity.WOOD, Double.valueOf(stats.get(Commodity.WOOD.name()).toString()));
                System.out.println("WOOD "+stats.get(Commodity.WOOD.name()).toString());
                resourcesmap.put(Commodity.STONE, Double.valueOf(stats.get(Commodity.STONE.name()).toString()));
                System.out.println("STONE "+stats.get(Commodity.STONE.name()).toString());
                resourcesmap.put(Commodity.GOLD, Double.valueOf(stats.get(Commodity.GOLD.name()).toString()));
                System.out.println("GOLD "+stats.get(Commodity.GOLD.name()).toString());
                people = stats.getInt("PEOPLE");
                System.out.println("people " + String.valueOf(people));
            }
            data.add(resourcesmap);
            
            // saved builded buildings
            JsonArray builded_buildings = savedData.getJsonArray("builded_buildings");
            HashMap<Integer, GameBuilding> gameBuildings = new HashMap<>();
            
            if (!builded_buildings.isEmpty()) {
                
                for (JsonObject building : builded_buildings.getValuesAs(JsonObject.class)) {
                    MapPosition mp = new MapPosition(building.getInt("positionRow"), building.getInt("positionCol"));
                    int id= building.getInt("id");
                    System.out.println("id "+String.valueOf(id));
                    GameBuilding gb = new GameBuilding(buildingData.get(id));
                    gb.setLevel(building.getInt("level"));
                    System.out.println("level "+String.valueOf(building.getInt("level")));
                    gb.setMapPosition(mp);
                    gb.setWorkers(building.getInt("people"));
                    System.out.println("people "+String.valueOf(building.getInt("people")));
                    gameBuildings.put(id, gb);
                }
            }
            data.add(gameBuildings);
            data.add(people);
        }
        return data;
    }

    public static HashMap<Integer, Building> JSONloadBuildings(String path) throws FileNotFoundException {
        //String path =  System.getProperty("user.dir") + "\\savedata"+ "savedGame.json";
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
            try {
                id = building.getInt("id");
            } catch (ClassCastException cce) {
                id = Integer.valueOf(building.getString("id"));
            }

            System.out.println("Building " + id);
            // Instance
            Building tempBuilding = new Building(id, name);

            // Price
            price = new int[Commodity.values().length];
            JsonArray tempPrice = building.getJsonArray("price");
            for (int i = 0; i < tempPrice.size(); i++) {
                price[i] = tempPrice.getInt(i);
            }
            try {
                tempBuilding.setPrice(price);
            } catch (Exception e) { }


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



            try {
                // BuildingUpgrades
                JsonObject tempUpgrade = building.getJsonObject("upgrade");

                if (!tempUpgrade.isEmpty()) {
                    tempBuildingUpgrade = new BuildingUpgrade(tempUpgrade.getString("upgrade_name"),
                            Double.valueOf(tempUpgrade.get("upgrade_rate").toString()),
                            Double.valueOf(tempUpgrade.get("upgrade_level_increase").toString())
                    );
                    tempBuilding.setUpgrade(tempBuildingUpgrade);
                }

            } catch (NullNameBuildingUpgradeException nnbue) {
                throw new GameInicializaitonFailedException("Building Upgrade parameter name cannot be null!");
            }


            // ----------------------------
            // Add building
            data.put(id, tempBuilding);
            //System.out.println(tempBuilding);
        }
        try {
            jreader.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(JSONloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // Main
    public static void main(String[] args) {
        try {
            HashMap<Integer, Building> hm = JSONloader.JSONloadBuildings("/Users/newnew/IdeaProjects/latest_extremne_programovanie/xpgame/src/dataloader/baseBuildingsData.json");
            //HashMap<Integer, Building> hm = JSONloader.JSONloadBuildings("/Users/newnew/IdeaProjects/xpgame/savedatatest.json");
            //JSONloader.JSONsaveBuildings(hm);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }
    
    
    
    
}
