package com.memorybox.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.memorybox.domain.memory.entity.Memory;
import com.memorybox.domain.memory.entity.vo.Image;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MemoryListDto(
        long memoryId,
        String title,
        int depositAmount,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate createdAt,
        String image
) {
    public MemoryListDto(Memory memory) {
        this(memory.getId(), memory.getTitle(), memory.getDepositAmount(),
                getCreatedAt(memory.getCreatedAt()), getImageName(memory.getImages()));
    }

    private static String getImageName(List<Image> images) {
        if (images.get(0) == null) {
            return "/";
        }
        return images.get(0).getImageName();
    }

    private static LocalDate getCreatedAt(LocalDateTime createdAt) {
        return createdAt.toLocalDate();
    }
}
