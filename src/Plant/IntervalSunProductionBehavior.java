public class IntervalSunProductionBehavior implements SunProductionBehavior {

    private final double produceInterval; // khoảng cách giữa 2 lần sinh sun (giây)
    private final int sunAmount;          // giá trị sun tạo ra mỗi lần

    public IntervalSunProductionBehavior(double produceInterval, int sunAmount) {
        this.produceInterval = Math.max(0.0, produceInterval);
        this.sunAmount = Math.max(0, sunAmount);
    }

    @Override
    public void updateSunProduction(Sunflower source, double currentTime) {
        if (!source.isAlive()) return;

        double elapsed = currentTime - source.getLastActTime();
        if (elapsed < produceInterval) return;

        double x = source.getPositionX();
        double y = source.getPositionY() - 40;

        Sun sun = new Sun(
                x,
                y,
                sunAmount,
                false,   // fromSky = false → không rơi
                y        // targetY không dùng
        );

        source.getGameWorld().addSun(sun); // hoặc truyền world từ ngoài vào

        source.setLastActTime(currentTime);
    }
}
