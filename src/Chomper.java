public class Chomper extends Plant {

    private static final int default_health = 300;
    private static final int damage = 150;          // cắn rất đau
    private static final double biteCool    down = 7.0; // thời gian giữa hai cú cắn
    private static final double cooldown = 7.0;
    private static final int default_sunCost = 150;
    private static final String default_spritePath = "Chomper.png";

    public Chomper(int row, int col, double positionX, double positionY) {
        super("Chomper",
                default_health,
                damage,
                biteCooldown,
                cooldown,
                row,
                col,
                true,
                default_sunCost,
                positionX,
                positionY,
                PlantType.CHOMPER,
                0.0,
                EffectType.NONE,
                default_spritePath,
                null
        );
    }

    @Override
    public void onPlaced() {
        System.out.println("Chomper placed at row " + getRow() + ", col " + getCol());
    }

    @Override
    public void update(double currentTime) {
        if (!isAlive())
            return;

        if (getState() == PlantState.SPAWNING) {
            setState(PlantState.IDLE);
        }

        double elapsed = currentTime - getLastActTime();
        if (elapsed >= getAttackSpeed()) {
            performBite();
            setLastActTime(currentTime);
        }


        if (canAct(currentTime)) {
            doAttack();
            setLastActTime(currentTime);
        }
    }

   /* private void performBite() {
        // Hook: trong game thật, Chomper sẽ tìm zombie gần nhất trong tầm rồi cắn one-shot
        System.out.println("Chomper at row " + getRow() + ", col " + getCol()
                + " bites a zombie (damage = " + getAttackDamage() + ")");
    }*/

    @Override
    public void onRemoved() {
        System.out.println("Chomper removed.");
    }
}
