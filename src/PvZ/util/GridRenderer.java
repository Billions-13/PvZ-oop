package PvZ.util;

import java.awt.*;

public class GridRenderer {
    private final int blockSize;
    private final int maxCols;
    private final int maxRows;
    private final Color gridColor = new Color(255, 255, 255, 100); 
    private final Color selectedCellColor = new Color(255, 255, 255, 200);
    private final Stroke gridStroke = new BasicStroke(2);
    private final Stroke selectedStroke = new BasicStroke(3);

    public GridRenderer(int blockSize, int maxCols, int maxRows) {
        this.blockSize = blockSize * 2;
        this.maxCols = maxCols;
        this.maxRows = maxRows;
    }

    public void drawGrid(Graphics2D g2, int playerX, int playerY, 
                         int playerScreenX, int playerScreenY,
                         int screenWidth, int screenHeight) {
        g2.setColor(gridColor);
        g2.setStroke(gridStroke);

        for (int col = 0; col <= maxCols; col++) {
            int worldX = col * blockSize;
            int screenX = worldX - playerX + playerScreenX;
            if (screenX > 0 && screenX < screenWidth) {
                g2.drawLine(screenX, 0, screenX, screenHeight);
            }
        }

        for (int row = 0; row <= maxRows; row++) {
            int worldY = row * blockSize;
            int screenY = worldY - playerY + playerScreenY;
            if (screenY > 0 && screenY < screenHeight) {
                g2.drawLine(0, screenY, screenWidth, screenY);
            }
        }
    }

    public void drawSelectedCell(Graphics2D g2, int playerX, int playerY,
                                 int playerScreenX, int playerScreenY,
                                 int gridX, int gridY) {
        int worldX = gridX * blockSize;
        int worldY = gridY * blockSize;
        int screenX = worldX - playerX + playerScreenX;
        int screenY = worldY - playerY + playerScreenY;

        // Vẽ viền trắng quanh ô
        g2.setColor(selectedCellColor);
        g2.setStroke(selectedStroke);
        g2.drawRect(screenX, screenY, blockSize, blockSize);

        // Vẽ một lớp viền bên trong
        g2.setColor(new Color(255, 255, 255, 150));
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(screenX + 2, screenY + 2, blockSize - 4, blockSize - 4);
    }

    /**
     * Vẽ viền xung quanh một ô cụ thể (có thể dùng cho cây, zombie, etc.)
     */
    public void drawCellBorder(Graphics2D g2, int playerX, int playerY,
                               int playerScreenX, int playerScreenY,
                               int gridX, int gridY, Color color, Stroke stroke) {
        int worldX = gridX * blockSize;
        int worldY = gridY * blockSize;
        int screenX = worldX - playerX + playerScreenX;
        int screenY = worldY - playerY + playerScreenY;

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.drawRect(screenX, screenY, blockSize, blockSize);
    }

    public void drawCellHighlight(Graphics2D g2, int playerX, int playerY,
                                  int playerScreenX, int playerScreenY,
                                  int gridX, int gridY, Color color) {
        int worldX = gridX * blockSize;
        int worldY = gridY * blockSize;
        int screenX = worldX - playerX + playerScreenX;
        int screenY = worldY - playerY + playerScreenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, blockSize, blockSize);
    }

    public int getCellSize() {
        return blockSize;
    }
}
