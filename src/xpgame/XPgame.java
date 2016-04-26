/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpgame;

import Buildings.GameBuildingController;
import dataloader.JSONloader;
import entity.Building;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author peto
 */
public class XPgame implements Runnable{
    
    private HashMap<Integer, Building> BuildingData;
    private final String path="structure.json";
    private GameBuildingController GBC;
    public boolean running;
    
    public void XPgame(){
        init();
    }
    
    public void createBuilding(int id){
    
    } 
    
    private void setRunning(boolean b){
        this.running=b;
    }
    
    private void init(){
        BuildingData = JSONloader.JSONloadBuildings(path);
        GBC= new GameBuildingController(BuildingData);
    }
    
    /*
        Vytvori sa instancia hry, nainicializuje sa a spusti sa Tread
    */
    public static void main(String[] args) {
        XPgame game=new XPgame();
        game.init();
        game.setRunning(true);
        game.run();
    }

    @Override
    public void run() {
        while(running){
            GBC.updateResources();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(XPgame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
}
