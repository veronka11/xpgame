package xpgame.graphics;

import Buildings.GameBuilding;
import entity.Building;
import xpgame.GameHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Stiffix on 19/06/16.
 */
public class FunctionPanel extends JPanel {
    private JButton upgradeBtn, addWorkerBtn, removeWorkerBtn, destroyBtn;
    private JLabel buildingLabel;
    private GameBuilding latestData;
    private GameHandler gHandler;

    public FunctionPanel(GameHandler gh) {
        gHandler = gh;
        setPreferredSize(new Dimension(300, 120));
        setBackground(Color.darkGray);
        buildingLabel = new JLabel();
        upgradeBtn = new JButton("UPGRADE");
        addWorkerBtn = new JButton("+ WORKER");
        removeWorkerBtn = new JButton("- WORKER");
        destroyBtn = new JButton("DESTROY");

        add(buildingLabel);
        add(upgradeBtn);
        add(addWorkerBtn);
        add(removeWorkerBtn);
        add(destroyBtn);

        // Listeners
        upgradeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isSelected()) {
                    //gHandler.upgradeBuilding(latestData.getId());
                }
            }
        });

        addWorkerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isSelected()) {
                    //gHandler.addWorkerToBuilding(latestData.getId());
                }
            }
        });

        removeWorkerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isSelected()) {
                    //gHandler.removeWorkerFromBuilding(latestData.getId());
                }
            }
        });

        destroyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isSelected()) {
                    //gHandler.destroyBuilding(latestData.getId());
                    clearBuilding();
                    hidePanel();
                }
            }
        });

        hidePanel();


    }

    public void renderPanel(GameBuilding data) {
        latestData = data;
        buildingLabel.setText(data.Name);
        setVisible(true);
    }

    public void hidePanel() {
        setVisible(false);

    }

    private void clearBuilding() {
        latestData = null;
    }

    private boolean isSelected() {
        return !(latestData != null);
    }
}
