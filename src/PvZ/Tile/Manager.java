package PvZ.Tile;

import PvZ.GamePanel;
import java.awt.*;

public class Manager {
    private final GamePanel gp;
    private final TileSet tileSet;
    private final MapLoader mapLoader;
    private final MapRenderer mapRenderer;
    private int frame = 0;

    public Manager(GamePanel gp) {
        this.gp = gp;
        this.tileSet = new TileSet();
        this.mapLoader = new MapLoader(gp.maxcol, gp.maxrow);
        this.mapRenderer = new MapRenderer(
            tileSet, 
            mapLoader, 
            gp.maxcol, 
            gp.maxrow, 
            gp.block,
            gp.mwidth * 2,
            gp.mheight * 2
        );
    }

    public void update() {
        frame++;
        if (frame >= tileSet.getTileCount() * 5) {
            frame = 0;
        }
    }

    public void draw(Graphics2D g2) {
        mapRenderer.draw(g2, gp.player.pX, gp.player.pY, 
                        gp.player.sX, gp.player.sY);
    }
}
