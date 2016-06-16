package xpgame.graphics;

import xpgame.XPgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Stiffix on 16/06/16.
 */
public class MenuPanel extends JPanel {
    public MenuPanel() {
        setOpaque(true);
    }

    public Dimension getPreferredSize() {
        return XPgame.GAME_RESOLUTION;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/Users/newnew/IdeaProjects/latest_extremne_programovanie/xpgame/src/xpgame/resources/main_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img != null)
            g.drawImage(img, 0, 0, 800, 600, Color.black, this);
    }
}
