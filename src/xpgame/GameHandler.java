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

        // Not selected
        if (selectedBuilding == -1) return;

        // Try build
        if (mGameBuildingController.build(selectedBuilding) == 1) { // Build successful
            mGameCanvasPanel.assignBuilding(row, col, selectedBuilding);
            mXPgame.updateStats();
            selectedBuilding = -1;

        } else {
            System.out.println("Not enough commodities!");
        }
    }
}
