package Zombies;

import javax.swing.ImageIcon;

public class Flag extends Zombie {

    public Flag(int row,int startX){
        super(row,startX,190,4.7);
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/FlagZombieImage/FlagZombie.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/FlagZombieImage/FlagZombieAttack.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/FlagZombieImage/frozenFlagZombie.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/FlagZombieImage/frozenFlagZombieAttack.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombieImage/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombieImage/ZombieHead.gif")).getImage();
    }
}