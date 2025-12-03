package PvZ.Tile;

import java.awt.*;

public class MapRenderer {
    private final TileSet tileSet;
    private final MapLoader mapLoader;
    private final int maxCols;
    private final int maxRows;
    private final int blockSize;
    private final int screenWidth;
    private final int screenHeight;

    public MapRenderer(TileSet tileSet, MapLoader mapLoader, 
                      int maxCols, int maxRows, int blockSize,
                      int screenWidth, int screenHeight) {
        this.tileSet = tileSet;
        this.mapLoader = mapLoader;
        this.maxCols = maxCols;
        this.maxRows = maxRows;
        this.blockSize = blockSize;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void draw(Graphics2D g2, int playerX, int playerY, int playerScreenX, int playerScreenY) {
        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                int worldX = c * blockSize * 2;
                int worldY = r * blockSize * 2;
                int screenX = worldX - playerX + playerScreenX;
                int screenY = worldY - playerY + playerScreenY;
                
                if (screenX + blockSize * 2 > 0 && screenX < screenWidth &&
                    screenY + blockSize * 2 > 0 && screenY < screenHeight) {
                    
                    int tileId = mapLoader.getTileId(c, r);
                    Tile tile = tileSet.getTile(tileId);
                    g2.drawImage(tile.getImage(), screenX, screenY, 
                                blockSize * 2, blockSize * 2, null);
                }
            }
        }
    }
}
