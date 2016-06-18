package xpgame.graphics;

import xpgame.XPgame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Stiffix on 16/06/16.
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
