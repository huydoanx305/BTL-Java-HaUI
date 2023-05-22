package com.hit.admission.utils;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.SneakyThrows;

/**
 *
 * @author Huy Doan
 */
public class ResourceUtil {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    private static final Path RESOURCES_PATH = CURRENT_FOLDER.resolve(Paths.get("src/main/resources"));

    public static ImageIcon getAvatar(String path) {
        return new ImageIcon(RESOURCES_PATH.resolve(path).toString());
    }

    public static ImageIcon getImageIcon(String path) {
        return new ImageIcon(RESOURCES_PATH.resolve("images").resolve(path).toString());
    }

    @SneakyThrows
    public static String saveFile(String newFileName, String uploadPath, File file) {
        Path path = RESOURCES_PATH.resolve(Paths.get(uploadPath));
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        Path filePath;
        try (InputStream inputStream = new FileInputStream(file)){
            String fileName = file.getName();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String newFile = newFileName + fileType;
            filePath = path.resolve(newFile);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return uploadPath + "/" + newFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
