/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpgame;

import Buildings.GameBuildingController;
import dataloader.JSONloader;
import entity.Building;
import xpgame.graphics.ControlPanel;
import xpgame.graphics.GameCanvasPanel;
import xpgame.graphics.JBuildingButton;
import xpgame.graphics.MenuPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */
public class XPgame {
    private final static String GAME_NAME = "XPgame";
    // GUI
    private JFrame mainWindow;
    private GameCanvasPanel gameCanvas;
    private ControlPanel controlPanel;
    private JBuildingButton selectedBuildingButton;
    private JLabel statistics;
    // DATA-HANDLERS
    private HashMap<Integer, Building> BuildingData;
    private final String path = this.getClass().getClassLoader().getResource("dataloader/baseBuildingsData.json").getPath();
    private GameBuildingController GBC;
    private GameHandler gHandler;
    private GameTimer timer;
    private Thread gameThread;

    public final static Dimension GAME_RESOLUTION = new Dimension(800, 600);

    public void XPgame() { }

    private void startGame(MenuPanel menuPanel) {
        try {
            // Initialize Data
            initData();
            // Clear GUI
            mainWindow.remove(menuPanel);
            mainWindow.revalidate();
            mainWindow.repaint();
            // Init timer
            timer = new GameTimer(GBC);
            gameThread = new Thread(timer);

            // Draw GUI
            renderGame();

            // Assign handler
            gHandler = new GameHandler(this, GBC, gameCanvas);

            // Start game
            //gameThread.start();



        } catch (GameInicializaitonFailedException e) {
            e.printStackTrace();
            // TODO handle exception to GUI
        }


    }

    public void renderMenu() {

        mainWindow = new JFrame(GAME_NAME);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(GAME_RESOLUTION);
        mainWindow.setResizable(false);

        MenuPanel panel = new MenuPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);

        JLabel logo = new JLabel("XPgame");
        JButton startBtn = new JButton("Start Game");
        JButton loadBtn = new JButton("Load Game");
        JButton exitBtn = new JButton("Exit Game");

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                startGame(panel);
            }
        });

        loadBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // TODO dialog file open window
            }
        });

        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        panel.add(logo);
        panel.add(startBtn);
        panel.add(loadBtn);
        panel.add(exitBtn);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainWindow.add(panel);



        mainWindow.setVisible(true);

    }

    private void renderGame() {
        Container pane = mainWindow.getContentPane();


        // Stats panel for commodity statistics
        JPanel statsPanel = new JPanel();
        statsPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        statsPanel.setBackground(Color.red);
        statsPanel.setPreferredSize(new Dimension(800, 30));

        // Stats
        String statsRepresentation = GBC.getStats();

        statistics = new JLabel(statsRepresentation);
        statistics.setForeground(Color.WHITE);
        statistics.setFont(new Font("Courier New", Font.BOLD, 14));
        statsPanel.add(statistics);

        pane.add(statsPanel, BorderLayout.PAGE_START);

        // GameCanvas passes reference of Buildings length
        gameCanvas = new GameCanvasPanel(this, BuildingData.size());
        pane.add(gameCanvas);

        // Control Panel for upgrades and buildings
        controlPanel = new ControlPanel(this);
        controlPanel.setBackground(Color.darkGray);
        controlPanel.setPreferredSize(new Dimension(800, 120));

        // add house panel
        JPanel housePanel = new JPanel();
        JScrollPane scrollbar = new JScrollPane(housePanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        Iterator it = BuildingData.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            Building buildingData = (Building) pair.getValue();

            // Make component
            JBuildingButton buildingButton = GameBuildingController.createButtonFromData(buildingData);
            housePanel.add(buildingButton);

            // Listener
            buildingButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    // Unselect previous
                    if (selectedBuildingButton != null) {
                        selectedBuildingButton.deselect();
                    }

                    // Select new one
                    selectedBuildingButton = (JBuildingButton) e.getSource();
                    selectedBuildingButton.select();
                    gHandler.chooseBuilding(selectedBuildingButton.getbuildingId());

                }
            });
        }

        //housePanel.setPreferredSize(new Dimension(500, 120));
        scrollbar.setPreferredSize(new Dimension(500, 120));
        housePanel.setBackground(Color.green);

        controlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //controlPanel.add(housePanel, BorderLayout.LINE_START);
        controlPanel.add(scrollbar, BorderLayout.PAGE_START);

        pane.add(controlPanel, BorderLayout.PAGE_END);

        controlPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectedBuildingButton != null) {
                    selectedBuildingButton.deselect();
                    gHandler.chooseBuilding(GameCanvasPanel.EMPTY);
                }
            }
        });

        mainWindow.revalidate();
        mainWindow.repaint();
    }

    private void initData() throws GameInicializaitonFailedException {
        try {
            BuildingData = JSONloader.JSONloadBuildings(path);
            GBC = new GameBuildingController(BuildingData);
        } catch (FileNotFoundException e) {
            throw new GameInicializaitonFailedException(e.getMessage());
        }
    }

    /*
        Vytvori sa instancia hry a nainicializuje sa
    */
    public static void main(String[] args) {
        XPgame game = new XPgame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                game.renderMenu();
            }
        });
    }

    public void evaluateMapTouch(int row, int col) {
        gHandler.buildBuilding(row, col);
        selectedBuildingButton.deselect();
    }

    public void updateStats() {
        statistics.setText(GBC.getStats());
        mainWindow.revalidate();
        mainWindow.repaint();
    }
}