package com.memorybox.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.memorybox.domain.memory.entity.Memory;
import com.memorybox.domain.memory.entity.vo.Image;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record MemoryResponseDto(
    long memoryId,
    String title,
    int depositAmount,
    @JsonFormat(pattern = "yyyy.MM.dd")
    LocalDate createdAt,
    String content,
    List<String> images
) {

    public MemoryResponseDto(Memory memory) {
        this(memory.getId(), memory.getTitle(), memory.getDepositAmount(),
                getCreatedAt(memory.getCreatedAt()), memory.getContent(), getImageNames(memory));
    }

    private static List<String> getImageNames(Memory memory) {
        return memory.getImages().stream()
                .map(Image::getImageName)
                .collect(Collectors.toList());
    }

    private static LocalDate getCreatedAt(LocalDateTime createdAt) {
        return createdAt.toLocalDate();
    }
}
