import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private final List<Plant> plants = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Sun> suns = new ArrayList<>();

    // ==== getter cho PlantPanel dùng ====
    public List<Plant> getPlants() {
        return plants;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public List<Sun> getSuns() {
        return suns;
    }

    // ==== tiện ích để chỗ khác thêm đạn / sun vào world ====
    public void addPlant(Plant plant) {
        if (plant != null) {
            plants.add(plant);
        }
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public void addProjectile(Projectile projectile) {
        if (projectile != null) {
            projectiles.add(projectile);
        }
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public void addSun(Sun sun) {
        if (sun != null) {
            suns.add(sun);
        }
    }

    public void removeSun(Sun sun) {
        suns.remove(sun);
    }

    // ... phần update() logic khác của bạn ...
}
