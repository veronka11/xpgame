/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpgame;

import Buildings.GameBuildingController;
import dataloader.JSONloader;
import entity.Building;
import xpgame.graphics.GameCanvasPanel;
import xpgame.graphics.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */
public class XPgame {
    private final static String GAME_NAME = "XPgame";
    public final static Dimension GAME_RESOLUTION = new Dimension(800, 600);
    // GUI
    private JFrame mainWindow;
    private GameCanvasPanel gameCanvas;
    // DATA-HANDLERS
    private HashMap<Integer, Building> BuildingData;
    private final String path = "/Users/newnew/IdeaProjects/latest_extremne_programovanie/xpgame/src/dataloader/baseBuildingsData.json";
    private GameBuildingController GBC;
    private GameTimer timer;
    private Thread gameThread;

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
        statsPanel.setBackground(Color.red);
        statsPanel.setPreferredSize(new Dimension(800, 30));

        pane.add(statsPanel, BorderLayout.PAGE_START);

        // GameCanvas passes reference of Buildings length
        gameCanvas = new GameCanvasPanel(BuildingData.size());
        pane.add(gameCanvas);

        // Control Panel for upgrades and buildings
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.blue);
        controlPanel.setPreferredSize(new Dimension(800, 120));

        pane.add(controlPanel, BorderLayout.PAGE_END);


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
}



        /*
        mainWindow.getContentPane().setLayout(new GridLayout(3, 3));
        mainWindow.add (new JButton ("NW"));
        mainWindow.add (new JButton ("N"));
        mainWindow.add (new JButton ("NE"));
        mainWindow.add (new JButton ("W"));
        mainWindow.add (new JButton (" "));
        mainWindow.add (new JButton ("E"));
        mainWindow.add (new JButton ("SW"));
        mainWindow.add (new JButton ("S"));
        mainWindow.add (new JButton ("SE"));
        */