package com.memorybox.domain.memory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ImageService {

    public static final String UPLOAD_FOLDER = "/root/data/";

    public List<String> saveImages(List<MultipartFile> imageFiles) {
        List<String> imageNames = new ArrayList<>();
        if (imageFiles != null) {
            for (MultipartFile file : imageFiles) {
                String imageFileName = saveImage(file);
                log.info(" >>> Save Image file name = {}", imageFileName);
                imageNames.add(imageFileName);
            }
        }
        return imageNames;
    }

    private String saveImage(MultipartFile imageFile) {
        String imageFileName = makeFileName(imageFile);
        String imageFilePath = UPLOAD_FOLDER.concat(imageFileName);

        saveFile(imageFile, imageFilePath);

        return imageFileName;
    }

    private String makeFileName(MultipartFile imageFile) {
        UUID uuid = UUID.randomUUID();

        String originalFilename = imageFile.getOriginalFilename();
        String imageName = originalFilename;
        String fileExtension = "png";
        int i = originalFilename.lastIndexOf('.');
        if (i >= 0) {
            fileExtension = originalFilename.substring(i + 1);
            imageName = originalFilename.substring(0, i);
        }
        return String.format("%s_%s.%s", imageName, uuid, fileExtension);
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
