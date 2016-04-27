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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */
public class XPgame implements Runnable{
    
    private HashMap<Integer, Building> BuildingData;
    private final String path = "structure.json";
    private GameBuildingController GBC;
    public boolean running;
    
    public void XPgame() throws GameInicializaitonFailedException {
        init();
    }

    private void init() throws GameInicializaitonFailedException {
        try {
            BuildingData = JSONloader.JSONloadBuildings(path);
            GBC = new GameBuildingController(BuildingData);
        } catch (FileNotFoundException e) {
            throw new GameInicializaitonFailedException(e.getMessage());
        }
    }

    private void setRunning(boolean b){
        this.running=b;
    }

    /*
        Vytvori sa instancia hry, nainicializuje sa a spusti sa Tread
    */
    public static void main(String[] args) throws GameInicializaitonFailedException {
        XPgame game = new XPgame();
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
