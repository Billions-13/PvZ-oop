public abstract class Plant {

    protected String name;
    protected int health;
    protected int attackDamage;
    protected double attackSpeed;   // thời gian giữa 2 lần tấn công (giây)
    protected double coolDown;
    protected int row, col;
    protected boolean isAlive;
    private int sunCost;
    private double positionX, positionY;
    private PlantType plantType;
    protected double lastActTime;   // mốc thời gian hành động gần nhất
    private EffectType specialEffect;
    private String spritePath;

    private AttackHandler attackHandler;
    private AttackBehavior attackBehavior;

    // 🔥 NEW: trạng thái Plant (state machine đơn giản)
    private PlantState state = PlantState.SPAWNING;

    // =============== CONSTRUCTOR ===============
    protected Plant(String name,
                    int health,
                    int attackDamage,
                    double attackSpeed,
                    double coolDown,
                    int row,
                    int col,
                    boolean isAlive,
                    int sunCost,
                    double positionX,
                    double positionY,
                    PlantType plantType,
                    double lastActTime,
                    EffectType specialEffect,
                    String spritePath,
                    AttackHandler attackHandler) {

        this.name = name;
        this.health = Math.max(0, health);
        this.attackDamage = Math.max(0, attackDamage);
        this.attackSpeed = Math.max(0.0, attackSpeed);
        this.coolDown = Math.max(0.0, coolDown);
        this.row = row;
        this.col = col;
        this.sunCost = Math.max(0, sunCost);
        this.isAlive = (this.health > 0);
        this.positionX = positionX;
        this.positionY = positionY;
        this.plantType = plantType;
        this.lastActTime = Math.max(0.0, lastActTime);
        this.specialEffect = specialEffect;
        this.spritePath = spritePath;
        this.attackHandler = attackHandler;
        this.state = this.isAlive ? PlantState.SPAWNING : PlantState.DEAD;
    }

    // =============== GETTERS ===============

    // infor
    public String getName() { return name; }
    public PlantType getPlantType() { return plantType; }
    public EffectType getSpecialEffect() { return specialEffect; }
    public String getSpritePath() { return spritePath; }
    public PlantState getState() { return state; }

    // combat
    public int getHealth() { return health; }
    public int getAttackDamage() { return attackDamage; }
    public double getAttackSpeed() { return attackSpeed; }
    public double getCoolDown() { return coolDown; }
    public int getSunCost() { return sunCost; }
    public AttackHandler getAttackHandler() { return attackHandler; }
    public AttackBehavior getAttackBehavior() { return attackBehavior; }

    // position
    public int getRow() { return row; }
    public int getCol() { return col; }
    public double getPositionX() { return positionX; }
    public double getPositionY() { return positionY; }

    // state/time
    public boolean isAlive() { return isAlive; }
    public double getLastActTime() { return lastActTime; }

    // =============== SETTERS ===============

    public void setName(String name) { this.name = name; }

    protected void setHealth(int health) {
        this.health = Math.max(0, health);
        if (this.health == 0) {
            this.isAlive = false;
            this.state = PlantState.DYING;
        }
    }

    protected void setAttackDamage(int attackDamage) {
        this.attackDamage = Math.max(0, attackDamage);
    }

    protected void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = Math.max(0.0, attackSpeed);
    }

    protected void setCoolDown(double coolDown) {
        this.coolDown = Math.max(0.0, coolDown);
    }

    protected void setSunCost(int sunCost) {
        this.sunCost = Math.max(0, sunCost);
    }

    protected void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    protected void setSpecialEffect(EffectType specialEffect) {
        this.specialEffect = specialEffect;
    }

    protected void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setAttackHandler(AttackHandler attackHandler) {
        this.attackHandler = attackHandler;
    }

    public void setAttackBehavior(AttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
    }

    public void setRow(int row) { this.row = row; }
    public void setCol(int col) { this.col = col; }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void setPositionX(double positionX) { this.positionX = positionX; }
    public void setPositionY(double positionY) { this.positionY = positionY; }

    protected void setAlive(boolean alive) {
        this.isAlive = alive;
        if (!alive) {
            this.health = 0;
            this.state = PlantState.DEAD;
        }
    }

    protected void setLastActTime(double lastActTime) {
        this.lastActTime = Math.max(0.0, lastActTime);
    }

    protected void setState(PlantState state) {
        this.state = state;
    }

    // =============== CONVENIENCE ===============

    /** Gây sát thương cho plant (dùng cho zombie attack). */
    public void takeDamage(int damage) {
        if (damage <= 0 || !isAlive) return;
        setHealth(this.health - damage);
        if (!isAlive) {
            setState(PlantState.DYING);
        }
    }

    /** Hồi máu cho plant (vd: hiệu ứng heal). */
    public void heal(int amount) {
        if (amount <= 0 || !isAlive) return;
        setHealth(this.health + amount);
    }

    /** Kiểm tra plant có đủ điều kiện để hành động (tấn công) không. */
    protected boolean canAct(double currentTime) {
        if (!isAlive) return false;
        if (state == PlantState.DEAD || state == PlantState.DYING) return false;
        double elapsed = currentTime - getLastActTime();
        return elapsed >= getAttackSpeed();
    }

    /**
     * Thực hiện tấn công theo Strategy nếu có.
     * Subclass gọi hàm này trong update().
     */
    protected void doAttack() {
        if (attackBehavior != null && isAlive) {
            setState(PlantState.ATTACKING);
            attackBehavior.performAttack(this);
            setState(PlantState.COOLDOWN);
        }
    }

    public abstract void onPlaced();
    public abstract void update(double currentTime);
    public abstract void onRemoved();
}
