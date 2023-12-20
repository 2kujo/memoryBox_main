package com.memorybox.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record MemoryCreateRequestDto(

        String title,

        String content,

        String depositAmount,

        List<MultipartFile> imageFiles
) {
    public int getDepositAmount() {
        return Integer.parseInt(this.depositAmount);
    }
}
