public enum ProjectileType {
    PEA,
    ICE_PEA
}

public class Projectile {

    private double x, y;
    private double speed;
    private int damage;
    private ProjectileType type;
    private boolean active = true;
    private int row;

    public Projectile(int row,
                      double x,
                      double y,
                      double speed,
                      int damage,
                      ProjectileType type) {
        this.row = row;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.type = type;
    }

    public void update(GameWorld world, double deltaTime) {
        if (!active) return;
        x += speed * deltaTime;

        Zombie hit = world.findFirstZombieInPath(row, x);
        if (hit != null && hit.isAlive()) {
            hit.takeDamage(damage);
            if (type == ProjectileType.ICE_PEA) {
                world.applySlowToZombie(hit);
            }
            active = false;
        }
    }

    public boolean isActive() { return active; }
    public int getRow() { return row; }
    public double getX() { return x; }
    public double getY() { return y; }

    public int getDamage() { return damage; }
    public ProjectileType getType() { return type; }
}
