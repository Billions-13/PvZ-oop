package Zombies;

import javax.swing.ImageIcon;

public class ConeHead extends Zombie {

    private int coneHp = 370;
    private boolean coneBroken = false;

    public ConeHead(int row,int startX){
        super(row,startX,190,4.7);
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ConeheadZombie/ConeheadZombie.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/ConeheadZombie/ConeheadZombieAttack.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ConeheadZombie/frozenConeheadZombie.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/ConeheadZombie/frozenConeheadZombieAttack.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombieImage/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombieImage/ZombieHead.gif")).getImage();
    }

    @Override
    public void takeDamage(int dmg) {
        if (!coneBroken) {
            coneHp -= dmg;

            if (coneHp <= 0) {
                coneBroken = true;
            }
            return;
        }
        super.takeDamage(dmg);
    }
}