package xpgame.graphics;

import entity.Building;

import javax.swing.*;
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
        deselect();
    }

    public int getbuildingId() {
        return buildingId;
    }

    public boolean isSelected() {
        return isSelected();
    }

    public void select() {
        selected = true;
        this.setBorder(new LineBorder(Color.RED, 2));
    }

    public void deselect() {
        selected = false;
        this.setBorder(BorderFactory.createEmptyBorder());
    }
}
