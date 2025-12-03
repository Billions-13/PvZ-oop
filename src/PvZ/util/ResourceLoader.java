package PvZ.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ResourceLoader {

    public static BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(ResourceLoader.class.getResourceAsStream(path));
    }

    public static BufferedImage loadImageFromURL(String url) throws IOException {
        return ImageIO.read(ResourceLoader.class.getResource(url));
    }
}
