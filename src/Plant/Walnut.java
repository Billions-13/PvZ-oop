public class Walnut extends Plant {

    private static final int default_health = 4000;
    private static final int default_sunCost = 50;
    private static final double cooldown = 30.0;
    private static final String default_spritePath = "Walnut.png";

    public Walnut(int row, int col, double positionX, double positionY) {
        super("Walnut",
                default_health,
                0,
                0.0,
                cooldown,
                row,
                col,
                true,
                default_sunCost,
                positionX,
                positionY,
                PlantType.WALNUT,
                0.0,
                EffectType.NONE,
                default_spritePath,
                null
        );
    }

    @Override
    public void onPlaced() {
        System.out.println("Walnut placed at row " + getRow() + ", col " + getCol());
    }

    @Override
    public void update(double currentTime) {
        if (!isAlive()) return;
        // Walnut chỉ đứng chắn, sau này có thể đổi sprite theo % máu
    }

    @Override
    public void onRemoved() {
        System.out.println("Walnut removed.");
    }
}
