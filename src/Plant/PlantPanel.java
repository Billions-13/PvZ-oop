import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.

/**
 * Panel hiển thị game:
 * - Giữ danh sách PlantView.
 * - Đồng bộ với GameWorld mỗi frame.
 * - Gọi render() để cập nhật sprite theo state/position mới.
 */
public class PlantPanel extends JPanel {

    private final GameWorld world;
    private final List<PlantView> plantViews = new ArrayList<>();

    public PlantPanel(GameWorld world) {
        this.world = world;
        setLayout(null);          // tự setLocation cho từng JLabel
        setBackground(Color.DARK_GRAY); // tạm background
    }

    /**
     * Đồng bộ giữa danh sách Plant (model) với danh sách PlantView (view):
     * - Plant mới xuất hiện -> tạo PlantView + add JLabel vào panel.
     * - Plant chết / bị xóa khỏi world -> remove JLabel + remove PlantView.
     */
    private void syncPlants() {
        // 1. Xóa view của plant không còn trong world hoặc đã chết
        Iterator<PlantView> it = plantViews.iterator();
        while (it.hasNext()) {
            PlantView pv = it.next();
            Plant plant = pv.getPlant();

            boolean stillInWorld = world.getPlants().contains(plant);
            if (!stillInWorld || !plant.isAlive()) {
                remove(pv.getLabel());  // xóa sprite khỏi panel
                it.remove();
            }
        }

        // 2. Tạo view cho plant mới (có trong world nhưng chưa có view)
        for (Plant p : world.getPlants()) {
            boolean hasView = false;
            for (PlantView pv : plantViews) {
                if (pv.getPlant() == p) {
                    hasView = true;
                    break;
                }
            }

            if (!hasView) {
                PlantView pv = new PlantView(p);
                plantViews.add(pv);
                add(pv.getLabel());
            }
        }
    }

    /**
     * Gọi mỗi frame (sau khi world.update(...) đã chạy xong):
     * - Sync model <-> view.
     * - Gọi render() cho từng PlantView.
     * - Yêu cầu Swing vẽ lại.
     */
    public void render() {
        // Đồng bộ danh sách
        syncPlants();

        // Cập nhật sprite theo state/position
        for (PlantView pv : plantViews) {
            pv.render();
        }

        // Báo Swing là panel đã thay đổi, cần vẽ lại
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Nếu muốn vẽ thêm nền, grid, đường lane, v.v. thì vẽ ở đây
    }
}
