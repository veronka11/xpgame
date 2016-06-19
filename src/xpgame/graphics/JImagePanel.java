package xpgame.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Stiffix on 19/06/16.
 */
public class JImagePanel extends JPanel {
    private BufferedImage image = null;
    public JImagePanel(String imagePath) {
        try {
            String absolutePath = this.getClass().getClassLoader().getResource(imagePath).getPath();
            this.image = ImageIO.read(new File(absolutePath));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image != null)
            g.drawImage(this.image, 0, 0, Color.black, this);
    }
}
