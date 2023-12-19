package com.memorybox.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    public static final String UPLOAD_FOLDER = "/root/data/";

    public List<String> saveImages(List<MultipartFile> imageFiles) {
        List<String> imageNames = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            String imageFileName = saveImage(imageNames, file);
            imageNames.add(imageFileName);
        }
        return imageNames;
    }

    private String saveImage(List<String> imageNames, MultipartFile imageFile) {
        String imageFileName = makeFileName(imageFile);
        String imageFilePath = UPLOAD_FOLDER.concat(imageFileName);

        saveFile(imageFile, imageFilePath);

        return imageFileName;
    }

    private String makeFileName(MultipartFile imageFile) {
        UUID uuid = UUID.randomUUID();
        return String.format("%s_%s", imageFile.getOriginalFilename(), uuid);
    }

    private void saveFile(MultipartFile file, String imageFilePath) {
        File destinationFile = new File(imageFilePath);
        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
