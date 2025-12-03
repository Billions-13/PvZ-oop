package PvZ;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keybind implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean fPressed = false;
    public boolean gPressed = false;
    public boolean tPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_F:
                fPressed = true;
                break;
            case KeyEvent.VK_G:
                gPressed = true;
                break;
            case KeyEvent.VK_T:
                tPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_F:
                fPressed = false;
                break;
            case KeyEvent.VK_G:
                gPressed = false;
                break;
            case KeyEvent.VK_T:
                tPressed = false;
                break;
        }
    }
}
