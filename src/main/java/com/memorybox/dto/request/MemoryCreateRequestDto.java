package com.memorybox.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record MemoryCreateRequestDto(

        String title,

        String content,

        int depositAmount,

        List<MultipartFile> imageFiles
) {
}
