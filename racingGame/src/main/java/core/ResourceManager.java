package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ResourceManager {
    private HashMap<String, BufferedImage> textures;
    private static volatile ResourceManager instance;

    private ResourceManager() {
        textures = new HashMap<>();
    }

    public static ResourceManager getInstance() {
        if (instance == null) {
            synchronized (ResourceManager.class) {
                if (instance == null) {
                    instance = new ResourceManager();
                }
            }
        }
        return instance;
    }

    public BufferedImage getTexture(String name) {
        return textures.get(name);
    }

    public void loadTexture(String path, String name) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            System.out.println("Successfully loaded " + name);
            textures.put(name, img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
