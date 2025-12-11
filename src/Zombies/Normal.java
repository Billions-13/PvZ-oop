package Zombies;

import javax.swing.ImageIcon;

public class Normal extends Zombie {

    public Normal(int row,int startX){
        super(row,startX,190,4.7);
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombie/ZombieAdvanceImage.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombie/ZombieAttackImage.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombie/frozenZombieAdvanceImage.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombie/frozenZombieAttackImage.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombie/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/NormalZombie/ZombieHead.gif")).getImage();
    }
}