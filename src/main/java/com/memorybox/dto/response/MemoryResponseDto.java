package com.memorybox.dto.response;

import com.memorybox.domain.memory.entity.Memory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record MemoryResponseDto(
    long memoryId,
    String title,
    int depositAmount,
    LocalDateTime createdAt,
    String content,
    List<String> images
) {

    public MemoryResponseDto(Memory memory) {
        this(memory.getId(), memory.getTitle(), memory.getDepositAmount(),
                memory.getCreatedAt(), memory.getContent(), getImageNames(memory));
    }

    private static List<String> getImageNames(Memory memory) {
        return memory.getImages().stream()
                .map(image -> image.getImageName())
                .collect(Collectors.toList());
    }
}
