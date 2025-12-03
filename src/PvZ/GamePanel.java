package PvZ;

import PvZ.Tile.Manager;
import PvZ.Tile.PlantManager;
import PvZ.entity.*;
import PvZ.util.GameMode;
import PvZ.util.GridRenderer;
import PvZ.util.MapViewer;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    final int resol = 16;
    final int scale = 3;

    public final int block = resol * scale;
    public final int maxcol = 14;
    public final int maxrow = 7;

    public final int mwidth = 5 * block;
    public final int mheight = 5 * block;

    public final int width = maxcol * block * 2;
    public final int height = maxrow * block * 2;

    final int fps = 60;

    Manager tm = new Manager(this);
    PlantManager plantManager = new PlantManager(this);
    GridRenderer gridRenderer = new GridRenderer(block, maxcol, maxrow);
    MapViewer mapViewer = new MapViewer(this);

    GameMode gameMode = GameMode.CAMERA;

    Thread gameThread;
    Keybind key = new Keybind();

    public Player player = new Player(key, this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(mwidth * 2, mheight * 2));
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        this.setBackground(new Color(34, 34, 34));
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double delta = 0;
        double time = 1000000000 / fps;

        long last = System.nanoTime();
        long now;

        while (gameThread != null) {
            now = System.nanoTime();
            delta += (now - last) / time;
            last = now;

            if (delta >= 1) {
                tm.update();
                plantManager.update();
                player.update();

                if (key.fPressed) {
                    int gridX = plantManager.getGridX(player.pX);
                    int gridY = plantManager.getGridY(player.pY);
                    plantManager.plantAtGrid(gridX, gridY);
                    key.fPressed = false;
                }

                if (key.tPressed) {
                    gameMode = (gameMode == GameMode.CAMERA) ? GameMode.MAP_VIEWER : GameMode.CAMERA;
                    key.tPressed = false;
                }

                repaint();
                delta--;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (gameMode == GameMode.CAMERA) {
            tm.draw(g2d);
            plantManager.draw(g2d, player.pX, player.pY, player.sX, player.sY);

            player.draw(g2d);

            int gridX = plantManager.getGridX(player.pX);
            int gridY = plantManager.getGridY(player.pY);
            gridRenderer.drawSelectedCell(g2d, player.pX, player.pY, player.sX, player.sY, gridX, gridY);

        } else {
            mapViewer.draw(g2d, width, height);
        }

        g2d.dispose();
    }
}
