package xpgame;

import Buildings.GameBuilding;
import Buildings.GameBuildingController;
import xpgame.graphics.GameCanvasPanel;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */
public class GameHandler {
    private GameBuildingController mGameBuildingController;
    private GameCanvasPanel mGameCanvasPanel;
    private XPgame mXPgame;
    private int selectedBuilding = -1;

    public GameHandler(XPgame xpg, GameBuildingController gameBuildingController) {
        mGameBuildingController = gameBuildingController;
        mXPgame = xpg;
    }

    public void chooseBuilding(int id) {
        selectedBuilding = id;
    }

    public void buildBuilding(int row, int col) {

        //System.out.println("SelectedBuilding[buildBuilding()] -> " + selectedBuilding);

        // Not selected
        if (selectedBuilding == -1) {
            displayDetailOfBuilding(row, col);
            return;
        }

        // Try build
        if (mGameCanvasPanel.canBuildAt(row, col)) {
            if (mGameBuildingController.build(selectedBuilding, row, col) == 1) { // Build successful
                mGameCanvasPanel.assignBuilding(row, col, selectedBuilding);
                notifyOnUpdate();
            } else {
                //System.out.println("Not enough commodities!");
            }
        } else {
            //System.out.println("You can NOT build here!");
        }
        selectedBuilding = -1;
    }

    private void displayDetailOfBuilding(int row, int col) {
        mXPgame.displayInfoOf(row, col);
        mGameCanvasPanel.select(row, col);
    }

    public void notifyOnUpdate() {
        mXPgame.updateStats();
    }

    public void deselect() {
        selectedBuilding = -1;
        mGameCanvasPanel.deselect();
    }

    public void destroyBuilding(GameBuilding latestData) {
        //System.out.println("DESTROY BUILDING @ GameHandler.class");
        mGameBuildingController.destroy(latestData);
        mGameCanvasPanel.destroyAt(latestData.getMapPosition());
        notifyOnUpdate();
    }

    public void addWorkerToBuilding(GameBuilding latestData) {
        mGameBuildingController.addWorker(latestData);
        mGameCanvasPanel.render();
        notifyOnUpdate();
    }

    public void removeWorkerFromBuilding(GameBuilding latestData) {
        if (mGameBuildingController.removeWorker(latestData) == -1) {
            // TODO GUI WARNING -> can NOT remove worker from this building
        }
        mGameCanvasPanel.render();
        notifyOnUpdate();
    }

    @Override
    public String toString() {
        return "GameHandler initialized!! SelectedBuilding = " + selectedBuilding + " :: References: XPGAME " + String.valueOf(mXPgame != null) + " | GameCanvasPanel " + String.valueOf(mGameCanvasPanel != null) + " | GameBuildingController " + String.valueOf(mGameBuildingController != null) + " |";
    }

    public void assignGameCanvas(GameCanvasPanel gameCanvas) {
        mGameCanvasPanel = gameCanvas;
    }
}
