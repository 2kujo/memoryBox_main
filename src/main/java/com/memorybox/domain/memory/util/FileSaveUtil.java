package com.memorybox.domain.memory.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class FileSaveUtil {

    @Async
    public void saveFile(MultipartFile file, String filePath) {
        File destinationFile = new File(filePath);
        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            log.info(" [Error] Save Image : {} ", filePath);
            throw new RuntimeException(e);
        }
    }

}
