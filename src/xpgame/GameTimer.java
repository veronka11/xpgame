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
    private final static int TIMER_TICK = 1000;

    public GameTimer(GameBuildingController gbcRef) {
        this.running = true;
        this.mGameBuidlingController = gbcRef;
    }

    public void stopTimer() {
        this.running = false;
    }

    @Override
    public void run() {
        while(running){
            mGameBuidlingController.updateResources();
            try {
                Thread.sleep(TIMER_TICK);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
