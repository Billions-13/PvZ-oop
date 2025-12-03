package PvZ.util;

/**
 * Xử lý logic animation cho nhân vật
 * Single Responsibility: Chỉ lo quản lý animation frame
 */
public class AnimationController {
    private int counter = 0;
    private int animationFrame = 1;
    private final int maxFrame = 3;
    private final int frameDuration = 20; // Số frame trước khi đổi ảnh

    /**
     * Cập nhật animation state
     */
    public void update() {
        counter++;
        if (counter > frameDuration) {
            animationFrame++;
            if (animationFrame > maxFrame) {
                animationFrame = 1;
            }
            counter = 0;
        }
    }

    /**
     * Reset animation về frame đầu tiên
     */
    public void reset() {
        counter = 0;
        animationFrame = 1;
    }

    /**
     * Lấy frame animation hiện tại
     */
    public int getCurrentFrame() {
        return animationFrame;
    }
}
