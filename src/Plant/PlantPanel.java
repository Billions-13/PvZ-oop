import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlantPanel extends JPanel {

    private final GameWorld world;

    private final List<PlantView> plantViews = new ArrayList<>();
    private final List<ProjectileView> projectileViews = new ArrayList<>();
    private final List<SunView> sunViews = new ArrayList<>();

    public PlantPanel(GameWorld world) {
        this.world = world;
        setLayout(null);
        setBackground(Color.DARK_GRAY);
    }

    // ... syncPlants() của bạn giữ nguyên ...

    // ============ PROJECTILE ============

    private void syncProjectiles() {
        List<Projectile> projectiles = world.getProjectiles();
        if (projectiles == null) return;

        // 1. Xóa view của projectile không còn trong world hoặc không active
        Iterator<ProjectileView> it = projectileViews.iterator();
        while (it.hasNext()) {
            ProjectileView pv = it.next();
            Projectile proj = pv.getProjectile();

            boolean stillInWorld = projectiles.contains(proj);
            if (!stillInWorld || !proj.isActive()) {
                remove(pv.getLabel());
                it.remove();
            }
        }

        // 2. Tạo view cho projectile mới
        for (Projectile p : projectiles) {
            if (!p.isActive()) continue;

            boolean hasView = false;
            for (ProjectileView pv : projectileViews) {
                if (pv.getProjectile() == p) {
                    hasView = true;
                    break;
                }
            }

            if (!hasView) {
                ProjectileView pv = new ProjectileView(p);
                projectileViews.add(pv);
                add(pv.getLabel());
            }
        }
    }

    // ============ SUN ============

    private void syncSuns() {
        List<Sun> suns = world.getSuns();
        if (suns == null) return;

        // 1. Xóa view của sun không còn trong world hoặc đã collect
        Iterator<SunView> it = sunViews.iterator();
        while (it.hasNext()) {
            SunView sv = it.next();
            Sun sun = sv.getSun();

            boolean stillInWorld = suns.contains(sun);
            if (!stillInWorld || sun.isCollected()) {
                remove(sv.getLabel());
                it.remove();
            }
        }

        // 2. Tạo view cho sun mới
        for (Sun s : suns) {
            if (s.isCollected()) continue;

            boolean hasView = false;
            for (SunView sv : sunViews) {
                if (sv.getSun() == s) {
                    hasView = true;
                    break;
                }
            }

            if (!hasView) {
                SunView sv = new SunView(s);
                sunViews.add(sv);
                add(sv.getLabel());
            }
        }
    }

    // ============ RENDER ============

    public void render() {
        syncPlants();
        syncProjectiles();
        syncSuns();

        for (PlantView pv : plantViews) pv.render();
        for (ProjectileView pv : projectileViews) pv.render();
        for (SunView sv : sunViews) sv.render();

        revalidate();
        repaint();
    }
}
