package com.memorybox.dto.request;

import java.util.List;

public record MemoryCreateRequestDto(

        String title,

        String content,

        int depositAmount,

        List<String>images
) {
}
