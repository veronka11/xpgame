package xpgame.graphics;

import Buildings.GameBuilding;
import Buildings.GameBuildingController;
import entity.MapPosition;
import xpgame.XPgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Stefan Gerboc, Ondrej Husar, Veronika Krajcovicova, Peter Zapalac
 */

public class GameCanvasPanel extends JPanel{
    private BufferedImage grassSprite;
    private BufferedImage[] buildingsSprite;
    private int[][] buildingsMap;
    private XPgame xpgameRef;
    private int sRow, sCol;
    public final static int EMPTY = -1;
    public final static String BUILDING_PREFIX = "obr";
    public final static String IMAGE_SUFFIX = ".png";


    public GameCanvasPanel (XPgame xpg, int buildingsLength) {
        setOpaque(true);
        xpgameRef = xpg;
        GameBuildingController GBCref=xpgameRef.getGBC();
        sRow = sCol = -1;
        // Fetch sprites
        fetchMapSprites(buildingsLength);
        try {
            grassSprite = ImageIO.read(new File(this.getClass().getClassLoader().getResource("xpgame/resources/sprites/grass.png").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clean map
        buildingsMap = new int[5][10];
        for (int i = 0; i < buildingsMap.length; i++) {
            for (int j = 0; j < buildingsMap[i].length; j++) {
                GameBuilding foundBuildingOnMap = GBCref.foundBuildingOnMap(i, j);
                if(foundBuildingOnMap ==null){
                    buildingsMap[i][j] = EMPTY;
                }else{
                    buildingsMap[i][j] = foundBuildingOnMap.id;
                }
            }
        }
        
//        updateAfterLoad();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int col = e.getX() / 80;
                    int row = e.getY() / 80;

                    if (row < buildingsMap.length) {
                        // Valid click
                        xpgameRef.evaluateMapTouch(row, col);
                    }
                } else {
                    xpgameRef.clearSelection();
                }
            }
        });

    }
    
//    private void updateAfterLoad(){
//        GameBuildingController
//    
//    }

    public boolean canBuildAt(int row, int col) {
        return (buildingsMap[row][col] == -1);
    }

    private void fetchMapSprites(int buildingsLength) {
        try {
            buildingsSprite = new BufferedImage[buildingsLength];
            String pathToLoad;
            for (int i = 0; i < buildingsSprite.length; i++) {
                pathToLoad = "xpgame/resources/sprites/" + BUILDING_PREFIX + (i + 1) + IMAGE_SUFFIX;
                System.out.println(pathToLoad);
                buildingsSprite[i] = ImageIO.read(new File(this.getClass().getClassLoader().getResource(pathToLoad).getPath()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Background
        g.drawImage(grassSprite, 0, 0, Color.green, this);
        g.drawImage(grassSprite, 512, 0, Color.green, this);

        for (int row = 0; row < buildingsMap.length; row++) {
            for (int col = 0; col < buildingsMap[row].length; col++) {
                if (row == sRow && col == sCol && !canBuildAt(row, col)) {
                    g.setColor(new Color(255, 255, 255,100));
                } else {
                    g.setColor(new Color(255,255,255,20));
                }
                g.drawRect(col * 80, row * 80, 80, 80);
                if (buildingsMap[row][col] != -1) {
                    // Draw house
                    g.drawImage(buildingsSprite[buildingsMap[row][col]], col * 80, row * 80, this);
                    g.setColor(Color.green);
                    String workingPeople = xpgameRef.GUIgetWorkersAt(row, col);
                    if (!workingPeople.equals("0")) {
                        g.drawString(workingPeople, col * 80 + 5, row * 80 + 20);
                    }
                }
            }
        }

    }

    public void assignBuilding(int row, int col, int selectedBuilding) {
        buildingsMap[row][col] = selectedBuilding;
        repaint();
    }

    public void destroyAt(MapPosition mapPosition) {
        this.buildingsMap[mapPosition.getRow()][mapPosition.getCol()] = EMPTY;
        repaint();
        revalidate();
    }

    public void render() {
        repaint();
        revalidate();
    }

    public void deselect() {
        sRow = -1;
        sCol = -1;
        render();
    }

    public void select(int row, int col) {
        sRow = row;
        sCol = col;
        render();
    }
}
