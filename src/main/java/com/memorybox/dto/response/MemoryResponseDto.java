package com.memorybox.dto.response;

import com.memorybox.domain.memory.entity.Memory;

import java.time.LocalDateTime;
import java.util.List;

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
                memory.getCreatedAt(), memory.getContent(), memory.getImages());
    }
}
