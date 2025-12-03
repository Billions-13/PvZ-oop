package PvZ.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapLoader {
    private final int maxCols;
    private final int maxRows;
    private final int[][] mapData;

    public MapLoader(int maxCols, int maxRows) {
        this.maxCols = maxCols;
        this.maxRows = maxRows;
        this.mapData = new int[maxCols][maxRows];
        loadMap();
    }

    private void loadMap() {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("map.txt");
            
            if (is == null) {
                throw new RuntimeException();
            }
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                for (int r = 0; r < maxRows; r++) {
                    String line = br.readLine();
                    if (line == null) {
                        throw new RuntimeException();
                    }
                    String[] parts = line.split(" ");
                    for (int c = 0; c < maxCols; c++) {
                        mapData[c][r] = Integer.parseInt(parts[c]);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public int getTileId(int col, int row) {
        if (col < 0 || col >= maxCols || row < 0 || row >= maxRows) {
            throw new IndexOutOfBoundsException("Map position out of bounds");
        }
        return mapData[col][row];
    }

    public int[][] getMapData() {
        return mapData;
    }
}
