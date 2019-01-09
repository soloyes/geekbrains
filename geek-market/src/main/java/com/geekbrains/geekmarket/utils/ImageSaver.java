package com.geekbrains.geekmarket.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageSaver {
    public static final String UPLOADED_FOLDER = "./images/";

    public static String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        try {
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
