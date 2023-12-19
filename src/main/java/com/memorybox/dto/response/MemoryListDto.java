package com.memorybox.dto.response;

import com.memorybox.domain.memory.entity.Memory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemoryListDto(
        long memoryId,
        String title,
        int depositAmount,
        LocalDateTime createdAt,
        String image
) {
    public MemoryListDto(Memory memory) {
        this(memory.getId(), memory.getTitle(), memory.getDepositAmount(),
                memory.getCreatedAt(), memory.getImages().get(0));
    }
}
