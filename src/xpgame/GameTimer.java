package xpgame;

import Buildings.GameBuildingController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */

public class GameTimer implements Runnable {
    private boolean running;
    private GameBuildingController mGameBuidlingController;
    private GameHandler mGameHandler;
    private final static int TIMER_TICK = 1000;

    public GameTimer(GameBuildingController gbcRef, GameHandler gameHandler) {
        this.running = true;
        this.mGameHandler = gameHandler;
        this.mGameBuidlingController = gbcRef;
    }

    public void stopTimer() {
        this.running = false;
    }

    @Override
    public void run() {
        while(running){
            mGameBuidlingController.collectResources();
            mGameHandler.notifyOnUpdate();
            try {
                Thread.sleep(TIMER_TICK);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
