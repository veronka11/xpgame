package xpgame;

import Buildings.GameBuildingController;
import xpgame.graphics.GameCanvasPanel;

import java.util.logging.Handler;

/**
 * Created by Stiffix on 16/06/16.
 */
public class GameHandler {
    private GameBuildingController mGameBuildingController;
    private GameCanvasPanel mGameCanvasPanel;
    private XPgame mXPgame;
    private int selectedBuilding = -1;

    public GameHandler(XPgame xpg, GameBuildingController gameBuildingController, GameCanvasPanel gameCanvasPanel) {
        mGameBuildingController = gameBuildingController;
        mGameCanvasPanel = gameCanvasPanel;
        mXPgame = xpg;
    }

    public void chooseBuilding(int id) {
        selectedBuilding = id;
    }

    public void buildBuilding(int row, int col) {

        System.out.println("SelectedBuilding[buildBuilding()] -> " + selectedBuilding);

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
                System.out.println("Not enough commodities!");
            }
        } else {
            System.out.println("You can NOT build here!");
        }
        selectedBuilding = -1;
    }

    private void displayDetailOfBuilding(int row, int col) {
        mXPgame.displayInfoOf(row, col);
    }

    public void notifyOnUpdate() {
        mXPgame.updateStats();
    }
}
