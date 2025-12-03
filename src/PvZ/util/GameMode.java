package PvZ.util;

/**
 * GameMode - Chọn cách hiển thị game
 * 
 * CAMERA: Theo dõi nhân vật (hiện tại)
 * MAP_VIEWER: Hiển thị toàn bộ map
 */
public enum GameMode {
    CAMERA("Camera View - Theo dõi nhân vật"),
    MAP_VIEWER("Map Viewer - Toàn bộ bản đồ");

    private final String description;

    GameMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
