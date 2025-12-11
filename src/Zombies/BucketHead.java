package Zombies;

import javax.swing.ImageIcon;

public class BucketHead extends Zombie {

    private int bucketHp = 1100;
    private boolean bucketBroken = false;

    public BucketHead(int row,int startX){
        super(row,startX,190,4.7);
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/BucketheadZombieImage/BucketheadZombie.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/BucketheadZombieImage/BucketheadZombieAttack.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/BucketheadZombieImage/frozenBucketheadZombie.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/BucketheadZombieImage/frozenBucketheadZombieAttack.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombieImage/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombieImage/ZombieHead.gif")).getImage();
    }

    @Override
    public void takeDamage(int dmg) {
        if (!bucketBroken) {
            bucketHp -= dmg;

            if (bucketHp <= 0) {
                bucketBroken = true;
            }
            return;
        }
        super.takeDamage(dmg);
    }
}