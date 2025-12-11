package Zombies;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Zombie extends JPanel {

    private int hp;
    private double speed;
    private int row;
    private int dmg;
    private int px;
    private int py;
    private int px0;
    private int py0;
    private boolean dead;

    public enum State {
        ADVANCE, ATTACK, DEAD;
    }
    State state;

    public Image zombieNormalAttackImage;
    public Image zombieNormalAdvanceImage;
    public Image zombieFrozenAttackImage;
    public Image zombieFrozenAdvanceImage;
    public Image zombieAdvanceImage;
    public Image zombieAttackImage;
    public Image zombieDeadImage;
    public Image zombieHeadImage;

    Timer resetSpeedTimer;

    public Zombie(int row, int startX, int hp, double speed) {
        this.row = row;
        this.px = startX;
        this.hp = hp;
        this.speed = speed;
        this.dead = false;
        state = State.ADVANCE;

        zombieAdvanceImage = zombieNormalAdvanceImage;
        zombieAttackImage = zombieNormalAttackImage;

        resetSpeedTimer = new Timer(4000, (ActionListener) -> resetSpeed()); //Thoát làm chậm sau 4 giây
        resetSpeedTimer.start();
        
    }

    public void attack() {
        for (int i = 1; i <= 5; i++) {
            ArrayList<Zombie> zombieArrayList = getZombieArrayList(i);
            Iterator iterator = zombieArrayList.iterator();
            while (iterator.hasNext()) {
                Zombie zombie = (Zombie) iterator.next();
                if (zombie.getState() == Zombie.State.ATTACK) {
                    if (gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).isAssigned()) {
                        gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).loseLife();
                    }
                }
            }
        }
    }

    public int getRow() { 
        return row; 
    }
    public int getX() { 
        return px;
    }
    public int getHp() { 
        return hp; 
    }
    public boolean isDead() { 
        return dead; 
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void hpcheck() {
        if (hp <= 0) {
            dead = true;
            return;
        }
        px -= speed;
    } //kiểm tra còn sống ko

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) dead = true;
    } //bị bắn

    public void paintComponent(Graphics g) {
        if (state == State.ADVANCE) {
            super.paintComponent(g);
            g.drawImage(zombieAdvanceImage, px, py, null);
        } else if (state == State.ATTACK) {
            super.paintComponent(g);
            g.drawImage(zombieAttackImage, px - 2, py -3, null);
        } else if (state == State.DEAD) {
            super.paintComponent(g);
            g.drawImage(zombieDeadImage, px, py, null);
            g.drawImage(zombieHeadImage, px, py, null);
        }
    }

    public void resetSpeed() {
        px = px0;
        py = py0;
        setSpeed(2);
        zombieAdvanceImage = zombieNormalAdvanceImage;
        zombieAttackImage = zombieNormalAttackImage;
        resetSpeedTimer.stop();
    }

    public void slow() {
        px = 0;
        py = 0;
        
        if (speed != 1) {
            zombieAdvanceImage = zombieFrozenAdvanceImage;
            zombieAttackImage = zombieFrozenAttackImage;
            setSpeed(1);
        }
        resetSpeedTimer.restart();
    }

}    
