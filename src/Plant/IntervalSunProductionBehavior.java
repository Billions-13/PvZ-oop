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
        if (elapsed < produceInterval) {
            return;
        }

        // Hook: sau này nối với SunManager / GameBoard để spawn Sun object thật
        System.out.println(
                "Sunflower at row " + source.getRow()
                        + ", col " + source.getCol()
                        + " produces sun (value = " + sunAmount + ")"
        );

        source.setLastActTime(currentTime);
    }
}
