/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpgame;

import Buildings.GameBuildingController;
import dataloader.JSONloader;
import entity.Building;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 *
 * @author peto
 */
public class XPgame implements Runnable{
    
    private HashMap<Integer, Building> BuildingData;
    private final String path = "structure.json";
    private GameBuildingController GBC;
    
    public void XPgame() throws GameInicializaitonFailedException {
        init();
    }
    
    public void createBuilding(int id){
    
    } 
    
    private void init() throws GameInicializaitonFailedException{
        try {
            BuildingData = JSONloader.JSONloadBuildings(path);
            GBC = new GameBuildingController(BuildingData);
        }
        catch (FileNotFoundException e){
            throw new GameInicializaitonFailedException(e.getMessage());
        }
    }
    
    /*
        Vytvori sa instancia hry, nainicializuje sa a spusti sa Tread
    */
    public static void main(String[] args) throws GameInicializaitonFailedException {
        XPgame game = new XPgame();
        game.init();
        game.run();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
        // To change body of generated methods, choose Tools | Templates.
    }
    
}
