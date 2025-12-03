package PvZ.util;

import PvZ.GamePanel;
import java.awt.*;
import java.util.List;

/**
 * MapViewer - Hiển thị toàn bộ bản đồ không cần camera
 *
 * Tính năng:
 * - Render toàn bộ map tại một lần
 * - Không phải render từng frame (static/cached)
 * - Có thể zoom in/out để xem
 * - Hiển thị các element (player, plants) ở vị trí đúng
 */
public class MapViewer {

    private final GamePanel panel;

    // Cấu hình hiển thị
    private static final boolean SHOW_GRID = true;
    private static final boolean SHOW_COORDINATES = true;
    private static final Color GRID_COLOR = new Color(100, 100, 100, 100);
    private static final Color COORD_COLOR = new Color(200, 200, 200);

    public MapViewer(GamePanel panel) {
        this.panel = panel;
    }

    /**
     * Vẽ toàn bộ bản đồ
     */
    public void draw(Graphics2D g2d, int worldWidth, int worldHeight) {
        // Vẽ nền
        g2d.setColor(new Color(34, 34, 34));
        g2d.fillRect(0, 0, panel.mwidth * 2, panel.mheight * 2);

        // Tính scale fit map vào màn hình
        float scaleX = (panel.mwidth * 2 - 20f) / worldWidth;
        float scaleY = (panel.mheight * 2 - 20f) / worldHeight;
        float fitScale = Math.min(scaleX, scaleY);

        // Vẽ full map tiles
        drawFullMap(g2d, worldWidth, worldHeight, fitScale);

        // Vẽ lưới nếu bật
        if (SHOW_GRID) {
            drawGridOverlay(g2d, worldWidth, worldHeight, fitScale);
        }

        // Vẽ tọa độ nếu bật
        if (SHOW_COORDINATES) {
            drawCoordinates(g2d, worldWidth, worldHeight, fitScale);
        }
    }

    /**
     * Vẽ toàn bộ bản đồ bằng tile
     */
    private void drawFullMap(Graphics2D g2d, int worldWidth, int worldHeight, float scale) {
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int startX = (panel.mwidth * 2 - (int)(worldWidth * scale)) / 2;
        int startY = (panel.mheight * 2 - (int)(worldHeight * scale)) / 2;

        // Vẽ từng tile
        for (int col = 0; col < panel.maxcol; col++) {
            for (int row = 0; row < panel.maxrow; row++) {

                int x = startX + (int)(col * panel.block * 2 * scale);
                int y = startY + (int)(row * panel.block * 2 * scale);
                int size = (int)(panel.block * 2 * scale);

                // Tile màu xanh
                g2d.setColor(new Color(76, 153, 76));
                g2d.fillRect(x, y, size, size);

                // Border
                g2d.setColor(new Color(50, 100, 50));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRect(x, y, size, size);
            }
        }
    }

    /**
     * Vẽ lưới overlay
     */
    private void drawGridOverlay(Graphics2D g2d, int worldWidth, int worldHeight, float scale) {
        int startX = (panel.mwidth * 2 - (int)(worldWidth * scale)) / 2;
        int startY = (panel.mheight * 2 - (int)(worldHeight * scale)) / 2;

        g2d.setColor(GRID_COLOR);
        g2d.setStroke(new BasicStroke(1));

        // Dọc
        for (int col = 0; col <= panel.maxcol; col++) {
            int x = startX + (int)(col * panel.block * 2 * scale);
            g2d.drawLine(x, startY, x, startY + (int)(worldHeight * scale));
        }

        // Ngang
        for (int row = 0; row <= panel.maxrow; row++) {
            int y = startY + (int)(row * panel.block * 2 * scale);
            g2d.drawLine(startX, y, startX + (int)(worldWidth * scale), y);
        }
    }

    /**
     * Vẽ tọa độ trên tile
     */
    private void drawCoordinates(Graphics2D g2d, int worldWidth, int worldHeight, float scale) {
        int startX = (panel.mwidth * 2 - (int)(worldWidth * scale)) / 2;
        int startY = (panel.mheight * 2 - (int)(worldHeight * scale)) / 2;

        g2d.setColor(COORD_COLOR);
        g2d.setFont(new Font("Arial", Font.PLAIN, (int)(10 * scale)));

        for (int col = 0; col < panel.maxcol; col++) {
            for (int row = 0; row < panel.maxrow; row++) {

                int x = startX + (int)(col * panel.block * 2 * scale) + (int)(10 * scale);
                int y = startY + (int)(row * panel.block * 2 * scale) + (int)(20 * scale);

                String coord = col + "," + row;
                g2d.drawString(coord, x, y);
            }
        }
    }

    /**
     * Đánh dấu nhân vật trên map view
     */
    public void drawPlayerMarker(Graphics2D g2d,
                                 int playerX, int playerY,
                                 int playerSizeX, int playerSizeY,
                                 int worldWidth, int worldHeight) {

        float scaleX = (panel.mwidth * 2 - 20f) / worldWidth;
        float scaleY = (panel.mheight * 2 - 20f) / worldHeight;
        float fitScale = Math.min(scaleX, scaleY);

        int startX = (panel.mwidth * 2 - (int)(worldWidth * fitScale)) / 2;
        int startY = (panel.mheight * 2 - (int)(worldHeight * fitScale)) / 2;

        int markerX = startX + (int)(playerX * fitScale);
        int markerY = startY + (int)(playerY * fitScale);
        int markerSize = (int)(playerSizeX * fitScale);

        g2d.setColor(new Color(255, 0, 0, 150));
        g2d.fillOval(markerX, markerY, markerSize, markerSize);

        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(markerX, markerY, markerSize, markerSize);
    }

    /**
     * Đánh dấu cây trên map view
     */
    public void drawPlantMarkers(Graphics2D g2d,
                                 List<PlantInfo> plants,
                                 int worldWidth, int worldHeight) {

        float scaleX = (panel.mwidth * 2 - 20f) / worldWidth;
        float scaleY = (panel.mheight * 2 - 20f) / worldHeight;
        float fitScale = Math.min(scaleX, scaleY);

        int startX = (panel.mwidth * 2 - (int)(worldWidth * fitScale)) / 2;
        int startY = (panel.mheight * 2 - (int)(worldHeight * fitScale)) / 2;

        for (PlantInfo plant : plants) {

            int markerX = startX + (int)(plant.worldX * fitScale);
            int markerY = startY + (int)(plant.worldY * fitScale);
            int markerSize = (int)(30 * fitScale);

            g2d.setColor(new Color(0, 255, 0, 150));
            g2d.fillOval(markerX, markerY, markerSize, markerSize);

            g2d.setColor(new Color(0, 200, 0));
            g2d.setStroke(new BasicStroke(1));
            g2d.drawOval(markerX, markerY, markerSize, markerSize);
        }
    }

    /**
     * Class chứa thông tin cây
     */
    public static class PlantInfo {
        public int gridX, gridY;
        public int worldX, worldY;
        public String type;

        public PlantInfo(int gridX, int gridY, int worldX, int worldY, String type) {
            this.gridX = gridX;
            this.gridY = gridY;
            this.worldX = worldX;
            this.worldY = worldY;
            this.type = type;
        }
    }
}
