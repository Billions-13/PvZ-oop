package PvZ.entity;

import PvZ.*;
import PvZ.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

public class Player extends Entity implements MovementHandler.PlayerPosition {
    private static final Logger logger = Logger.getLogger(Player.class.getName());
    
    private final GamePanel gp;
    private final AnimationController animationController;
    private final MovementHandler movementHandler;
    private final CollisionDetector collisionDetector;

    public static BufferedImage fliph(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage flipped = new BufferedImage(w, h, img.getType());
        Graphics2D g = flipped.createGraphics();

        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();

        return flipped;
    }

    public final int sX;
    public final int sY;

    public Player(Keybind kb, GamePanel gp) {
        this.gp = gp;
        sX = gp.mwidth/2 - (gp.block/2);
        sY = gp.mheight/2 - (gp.block/2);
        
        // Khởi tạo stat trước để có speed
        this.pX = gp.width/2 - (gp.block/2);
        this.pY = gp.height/2 - (gp.block/2);
        this.sp = 3;
        this.direction = "";
        
        // Khởi tạo các handler
        this.animationController = new AnimationController();
        this.collisionDetector = new CollisionDetector(
            gp.width,
            gp.height,
            gp.block
        );
        this.movementHandler = new MovementHandler(kb, collisionDetector, sp);
        
        try {
            stand = ResourceLoader.loadImage("/stand.png");
            right1 = ResourceLoader.loadImage("/run1.png");
            right2 = ResourceLoader.loadImage("/run2.png");
            right3 = ResourceLoader.loadImage("/run3.png");
            up1 = ResourceLoader.loadImage("/up1.png");
        } catch (IOException e){
            logger.severe(() ->  e.getMessage());
        }
    }

    public void getImage(){
        // Phương thức này để tương thích với Entity base class
        try {
            stand = ResourceLoader.loadImage("/stand.png");
            right1 = ResourceLoader.loadImage("/run1.png");
            right2 = ResourceLoader.loadImage("/run2.png");
            right3 = ResourceLoader.loadImage("/run3.png");
            up1 = ResourceLoader.loadImage("/up1.png");
        } catch (IOException e){
            logger.severe(() -> e.getMessage());
        }
    }

    @Override
    public void moveBy(int dx, int dy) {
        pX += dx;
        pY += dy;
    }

    public void update(){
        direction = movementHandler.update(pX, pY, this);

        if (!"stand".equals(direction)) {
            animationController.update();
            num = animationController.getCurrentFrame();
        } else {
            animationController.reset();
            num = 1;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage img = getImageForDirection();
        g2.drawImage(img, sX, sY, gp.block, gp.block, null);
    }

    private BufferedImage getImageForDirection() {
        return switch (direction) {
            case "up" -> up1;
            case "down" -> stand;
            case "left" -> getLeftImage();
            case "right" -> getRightImage();
            default -> stand;
        };
    }

    private BufferedImage getLeftImage() {
        return switch (num) {
            case 1 -> fliph(right1);
            case 2 -> fliph(right2);
            case 3 -> fliph(right3);
            default -> fliph(right1);
        };
    }

    private BufferedImage getRightImage() {
        return switch (num) {
            case 1 -> right1;
            case 2 -> right2;
            case 3 -> right3;
            default -> right1;
        };
    }
}
