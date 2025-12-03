package PvZ.util;

/**
 * Xử lý kiểm tra va chạm và giới hạn di chuyển
 * Single Responsibility: Chỉ lo kiểm tra collision
 */
public class CollisionDetector {
    private final int mapWidth;
    private final int mapHeight;
    private final int blockSize;

    public CollisionDetector(int mapWidth, int mapHeight, int blockSize) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.blockSize = blockSize;
    }

    /**
     * Kiểm tra xem vị trí mới có hợp lệ không (không vượt ra ngoài bản đồ)
     */
    public boolean isValidPosition(int newX, int newY) {
        return newX >= 0 && 
               newX <= (mapWidth - blockSize) && 
               newY >= blockSize && 
               newY <= (mapHeight - blockSize);
    }

    /**
     * Kiểm tra xem người chơi di chuyển up-left có hợp lệ không
     */
    public boolean canMoveUpLeft(int currentX, int currentY, int distance) {
        return isValidPosition(currentX - distance, currentY - distance);
    }

    /**
     * Kiểm tra xem người chơi di chuyển up-right có hợp lệ không
     */
    public boolean canMoveUpRight(int currentX, int currentY, int distance) {
        return isValidPosition(currentX + distance, currentY - distance);
    }

    /**
     * Kiểm tra xem người chơi di chuyển down-left có hợp lệ không
     */
    public boolean canMoveDownLeft(int currentX, int currentY, int distance) {
        return isValidPosition(currentX - distance, currentY + distance);
    }

    /**
     * Kiểm tra xem người chơi di chuyển down-right có hợp lệ không
     */
    public boolean canMoveDownRight(int currentX, int currentY, int distance) {
        return isValidPosition(currentX + distance, currentY + distance);
    }

    /**
     * Kiểm tra xem người chơi di chuyển lên có hợp lệ không
     */
    public boolean canMoveUp(int currentX, int currentY, int distance) {
        return isValidPosition(currentX, currentY - distance);
    }

    /**
     * Kiểm tra xem người chơi di chuyển xuống có hợp lệ không
     */
    public boolean canMoveDown(int currentX, int currentY, int distance) {
        return isValidPosition(currentX, currentY + distance);
    }

    /**
     * Kiểm tra xem người chơi di chuyển trái có hợp lệ không
     */
    public boolean canMoveLeft(int currentX, int currentY, int distance) {
        return isValidPosition(currentX - distance, currentY);
    }

    /**
     * Kiểm tra xem người chơi di chuyển phải có hợp lệ không
     */
    public boolean canMoveRight(int currentX, int currentY, int distance) {
        return isValidPosition(currentX + distance, currentY);
    }
}
