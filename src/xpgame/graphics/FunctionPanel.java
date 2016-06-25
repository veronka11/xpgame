package xpgame.graphics;

import Buildings.GameBuilding;
import entity.Building;
import xpgame.GameHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
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
        buildingLabel.setForeground(Color.white);
        buildingLabel.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth(), (int)this.getPreferredSize().getHeight() / 4));
        buildingLabel.setBorder(new LineBorder(Color.GRAY, 2));
        buildingLabel.setBackground(Color.red);
        buildingLabel.setFont(new Font("Verdana", Font.BOLD, 12));

        buildingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upgradeBtn = new JButton("UPGRADE");
        addWorkerBtn = new JButton("+ WORKER");
        removeWorkerBtn = new JButton("- WORKER");
        destroyBtn = new JButton("DESTROY");

        add(buildingLabel, BorderLayout.PAGE_START);
        //add(upgradeBtn, BorderLayout.L);
        add(addWorkerBtn, BorderLayout.LINE_START);
        add(removeWorkerBtn, BorderLayout.LINE_END);
        add(destroyBtn, BorderLayout.PAGE_END);

        System.out.println(gHandler.toString());

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
                    gHandler.addWorkerToBuilding(latestData);
                }
            }
        });

        removeWorkerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isSelected()) {
                    gHandler.removeWorkerFromBuilding(latestData);
                }
            }
        });

        destroyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isSelected()) {
                    //System.out.println("DESTROY BUILDING @ FunctionPanel.class");
                    gHandler.destroyBuilding(latestData);
                    clearBuilding();
                    hidePanel();
                }
            }
        });

        hidePanel();


    }

    public void renderPanel(GameBuilding data) {
        latestData = data;
        buildingLabel.setText(data.Name.toUpperCase());
        removeWorkerBtn.setVisible(data.isProductive());
        addWorkerBtn.setVisible(data.isProductive());
        setVisible(true);
    }

    public void hidePanel() {
        setVisible(false);

    }

    private void clearBuilding() {
        latestData = null;
    }

    private boolean isSelected() {
        return (latestData != null);
    }
}
