package xpgame;

import java.io.FileNotFoundException;

/**
 * @author oh
 */
public class GameInicializaitonFailedException extends FileNotFoundException{

    public GameInicializaitonFailedException(String message) {
        super(message);
    }
}
