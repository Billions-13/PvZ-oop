package PvZ.Tile;

import PvZ.GamePanel;
import PvZ.entity.Plant;
import PvZ.util.ResourceLoader;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private final GamePanel gp;
    private final List<Plant> plants;
    private Plant currentPlantType;

    public PlantManager(GamePanel gp) {
        this.gp = gp;
        this.plants = new ArrayList<>();
        this.currentPlantType = new Plant(0, 0, 0, 0, "sunflower");
        loadPlantImages();
    }

    private void loadPlantImages() {
        try {
            currentPlantType.setImage(ResourceLoader.loadImage("/tree1.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void plantAtGrid(int gridX, int gridY) {
        if (hasPlantAt(gridX, gridY)) {
            return;
        }

        int worldX = gridX * gp.block * 2;
        int worldY = gridY * gp.block * 2;

        Plant newPlant = new Plant(gridX, gridY, worldX, worldY, "sunflower");
        try {
            newPlant.setImage(ResourceLoader.loadImage("/tree1.png"));
        } catch (IOException e) {
            System.err.println( e.getMessage());
        }

        plants.add(newPlant);
    }

    public boolean hasPlantAt(int gridX, int gridY) {
        return plants.stream()
                .anyMatch(p -> p.gridX == gridX && p.gridY == gridY);
    }

    public void digPlantAt(int gridX, int gridY) {
        plants.removeIf(p -> p.gridX == gridX && p.gridY == gridY);
    }

    public int getGridX(int worldX) {
        int centerX = worldX + gp.block / 2;
        return centerX / (gp.block * 2);
    }

    public int getGridY(int worldY) {
        int centerY = worldY + gp.block / 2;
        return centerY / (gp.block * 2);
    }

    public void draw(Graphics2D g2, int playerX, int playerY, int playerScreenX, int playerScreenY) {
        for (Plant plant : plants) {
            int screenX = plant.worldX - playerX + playerScreenX;
            int screenY = plant.worldY - playerY + playerScreenY;

            if (screenX + gp.block * 2 > 0 && screenX < gp.mwidth * 2 &&
                screenY + gp.block * 2 > 0 && screenY < gp.mheight * 2) {
                g2.drawImage(plant.image, screenX, screenY, 
                           gp.block * 2, gp.block * 2, null);
            }
        }
    }

    public void update() {}

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }
}
