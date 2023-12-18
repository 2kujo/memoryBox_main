package com.memorybox.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MemoryListResponseDto(
        List<MemoryListDto> cashBoxList
) {
}
