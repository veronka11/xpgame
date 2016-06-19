package xpgame.graphics;

import entity.Building;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Stiffix on 18/06/16.
 */
public class JBuildingButton extends JButton {

    private final int buildingId;
    private boolean selected;

    public JBuildingButton(String text, Icon icon, int keyID) {
        super(text, icon);
        buildingId = keyID;
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setForeground(Color.white);

        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 30));
        deselect();
    }

    public int getbuildingId() {
        return buildingId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
        //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setBackground(new Color(0,0,0,160));
        repaint();
    }

    public void deselect() {
        selected = false;
        //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setBackground(new Color(0,0,0,100));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paintComponent(g);
    }
}
