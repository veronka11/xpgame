package xpgame.graphics;

import entity.Building;
import xpgame.Commodity;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */
public class JBuildingButton extends JButton {

    private final Building building;
    private boolean selected;

    public JBuildingButton(String text, Icon icon, Building building) {
        super(text, icon);
        this.building = building;
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setForeground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 30));

        deselect();
    }

    public int getbuildingId() {
        return this.building.getId();
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
        this.setBackground(new Color(0,0,0,160));
        repaint();
    }

    public void deselect() {
        selected = false;
        this.setBackground(new Color(0,0,0,100));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            // Draw price rectangle
            g.setColor(new Color(76, 38, 19, 146));
            g.fillRect(0, 0, getWidth(), 20);
        }


        super.paintComponent(g);
        // Paint price
        paintPrice(g);

    }

    private void paintPrice(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();


        g2.setFont(new Font(g2.getFont().getName(), Font.BOLD, 12));
        // FOOD
        g2.setPaint(new Color(190, 44, 59));
        g2.drawString(String.valueOf(this.building.getPrice()[Commodity.FOOD.ordinal()]), 20, 15);
        // WOOD
        g2.setColor(new Color(152, 93, 31));
        g2.drawString(String.valueOf(this.building.getPrice()[Commodity.WOOD.ordinal()]), 60, 15);
        // STONE
        g2.setColor(new Color(151, 148, 152));
        g2.drawString(String.valueOf(this.building.getPrice()[Commodity.STONE.ordinal()]), 100, 15);
        // GOLD
        g2.setColor(new Color(186, 182, 25));
        g2.drawString(String.valueOf(this.building.getPrice()[Commodity.GOLD.ordinal()]), 140, 15);
        g2.dispose();

    }
}
