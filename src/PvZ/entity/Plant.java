package PvZ.entity;

import java.awt.image.BufferedImage;

public class Plant {
    public int gridX;
    public int gridY;
    public int worldX;
    public int worldY;
    public BufferedImage image;
    public String type;

    public Plant(int gridX, int gridY, int worldX, int worldY, String type) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.worldX = worldX;
        this.worldY = worldY;
        this.type = type;
    }

    public void setImage(BufferedImage img) {
        this.image = img;
    }
}
