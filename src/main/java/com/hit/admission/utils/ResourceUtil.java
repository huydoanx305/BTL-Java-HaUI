package com.hit.admission.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Huy Doan
 */
public class ResourceUtil {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    private static final Path RESOURCES_PATH = CURRENT_FOLDER.resolve(Paths.get("src/main/resources"));

    public static Image getImage(String fileName) {
        return readImage(RESOURCES_PATH.resolve("images").resolve(fileName).toString());
    }

    public static ImageIcon getImageIcon(String resourcePath) {
        return new ImageIcon(RESOURCES_PATH.resolve("images").resolve(resourcePath).toString());
    }

    public static BufferedImage readImage(String imageName) {
        try {
            File input = new File(imageName);
            return ImageIO.read(input);
        } catch (IOException ie) {
            System.out.println("Error:" + ie.getMessage());
        }
        return null;
    }

}
