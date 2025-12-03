package PvZ.util;

import PvZ.Keybind;

public class MovementHandler {
    private final Keybind keybind;
    private final CollisionDetector collisionDetector;
    private final int speed;

    public MovementHandler(Keybind keybind, CollisionDetector collisionDetector, int speed) {
        this.keybind = keybind;
        this.collisionDetector = collisionDetector;
        this.speed = speed;
    }

    public String update(int currentX, int currentY, PlayerPosition position) {
        int ds = speed - 1;
        String direction = "stand";

        if (keybind.upPressed || keybind.downPressed || keybind.rightPressed || keybind.leftPressed) {
            if (keybind.upPressed && keybind.leftPressed) {
                direction = "left";
                if (collisionDetector.canMoveUpLeft(currentX, currentY, ds)) {
                    position.moveBy(-ds, -ds);
                }
            } else if (keybind.upPressed && keybind.rightPressed) {
                direction = "right";
                if (collisionDetector.canMoveUpRight(currentX, currentY, ds)) {
                    position.moveBy(ds, -ds);
                }
            } else if (keybind.downPressed && keybind.leftPressed) {
                direction = "left";
                if (collisionDetector.canMoveDownLeft(currentX, currentY, ds)) {
                    position.moveBy(-ds, ds);
                }
            } else if (keybind.downPressed && keybind.rightPressed) {
                direction = "right";
                if (collisionDetector.canMoveDownRight(currentX, currentY, ds)) {
                    position.moveBy(ds, ds);
                }
            } else if (keybind.upPressed) {
                direction = "up";
                if (collisionDetector.canMoveUp(currentX, currentY, speed)) {
                    position.moveBy(0, -speed);
                }
            } else if (keybind.downPressed) {
                direction = "down";
                if (collisionDetector.canMoveDown(currentX, currentY, speed)) {
                    position.moveBy(0, speed);
                }
            } else if (keybind.leftPressed) {
                direction = "left";
                if (collisionDetector.canMoveLeft(currentX, currentY, speed)) {
                    position.moveBy(-speed, 0);
                }
            } else if (keybind.rightPressed) {
                direction = "right";
                if (collisionDetector.canMoveRight(currentX, currentY, speed)) {
                    position.moveBy(speed, 0);
                }
            }
        }

        return direction;
    }

    public interface PlayerPosition {
        void moveBy(int dx, int dy);
    }
}
