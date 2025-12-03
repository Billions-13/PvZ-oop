package PvZ.Tile;

import PvZ.util.ResourceLoader;
import java.io.IOException;

public class TileSet {
    private final Tile[] tiles;
    private static final int TILE_COUNT = 8;

    public TileSet() {
        tiles = new Tile[TILE_COUNT];
        loadTiles();
    }

    private void loadTiles() {
        try {
            tiles[0] = new Tile();
            tiles[0].setImage(ResourceLoader.loadImageFromURL("/grass1.png"));
            
            tiles[1] = new Tile();
            tiles[1].setImage(ResourceLoader.loadImageFromURL("/grass2.png"));
            
            tiles[2] = new Tile();
            tiles[2].setImage(ResourceLoader.loadImageFromURL("/grass1p.png"));
            
            tiles[3] = new Tile();
            tiles[3].setImage(ResourceLoader.loadImageFromURL("/grass2p.png"));
            
            tiles[4] = new Tile();
            tiles[4].setImage(ResourceLoader.loadImageFromURL("/tile_house.png"));
            
            tiles[5] = new Tile();
            tiles[5].setImage(ResourceLoader.loadImageFromURL("/tile_house2.png"));
            
            tiles[6] = new Tile();
            tiles[6].setImage(ResourceLoader.loadImageFromURL("/tree1.png"));
            
            tiles[7] = new Tile();
            tiles[7].setImage(ResourceLoader.loadImageFromURL("/tree2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Tile getTile(int index) {
        if (index < 0 || index >= TILE_COUNT) {
            throw new IndexOutOfBoundsException("Tile index: " + index);
        }
        return tiles[index];
    }

    public int getTileCount() {
        return TILE_COUNT;
    }
}
