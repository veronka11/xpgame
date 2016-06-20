package xpgame.graphics;

import xpgame.XPgame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */
public class ControlPanel extends JPanel {
    private XPgame xpgameref;

    public ControlPanel(XPgame xpg) {
        xpgameref = xpg;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = e.getX() / 80;
            }
        });
    }
}
